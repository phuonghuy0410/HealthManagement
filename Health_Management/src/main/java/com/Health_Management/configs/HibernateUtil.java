/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.configs;

import com.Health_Management.pojo.User;
import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.pojo.Workout;
import com.Health_Management.pojo.Reminder;
import com.Health_Management.pojo.Report;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

    
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(HealthProfile.class);
        cfg.addAnnotatedClass(Workout.class);
        cfg.addAnnotatedClass(Reminder.class);
        cfg.addAnnotatedClass(Report.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());

        FACTORY = cfg.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}