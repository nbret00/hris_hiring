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
@Table(name = "endorsement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endorsement.findAll", query = "SELECT e FROM Endorsement e"),
    @NamedQuery(name = "Endorsement.findByIdendorsement", query = "SELECT e FROM Endorsement e WHERE e.idendorsement = :idendorsement"),
    @NamedQuery(name = "Endorsement.findByEndorsedDate", query = "SELECT e FROM Endorsement e WHERE e.endorsedDate = :endorsedDate"),
    @NamedQuery(name = "Endorsement.findByPriority", query = "SELECT e FROM Endorsement e WHERE e.priority = :priority"),
    @NamedQuery(name = "Endorsement.findByStatus", query = "SELECT e FROM Endorsement e WHERE e.status = :status"),
    @NamedQuery(name = "Endorsement.findByHRInterviewDate", query = "SELECT e FROM Endorsement e WHERE e.hRInterviewDate = :hRInterviewDate"),
    @NamedQuery(name = "Endorsement.findByHRInterviewResult", query = "SELECT e FROM Endorsement e WHERE e.hRInterviewResult = :hRInterviewResult"),
    @NamedQuery(name = "Endorsement.findByFinalInterviewDate", query = "SELECT e FROM Endorsement e WHERE e.finalInterviewDate = :finalInterviewDate"),
    @NamedQuery(name = "Endorsement.findByFinalinterviewResult", query = "SELECT e FROM Endorsement e WHERE e.finalinterviewResult = :finalinterviewResult"),
    @NamedQuery(name = "Endorsement.findByJobOfferDate", query = "SELECT e FROM Endorsement e WHERE e.jobOfferDate = :jobOfferDate"),
    @NamedQuery(name = "Endorsement.findByJobOfferStatus", query = "SELECT e FROM Endorsement e WHERE e.jobOfferStatus = :jobOfferStatus"),
    @NamedQuery(name = "Endorsement.findByStartdate", query = "SELECT e FROM Endorsement e WHERE e.startdate = :startdate")})
public class Endorsement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idendorsement")
    private Integer idendorsement;
    @Column(name = "EndorsedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endorsedDate;
    @Column(name = "Priority")
    private Integer priority;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Column(name = "HRInterviewDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hRInterviewDate;
    @Size(max = 250)
    @Column(name = "HRInterviewResult")
    private String hRInterviewResult;
    @Column(name = "FinalInterviewDate")
    @Temporal(TemporalType.DATE)
    private Date finalInterviewDate;
    @Size(max = 250)
    @Column(name = "FinalinterviewResult")
    private String finalinterviewResult;
    @Column(name = "JobOfferDate")
    @Temporal(TemporalType.DATE)
    private Date jobOfferDate;
    @Size(max = 45)
    @Column(name = "JobOfferStatus")
    private String jobOfferStatus;
    @Column(name = "Startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @JoinColumn(name = "company_idclient", referencedColumnName = "idclient")
    @ManyToOne(optional = false)
    private Company companyIdclient;
    @JoinColumn(name = "job_idjobpk", referencedColumnName = "idjobpk")
    @ManyToOne(optional = false)
    private Job jobIdjobpk;
    @JoinColumn(name = "person_idPerson", referencedColumnName = "idPerson")
    @ManyToOne(optional = false)
    private Person personidPerson;

    public Endorsement() {
    }

    public Endorsement(Integer idendorsement) {
        this.idendorsement = idendorsement;
    }

    public Integer getIdendorsement() {
        return idendorsement;
    }

    public void setIdendorsement(Integer idendorsement) {
        this.idendorsement = idendorsement;
    }

    public Date getEndorsedDate() {
        return endorsedDate;
    }

    public void setEndorsedDate(Date endorsedDate) {
        this.endorsedDate = endorsedDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHRInterviewDate() {
        return hRInterviewDate;
    }

    public void setHRInterviewDate(Date hRInterviewDate) {
        this.hRInterviewDate = hRInterviewDate;
    }

    public String getHRInterviewResult() {
        return hRInterviewResult;
    }

    public void setHRInterviewResult(String hRInterviewResult) {
        this.hRInterviewResult = hRInterviewResult;
    }

    public Date getFinalInterviewDate() {
        return finalInterviewDate;
    }

    public void setFinalInterviewDate(Date finalInterviewDate) {
        this.finalInterviewDate = finalInterviewDate;
    }

    public String getFinalinterviewResult() {
        return finalinterviewResult;
    }

    public void setFinalinterviewResult(String finalinterviewResult) {
        this.finalinterviewResult = finalinterviewResult;
    }

    public Date getJobOfferDate() {
        return jobOfferDate;
    }

    public void setJobOfferDate(Date jobOfferDate) {
        this.jobOfferDate = jobOfferDate;
    }

    public String getJobOfferStatus() {
        return jobOfferStatus;
    }

    public void setJobOfferStatus(String jobOfferStatus) {
        this.jobOfferStatus = jobOfferStatus;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Company getCompanyIdclient() {
        return companyIdclient;
    }

    public void setCompanyIdclient(Company companyIdclient) {
        this.companyIdclient = companyIdclient;
    }

    public Job getJobIdjobpk() {
        return jobIdjobpk;
    }

    public void setJobIdjobpk(Job jobIdjobpk) {
        this.jobIdjobpk = jobIdjobpk;
    }

    public Person getPersonidPerson() {
        return personidPerson;
    }

    public void setPersonidPerson(Person personidPerson) {
        this.personidPerson = personidPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idendorsement != null ? idendorsement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endorsement)) {
            return false;
        }
        Endorsement other = (Endorsement) object;
        if ((this.idendorsement == null && other.idendorsement != null) || (this.idendorsement != null && !this.idendorsement.equals(other.idendorsement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Endorsement[ idendorsement=" + idendorsement + " ]";
    }
    
}
