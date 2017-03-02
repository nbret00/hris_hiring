/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "stage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stage.findAll", query = "SELECT s FROM Stage s"),
    @NamedQuery(name = "Stage.findByIdHRISStage", query = "SELECT s FROM Stage s WHERE s.idHRISStage = :idHRISStage"),
    @NamedQuery(name = "Stage.findByName", query = "SELECT s FROM Stage s WHERE s.name = :name"),
    @NamedQuery(name = "Stage.findByDescription", query = "SELECT s FROM Stage s WHERE s.description = :description")})
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHRISStage")
    private Integer idHRISStage;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @OneToMany(mappedBy = "stageidHRISStage")
    private Collection<Personstagestatus> personstagestatusCollection;

    public Stage() {
    }

    public Stage(Integer idHRISStage) {
        this.idHRISStage = idHRISStage;
    }

    public Integer getIdHRISStage() {
        return idHRISStage;
    }

    public void setIdHRISStage(Integer idHRISStage) {
        this.idHRISStage = idHRISStage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlTransient
    public Collection<Personstagestatus> getPersonstagestatusCollection() {
        return personstagestatusCollection;
    }

    public void setPersonstagestatusCollection(Collection<Personstagestatus> personstagestatusCollection) {
        this.personstagestatusCollection = personstagestatusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHRISStage != null ? idHRISStage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stage)) {
            return false;
        }
        Stage other = (Stage) object;
        if ((this.idHRISStage == null && other.idHRISStage != null) || (this.idHRISStage != null && !this.idHRISStage.equals(other.idHRISStage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Stage[ idHRISStage=" + idHRISStage + " ]";
    }
    
}
