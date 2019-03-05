package hibernate.simple.dao;

import hibernate.simple.factory.HibernateSessionFactoryUtil;
import hibernate.simple.model.Book;
import hibernate.simple.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Dao {
    public User getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public User saveUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
        return user;
    }

    public Book saveBook(Book book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(book);
        tx1.commit();
        session.close();
        return book;
    }

    public List<User> getUsers() {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM User").list();
    }


}
