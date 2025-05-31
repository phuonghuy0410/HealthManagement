package com.Health_Management.Repository;

import com.Health_Management.configs.HibernateUtil;
import com.Health_Management.pojo.HealthProfile;
import org.hibernate.Session;

import java.util.List;

public class HealthProfileRepository {

    public List<HealthProfile> getAll() {
        List<HealthProfile> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("FROM HealthProfile", HealthProfile.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
