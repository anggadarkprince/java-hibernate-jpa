package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Bond;
import com.sketchproject.myhibernate.entities.Stock;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MappedSuperclass {

    public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Stock stock = createStock();
            session.save(stock);

            Bond bond = createBond();
            session.save(bond);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static Bond createBond() {
        Bond bond = new Bond();
        bond.setInterestRate(new BigDecimal("123.22"));
        bond.setIssuer("JP Morgan Chase");
        bond.setMaturityDate(new Date());
        bond.setPurchaseDate(new Date());
        bond.setName("Long Term Bond Purchases");
        bond.setValue(new BigDecimal("10.22"));
        return bond;
    }

    private static Stock createStock() {
        Stock stock = new Stock();
        stock.setIssuer("Allen Edmonds");
        stock.setName("Private American Stock Purchases");
        stock.setPurchaseDate(new Date());
        stock.setQuantity(new BigDecimal("1922"));
        stock.setSharePrice(new BigDecimal("100.00"));
        return stock;
    }

}
