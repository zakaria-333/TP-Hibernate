/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class EmployeTacheKey implements Serializable {

    private int employe;
    private int tache;
    private Date dateDebut;
    private Date dateFin;

    public EmployeTacheKey() {
    }

    public EmployeTacheKey(int tache, int employe, Date dateDebut, Date dateFin) {
        this.employe = employe;
        this.tache = tache;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getTache() {
        return tache;
    }

    public void setTache(int tache) {
        this.tache = tache;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

  
}
