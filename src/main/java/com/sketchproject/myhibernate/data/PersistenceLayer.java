package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.data.dao.UserHibernateDao;
import com.sketchproject.myhibernate.data.dao.interfaces.UserDao;
import com.sketchproject.myhibernate.entities.Address;
import com.sketchproject.myhibernate.entities.Bond;
import com.sketchproject.myhibernate.entities.Credential;
import com.sketchproject.myhibernate.entities.User;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersistenceLayer {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            UserDao dao = new UserHibernateDao();
            dao.setSession(session);

            tx = session.beginTransaction();

            User user = createUser();

            dao.save(user);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static User createUser() {
        User user = new User();
        user.setAddresses(Arrays.asList(new Address[]{createAddress()}));
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin Bowersox");
        user.setCreatedDate(new Date());
        user.setCredential(createCredential(user));
        user.setEmailAddress("test@test.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setLastUpdatedBy("system");
        user.setLastUpdatedDate(new Date());
        return user;
    }

    private static Credential createCredential(User user) {
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setUsername("test_username");
        credential.setPassword("test_password");
        return credential;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressLine1("101 Address Line");
        address.setAddressLine2("102 Address Line");
        address.setCity("New York");
        address.setState("PA");
        address.setZipCode("10000");
        address.setAddressType("PRIMARY");
        return address;
    }

}
