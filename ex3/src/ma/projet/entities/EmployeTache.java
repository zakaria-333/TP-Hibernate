/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author pc
 */
@Entity
public class EmployeTache {
      @EmbeddedId
    private EmployeTacheKey id;
    @JoinColumn(name = "tache", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Tache tache;
    @JoinColumn(name = "employe", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Employe employe;

    public EmployeTache() {
    }

    public EmployeTache(EmployeTacheKey id) {
        this.id = id;
    }

    public EmployeTacheKey getId() {
        return id;
    }

    public void setId(EmployeTacheKey id) {
        this.id = id;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
}
