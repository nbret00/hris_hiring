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
@Table(name = "nsb_remarks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbRemarks.findAll", query = "SELECT n FROM NsbRemarks n"),
    @NamedQuery(name = "NsbRemarks.findByIdremarks", query = "SELECT n FROM NsbRemarks n WHERE n.idremarks = :idremarks"),
    @NamedQuery(name = "NsbRemarks.findByCreatedBy", query = "SELECT n FROM NsbRemarks n WHERE n.createdBy = :createdBy"),
    @NamedQuery(name = "NsbRemarks.findByCreatedDt", query = "SELECT n FROM NsbRemarks n WHERE n.createdDt = :createdDt"),
    @NamedQuery(name = "NsbRemarks.findByStatus", query = "SELECT n FROM NsbRemarks n WHERE n.status = :status"),
    @NamedQuery(name = "NsbRemarks.findByParentId", query = "SELECT n FROM NsbRemarks n WHERE n.parentId = :parentId"),
    @NamedQuery(name = "NsbRemarks.findByCreatedByName", query = "SELECT n FROM NsbRemarks n WHERE n.createdByName = :createdByName")})
public class NsbRemarks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idremarks")
    private Integer idremarks;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Lob
    @Size(max = 65535)
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "status")
    private Integer status;
    @Column(name = "parent_id")
    private Integer parentId;
    @Size(max = 100)
    @Column(name = "created_by_name")
    private String createdByName;
    @JoinColumn(name = "identity_activities", referencedColumnName = "idpersonactivities")
    @ManyToOne(optional = false)
    private NsbPersonActivities identityActivities;

    public NsbRemarks() {
    }

    public NsbRemarks(Integer idremarks) {
        this.idremarks = idremarks;
    }

    public Integer getIdremarks() {
        return idremarks;
    }

    public void setIdremarks(Integer idremarks) {
        this.idremarks = idremarks;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public NsbPersonActivities getIdentityActivities() {
        return identityActivities;
    }

    public void setIdentityActivities(NsbPersonActivities identityActivities) {
        this.identityActivities = identityActivities;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idremarks != null ? idremarks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbRemarks)) {
            return false;
        }
        NsbRemarks other = (NsbRemarks) object;
        if ((this.idremarks == null && other.idremarks != null) || (this.idremarks != null && !this.idremarks.equals(other.idremarks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbRemarks[ idremarks=" + idremarks + " ]";
    }
    
}
