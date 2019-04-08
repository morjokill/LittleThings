package tf_idf;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    // Количество слов во всех статьях
    private static final String SQL_GET_ALL_ARTICLES_WORD_COUNT = "SELECT COUNT(*) FROM words_mystem WHERE;";

    //Количество статей
    private static final String SQL_GET_ALL_ARTICLES_COUNT = "SELECT count(*) FROM articles;";

    //Количество статей со словом term
    private static final String SQL_GET_ALL_ARTICLES_COUNT_WITH_WORD_TERM = "SELECT count(*) FROM (SELECT term, articles_id FROM words_mystem WHERE" +
            " term = ? GROUP BY term, articles_id) AS a;";

    private static final String UPDATE_TF_IDF_FOR_ARTICLE_TERM = "UPDATE article_term SET tf_idf = ? WHERE article_id = ? AND term_id = ?;";

    private static final String SQL_GET_TERM_ID_IN_ARTICLE = "SELECT DISTINCT at.term_id FROM article_term at INNER JOIN terms_list " +
            "list2 on at.term_id = list2.term_id WHERE article_id = ? AND term_text = ?;";

    private static final String SQL_GET_ALL_ARTICLES_WITH_WORD_TERM = "SELECT term, articles_id FROM words_mystem WHERE" +
            " term = ? GROUP BY term, articles_id;";

    private static final String SQL_GET_TF_IDF_FOR_TERM_IN_ARTICLE = "SELECT tf_idf FROM article_term WHERE term_id = ? AND article_id = ?;";

    private static final String SQL_GET_EVERY_TERM_IN_ARTICLE_FREQ = "SELECT count(article_id) FROM terms_list LEFT OUTER JOIN article_term a " +
            "    ON terms_list.term_id = a.term_id AND article_id = ? " +
            " GROUP BY article_id, term_text ORDER BY term_text ASC;";

    private static final String SQL_GET_TERM_COUNT = "SELECT count(*) FROM terms_list;";

    private static final String SQL_GET_ALL_TERM_ORDERED_BY_ASC = "SELECT term_text FROM terms_list ORDER BY term_text ASC;";

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

    public Integer getAllArticlesWordCount(String articleId) {
        return jdbcTemplate.queryForObject(SQL_GET_ALL_ARTICLES_WORD_COUNT, new Object[]{articleId}, new int[]{Types.OTHER}, Integer.class);
    }

    public Integer getAllArticlesCount() {
        return jdbcTemplate.queryForObject(SQL_GET_ALL_ARTICLES_COUNT, Integer.class);
    }

    public Integer getAllArticlesCountWithWordTerm(String term) {
        return jdbcTemplate.queryForObject(SQL_GET_ALL_ARTICLES_COUNT_WITH_WORD_TERM, new Object[]{term}, Integer.class);
    }

    public String getTermIdByArticleIdAndTerm(String articleId, String term) {
        return jdbcTemplate.queryForObject(SQL_GET_TERM_ID_IN_ARTICLE, new Object[]{articleId, term}, new int[]{Types.OTHER, Types.VARCHAR}, String.class);
    }

    public void updateTfIdf(Double tfIdf, String articleId, String termId) {
        jdbcTemplate.update(UPDATE_TF_IDF_FOR_ARTICLE_TERM, new Object[]{tfIdf, articleId, termId}, new int[]{Types.DOUBLE, Types.OTHER, Types.OTHER});
    }

    public double getTfIdfForTermInArticle(String termId, String articleId) {
        return jdbcTemplate.queryForObject(SQL_GET_TF_IDF_FOR_TERM_IN_ARTICLE, new Object[]{termId, articleId}, new int[]{Types.OTHER, Types.OTHER}, Double.class);
    }

    public List<Article> getArticlesWithWordTerm(String term) {
        return jdbcTemplate.query(SQL_GET_ALL_ARTICLES_WITH_WORD_TERM, new Object[]{term}, new BeanPropertyRowMapper<>(Article.class));
    }

    public List<Integer> getEveryTermCountInArticle(String articleId) {
        return jdbcTemplate.queryForList(SQL_GET_EVERY_TERM_IN_ARTICLE_FREQ, new Object[]{articleId}, new int[]{Types.OTHER}, Integer.class);
    }

    public int getTermsCount() {
        return jdbcTemplate.queryForObject(SQL_GET_TERM_COUNT, Integer.class);
    }

    public List<String> getTermsOrderedByAsc() {
        return jdbcTemplate.queryForList(SQL_GET_ALL_TERM_ORDERED_BY_ASC, String.class);
    }
}
