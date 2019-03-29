package Oving5;


import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.swing.*;
import java.util.ArrayList;

public class Oving5 {
    /*
    ---------------Oppg. 1--------------
    Optimistisk låsing er en form for låsing man bruker for å unngå dirty reads. Det fungerer ved å ha en sjekk på om
    databaseverdiene er endret siden sist vi leste fra dataen. Dersom noe er endret, vil vi rulle tilbake og begynne på
    nytt. Vi kan for eksempel se på sist oppdatert for å finne ut av om databasen er oppdatert siden sist.
    Det eksisterer en strengere variat av låsen som heter pessimistisk lås, som igjen vil gi din prossess eksklusiv
    tilgang, og låse ute alle andre prosesser for å unngå potensielle deadlocks.
     */
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/Oving5/hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
    private static void oppgave2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kontoManager");
        kontoDAO fasade = null;

        try {
            fasade = new kontoDAO(emf);

            //Opprette to kontoer til databasen
            konto konto1 = new konto(123456789, 113.37, "Kevin Helgeland");
            konto konto2 = new konto(987654321, 100.23, "Bolly Goober");

            fasade.setKonto(konto1);
            fasade.setKonto(konto2);

            //Finne kontoer med større saldo enn gitt beløp
            ArrayList<konto> kontos = fasade.finnKontoerMedStorreSaldo(50.0);
            for (konto k : kontos) {
                System.out.println(k.getNavn());
            }

            //Endre eier av et av objektene og lagre til databasen
            konto konto3 = konto1;
            String nyEier = "B-gjengen";
            fasade.endreEier(konto3, nyEier);

        } finally {
            emf.close();
        }
    }

    private static void oppgave3() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kontoManager");
        kontoDAO fasade = null;

        try {
            fasade = new kontoDAO(emf);
            int kontonrFra = 123456789;
            int kontonrTil = 987654321;
            double sumOverforing = 22.00;
            JOptionPane.showMessageDialog(null, "Stanser koden litt her");
            fasade.overforPenger(kontonrFra, kontonrTil, sumOverforing);
        } finally {
            emf.close();
        }
    }

    public static void main(final String args[]){
        /*final Session session = getSession();
        try{
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("FROM " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                    konto kontoTest = (konto)o;
                    System.out.println(kontoTest.getNavn() + ": " + kontoTest.getKontoNr());
                }
            }
        } finally {
            session.close();
        }

        ourSessionFactory.close();*/
        //oppgave2();
        oppgave3();

    }
}

