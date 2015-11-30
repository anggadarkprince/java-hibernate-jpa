package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.entities.Currency;
import com.sketchproject.myhibernate.entities.Market;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CompoundJoin {

    public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("Indonesia");
            currency.setName("Rupiah");
            currency.setSymbol("Rp");

            Market market = new Market();
            market.setMarketName("IXBN");
            market.setCurrency(currency);

            session.persist(market);
            tx.commit();

            Market dbMarket = (Market) session.get(Market.class, market.getMarketId());
            System.out.println(dbMarket.getCurrency().getName());
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

}
