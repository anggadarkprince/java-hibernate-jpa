package com.sketchproject.myhibernate.data;

import com.sketchproject.myhibernate.data.HibernateUtil;
import com.sketchproject.myhibernate.entities.Bond;
import com.sketchproject.myhibernate.entities.Investment;
import com.sketchproject.myhibernate.entities.Portfolio;
import com.sketchproject.myhibernate.entities.Stock;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InheritanceSingleTable {

    public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Portfolio portfolio = new Portfolio();
            portfolio.setName("First Investments");

            Stock stock = createStock();
            stock.setPortfolio(portfolio);

            Bond bond = createBond();
            bond.setPortfolio(portfolio);

            portfolio.getInvestements().add(stock);
            portfolio.getInvestements().add(bond);

            session.save(stock);
            session.save(bond);

            tx.commit();

            Portfolio dbPortfolio = (Portfolio) session.get(Portfolio.class, portfolio.getPortfolioId());
            session.refresh(dbPortfolio);

            for (Investment i : dbPortfolio.getInvestements()) {
                System.out.println(i.getName());
            }

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
