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
@Table(name = "nsb_activity_tp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivityTp.findAll", query = "SELECT n FROM NsbActivityTp n"),
    @NamedQuery(name = "NsbActivityTp.findByIdActivityTp", query = "SELECT n FROM NsbActivityTp n WHERE n.idActivityTp = :idActivityTp"),
    @NamedQuery(name = "NsbActivityTp.findByDescription", query = "SELECT n FROM NsbActivityTp n WHERE n.description = :description"),
    @NamedQuery(name = "NsbActivityTp.findByTpOrder", query = "SELECT n FROM NsbActivityTp n WHERE n.tpOrder = :tpOrder"),
    @NamedQuery(name = "NsbActivityTp.findByName", query = "SELECT n FROM NsbActivityTp n WHERE n.name = :name")})
public class NsbActivityTp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activity_tp")
    private Integer idActivityTp;
    @Size(max = 2500)
    @Column(name = "description")
    private String description;
    @Column(name = "tp_order")
    private Integer tpOrder;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nsbActivityTp")
    private Collection<NsbActivities> nsbActivitiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivityTp")
    private Collection<NsbActivityStatusTp> nsbActivityStatusTpCollection;

    public NsbActivityTp() {
    }

    public NsbActivityTp(Integer idActivityTp) {
        this.idActivityTp = idActivityTp;
    }

    public Integer getIdActivityTp() {
        return idActivityTp;
    }

    public void setIdActivityTp(Integer idActivityTp) {
        this.idActivityTp = idActivityTp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTpOrder() {
        return tpOrder;
    }

    public void setTpOrder(Integer tpOrder) {
        this.tpOrder = tpOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<NsbActivities> getNsbActivitiesCollection() {
        return nsbActivitiesCollection;
    }

    public void setNsbActivitiesCollection(Collection<NsbActivities> nsbActivitiesCollection) {
        this.nsbActivitiesCollection = nsbActivitiesCollection;
    }

    @XmlTransient
    public Collection<NsbActivityStatusTp> getNsbActivityStatusTpCollection() {
        return nsbActivityStatusTpCollection;
    }

    public void setNsbActivityStatusTpCollection(Collection<NsbActivityStatusTp> nsbActivityStatusTpCollection) {
        this.nsbActivityStatusTpCollection = nsbActivityStatusTpCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivityTp != null ? idActivityTp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivityTp)) {
            return false;
        }
        NsbActivityTp other = (NsbActivityTp) object;
        if ((this.idActivityTp == null && other.idActivityTp != null) || (this.idActivityTp != null && !this.idActivityTp.equals(other.idActivityTp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivityTp[ idActivityTp=" + idActivityTp + " ]";
    }
    
}
