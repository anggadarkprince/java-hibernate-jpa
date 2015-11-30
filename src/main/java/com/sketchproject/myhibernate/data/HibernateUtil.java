/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Bank;
import com.sketchproject.myhibernate.entities.Credential;
import com.sketchproject.myhibernate.entities.TimeTest;
import com.sketchproject.myhibernate.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Angga
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
        try{
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(TimeTest.class);
            configuration.addAnnotatedClass(Bank.class);
            configuration.addAnnotatedClass(Credential.class);
            
            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build());
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("There was an error building the factory season");
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
