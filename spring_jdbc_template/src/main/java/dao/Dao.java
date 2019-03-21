package dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import config.DaoConfig;
import model.Book;
import model.User;

import java.util.List;

public class Dao {
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM users;";
    private static final String SQL_INSERT_USER = "INSERT INTO users (name) VALUES (?);";
    private static final String SQL_INSERT_BOOK = "INSERT INTO books (name, author_id) VALUES (?, ?);";

    private JdbcTemplate jdbcTemplate;

    public Dao() {
        this.jdbcTemplate = new JdbcTemplate(DaoConfig.getDataSource());
    }

    public User getUserById(int userId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper<>(User.class));
    }

    public void saveUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getName());
    }

    public void saveBook(Book book) {
        jdbcTemplate.update(SQL_INSERT_BOOK, book.getName(), book.getAuthor_id());
    }
}
