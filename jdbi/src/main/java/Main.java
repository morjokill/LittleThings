import dao.Dao;
import model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
        User user = dao.getUserById(5);
        System.out.println(user);
        List<User> users = dao.getUsers();
        System.out.println(users);
    }
}
