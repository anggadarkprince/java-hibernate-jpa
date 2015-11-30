package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Account;
import com.sketchproject.myhibernate.entities.AccountType;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Enumerations {

    public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Account account = createNewAccount();
            account.setAccountType(AccountType.SAVINGS);

            session.save(account);
            tx.commit();

            Account dbAccount = (Account) session.get(Account.class, account.getAccountId());
            System.out.println(dbAccount.getName());
            System.out.println(dbAccount.getAccountType());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
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

}
