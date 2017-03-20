/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "nsb_activities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivities.findAll", query = "SELECT n FROM NsbActivities n"),
    @NamedQuery(name = "NsbActivities.findByIdSourcingActivities", query = "SELECT n FROM NsbActivities n WHERE n.idSourcingActivities = :idSourcingActivities"),
    @NamedQuery(name = "NsbActivities.findByActOwner", query = "SELECT n FROM NsbActivities n WHERE n.actOwner = :actOwner"),
    @NamedQuery(name = "NsbActivities.findByCreatedBy", query = "SELECT n FROM NsbActivities n WHERE n.createdBy = :createdBy"),
    @NamedQuery(name = "NsbActivities.findByCreatedDt", query = "SELECT n FROM NsbActivities n WHERE n.createdDt = :createdDt"),
    @NamedQuery(name = "NsbActivities.findByLastUpdatedDt", query = "SELECT n FROM NsbActivities n WHERE n.lastUpdatedDt = :lastUpdatedDt"),
    @NamedQuery(name = "NsbActivities.findByUpdatedBy", query = "SELECT n FROM NsbActivities n WHERE n.updatedBy = :updatedBy"),
    @NamedQuery(name = "NsbActivities.findByDescription", query = "SELECT n FROM NsbActivities n WHERE n.description = :description"),
    @NamedQuery(name = "NsbActivities.findByStartDt", query = "SELECT n FROM NsbActivities n WHERE n.startDt = :startDt"),
    @NamedQuery(name = "NsbActivities.findByEndDt", query = "SELECT n FROM NsbActivities n WHERE n.endDt = :endDt"),
    @NamedQuery(name = "NsbActivities.findByActOrder", query = "SELECT n FROM NsbActivities n WHERE n.actOrder = :actOrder"),
    @NamedQuery(name = "NsbActivities.findByUpdatedByName", query = "SELECT n FROM NsbActivities n WHERE n.updatedByName = :updatedByName"),
    @NamedQuery(name = "NsbActivities.findByCreatedByName", query = "SELECT n FROM NsbActivities n WHERE n.createdByName = :createdByName")})
public class NsbActivities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSourcingActivities")
    private Integer idSourcingActivities;
    @Column(name = "act_owner")
    private Integer actOwner;
    @Size(max = 45)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Column(name = "last_updated_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Column(name = "start_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDt;
    @Column(name = "end_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDt;
    @Column(name = "act_order")
    private Integer actOrder;
    @Size(max = 150)
    @Column(name = "updated_by_name")
    private String updatedByName;
    @Size(max = 150)
    @Column(name = "created_by_name")
    private String createdByName;
    @JoinColumn(name = "nsb_activity_status_tp", referencedColumnName = "idactivity_status")
    @ManyToOne(optional = false)
    private NsbActivityStatusTp nsbActivityStatusTp;
    @JoinColumn(name = "nsb_activity_tp", referencedColumnName = "id_activity_tp")
    @ManyToOne(optional = false)
    private NsbActivityTp nsbActivityTp;
    @JoinColumn(name = "nsb_entity_activities", referencedColumnName = "idpersonactivities")
    @ManyToOne(optional = false)
    private NsbPersonActivities nsbEntityActivities;

    public NsbActivities() {
    }

    public NsbActivities(Integer idSourcingActivities) {
        this.idSourcingActivities = idSourcingActivities;
    }

    public Integer getIdSourcingActivities() {
        return idSourcingActivities;
    }

    public void setIdSourcingActivities(Integer idSourcingActivities) {
        this.idSourcingActivities = idSourcingActivities;
    }

    public Integer getActOwner() {
        return actOwner;
    }

    public void setActOwner(Integer actOwner) {
        this.actOwner = actOwner;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(Date lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public Integer getActOrder() {
        return actOrder;
    }

    public void setActOrder(Integer actOrder) {
        this.actOrder = actOrder;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public NsbActivityStatusTp getNsbActivityStatusTp() {
        return nsbActivityStatusTp;
    }

    public void setNsbActivityStatusTp(NsbActivityStatusTp nsbActivityStatusTp) {
        this.nsbActivityStatusTp = nsbActivityStatusTp;
    }

    public NsbActivityTp getNsbActivityTp() {
        return nsbActivityTp;
    }

    public void setNsbActivityTp(NsbActivityTp nsbActivityTp) {
        this.nsbActivityTp = nsbActivityTp;
    }

    public NsbPersonActivities getNsbEntityActivities() {
        return nsbEntityActivities;
    }

    public void setNsbEntityActivities(NsbPersonActivities nsbEntityActivities) {
        this.nsbEntityActivities = nsbEntityActivities;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSourcingActivities != null ? idSourcingActivities.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivities)) {
            return false;
        }
        NsbActivities other = (NsbActivities) object;
        if ((this.idSourcingActivities == null && other.idSourcingActivities != null) || (this.idSourcingActivities != null && !this.idSourcingActivities.equals(other.idSourcingActivities))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivities[ idSourcingActivities=" + idSourcingActivities + " ]";
    }
    
}
