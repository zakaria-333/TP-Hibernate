/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import ma.projet.dao.IDao;
import ma.projet.entities.EmployeTache;
import ma.projet.entities.Projet;
import ma.projet.entities.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pc
 */
public class ProjetService  implements IDao<Projet> {

    @Override
    public boolean create(Projet o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Projet o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Projet o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Projet> findAll(Projet o) {
        List<Projet> projets = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projets = session.createQuery("from Projet").list();
            tx.commit();
            return projets;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
            return projets;
        } finally {
            if(session != null)
                session.close();
        }
    }

    @Override
    public Projet findById(int id) {
        Projet projet = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            projet = (Projet) session.get(Projet.class, id);
            tx.commit();
            return projet;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return projet;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Tache> getTachesPlanifieesPourProjet(int projetId) {
        List<Tache> tachesPlanifiees = null;
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

          
            String hql = "SELECT t FROM Tache t " +
                         "JOIN t.projet AS p " +
                         "WHERE p.id = :projetId";

            tachesPlanifiees = session.createQuery(hql)
                                      .setParameter("projetId", projetId)
                                      .list();

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return tachesPlanifiees;
    }

    public void afficherTachesPlanifieesPourProjet(int projetId) {
        List<Tache> tachesPlanifiees = getTachesPlanifieesPourProjet(projetId);

        if (tachesPlanifiees.isEmpty()) {
            System.out.println("Aucune tâche planifiée pour le projet avec l'ID " + projetId);
        } else {
            System.out.println("Tâches planifiées pour le projet avec l'ID " + projetId + ":");
            for (Tache tache : tachesPlanifiees) {
                System.out.println("Tâche ID : " + tache.getId() + ", Nom : " + tache.getNom());
               
            }
        }
    }
     public  List<EmployeTache> getTacheList(int id) {
        List<EmployeTache> laListe=null;
          Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
          String hql = "SELECT et FROM EmployeTache et " +
             "JOIN et.tache AS t " +
             "JOIN et.employe AS e " +
             "WHERE e.id = :id";


            laListe = session.createQuery(hql)
                    .setParameter("id",id).list();
            
            tx.commit();
          
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
           
           
        } finally {
            if (session != null) {
                session.close();
            }
        }
          return laListe;
      }
    
    ///6//
public void afficherTachesRealiseesPourProjet(int projetId) {
    Projet projet = findById(projetId);

    if (projet == null) {
        System.out.println("Projet avec l'ID " + projetId + " non trouvé.");
    } else {
        System.out.println("Projet : " + projet.getId());
        System.out.println("Nom : " + projet.getNom());
        System.out.println("Date début : " + projet.getDateDebut());
        System.out.println("Liste des tâches:");

        List<Tache> tachesRealisees = new ArrayList<>();

        for (Tache tache : projet.getTaches()) {
            List<EmployeTache> employeTaches = getTacheList(tache.getId());

            if (!employeTaches.isEmpty()) {
                tachesRealisees.add(tache);
            }
        }

        if (tachesRealisees.isEmpty()) {
            System.out.println("Aucune tâche réalisée pour ce projet.");
        } else {
            System.out.printf("%-4s %-15s %-18s %-18s%n", "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");
            int num = 1;

            for (Tache tache : tachesRealisees) {
                List<EmployeTache> employeTaches = getTacheList(tache.getId());

                if (!employeTaches.isEmpty()) {
                    EmployeTache employeTache = employeTaches.get(0);

                    System.out.printf("%-4d %-15s %-18s %-18s%n",
                            num,
                            tache.getNom(),
                            employeTache.getId().getDateDebut(),
                            employeTache.getId().getDateFin()
                    );

                    num++;
                }
            }
        }
    }
}

}