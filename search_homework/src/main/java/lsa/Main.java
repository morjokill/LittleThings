package lsa;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import cosine_similarity.ArticleSimilarity;
import tf_idf.Dao;

import java.util.*;

public class Main {
    private static final int APPROXIMATION_FACTOR = 5;

    private static double[][] aArray;
    private static Matrix matrixU;
    private static Matrix matrixS;
    private static Matrix matrixVTransposed;

    private static Matrix matrixUApproximated;
    private static Matrix matrixSApproximated;
    private static Matrix matrixVTransposedApproximated;

    private static Dao dao = new Dao();

    public static void main(String[] args) throws Exception {
        System.out.println("Type your search request");
        Scanner sc = new Scanner(System.in);

        String next = sc.nextLine();
        String[] words;
        System.out.println("Search line: " + next);
        words = next.trim().split(" ");

        Map<String, Double> wordsAfterStemCount = new HashMap<>();

        for (String word : words) {
            String wordAfterStem = string_split.Main.myStemAnalyze(word);
            System.out.println("Word after stem: " + wordAfterStem);
            wordsAfterStemCount.merge(wordAfterStem, 1.0, (a, b) -> a + b);
        }

        List<String> articles = dao.getAllArticles();
        List<String> terms = dao.getTermsOrderedByAsc();

        fillMatrixA(articles, terms);

        double[][] qArray = fillMatrixQ(wordsAfterStemCount, terms);

        Matrix matrixA = new Matrix(aArray);
        Matrix matrixQ = new Matrix(qArray);

        SingularValueDecomposition svd = matrixA.svd();

        fillUSVMatrices(svd);

        approximateMatrices();

        Matrix matrixSAppInverted = matrixSApproximated.inverse();

        Matrix result = matrixQ.times(matrixUApproximated).times(matrixSAppInverted);

        List<ArticleSimilarity> articleSimilarities = new LinkedList<>();
        for (int i = 0; i < articles.size(); i++) {
            Matrix d = matrixVTransposedApproximated.getMatrix(0, APPROXIMATION_FACTOR - 1, i, i).transpose();
            double cosine = cosine_similarity.Main.calculateCosineSimilarity(result.getArrayCopy()[0], d.getArrayCopy()[0]);
            articleSimilarities.add(new ArticleSimilarity(articles.get(i), cosine));
        }

        sortSimilaritiesAndPrint(articleSimilarities);
    }

    private static void fillMatrixA(List<String> articles, List<String> terms) {
        aArray = new double[terms.size()][articles.size()];

        for (int i = 0; i < articles.size(); i++) {
            String article = articles.get(i);

            double[] termsCount = dao.getEveryTermCountInArticle(article).stream().mapToDouble(number -> number).toArray();
            for (int j = 0; j < terms.size(); j++) {
                aArray[j][i] = termsCount[j];
            }
        }
    }

    private static double[][] fillMatrixQ(Map<String, Double> wordsAfterStemCount, List<String> terms) throws Exception {
        double[][] matrixQ = new double[1][terms.size()];

        int count = 0;
        for (int i = 0; i < terms.size(); i++) {
            if (wordsAfterStemCount.get(terms.get(i)) != null) {
                matrixQ[0][i] = wordsAfterStemCount.get(terms.get(i));
                count++;
            }
        }

        if (count > 0) {
            return matrixQ;
        } else {
            throw new Exception("No words in database for this search line");
        }
    }

    private static void fillUSVMatrices(SingularValueDecomposition svd) {
        matrixU = svd.getU();
        matrixS = svd.getS();
        matrixVTransposed = svd.getV().transpose();
    }

    private static void approximateMatrices() {
        matrixUApproximated = matrixU.getMatrix(0, matrixU.getRowDimension() - 1, 0, APPROXIMATION_FACTOR - 1);
        matrixSApproximated = matrixS.getMatrix(0, APPROXIMATION_FACTOR - 1, 0, APPROXIMATION_FACTOR - 1);
        matrixVTransposedApproximated = matrixVTransposed.getMatrix(0, APPROXIMATION_FACTOR - 1, 0, matrixVTransposed.getRowDimension() - 1);
    }

    private static void sortSimilaritiesAndPrint(List<ArticleSimilarity> articleSimilarities) {
        articleSimilarities.sort((o1, o2) -> {
            if (o1.getSimilarity() - o2.getSimilarity() > 0) {
                return -1;
            } else if (o1.getSimilarity() - o2.getSimilarity() < 0) {
                return 1;
            } else return 0;
        });

        for (int i = 0; i < 10; i++) {
            ArticleSimilarity articleSimilarity = articleSimilarities.get(i);
            System.out.println("Article: " + articleSimilarity.getArticleId() + " similarity: " + articleSimilarity.getSimilarity());
        }
    }
}
