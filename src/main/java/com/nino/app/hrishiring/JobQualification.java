/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "job_qualification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobQualification.findAll", query = "SELECT j FROM JobQualification j"),
    @NamedQuery(name = "JobQualification.findByIdJobQualification", query = "SELECT j FROM JobQualification j WHERE j.jobQualificationPK.idJobQualification = :idJobQualification"),
    @NamedQuery(name = "JobQualification.findByJobTitle", query = "SELECT j FROM JobQualification j WHERE j.jobTitle = :jobTitle"),
    @NamedQuery(name = "JobQualification.findBySkillsCategorymo", query = "SELECT j FROM JobQualification j WHERE j.skillsCategorymo = :skillsCategorymo"),
    @NamedQuery(name = "JobQualification.findByJoblevelmo", query = "SELECT j FROM JobQualification j WHERE j.joblevelmo = :joblevelmo"),
    @NamedQuery(name = "JobQualification.findByQualificationSummary", query = "SELECT j FROM JobQualification j WHERE j.qualificationSummary = :qualificationSummary"),
    @NamedQuery(name = "JobQualification.findByYrsOfExperience", query = "SELECT j FROM JobQualification j WHERE j.yrsOfExperience = :yrsOfExperience"),
    @NamedQuery(name = "JobQualification.findByCurrentSalary", query = "SELECT j FROM JobQualification j WHERE j.currentSalary = :currentSalary"),
    @NamedQuery(name = "JobQualification.findByTargetSalary", query = "SELECT j FROM JobQualification j WHERE j.targetSalary = :targetSalary"),
    @NamedQuery(name = "JobQualification.findByTargetPosition", query = "SELECT j FROM JobQualification j WHERE j.targetPosition = :targetPosition"),
    @NamedQuery(name = "JobQualification.findByPriority", query = "SELECT j FROM JobQualification j WHERE j.priority = :priority"),
    @NamedQuery(name = "JobQualification.findByPersonidPerson", query = "SELECT j FROM JobQualification j WHERE j.jobQualificationPK.personidPerson = :personidPerson"),
    @NamedQuery(name = "JobQualification.findBySkills", query = "SELECT j FROM JobQualification j WHERE j.skills = :skills"),
    @NamedQuery(name = "JobQualification.findBySearchText", query = "SELECT j FROM JobQualification j WHERE j.searchText = :searchText")})
public class JobQualification implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JobQualificationPK jobQualificationPK;
    @Size(max = 45)
    @Column(name = "JobTitle")
    private String jobTitle;
    @Size(max = 45)
    @Column(name = "SkillsCategory_mo")
    private String skillsCategorymo;
    @Size(max = 45)
    @Column(name = "Joblevel_mo")
    private String joblevelmo;
    @Size(max = 255)
    @Column(name = "QualificationSummary")
    private String qualificationSummary;
    @Size(max = 10)
    @Column(name = "YrsOfExperience")
    private String yrsOfExperience;
    @Size(max = 10)
    @Column(name = "CurrentSalary")
    private String currentSalary;
    @Size(max = 10)
    @Column(name = "TargetSalary")
    private String targetSalary;
    @Size(max = 45)
    @Column(name = "TargetPosition")
    private String targetPosition;
    @Size(max = 45)
    @Column(name = "Priority")
    private String priority;
    @Size(max = 255)
    @Column(name = "Skills")
    private String skills;
    @Size(max = 100)
    @Column(name = "SearchText")
    private String searchText;
    @JoinColumn(name = "person_idPerson", referencedColumnName = "idPerson", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;
    @JoinColumn(name = "payrate_idpayrate", referencedColumnName = "idpayrate")
    @ManyToOne(optional = false)
    private Payrate payrateIdpayrate;
    @JoinColumn(name = "industry_level_idindustry_level", referencedColumnName = "idindustry_level")
    @ManyToOne(optional = false)
    private IndustryLevel industryLevelIdindustryLevel;
    @JoinColumn(name = "industries_idindustries", referencedColumnName = "idindustries")
    @ManyToOne(optional = false)
    private Industries industriesIdindustries;

    public JobQualification() {
    }

    public JobQualification(JobQualificationPK jobQualificationPK) {
        this.jobQualificationPK = jobQualificationPK;
    }

    public JobQualification(int idJobQualification, int personidPerson) {
        this.jobQualificationPK = new JobQualificationPK(idJobQualification, personidPerson);
    }

    public JobQualificationPK getJobQualificationPK() {
        return jobQualificationPK;
    }

    public void setJobQualificationPK(JobQualificationPK jobQualificationPK) {
        this.jobQualificationPK = jobQualificationPK;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSkillsCategorymo() {
        return skillsCategorymo;
    }

    public void setSkillsCategorymo(String skillsCategorymo) {
        this.skillsCategorymo = skillsCategorymo;
    }

    public String getJoblevelmo() {
        return joblevelmo;
    }

    public void setJoblevelmo(String joblevelmo) {
        this.joblevelmo = joblevelmo;
    }

    public String getQualificationSummary() {
        return qualificationSummary;
    }

    public void setQualificationSummary(String qualificationSummary) {
        this.qualificationSummary = qualificationSummary;
    }

    public String getYrsOfExperience() {
        return yrsOfExperience;
    }

    public void setYrsOfExperience(String yrsOfExperience) {
        this.yrsOfExperience = yrsOfExperience;
    }

    public String getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(String currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getTargetSalary() {
        return targetSalary;
    }

    public void setTargetSalary(String targetSalary) {
        this.targetSalary = targetSalary;
    }

    public String getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(String targetPosition) {
        this.targetPosition = targetPosition;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Payrate getPayrateIdpayrate() {
        return payrateIdpayrate;
    }

    public void setPayrateIdpayrate(Payrate payrateIdpayrate) {
        this.payrateIdpayrate = payrateIdpayrate;
    }

    public IndustryLevel getIndustryLevelIdindustryLevel() {
        return industryLevelIdindustryLevel;
    }

    public void setIndustryLevelIdindustryLevel(IndustryLevel industryLevelIdindustryLevel) {
        this.industryLevelIdindustryLevel = industryLevelIdindustryLevel;
    }

    public Industries getIndustriesIdindustries() {
        return industriesIdindustries;
    }

    public void setIndustriesIdindustries(Industries industriesIdindustries) {
        this.industriesIdindustries = industriesIdindustries;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobQualificationPK != null ? jobQualificationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobQualification)) {
            return false;
        }
        JobQualification other = (JobQualification) object;
        if ((this.jobQualificationPK == null && other.jobQualificationPK != null) || (this.jobQualificationPK != null && !this.jobQualificationPK.equals(other.jobQualificationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.JobQualification[ jobQualificationPK=" + jobQualificationPK + " ]";
    }
    
}
