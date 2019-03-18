package cosine_similarity;

import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import tf_idf.Article;
import tf_idf.Dao;

import java.util.*;
import java.util.stream.Collectors;

import static tf_idf.Main.*;

public class Main {
    private static Dao dao = new Dao();

    public static void main(String[] args) throws MyStemApplicationException {
        System.out.println("Type your search request");
        Scanner sc = new Scanner(System.in);

        String next = sc.nextLine();
        String[] words;
        System.out.println("Search line: " + next);
        words = next.trim().split(" ");

        List<String> wordsAfterStem = new ArrayList<>();

        for (String word : words) {
            String wordAfterStem = string_split.Main.myStemAnalyze(word);
            System.out.println("Word after stem: " + wordAfterStem);
            wordsAfterStem.add(wordAfterStem);
        }

        double[] tfIdfForQueryWords = new double[wordsAfterStem.size()];
        Set<ArticleSimilarity> articlesWithWords = new LinkedHashSet<>();
        Integer allArticlesCount = dao.getAllArticlesCount();
        for (int i = 0; i < wordsAfterStem.size(); i++) {
            String stemmedWord = wordsAfterStem.get(i);
            List<Article> articlesWithWordTerm = dao.getArticlesWithWordTerm(stemmedWord);
            articlesWithWords.addAll(articlesWithWordTerm.stream().map(article -> new ArticleSimilarity(article.getArticlesId())).collect(Collectors.toList()));
            Integer allArticlesCountWithWordTerm = articlesWithWordTerm.size();

            int wordInQueryCount = getWordCountInList(stemmedWord, wordsAfterStem);

            double tf = calculateTf(wordInQueryCount, wordsAfterStem.size());
            double idf = calculateIdf(allArticlesCount, allArticlesCountWithWordTerm);
            double tfIdf = calculateTfIdf(tf, idf);

            tfIdfForQueryWords[i] = tfIdf;

            System.out.println("tf: " + wordInQueryCount + " / " + wordsAfterStem.size() + " = " + tf);
            System.out.println("idf: log(" + allArticlesCount + " / " + allArticlesCountWithWordTerm + ") = " + idf);
            System.out.println("tfIdf: " + tf + " / " + idf + " = " + tfIdf);
        }

        for (ArticleSimilarity articlesWithWord : articlesWithWords) {
            double[] tfIdfForArticle = new double[wordsAfterStem.size()];
            for (int i = 0; i < wordsAfterStem.size(); i++) {
                String stemmedWord = wordsAfterStem.get(i);
                String articleId = articlesWithWord.getArticleId();
                String termId = dao.getTermIdByArticleIdAndTerm(articleId, stemmedWord);
                double tfIdfForWordInArticle = dao.getTfIdfForTermInArticle(termId, articleId);
                tfIdfForArticle[i] = tfIdfForWordInArticle;
            }
            double cosine_similarity = calculateCosineSimilarity(tfIdfForQueryWords, tfIdfForArticle);
            articlesWithWord.setSimilarity(cosine_similarity);
        }

        List<ArticleSimilarity> articlesList = new ArrayList<>(articlesWithWords);
        articlesList.sort((o1, o2) -> {
            if (o1.getSimilarity() - o2.getSimilarity() > 0) {
                return 1;
            } else if (o1.getSimilarity() - o2.getSimilarity() < 0) {
                return -1;
            } else return 0;
        });

        for (int i = 0; i < 10; i++) {
            ArticleSimilarity articleSimilarity = articlesList.get(i);
            System.out.println("Article: " + articleSimilarity.getArticleId() + " similarity: " + articleSimilarity.getSimilarity());
        }
    }

    private static int getWordCountInList(String word, List<String> list) {
        int count = 0;
        for (String s : list) {
            if (s.equals(word)) {
                count++;
            }
        }
        return count;
    }

    private static double calculateCosineSimilarity(double[] firstVector, double[] secondVector) {
        double dotProd = 0;
        double sqA = 0;
        double sqB = 0;
        for (int i = 0; i < firstVector.length; i++) {
            dotProd += firstVector[i] * secondVector[i];
            sqA += firstVector[i] * firstVector[i];
            sqB += secondVector[i] * secondVector[i];
        }
        return dotProd / (Math.sqrt(sqA) * Math.sqrt(sqB));
    }
}
