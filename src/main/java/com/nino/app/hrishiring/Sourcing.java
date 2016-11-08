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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "Sourcing.findByDataContacted", query = "SELECT s FROM Sourcing s WHERE s.dataContacted = :dataContacted"),
    @NamedQuery(name = "Sourcing.findBySource", query = "SELECT s FROM Sourcing s WHERE s.source = :source"),
    @NamedQuery(name = "Sourcing.findBySourcer", query = "SELECT s FROM Sourcing s WHERE s.sourcer = :sourcer"),
    @NamedQuery(name = "Sourcing.findByContactedBy", query = "SELECT s FROM Sourcing s WHERE s.contactedBy = :contactedBy"),
    @NamedQuery(name = "Sourcing.findByInterviewer", query = "SELECT s FROM Sourcing s WHERE s.interviewer = :interviewer"),
    @NamedQuery(name = "Sourcing.findByDateOfInterview", query = "SELECT s FROM Sourcing s WHERE s.dateOfInterview = :dateOfInterview"),
    @NamedQuery(name = "Sourcing.findByMoDateAcceptedInLinkedin", query = "SELECT s FROM Sourcing s WHERE s.moDateAcceptedInLinkedin = :moDateAcceptedInLinkedin")})
public class Sourcing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsourcing_campaigne")
    private Integer idsourcingCampaigne;
    @Column(name = "Title")
    private Integer title;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "TargetJobTitle")
    private String targetJobTitle;
    @Size(max = 45)
    @Column(name = "TargetJobCategory")
    private String targetJobCategory;
    @Size(max = 45)
    @Column(name = "DataContacted")
    private String dataContacted;
    @Size(max = 45)
    @Column(name = "Source")
    private String source;
    @Size(max = 45)
    @Column(name = "Sourcer")
    private String sourcer;
    @Size(max = 45)
    @Column(name = "ContactedBy")
    private String contactedBy;
    @Size(max = 45)
    @Column(name = "Interviewer")
    private String interviewer;
    @Column(name = "DateOfInterview")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfInterview;
    @Column(name = "mo_DateAcceptedInLinkedin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date moDateAcceptedInLinkedin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourcingIdsourcingCampaigne")
    private Collection<Person> personCollection;
    @JoinColumns({
        @JoinColumn(name = "job_idjob", referencedColumnName = "idjob"),
        @JoinColumn(name = "job_client_idclient", referencedColumnName = "client_idclient")})
    @ManyToOne(optional = false)
    private Job job;

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

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
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

    public String getDataContacted() {
        return dataContacted;
    }

    public void setDataContacted(String dataContacted) {
        this.dataContacted = dataContacted;
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

    @XmlTransient
    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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
