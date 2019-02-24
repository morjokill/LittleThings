package string_split;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static site_parse.Dao.getConnection;

public class Dao {
    private static final String selectLines = "SELECT id, content FROM articles";
    private static final String insertPortersStem = "INSERT INTO words_porter (articles_id, term) VALUES (?,?)";
    private static final String insertMyStem = "INSERT INTO words_mystem (articles_id, term) VALUES (?,?)";

    public static List<Article> getArticles() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        try {
            dbConnection = getConnection();
            statement = dbConnection.createStatement();

            ResultSet rs = statement.executeQuery(selectLines);

            List<Article> articles = new LinkedList<>();

            while (rs.next()) {
                articles.add(new Article(rs.getString("id"),
                        rs.getString("content")));
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

    public static void insertIntoMyStem(String articleId, List<String> lines) throws SQLException {
        insertRecordIntoTable(articleId, lines, insertMyStem);
    }

    public static void insertIntoPorterStem(String articleId, List<String> lines) throws SQLException {
        insertRecordIntoTable(articleId, lines, insertPortersStem);
    }

    public static void insertRecordIntoTable(String articleId, List<String> lines, String SQL) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection = getConnection();

            dbConnection.setAutoCommit(false);

            preparedStatement = dbConnection.prepareStatement(SQL);

            for (String line : lines) {
                preparedStatement.setObject(1, articleId, Types.OTHER);
                preparedStatement.setString(2, line);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            dbConnection.commit();

            System.out.println("Records are inserted into words tables!");

        } catch (SQLException e) {
            System.out.println(e.toString() + " " + e.getNextException().toString());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }
}
