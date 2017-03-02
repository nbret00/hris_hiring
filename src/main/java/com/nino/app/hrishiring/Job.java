/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByIdjobpk", query = "SELECT j FROM Job j WHERE j.idjobpk = :idjobpk"),
    @NamedQuery(name = "Job.findByTitle", query = "SELECT j FROM Job j WHERE j.title = :title"),
    @NamedQuery(name = "Job.findByDescription", query = "SELECT j FROM Job j WHERE j.description = :description"),
    @NamedQuery(name = "Job.findByLocation", query = "SELECT j FROM Job j WHERE j.location = :location"),
    @NamedQuery(name = "Job.findByStatus", query = "SELECT j FROM Job j WHERE j.status = :status"),
    @NamedQuery(name = "Job.findByRemarks", query = "SELECT j FROM Job j WHERE j.remarks = :remarks"),
    @NamedQuery(name = "Job.findByQualifications", query = "SELECT j FROM Job j WHERE j.qualifications = :qualifications"),
    @NamedQuery(name = "Job.findByDateRecieved", query = "SELECT j FROM Job j WHERE j.dateRecieved = :dateRecieved"),
    @NamedQuery(name = "Job.findByLastUpdatedDt", query = "SELECT j FROM Job j WHERE j.lastUpdatedDt = :lastUpdatedDt"),
    @NamedQuery(name = "Job.findByLastUpdateUser", query = "SELECT j FROM Job j WHERE j.lastUpdateUser = :lastUpdateUser"),
    @NamedQuery(name = "Job.findByOpenPosition", query = "SELECT j FROM Job j WHERE j.openPosition = :openPosition"),
    @NamedQuery(name = "Job.findByClosingDate", query = "SELECT j FROM Job j WHERE j.closingDate = :closingDate"),
    @NamedQuery(name = "Job.findByPAssignement", query = "SELECT j FROM Job j WHERE j.pAssignement = :pAssignement"),
    @NamedQuery(name = "Job.findByPtargetToMatch", query = "SELECT j FROM Job j WHERE j.ptargetToMatch = :ptargetToMatch"),
    @NamedQuery(name = "Job.findByPtargetToEndorse", query = "SELECT j FROM Job j WHERE j.ptargetToEndorse = :ptargetToEndorse")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjobpk")
    private Integer idjobpk;
    @Size(max = 45)
    @Column(name = "Title")
    private String title;
    @Size(max = 250)
    @Column(name = "Description")
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "DescriptionLong")
    private String descriptionLong;
    @Size(max = 45)
    @Column(name = "Location")
    private String location;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 250)
    @Column(name = "Remarks")
    private String remarks;
    @Size(max = 255)
    @Column(name = "Qualifications")
    private String qualifications;
    @Lob
    @Size(max = 65535)
    @Column(name = "Responsibility")
    private String responsibility;
    @Column(name = "DateRecieved")
    @Temporal(TemporalType.DATE)
    private Date dateRecieved;
    @Column(name = "last_updated_dt")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDt;
    @Column(name = "last_update_user")
    private Integer lastUpdateUser;
    @Column(name = "OpenPosition")
    private Integer openPosition;
    @Column(name = "ClosingDate")
    @Temporal(TemporalType.DATE)
    private Date closingDate;
    @Size(max = 100)
    @Column(name = "p_assignement")
    private String pAssignement;
    @Column(name = "p_targetToMatch")
    private Integer ptargetToMatch;
    @Column(name = "p_targetToEndorse")
    private Integer ptargetToEndorse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobIdjobpk")
    private Collection<Endorsement> endorsementCollection;
    @JoinColumn(name = "company_idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Company companyIdclient;

    public Job() {
    }

    public Job(Integer idjobpk) {
        this.idjobpk = idjobpk;
    }

    public Integer getIdjobpk() {
        return idjobpk;
    }

    public void setIdjobpk(Integer idjobpk) {
        this.idjobpk = idjobpk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public Date getDateRecieved() {
        return dateRecieved;
    }

    public void setDateRecieved(Date dateRecieved) {
        this.dateRecieved = dateRecieved;
    }

    public Date getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(Date lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }

    public Integer getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(Integer lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Integer getOpenPosition() {
        return openPosition;
    }

    public void setOpenPosition(Integer openPosition) {
        this.openPosition = openPosition;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getPAssignement() {
        return pAssignement;
    }

    public void setPAssignement(String pAssignement) {
        this.pAssignement = pAssignement;
    }

    public Integer getPtargetToMatch() {
        return ptargetToMatch;
    }

    public void setPtargetToMatch(Integer ptargetToMatch) {
        this.ptargetToMatch = ptargetToMatch;
    }

    public Integer getPtargetToEndorse() {
        return ptargetToEndorse;
    }

    public void setPtargetToEndorse(Integer ptargetToEndorse) {
        this.ptargetToEndorse = ptargetToEndorse;
    }

    @XmlTransient
    public Collection<Endorsement> getEndorsementCollection() {
        return endorsementCollection;
    }

    public void setEndorsementCollection(Collection<Endorsement> endorsementCollection) {
        this.endorsementCollection = endorsementCollection;
    }

    public Company getCompanyIdclient() {
        return companyIdclient;
    }

    public void setCompanyIdclient(Company companyIdclient) {
        this.companyIdclient = companyIdclient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjobpk != null ? idjobpk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.idjobpk == null && other.idjobpk != null) || (this.idjobpk != null && !this.idjobpk.equals(other.idjobpk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Job[ idjobpk=" + idjobpk + " ]";
    }
    
}
