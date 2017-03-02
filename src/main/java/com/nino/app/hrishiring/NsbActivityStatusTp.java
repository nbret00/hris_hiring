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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "nsb_activity_status_tp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivityStatusTp.findAll", query = "SELECT n FROM NsbActivityStatusTp n"),
    @NamedQuery(name = "NsbActivityStatusTp.findByIdactivityStatus", query = "SELECT n FROM NsbActivityStatusTp n WHERE n.idactivityStatus = :idactivityStatus"),
    @NamedQuery(name = "NsbActivityStatusTp.findByName", query = "SELECT n FROM NsbActivityStatusTp n WHERE n.name = :name"),
    @NamedQuery(name = "NsbActivityStatusTp.findByDescription", query = "SELECT n FROM NsbActivityStatusTp n WHERE n.description = :description"),
    @NamedQuery(name = "NsbActivityStatusTp.findByPriorityLevel", query = "SELECT n FROM NsbActivityStatusTp n WHERE n.priorityLevel = :priorityLevel")})
public class NsbActivityStatusTp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactivity_status")
    private Integer idactivityStatus;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @Column(name = "priority_level")
    private Integer priorityLevel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nsbActivityStatusTp")
    private Collection<NsbActivities> nsbActivitiesCollection;
    @JoinColumn(name = "id_activity_tp", referencedColumnName = "id_activity_tp")
    @ManyToOne(optional = false)
    private NsbActivityTp idActivityTp;

    public NsbActivityStatusTp() {
    }

    public NsbActivityStatusTp(Integer idactivityStatus) {
        this.idactivityStatus = idactivityStatus;
    }

    public Integer getIdactivityStatus() {
        return idactivityStatus;
    }

    public void setIdactivityStatus(Integer idactivityStatus) {
        this.idactivityStatus = idactivityStatus;
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

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @XmlTransient
    public Collection<NsbActivities> getNsbActivitiesCollection() {
        return nsbActivitiesCollection;
    }

    public void setNsbActivitiesCollection(Collection<NsbActivities> nsbActivitiesCollection) {
        this.nsbActivitiesCollection = nsbActivitiesCollection;
    }

    public NsbActivityTp getIdActivityTp() {
        return idActivityTp;
    }

    public void setIdActivityTp(NsbActivityTp idActivityTp) {
        this.idActivityTp = idActivityTp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactivityStatus != null ? idactivityStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivityStatusTp)) {
            return false;
        }
        NsbActivityStatusTp other = (NsbActivityStatusTp) object;
        if ((this.idactivityStatus == null && other.idactivityStatus != null) || (this.idactivityStatus != null && !this.idactivityStatus.equals(other.idactivityStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivityStatusTp[ idactivityStatus=" + idactivityStatus + " ]";
    }
    
}
