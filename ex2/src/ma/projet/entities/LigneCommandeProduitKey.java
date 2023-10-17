package ma.projet.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class LigneCommandeProduitKey implements Serializable {
       private int commande;
    private int produit;


    private int quantity;

    public LigneCommandeProduitKey() {
    }

    public LigneCommandeProduitKey(int produit, int commande, int quantity) {
        this.produit = produit;
        this.commande = commande;
        this.quantity = quantity;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
}
