package dao;

import dao.factory.HandleFactory;
import model.User;

import java.util.List;

public class Dao {
    public User getUserById(int id) {
        return HandleFactory.getHandle().createQuery("SELECT * FROM users WHERE id = :id;")
                .bind("id", id)
                .mapToBean(User.class)
                .findOnly();
    }

    public List<User> getUsers() {
        return HandleFactory.getHandle().createQuery("SELECT * FROM users;")
                .mapToBean(User.class)
                .list();
    }
}
