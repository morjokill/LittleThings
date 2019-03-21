package tf_idf;

import java.util.List;

public class Main {
    private static Dao dao = new Dao();

    public static void main(String[] args) {
        List<String> allArticles = dao.getAllArticles();
        System.out.println("Articles count: " + allArticles.size());
        System.out.println("Articles: " + allArticles);
        for (String article : allArticles) {
            System.out.println("Article words " + article + " :");
            System.out.println("________________________________");
            List<String> wordsForArticle = dao.getAllWordsForArticle(article);

            Integer wordsCount = wordsForArticle.size();
            System.out.println("Words count: " + wordsCount);

            for (String term : wordsForArticle) {
                String termId = dao.getTermIdByArticleIdAndTerm(article, term);
                Integer termCountInArticle = dao.getTermCountByArticleId(term, article);
                Integer articlesWithWordTerm = dao.getAllArticlesCountWithWordTerm(term);
                Double tf = calculateTf(termCountInArticle, wordsCount);
                Double idf = calculateIdf(allArticles.size(), articlesWithWordTerm);
                System.out.println("tf: " + termCountInArticle + " / " + wordsCount + " = " + tf);
                System.out.println("idf: log(" + allArticles.size() + " / " + articlesWithWordTerm + ") = " + idf);
                double tfIdf = calculateTfIdf(tf, idf);
                System.out.println(term + " count in this article: " + termCountInArticle +
                        " articles contains: " + articlesWithWordTerm + " tf-idf = " + tfIdf);
                dao.updateTfIdf(tfIdf, article, termId);
            }
            System.out.println("________________________________");
        }
    }

    public static double calculateTfIdf(Double tf, Double idf) {
        return tf * idf;
    }

    public static Double calculateTf(double termCountInArticle, Integer wordsCountInArticle) {
        return termCountInArticle / wordsCountInArticle;
    }

    public static Double calculateIdf(Integer articlesCount, Integer articlesWithWordTermCount) {
        return Math.log((double) (articlesCount / articlesWithWordTermCount));
    }
}
