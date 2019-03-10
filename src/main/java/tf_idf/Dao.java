package tf_idf;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.List;

public class Dao {
    // Все статьи
    private static final String SQL_GET_ALL_ARTICLES = "SELECT id FROM articles;";

    // Все слова одной статьи
    private static final String SQL_GET_ALL_WORDS_FOR_ARTICLE = "SELECT term_text FROM article_term inner join terms_list list2 on article_term.term_id = " +
            "list2.term_id WHERE article_id = ?;";

    // Количество вхождений слова term в статью
    private static final String SQL_GET_TERM_COUNT_IN_ARTICLE = "SELECT COUNT(*) FROM words_mystem WHERE term = ? AND " +
            "articles_id = ? GROUP BY term, articles_id;";

    // Количество слов статьи
    private static final String SQL_GET_ARTICLE_WORD_COUNT = "SELECT COUNT(*) FROM words_mystem WHERE articles_id = ?;";

    //Количество статей
    private static final String SQL_GET_ALL_ARTICLES_COUNT = "SELECT count(*) FROM articles;";

    //Количество статей со словом term
    private static final String SQL_GET_ALL_ARTICLES_WITH_WORD_TERM = "SELECT count(*) FROM (SELECT term, articles_id FROM words_mystem WHERE" +
            " term = ? GROUP BY term, articles_id) AS a;";

    private static final String UPDATE_TF_IDF_FOR_ARTICLE_TERM = "UPDATE article_term SET tf_idf = ? WHERE article_id = ? AND term_id = ?;";

    private static final String SQL_GET_TERM_ID = "SELECT DISTINCT at.term_id FROM article_term at INNER JOIN terms_list " +
            "list2 on at.term_id = list2.term_id WHERE article_id = ? AND term_text = ?;";

    private JdbcTemplate jdbcTemplate;

    public Dao() {
        this.jdbcTemplate = new JdbcTemplate(DaoConfig.getDataSource());
    }

    public List<String> getAllArticles() {
        return jdbcTemplate.queryForList(SQL_GET_ALL_ARTICLES, String.class);
    }

    public List<String> getAllWordsForArticle(String articleId) {
        return jdbcTemplate.queryForList(SQL_GET_ALL_WORDS_FOR_ARTICLE, new Object[]{articleId}, new int[]{Types.OTHER}, String.class);
    }

    public Integer getTermCountByArticleId(String term, String articleId) {
        return jdbcTemplate.queryForObject(SQL_GET_TERM_COUNT_IN_ARTICLE, new Object[]{term, articleId},
                new int[]{Types.VARCHAR, Types.OTHER}, Integer.class);
    }

    public Integer getArticleWordCount(String articleId) {
        return jdbcTemplate.queryForObject(SQL_GET_ARTICLE_WORD_COUNT, new Object[]{articleId}, new int[]{Types.OTHER}, Integer.class);
    }

    public Integer getAllArticlesCount() {
        return jdbcTemplate.queryForObject(SQL_GET_ALL_ARTICLES_COUNT, Integer.class);
    }

    public Integer getAllArticlesCountWithWordTerm(String term) {
        return jdbcTemplate.queryForObject(SQL_GET_ALL_ARTICLES_WITH_WORD_TERM, new Object[]{term}, Integer.class);
    }

    public String getTermIdByArticleIdAndTerm(String articleId, String term) {
        return jdbcTemplate.queryForObject(SQL_GET_TERM_ID, new Object[]{articleId, term}, new int[]{Types.OTHER, Types.VARCHAR}, String.class);
    }

    public void updateTfIdf(Double tfIdf, String articleId, String termId) {
        jdbcTemplate.update(UPDATE_TF_IDF_FOR_ARTICLE_TERM, new Object[]{tfIdf, articleId, termId}, new int[]{Types.DOUBLE, Types.OTHER, Types.OTHER});
    }
}
