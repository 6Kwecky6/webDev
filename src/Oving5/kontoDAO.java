package Oving5;

import javax.persistence.*;

import java.util.ArrayList;

public class kontoDAO {
    private EntityManagerFactory entManFac;

    kontoDAO(EntityManagerFactory entityManagerFactory){
        entManFac=entityManagerFactory;
    }

    public EntityManager getEntityManager(){
        return entManFac.createEntityManager();
    }

    private void closeEntityManager(EntityManager entityManager){
        if(entityManager!=null&&entityManager.isOpen()){
            entityManager.close();
        }
    }

    public ArrayList<konto> finnKontoerMedStorreSaldo(double saldo) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("FROM konto WHERE saldo >= :saldo");
            query.setParameter("saldo", saldo);
            return new ArrayList<konto>(query.getResultList());
        } finally {
            closeEntityManager(em);
        }
    }
    public void endreEier(konto konto, String eier) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            konto nyKonto = konto;
            nyKonto.setNavn(eier);
            //Konto konto1 = em.merge(konto);
            em.merge(nyKonto);
            em.getTransaction().commit();
        } finally {
            closeEntityManager(em);
        }
    }


    public void overforPenger(int fra, int til, double sum) {
        EntityManager em = getEntityManager();
        boolean skjedd = false;

        while(!skjedd) {
            try {
                konto trekkKonto = em.find(konto.class, fra);
                konto leggTilKonto = em.find(konto.class, til);

                em.getTransaction().begin();
                trekkKonto.trekk(sum);
                double nySaldo = leggTilKonto.getSaldo() + sum;
                leggTilKonto.setSaldo(nySaldo);
                em.merge(trekkKonto);
                em.merge(leggTilKonto);
                em.getTransaction().commit();
                skjedd = true;
             }catch (OptimisticLockException optiole) {
                skjedd = false;
            }finally{
                closeEntityManager(em);
            }
        }

    }

    public konto getKonto(int kontoNr) {
        EntityManager em = getEntityManager();
        try {
            return em.find(konto.class, kontoNr);
        } finally {
            closeEntityManager(em);
        }
    }

    public boolean setKonto(konto konto) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(konto);
            em.getTransaction().commit();
            return true;
        }finally{
            closeEntityManager(em);
        }
    }

}
