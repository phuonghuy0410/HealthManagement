package com.Health_Management.services;

import com.Health_Management.configs.HibernateUtil;
import com.Health_Management.pojo.Workout;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    public List<Workout> getWorkoutsByUser(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Workout w WHERE w.user.userId = :uid ORDER BY w.workoutDate DESC";
            Query<Workout> query = session.createQuery(hql, Workout.class);
            query.setParameter("uid", userId);
            return query.getResultList();
        }
    }

    public void saveOrUpdateWorkout(Workout workout) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(workout);
            session.getTransaction().commit();
        }
    }

    //  API cho React gọi tất cả workout
    public List<Workout> getAllWorkouts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Workout", Workout.class).list();
        }
    }
}
