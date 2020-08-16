package com.screencap.dictionary.database;

import java.util.Properties;
import com.screencap.dictionary.models.entities.Note;
import com.screencap.dictionary.models.entities.Page;
import com.screencap.dictionary.models.entities.User;
import com.screencap.dictionary.models.entities.Vocab;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Autowired
    private org.springframework.core.env.Environment env;

    private SessionFactory sessionFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties props = new Properties();

                props.put(Environment.DRIVER, env.getProperty("DB_DRIVER_CLASS"));
                props.put(Environment.URL, env.getProperty("DB_URL"));
                props.put(Environment.USER, env.getProperty("DB_USERNAME"));
                props.put(Environment.PASS, env.getProperty("DB_PASSWORD"));
                props.put(Environment.DIALECT, env.getProperty("DB_DIALECT"));
                props.put(Environment.SHOW_SQL, env.getProperty("DB_SHOW_SQL"));
                props.put(
                    Environment.CURRENT_SESSION_CONTEXT_CLASS,
                    env.getProperty("DB_CURRENT_SESSION_CONTEXT_CLASS")
                );
                props.put(Environment.HBM2DDL_AUTO, env.getProperty("DB_HBM2DDL_AUTO"));

                configuration.setProperties(props);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Note.class);
                configuration.addAnnotatedClass(Page.class);
                configuration.addAnnotatedClass(Vocab.class);



                ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {

                e.printStackTrace();

            }
        }

        return sessionFactory;
    }

}
