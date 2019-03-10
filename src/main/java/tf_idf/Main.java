package tf_idf;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
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
                Double tf = (double) termCountInArticle / wordsCount;
                Double idf = Math.log((double) (allArticles.size() / articlesWithWordTerm));
                System.out.println("TermId: " + termId);
                System.out.println("tf: " + termCountInArticle + " / " + wordsCount + " = " + tf);
                System.out.println("idf: log(" + allArticles.size() + " / " + articlesWithWordTerm + ") = " + idf);
                double tfIdf = tf * idf;
                System.out.println(term + " count in this article: " + termCountInArticle +
                        " articles contains: " + articlesWithWordTerm + " tf-idf = " + tfIdf);
                dao.updateTfIdf(tfIdf, article, termId);
            }
            System.out.println("________________________________");
        }
    }
}
