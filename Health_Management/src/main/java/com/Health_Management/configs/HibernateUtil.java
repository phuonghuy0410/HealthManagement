package com.Health_Management.configs;

import com.Health_Management.pojo.User;
import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.pojo.Message;
import com.Health_Management.pojo.Plan;
import com.Health_Management.pojo.Workout;
import com.Health_Management.pojo.Reminder;
import com.Health_Management.pojo.Report;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); 

        
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Workout.class);
            configuration.addAnnotatedClass(Reminder.class);
            configuration.addAnnotatedClass(Report.class);
            configuration.addAnnotatedClass(HealthProfile.class);
       configuration.addAnnotatedClass(Plan.class);
   configuration.addAnnotatedClass(Message.class);
       
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println(" Hibernate SessionFactory tạo thất bại: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
