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
@Table(name = "nsb_activity_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivityStatus.findAll", query = "SELECT n FROM NsbActivityStatus n"),
    @NamedQuery(name = "NsbActivityStatus.findByIdactivityStatus", query = "SELECT n FROM NsbActivityStatus n WHERE n.idactivityStatus = :idactivityStatus"),
    @NamedQuery(name = "NsbActivityStatus.findByStatusId", query = "SELECT n FROM NsbActivityStatus n WHERE n.statusId = :statusId"),
    @NamedQuery(name = "NsbActivityStatus.findByUpdatedDt", query = "SELECT n FROM NsbActivityStatus n WHERE n.updatedDt = :updatedDt"),
    @NamedQuery(name = "NsbActivityStatus.findByUpdatedBy", query = "SELECT n FROM NsbActivityStatus n WHERE n.updatedBy = :updatedBy")})
public class NsbActivityStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactivity_status")
    private Integer idactivityStatus;
    @Size(max = 45)
    @Column(name = "status_id")
    private String statusId;
    @Column(name = "updated_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDt;
    @Size(max = 45)
    @Column(name = "updated_by")
    private String updatedBy;
    @JoinColumn(name = "nsb_activities_idSourcingActivities", referencedColumnName = "idSourcingActivities")
    @ManyToOne(optional = false)
    private NsbActivities nsbactivitiesidSourcingActivities;

    public NsbActivityStatus() {
    }

    public NsbActivityStatus(Integer idactivityStatus) {
        this.idactivityStatus = idactivityStatus;
    }

    public Integer getIdactivityStatus() {
        return idactivityStatus;
    }

    public void setIdactivityStatus(Integer idactivityStatus) {
        this.idactivityStatus = idactivityStatus;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public NsbActivities getNsbactivitiesidSourcingActivities() {
        return nsbactivitiesidSourcingActivities;
    }

    public void setNsbactivitiesidSourcingActivities(NsbActivities nsbactivitiesidSourcingActivities) {
        this.nsbactivitiesidSourcingActivities = nsbactivitiesidSourcingActivities;
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
        if (!(object instanceof NsbActivityStatus)) {
            return false;
        }
        NsbActivityStatus other = (NsbActivityStatus) object;
        if ((this.idactivityStatus == null && other.idactivityStatus != null) || (this.idactivityStatus != null && !this.idactivityStatus.equals(other.idactivityStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivityStatus[ idactivityStatus=" + idactivityStatus + " ]";
    }
    
}
