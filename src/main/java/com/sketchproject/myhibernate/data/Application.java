/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Transaction;
import com.sketchproject.myhibernate.entities.Account;
import com.sketchproject.myhibernate.entities.Address;
import com.sketchproject.myhibernate.entities.Bank;
import com.sketchproject.myhibernate.entities.Credential;
import com.sketchproject.myhibernate.entities.TimeTest;
import com.sketchproject.myhibernate.entities.User;
import java.math.BigDecimal;
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
        try {

            session.getTransaction().begin();
        //session.beginTransaction();

            // User
            User user = saveUser(session);

            Credential credential = new Credential();
            credential.setPassword("anggapassword");
            credential.setUsername("anggadarkprince");
            credential.setUser(user);
            user.setCredential(credential);

            session.save(credential);

            User credentialUser = (User) session.get(User.class, credential.getUser().getUserId());
            System.out.println(credentialUser.getFirstName());

            Account account = createNewAccount();
            account.getTransactions().add(createNewBeltPurchase(account));
            account.getTransactions().add(createShoePurchase(account));
            session.save(account);

            Transaction dbTransaction = (Transaction) session.get(Transaction.class, account.getTransactions().get(0).getTransactionId());
            System.out.println(dbTransaction.getAccount().getName());

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
            //bank.getContacts().add("Manager");
            bank.getContacts().put("Manager", "Joe");
            bank.getContacts().put("Teller", "Mary");
            session.save(bank);

            // Adress
            User user2 = new User();
            Address address1 = new Address();
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

            address1.setAddressLine1("line 1");
            address1.setAddressLine2("line2");
            address1.setCity("Philadelphia");
            address1.setState("PA");
            address1.setZipCode("12345");

            address2.setAddressLine1("Avenue 34");
            address2.setAddressLine2("Brighman 25");
            address2.setCity("New York");
            address2.setState("NY");
            address2.setZipCode("54321");

            //user.setAddress(address1);
            user.getAddresses().add(address1);
            user.getAddresses().add(address2);
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }

    }

    private static Date getMyBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1992);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DATE, 26);
        return calendar.getTime();
    }

    private static Account createNewAccount() {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Kevin Bowersox");
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName("Savings Account");
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Kevin Bowersox");
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }

    private static Transaction createNewBeltPurchase(Account account) {
        Transaction beltPurchase = new Transaction();
        beltPurchase.setAccount(account);
        beltPurchase.setTitle("Dress Belt");
        beltPurchase.setAmount(new BigDecimal("50.00"));
        beltPurchase.setClosingBalance(new BigDecimal("0.00"));
        beltPurchase.setCreatedBy("Kevin Bowersox");
        beltPurchase.setCreatedDate(new Date());
        beltPurchase.setInitialBalance(new BigDecimal("0.00"));
        beltPurchase.setLastUpdatedBy("Kevin Bowersox");
        beltPurchase.setLastUpdatedDate(new Date());
        beltPurchase.setNotes("New Dress Belt");
        beltPurchase.setTransactionType("Debit");
        return beltPurchase;
    }

    private static Transaction createShoePurchase(Account account) {
        Transaction shoePurchase = new Transaction();
        shoePurchase.setAccount(account);
        shoePurchase.setTitle("Work Shoes");
        shoePurchase.setAmount(new BigDecimal("100.00"));
        shoePurchase.setClosingBalance(new BigDecimal("0.00"));
        shoePurchase.setCreatedBy("Kevin Bowersox");
        shoePurchase.setCreatedDate(new Date());
        shoePurchase.setInitialBalance(new BigDecimal("0.00"));
        shoePurchase.setLastUpdatedBy("Kevin Bowersox");
        shoePurchase.setLastUpdatedDate(new Date());
        shoePurchase.setNotes("Nice Pair of Shoes");
        shoePurchase.setTransactionType("Debit");
        return shoePurchase;
    }

    private static User saveUser(Session session) {
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
        return user;
    }

}
