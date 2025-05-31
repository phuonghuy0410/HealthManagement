package com.Health_Management.services;

import com.Health_Management.configs.HibernateUtil;
import com.Health_Management.pojo.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // ✅ Kiểm tra đăng nhập
    public User getUserByUsernameAndPassword(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE username = :username AND password = :password";
            return session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            System.err.println("Lỗi đăng nhập: " + e.getMessage());
            return null;
        }
    }

    //  Kiểm tra tên đăng nhập đã tồn tại chưa
    public boolean usernameExists(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE username = :username";
            return session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .uniqueResult() != null;
        } catch (Exception e) {
            System.err.println("Lỗi kiểm tra tên người dùng: " + e.getMessage());
            return false;
        }
    }

    //  Lưu hoặc đăng ký tài khoản mới
    public void saveUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Lỗi lưu người dùng: " + e.getMessage());
        }
    }

    //  Lấy tất cả người dùng (cho admin hoặc API frontend)
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    // Lấy người dùng theo ID
    public User getUserById(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        }
    }
}
