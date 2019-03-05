package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static site_parse.Dao.getConnection;

public class Dao {
    private static final String selectArticles = "SELECT articles.id, articles.url FROM articles INNER JOIN article_term" +
            " ON articles.id = article_term.article_id INNER JOIN terms_list ON terms_list.term_id = article_term.term_id WHERE term_text = ?";

    public static Set<Article> getArticles(String word) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getConnection();
            statement = dbConnection.prepareStatement(selectArticles);
            statement.setString(1, word);

            ResultSet rs = statement.executeQuery();

            Set<Article> articles = new HashSet<>();

            while (rs.next()) {
                articles.add(new Article(rs.getString("id"),
                        rs.getString("url")));
            }

            return articles;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }
}
