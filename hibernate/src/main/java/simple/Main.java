package simple;

import simple.dao.Dao;
import simple.model.Book;
import simple.model.User;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
        User user = new User(1, "Maxim");
        System.out.println(dao.saveBook(new Book("3 in a boat except of a dog", user)));
        System.out.println(dao.getUsers());
    }
}
