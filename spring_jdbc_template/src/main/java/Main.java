package spring_jdbc_template;

import dao.Dao;
import model.Book;
import model.User;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
        dao.saveUser(new User("Maxim"));
        System.out.println(dao.getUsers());
        System.out.println(dao.getUserById(1));
        dao.saveBook(new Book("3 in a boat", 1));
    }
}
