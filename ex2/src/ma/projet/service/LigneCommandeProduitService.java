
package ma.projet.service;

import java.util.ArrayList;
import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.LigneCommandeProduit;

import ma.projet.entities.LigneCommandeProduit;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author pc
 */
public class LigneCommandeProduitService implements IDao<LigneCommandeProduit> {

    @Override
    public boolean create(LigneCommandeProduit o) {
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
    public boolean delete(LigneCommandeProduit o) {
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
    public boolean update(LigneCommandeProduit o) {
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
    public List<LigneCommandeProduit> findAll(LigneCommandeProduit o) {
        List<LigneCommandeProduit> ligneCommandeProduits = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            ligneCommandeProduits = session.createQuery("from lignecommandeproduit").list();
            tx.commit();
            return ligneCommandeProduits;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return ligneCommandeProduits;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
        public List<LigneCommandeProduit> findAll() {
        List<LigneCommandeProduit> ligneCommandeProduits = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            ligneCommandeProduits = session.createQuery("from LigneCommandeProduit").list();
            tx.commit();
            return ligneCommandeProduits;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return ligneCommandeProduits;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public LigneCommandeProduit findById(int id) {
        LigneCommandeProduit ligneCommandeProduit = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            ligneCommandeProduit = (LigneCommandeProduit) session.get(LigneCommandeProduit.class, id);
            tx.commit();
            return ligneCommandeProduit;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return ligneCommandeProduit;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    
//     public LigneCommandeProduit findByCommande(int commande) {
//        LigneCommandeProduit ligneCommandeProduit = null;
//        Session session = null;
//        Transaction tx = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            tx = session.beginTransaction();
//           
//                Query query = session.createQuery("FROM LigneCommandeProduit WHERE id.commande = :commande ");
//        query.setParameter("commande", commande);
//       
//
//      
//        ligneCommandeProduit = (LigneCommandeProduit) query.uniqueResult();
//            
//            tx.commit();
//            return ligneCommandeProduit;
//        } catch (HibernateException ex) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            return ligneCommandeProduit;
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
    
    ////////////////////////////////mathode de recherche avec query
    public LigneCommandeProduit findById(int commande, int produit, int quantity) {
    LigneCommandeProduit ligneCommandeProduit = null;
    Session session = null;
    Transaction tx = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("FROM LigneCommandeProduit WHERE id.commande = :commande AND id.produit = :produit AND id.quantity = :quantity");
        query.setParameter("commande", commande);
        query.setParameter("produit", produit);
        query.setParameter("quantity", quantity);

      
        ligneCommandeProduit = (LigneCommandeProduit) query.uniqueResult();
        
        tx.commit();
        return ligneCommandeProduit;
    } catch (HibernateException ex) {
        if (tx != null) {
            tx.rollback();
        }
        return ligneCommandeProduit;
    } finally {
        if (session != null) {
            session.close();
        }
    }
}
    
    public List<LigneCommandeProduit> findProduitsDepuisIdCommande(int commandeId) {
    List<LigneCommandeProduit> produitsDepuisIdCommande = null;
    Session session = null;
    Transaction tx = null;

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        produitsDepuisIdCommande = session.createCriteria(LigneCommandeProduit.class)
                .createAlias("commande", "cmd")
                .createAlias("produit", "prod")
                .add(Restrictions.eq("cmd.id", commandeId))
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

    return produitsDepuisIdCommande;
}
    
    


 
}
