package bm25;

import cosine_similarity.ArticleSimilarity;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import tf_idf.Article;
import tf_idf.Dao;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final double k1 = 1.2;
    private static final double b = 0.75;
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

        double[] idfForQueryWords = new double[wordsAfterStem.size()];
        Set<ArticleSimilarity> articlesWithWords = new LinkedHashSet<>();
        Integer allArticlesCount = dao.getAllArticlesCount();
        for (int i = 0; i < wordsAfterStem.size(); i++) {
            String stemmedWord = wordsAfterStem.get(i);
            List<Article> articlesWithWordTerm = dao.getArticlesWithWordTerm(stemmedWord);
            articlesWithWords.addAll(articlesWithWordTerm.stream().map(article -> new ArticleSimilarity(article.getArticlesId())).collect(Collectors.toList()));
            Integer allArticlesCountWithWordTerm = articlesWithWordTerm.size();

            double idf = calculateIdf(allArticlesCount, allArticlesCountWithWordTerm);
            idfForQueryWords[i] = idf;
        }

        for (ArticleSimilarity articlesWithWord : articlesWithWords) {
            double sum = 0;
            for (int i = 0; i < wordsAfterStem.size(); i++) {
                String stemmedWord = wordsAfterStem.get(i);
                String articleId = articlesWithWord.getArticleId();
                int freq = dao.getTermCountByArticleId(stemmedWord, articleId);
                int D = dao.getArticleWordCount(articleId);
                int allWordsCount = dao.getAllArticlesWordCount(articleId);
                int avg = allWordsCount / allArticlesCount;

                sum += calculateBm25(idfForQueryWords[i], freq, D, avg);
            }
            articlesWithWord.setSimilarity(sum);
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

    private static double calculateIdf(Integer allArticlesCount, Integer allArticlesCountWithWordTerm) {
        return Math.log((allArticlesCount - allArticlesCountWithWordTerm + 0.5) / (allArticlesCountWithWordTerm + 0.5));
    }

    private static double calculateBm25(double idf, double freq, double D, double avg) {
        return idf * (freq * (k1 + 1) / (freq / k1 * (1 - b + b * D / avg)));
    }
}
