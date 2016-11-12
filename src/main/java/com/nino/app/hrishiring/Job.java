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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByIdjob", query = "SELECT j FROM Job j WHERE j.jobPK.idjob = :idjob"),
    @NamedQuery(name = "Job.findByTitle", query = "SELECT j FROM Job j WHERE j.title = :title"),
    @NamedQuery(name = "Job.findByDescription", query = "SELECT j FROM Job j WHERE j.description = :description"),
    @NamedQuery(name = "Job.findByDescriptionLong", query = "SELECT j FROM Job j WHERE j.descriptionLong = :descriptionLong"),
    @NamedQuery(name = "Job.findByLocation", query = "SELECT j FROM Job j WHERE j.location = :location"),
    @NamedQuery(name = "Job.findByStatus", query = "SELECT j FROM Job j WHERE j.status = :status"),
    @NamedQuery(name = "Job.findByRemarks", query = "SELECT j FROM Job j WHERE j.remarks = :remarks"),
    @NamedQuery(name = "Job.findByClientIdclient", query = "SELECT j FROM Job j WHERE j.jobPK.clientIdclient = :clientIdclient"),
    @NamedQuery(name = "Job.findByQualifications", query = "SELECT j FROM Job j WHERE j.qualifications = :qualifications"),
    @NamedQuery(name = "Job.findByResponsibility", query = "SELECT j FROM Job j WHERE j.responsibility = :responsibility"),
    @NamedQuery(name = "Job.findByJobPocIdjobPoc", query = "SELECT j FROM Job j WHERE j.jobPocIdjobPoc = :jobPocIdjobPoc"),
    @NamedQuery(name = "Job.findByDateRecieved", query = "SELECT j FROM Job j WHERE j.dateRecieved = :dateRecieved")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JobPK jobPK;
    @Size(max = 45)
    @Column(name = "Title")
    private String title;
    @Size(max = 45)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "DescriptionLong")
    private String descriptionLong;
    @Size(max = 45)
    @Column(name = "Location")
    private String location;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "Remarks")
    private String remarks;
    @Size(max = 45)
    @Column(name = "Qualifications")
    private String qualifications;
    @Size(max = 45)
    @Column(name = "Responsibility")
    private String responsibility;
    @Basic(optional = false)
    @NotNull
    @Column(name = "job_poc_idjob_poc")
    private int jobPocIdjobPoc;
    @Column(name = "DateRecieved")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRecieved;
    @OneToMany(mappedBy = "job")
    private Collection<Sourcing> sourcingCollection;
    @JoinColumn(name = "client_idclient", referencedColumnName = "idclient", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Company company;

    public Job() {
    }

    public Job(JobPK jobPK) {
        this.jobPK = jobPK;
    }

    public Job(JobPK jobPK, int jobPocIdjobPoc) {
        this.jobPK = jobPK;
        this.jobPocIdjobPoc = jobPocIdjobPoc;
    }

    public Job(int idjob, int clientIdclient) {
        this.jobPK = new JobPK(idjob, clientIdclient);
    }

    public JobPK getJobPK() {
        return jobPK;
    }

    public void setJobPK(JobPK jobPK) {
        this.jobPK = jobPK;
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

    public int getJobPocIdjobPoc() {
        return jobPocIdjobPoc;
    }

    public void setJobPocIdjobPoc(int jobPocIdjobPoc) {
        this.jobPocIdjobPoc = jobPocIdjobPoc;
    }

    public Date getDateRecieved() {
        return dateRecieved;
    }

    public void setDateRecieved(Date dateRecieved) {
        this.dateRecieved = dateRecieved;
    }

    @XmlTransient
    public Collection<Sourcing> getSourcingCollection() {
        return sourcingCollection;
    }

    public void setSourcingCollection(Collection<Sourcing> sourcingCollection) {
        this.sourcingCollection = sourcingCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobPK != null ? jobPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobPK == null && other.jobPK != null) || (this.jobPK != null && !this.jobPK.equals(other.jobPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Job[ jobPK=" + jobPK + " ]";
    }
    
}
