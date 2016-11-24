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
@Table(name = "sourcing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sourcing.findAll", query = "SELECT s FROM Sourcing s"),
    @NamedQuery(name = "Sourcing.findByIdsourcingCampaigne", query = "SELECT s FROM Sourcing s WHERE s.idsourcingCampaigne = :idsourcingCampaigne"),
    @NamedQuery(name = "Sourcing.findByTitle", query = "SELECT s FROM Sourcing s WHERE s.title = :title"),
    @NamedQuery(name = "Sourcing.findByStatus", query = "SELECT s FROM Sourcing s WHERE s.status = :status"),
    @NamedQuery(name = "Sourcing.findByTargetJobTitle", query = "SELECT s FROM Sourcing s WHERE s.targetJobTitle = :targetJobTitle"),
    @NamedQuery(name = "Sourcing.findByTargetJobCategory", query = "SELECT s FROM Sourcing s WHERE s.targetJobCategory = :targetJobCategory"),
    @NamedQuery(name = "Sourcing.findByDateContacted", query = "SELECT s FROM Sourcing s WHERE s.dateContacted = :dateContacted"),
    @NamedQuery(name = "Sourcing.findBySource", query = "SELECT s FROM Sourcing s WHERE s.source = :source"),
    @NamedQuery(name = "Sourcing.findBySourcer", query = "SELECT s FROM Sourcing s WHERE s.sourcer = :sourcer"),
    @NamedQuery(name = "Sourcing.findByContactedBy", query = "SELECT s FROM Sourcing s WHERE s.contactedBy = :contactedBy"),
    @NamedQuery(name = "Sourcing.findByInterviewer", query = "SELECT s FROM Sourcing s WHERE s.interviewer = :interviewer"),
    @NamedQuery(name = "Sourcing.findByDateOfInterview", query = "SELECT s FROM Sourcing s WHERE s.dateOfInterview = :dateOfInterview"),
    @NamedQuery(name = "Sourcing.findByMoDateAcceptedInLinkedin", query = "SELECT s FROM Sourcing s WHERE s.moDateAcceptedInLinkedin = :moDateAcceptedInLinkedin"),
    @NamedQuery(name = "Sourcing.findByLastUpdatedDt", query = "SELECT s FROM Sourcing s WHERE s.lastUpdatedDt = :lastUpdatedDt"),
    @NamedQuery(name = "Sourcing.findByLastUpdatedBy", query = "SELECT s FROM Sourcing s WHERE s.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "Sourcing.findByComments", query = "SELECT s FROM Sourcing s WHERE s.comments = :comments")})
public class Sourcing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsourcing_campaigne")
    private Integer idsourcingCampaigne;
    @Size(max = 250)
    @Column(name = "Title")
    private String title;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 250)
    @Column(name = "TargetJobTitle")
    private String targetJobTitle;
    @Size(max = 250)
    @Column(name = "TargetJobCategory")
    private String targetJobCategory;
    @Column(name = "DateContacted")
    @Temporal(TemporalType.DATE)
    private Date dateContacted;
    @Size(max = 250)
    @Column(name = "Source")
    private String source;
    @Size(max = 250)
    @Column(name = "Sourcer")
    private String sourcer;
    @Size(max = 250)
    @Column(name = "ContactedBy")
    private String contactedBy;
    @Size(max = 250)
    @Column(name = "Interviewer")
    private String interviewer;
    @Column(name = "DateOfInterview")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfInterview;
    @Column(name = "mo_DateAcceptedInLinkedin")
    @Temporal(TemporalType.DATE)
    private Date moDateAcceptedInLinkedin;
    @Column(name = "last_updated_dt")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDt;
    @Size(max = 45)
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Size(max = 255)
    @Column(name = "comments")
    private String comments;

    public Sourcing() {
    }

    public Sourcing(Integer idsourcingCampaigne) {
        this.idsourcingCampaigne = idsourcingCampaigne;
    }

    public Integer getIdsourcingCampaigne() {
        return idsourcingCampaigne;
    }

    public void setIdsourcingCampaigne(Integer idsourcingCampaigne) {
        this.idsourcingCampaigne = idsourcingCampaigne;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetJobTitle() {
        return targetJobTitle;
    }

    public void setTargetJobTitle(String targetJobTitle) {
        this.targetJobTitle = targetJobTitle;
    }

    public String getTargetJobCategory() {
        return targetJobCategory;
    }

    public void setTargetJobCategory(String targetJobCategory) {
        this.targetJobCategory = targetJobCategory;
    }

    public Date getDateContacted() {
        return dateContacted;
    }

    public void setDateContacted(Date dateContacted) {
        this.dateContacted = dateContacted;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourcer() {
        return sourcer;
    }

    public void setSourcer(String sourcer) {
        this.sourcer = sourcer;
    }

    public String getContactedBy() {
        return contactedBy;
    }

    public void setContactedBy(String contactedBy) {
        this.contactedBy = contactedBy;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public Date getDateOfInterview() {
        return dateOfInterview;
    }

    public void setDateOfInterview(Date dateOfInterview) {
        this.dateOfInterview = dateOfInterview;
    }

    public Date getMoDateAcceptedInLinkedin() {
        return moDateAcceptedInLinkedin;
    }

    public void setMoDateAcceptedInLinkedin(Date moDateAcceptedInLinkedin) {
        this.moDateAcceptedInLinkedin = moDateAcceptedInLinkedin;
    }

    public Date getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(Date lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsourcingCampaigne != null ? idsourcingCampaigne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sourcing)) {
            return false;
        }
        Sourcing other = (Sourcing) object;
        if ((this.idsourcingCampaigne == null && other.idsourcingCampaigne != null) || (this.idsourcingCampaigne != null && !this.idsourcingCampaigne.equals(other.idsourcingCampaigne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Sourcing[ idsourcingCampaigne=" + idsourcingCampaigne + " ]";
    }
    
}
