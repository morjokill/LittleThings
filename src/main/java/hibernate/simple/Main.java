package hibernate.simple;

import hibernate.simple.dao.Dao;
import hibernate.simple.model.Book;
import hibernate.simple.model.User;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
        User user = new User(1, "Maxim");
//        System.out.println(dao.saveUser(new User("Maxim", null)));

        System.out.println(dao.saveBook(new Book("3 in a boat except of a dog", user)));

//        System.out.println(dao.getUsers());
    }
}
