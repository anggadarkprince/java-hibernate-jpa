package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Account;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpqlJoin {

    public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence
                    .createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            TypedQuery<Account> query = em.createQuery("select distinct a from Transaction t"
                    + " join t.account a "
                    + "where t.amount > 500 and t.transactionType = 'Deposit'", Account.class);

            List<Account> accounts = query.getResultList();

            for (Account a : accounts) {
                System.out.println(a.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            factory.close();
        }
    }
}
