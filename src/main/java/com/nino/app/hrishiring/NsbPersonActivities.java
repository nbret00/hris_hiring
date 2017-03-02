/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "nsb_person_activities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbPersonActivities.findAll", query = "SELECT n FROM NsbPersonActivities n"),
    @NamedQuery(name = "NsbPersonActivities.findByIdpersonactivities", query = "SELECT n FROM NsbPersonActivities n WHERE n.idpersonactivities = :idpersonactivities"),
    @NamedQuery(name = "NsbPersonActivities.findByStatus", query = "SELECT n FROM NsbPersonActivities n WHERE n.status = :status")})
public class NsbPersonActivities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonactivities")
    private Integer idpersonactivities;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "identityActivities")
    private Collection<NsbRemarks> nsbRemarksCollection;
    @JoinColumn(name = "person_idPerson", referencedColumnName = "idPerson")
    @ManyToOne(optional = false)
    private Person personidPerson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nsbEntityActivities")
    private Collection<NsbActivities> nsbActivitiesCollection;

    public NsbPersonActivities() {
    }

    public NsbPersonActivities(Integer idpersonactivities) {
        this.idpersonactivities = idpersonactivities;
    }

    public Integer getIdpersonactivities() {
        return idpersonactivities;
    }

    public void setIdpersonactivities(Integer idpersonactivities) {
        this.idpersonactivities = idpersonactivities;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<NsbRemarks> getNsbRemarksCollection() {
        return nsbRemarksCollection;
    }

    public void setNsbRemarksCollection(Collection<NsbRemarks> nsbRemarksCollection) {
        this.nsbRemarksCollection = nsbRemarksCollection;
    }

    public Person getPersonidPerson() {
        return personidPerson;
    }

    public void setPersonidPerson(Person personidPerson) {
        this.personidPerson = personidPerson;
    }

    @XmlTransient
    public Collection<NsbActivities> getNsbActivitiesCollection() {
        return nsbActivitiesCollection;
    }

    public void setNsbActivitiesCollection(Collection<NsbActivities> nsbActivitiesCollection) {
        this.nsbActivitiesCollection = nsbActivitiesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonactivities != null ? idpersonactivities.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbPersonActivities)) {
            return false;
        }
        NsbPersonActivities other = (NsbPersonActivities) object;
        if ((this.idpersonactivities == null && other.idpersonactivities != null) || (this.idpersonactivities != null && !this.idpersonactivities.equals(other.idpersonactivities))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbPersonActivities[ idpersonactivities=" + idpersonactivities + " ]";
    }
    
}
