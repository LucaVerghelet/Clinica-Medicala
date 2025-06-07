package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.database.HibernateUtil;
import org.example.model.Pacient;
import org.example.model.UserProfile;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        HibernateUtil hu = new HibernateUtil();
        EntityManager em = hu.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
//        Pacient p = new Pacient("Alex","andrei", 1234561, "Nu exista");
//        UserProfile u = new UserProfile("alex2", "alex@12333.com", "1234");
//        p.setUserProfile(u);
//        em.persist(p);
//        em.persist(u);

        transaction.commit();

        if (transaction.isActive()){
            transaction.rollback();
        }
        em.close();

    }


}