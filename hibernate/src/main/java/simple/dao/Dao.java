package simple.dao;

import simple.factory.HibernateSessionFactoryUtil;
import simple.model.Book;
import simple.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Dao {
    public User getUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
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
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from User").list();
    }
}
