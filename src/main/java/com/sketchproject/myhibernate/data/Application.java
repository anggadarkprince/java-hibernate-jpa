/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.User;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author Angga
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        //session.beginTransaction();
        
        User user = new User();
        user.setBirthDate(new Date());
        user.setCreatedBy("Admin");
        user.setCreatedDate(new Date());
        user.setEmailAddress("me@angga-ari.com");
        user.setFirstName("Angga");
        user.setLastName("Ari Wijaya");
        user.setLastUpdateBy("Angga");
        user.setLastUpdateDate(new Date());
       
        session.save(user);        
        //session.getTransaction().commit();
        
        User dbUser = (User) session.get(User.class, user.getUserId());
        dbUser.setFirstName("Diaz");
        session.update(dbUser);
        
        session.getTransaction().commit();
        session.close();
    }
    
}
