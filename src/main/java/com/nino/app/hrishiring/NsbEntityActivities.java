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
@Table(name = "nsb_entity_activities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbEntityActivities.findAll", query = "SELECT n FROM NsbEntityActivities n"),
    @NamedQuery(name = "NsbEntityActivities.findByIdidentityActivities", query = "SELECT n FROM NsbEntityActivities n WHERE n.ididentityActivities = :ididentityActivities"),
    @NamedQuery(name = "NsbEntityActivities.findByEntityName", query = "SELECT n FROM NsbEntityActivities n WHERE n.entityName = :entityName"),
    @NamedQuery(name = "NsbEntityActivities.findByEntityId", query = "SELECT n FROM NsbEntityActivities n WHERE n.entityId = :entityId"),
    @NamedQuery(name = "NsbEntityActivities.findByEntityTp", query = "SELECT n FROM NsbEntityActivities n WHERE n.entityTp = :entityTp"),
    @NamedQuery(name = "NsbEntityActivities.findByStatus", query = "SELECT n FROM NsbEntityActivities n WHERE n.status = :status")})
public class NsbEntityActivities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ididentity_activities")
    private Integer ididentityActivities;
    @Size(max = 250)
    @Column(name = "entity_name")
    private String entityName;
    @Column(name = "entity_id")
    private Integer entityId;
    @Size(max = 45)
    @Column(name = "entity_tp")
    private String entityTp;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nsbEntityActivities")
    private Collection<NsbActivities> nsbActivitiesCollection;

    public NsbEntityActivities() {
    }

    public NsbEntityActivities(Integer ididentityActivities) {
        this.ididentityActivities = ididentityActivities;
    }

    public Integer getIdidentityActivities() {
        return ididentityActivities;
    }

    public void setIdidentityActivities(Integer ididentityActivities) {
        this.ididentityActivities = ididentityActivities;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityTp() {
        return entityTp;
    }

    public void setEntityTp(String entityTp) {
        this.entityTp = entityTp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        hash += (ididentityActivities != null ? ididentityActivities.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbEntityActivities)) {
            return false;
        }
        NsbEntityActivities other = (NsbEntityActivities) object;
        if ((this.ididentityActivities == null && other.ididentityActivities != null) || (this.ididentityActivities != null && !this.ididentityActivities.equals(other.ididentityActivities))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbEntityActivities[ ididentityActivities=" + ididentityActivities + " ]";
    }
    
}
