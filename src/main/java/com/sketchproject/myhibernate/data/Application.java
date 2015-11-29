/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Address;
import com.sketchproject.myhibernate.entities.Bank;
import com.sketchproject.myhibernate.entities.TimeTest;
import com.sketchproject.myhibernate.entities.User;
import java.util.Calendar;
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
        
        // User
        User user = new User();        
        user.setBirthDate(getMyBirthday());
        user.setCreatedBy("Admin");
        user.setCreatedDate(new Date());
        user.setEmailAddress("me@angga-ari.com");
        user.setFirstName("Angga");
        user.setLastName("Ari Wijaya");
        user.setLastUpdatedBy("Angga");
        user.setLastUpdatedDate(new Date());
        session.save(user);  
        
        // Bank
        Bank bank = new Bank();
        bank.setName("Federal Trust");
        bank.setAddressLine1("33 Wall Street");
        bank.setAddressLine2("Suite 233");
        bank.setCity("New York");
        bank.setState("NY");
        bank.setZipCode("12345");
        bank.setInternational(false);
        bank.setCreatedBy("Kevin");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Kevin");
        bank.setLastUpdatedDate(new Date());
        //bank.getContacts().put("MANAGER", "Joe");
        //bank.getContacts().put("TELLER", "Mary");
        session.save(bank);
        
        // Adress
        User user2 = new User();
        Address address2 = new Address();
        user2.setAge(22);
        user2.setBirthDate(new Date());
        user2.setCreatedBy("Kevin");
        user2.setCreatedDate(new Date());
        user2.setEmailAddress("kmb3");
        user2.setFirstName("kevin");
        user2.setLastName("bowersox");
        user2.setLastUpdatedBy("kmb");
        user2.setLastUpdatedDate(new Date());

        address2.setAddressLine1("line 1");
        address2.setAddressLine2("line2");
        address2.setCity("Philadelphia");
        address2.setState("PA");
        address2.setZipCode("12345");

        user.setAddress(address2);
        session.save(user);
       
        
        // Update User                
        User dbUser = (User) session.get(User.class, user.getUserId());
        dbUser.setFirstName("Diaz");
        session.update(dbUser);
        
        // Time check
        TimeTest test = new TimeTest(new Date());
        session.save(test);
        
        session.getTransaction().commit();
        
        // Refresh time
        session.refresh(test);
        System.out.println(test.toString());
        
        // refresh user
        session.refresh(user);
        System.out.println(user.getAge());
        
        session.close();
    }
    
    private static Date getMyBirthday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1992);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DATE, 26);
        return calendar.getTime();
    }
    
}
