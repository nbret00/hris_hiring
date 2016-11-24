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
@Table(name = "nsb_activity_status_hist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivityStatusHist.findAll", query = "SELECT n FROM NsbActivityStatusHist n"),
    @NamedQuery(name = "NsbActivityStatusHist.findByIdactivityStatusHist", query = "SELECT n FROM NsbActivityStatusHist n WHERE n.idactivityStatusHist = :idactivityStatusHist"),
    @NamedQuery(name = "NsbActivityStatusHist.findByFrom", query = "SELECT n FROM NsbActivityStatusHist n WHERE n.from = :from"),
    @NamedQuery(name = "NsbActivityStatusHist.findByTo", query = "SELECT n FROM NsbActivityStatusHist n WHERE n.to = :to"),
    @NamedQuery(name = "NsbActivityStatusHist.findByUpdatedDt", query = "SELECT n FROM NsbActivityStatusHist n WHERE n.updatedDt = :updatedDt"),
    @NamedQuery(name = "NsbActivityStatusHist.findByUpdatedBy", query = "SELECT n FROM NsbActivityStatusHist n WHERE n.updatedBy = :updatedBy")})
public class NsbActivityStatusHist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactivity_status_hist")
    private Integer idactivityStatusHist;
    @Size(max = 45)
    @Column(name = "from")
    private String from;
    @Size(max = 45)
    @Column(name = "to")
    private String to;
    @Column(name = "updated_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDt;
    @Size(max = 45)
    @Column(name = "updated_by")
    private String updatedBy;
    @JoinColumn(name = "nsb_activities_idSourcingActivities", referencedColumnName = "idSourcingActivities")
    @ManyToOne(optional = false)
    private NsbActivities nsbactivitiesidSourcingActivities;

    public NsbActivityStatusHist() {
    }

    public NsbActivityStatusHist(Integer idactivityStatusHist) {
        this.idactivityStatusHist = idactivityStatusHist;
    }

    public Integer getIdactivityStatusHist() {
        return idactivityStatusHist;
    }

    public void setIdactivityStatusHist(Integer idactivityStatusHist) {
        this.idactivityStatusHist = idactivityStatusHist;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        hash += (idactivityStatusHist != null ? idactivityStatusHist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivityStatusHist)) {
            return false;
        }
        NsbActivityStatusHist other = (NsbActivityStatusHist) object;
        if ((this.idactivityStatusHist == null && other.idactivityStatusHist != null) || (this.idactivityStatusHist != null && !this.idactivityStatusHist.equals(other.idactivityStatusHist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivityStatusHist[ idactivityStatusHist=" + idactivityStatusHist + " ]";
    }
    
}
