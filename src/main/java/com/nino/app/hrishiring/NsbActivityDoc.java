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
import javax.persistence.Lob;
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
@Table(name = "nsb_activity_doc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivityDoc.findAll", query = "SELECT n FROM NsbActivityDoc n"),
    @NamedQuery(name = "NsbActivityDoc.findByIdnsbActivityDoc", query = "SELECT n FROM NsbActivityDoc n WHERE n.idnsbActivityDoc = :idnsbActivityDoc"),
    @NamedQuery(name = "NsbActivityDoc.findByCreatedBy", query = "SELECT n FROM NsbActivityDoc n WHERE n.createdBy = :createdBy"),
    @NamedQuery(name = "NsbActivityDoc.findByCreatedDt", query = "SELECT n FROM NsbActivityDoc n WHERE n.createdDt = :createdDt"),
    @NamedQuery(name = "NsbActivityDoc.findByLastUpdatedBy", query = "SELECT n FROM NsbActivityDoc n WHERE n.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "NsbActivityDoc.findByLastUpdatedDt", query = "SELECT n FROM NsbActivityDoc n WHERE n.lastUpdatedDt = :lastUpdatedDt"),
    @NamedQuery(name = "NsbActivityDoc.findByStatus", query = "SELECT n FROM NsbActivityDoc n WHERE n.status = :status")})
public class NsbActivityDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnsb_activity_doc")
    private Integer idnsbActivityDoc;
    @Column(name = "created_by")
    private Integer createdBy;
    @Size(max = 45)
    @Column(name = "created_dt")
    private String createdDt;
    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;
    @Column(name = "last_updated_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDt;
    @Lob
    @Size(max = 16777215)
    @Column(name = "documentation")
    private String documentation;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "nsb_activities_idSourcingActivities", referencedColumnName = "idSourcingActivities")
    @ManyToOne
    private NsbActivities nsbactivitiesidSourcingActivities;

    public NsbActivityDoc() {
    }

    public NsbActivityDoc(Integer idnsbActivityDoc) {
        this.idnsbActivityDoc = idnsbActivityDoc;
    }

    public Integer getIdnsbActivityDoc() {
        return idnsbActivityDoc;
    }

    public void setIdnsbActivityDoc(Integer idnsbActivityDoc) {
        this.idnsbActivityDoc = idnsbActivityDoc;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(Date lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (idnsbActivityDoc != null ? idnsbActivityDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivityDoc)) {
            return false;
        }
        NsbActivityDoc other = (NsbActivityDoc) object;
        if ((this.idnsbActivityDoc == null && other.idnsbActivityDoc != null) || (this.idnsbActivityDoc != null && !this.idnsbActivityDoc.equals(other.idnsbActivityDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivityDoc[ idnsbActivityDoc=" + idnsbActivityDoc + " ]";
    }
    
}
