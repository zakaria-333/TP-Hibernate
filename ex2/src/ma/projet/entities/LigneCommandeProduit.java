package ma.projet.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import ma.projet.service.LigneCommandeProduitServiceKey;

@Entity
public class LigneCommandeProduit implements Serializable {
   
    @EmbeddedId
    private LigneCommandeProduitKey id;

    @JoinColumn(name = "produit", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "commande", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Commande commande;

    public LigneCommandeProduit() {
    }

    public LigneCommandeProduit(LigneCommandeProduitKey id) {
        this.id = id;
    }
    


    public LigneCommandeProduitKey getId() {
        return id;
    }

    public void setId(LigneCommandeProduitKey id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void findById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
