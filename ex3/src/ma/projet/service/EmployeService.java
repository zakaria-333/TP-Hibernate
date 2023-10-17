/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Employe;
import ma.projet.entities.EmployeTache;
import ma.projet.entities.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pc
 */
public class EmployeService  implements IDao<Employe> {

    @Override
    public boolean create(Employe o) {
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
    public boolean delete(Employe o) {
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
    public boolean update(Employe o) {
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
    public List<Employe> findAll(Employe o) {
        List<Employe> employes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employes = session.createQuery("from Employe").list();
            tx.commit();
            return employes;
        } catch (HibernateException ex) {
            if(tx != null)
                tx.rollback();
            return employes;
        } finally {
            if(session != null)
                session.close();
        }
    }

    @Override
    public Employe findById(int id) {
        Employe employe = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employe = (Employe) session.get(Employe.class, id);
            tx.commit();
            return employe;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return employe;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    //3
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
    
     public void afficherTachesRealiseesParEmploye(int employeId) {
        List<EmployeTache> tacheList = getTacheList(employeId);

        if (tacheList.isEmpty()) {
            System.out.println("Aucune tâche réalisée par l'employé avec l'ID " + employeId);
        } else {
            System.out.println("Tâches réalisées par l'employé avec l'ID " + employeId + ":");
            for (EmployeTache employeTache : tacheList) {
                  System.out.println("----------------------------------------------");
                System.out.println("Tâche ID : " + employeTache.getTache().getId()+"    Tâche Nom : " + employeTache.getTache().getNom());
             
            }
        }}
  public void afficherProjetsRealiseesParEmploye(int employeId) {
        List<EmployeTache> tacheList = getTacheList(employeId);
        if (tacheList.isEmpty()) {
            System.out.println("Aucune Projets réalisée par l'employé avec l'ID " + employeId);
        } else {
            System.out.println("Projets réalisées par l'employé avec l'ID " + employeId + ":");
            for (EmployeTache employeTache : tacheList) {
                  System.out.println("----------------------------------------------");
                System.out.println("Projet ID : " + employeTache.getEmploye().getId()
                        +"    Projet Nom : " + employeTache.getEmploye().getNom()+"    Projet Prenom : " + employeTache.getEmploye().getNom());
             
            }
        }}



}
