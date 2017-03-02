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
@Table(name = "stagestatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stagestatus.findAll", query = "SELECT s FROM Stagestatus s"),
    @NamedQuery(name = "Stagestatus.findByIdStageStatus", query = "SELECT s FROM Stagestatus s WHERE s.idStageStatus = :idStageStatus"),
    @NamedQuery(name = "Stagestatus.findByName", query = "SELECT s FROM Stagestatus s WHERE s.name = :name"),
    @NamedQuery(name = "Stagestatus.findByDescription", query = "SELECT s FROM Stagestatus s WHERE s.description = :description")})
public class Stagestatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStageStatus")
    private Integer idStageStatus;
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
    @OneToMany(mappedBy = "stageStatusidStageStatus")
    private Collection<Personstagestatus> personstagestatusCollection;

    public Stagestatus() {
    }

    public Stagestatus(Integer idStageStatus) {
        this.idStageStatus = idStageStatus;
    }

    public Integer getIdStageStatus() {
        return idStageStatus;
    }

    public void setIdStageStatus(Integer idStageStatus) {
        this.idStageStatus = idStageStatus;
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
        hash += (idStageStatus != null ? idStageStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stagestatus)) {
            return false;
        }
        Stagestatus other = (Stagestatus) object;
        if ((this.idStageStatus == null && other.idStageStatus != null) || (this.idStageStatus != null && !this.idStageStatus.equals(other.idStageStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Stagestatus[ idStageStatus=" + idStageStatus + " ]";
    }
    
}
