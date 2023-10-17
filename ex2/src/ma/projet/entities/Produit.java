/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author pc
 */
@Entity
@NamedQuery(name = "Produit.findProduitsPrixSuperieur", query = "SELECT p FROM Produit p WHERE p.prix >= :prixLimite")

public class Produit  {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String reference;
    private float prix;
   
    @ManyToOne
   private Categorie categories;
    
 
@OneToMany(mappedBy = "produit")
private List<LigneCommandeProduit> ligneCommandeProduits=new ArrayList<>();

    
    
    public Produit(){
        
    }
    public Produit(String reference, float prix, Categorie categories) {
        this.reference = reference;
        this.prix = prix;
        this.categories = categories;
    }

    
       public List<LigneCommandeProduit> getLigneCommandeProduits() {
        return ligneCommandeProduits;
    }

    public void setLigneCommandeProduits(List<LigneCommandeProduit> ligneCommandeProduits) {
        this.ligneCommandeProduits = ligneCommandeProduits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categorie getCategories() {
        return categories;
    }

    public void setCategories(Categorie categories) {
        this.categories = categories;
    }
   
}
