package com.Health_Management.services;

import com.Health_Management.configs.HibernateUtil;
import com.Health_Management.pojo.Plan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PlanService {

    public List<Plan> getPlansByUser(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Plan> query = session.createQuery(
                "FROM Plan p WHERE p.user.userId = :uid ORDER BY p.startDate DESC",
                Plan.class
            );
            query.setParameter("uid", userId);
            return query.getResultList();
        }
    }

    public List<Plan> getPlansByUserAndDate(int userId, Date date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Plan> query = session.createQuery(
                "FROM Plan p WHERE p.user.userId = :uid AND p.startDate = :d",
                Plan.class
            );
            query.setParameter("uid", userId);
            query.setParameter("d", date);
            return query.getResultList();
        }
    }

    public void savePlan(Plan plan) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(plan);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Plan> getAllPlans() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Plan", Plan.class).list();
        }
    }

   
    public void deletePlan(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Plan p = session.get(Plan.class, id);
            if (p != null)
                session.remove(p);
            tx.commit();
        }
    }
}
