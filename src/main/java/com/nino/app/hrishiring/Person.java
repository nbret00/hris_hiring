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
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
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
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Person.findByGender", query = "SELECT p FROM Person p WHERE p.gender = :gender"),
    @NamedQuery(name = "Person.findByLastUpdateDate", query = "SELECT p FROM Person p WHERE p.lastUpdateDate = :lastUpdateDate"),
    @NamedQuery(name = "Person.findByLastUpdatePersonID", query = "SELECT p FROM Person p WHERE p.lastUpdatePersonID = :lastUpdatePersonID")})


public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerson")
    private Integer idPerson;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 10)
    @Column(name = "Gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastUpdatePersonID")
    private int lastUpdatePersonID;
    @OneToMany(mappedBy = "personidPerson")
    private Collection<HrisAccount> hrisAccountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personidPerson")
    private Collection<Endorsement> endorsementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personidPerson")
    private Collection<NsbPersonActivities> nsbPersonActivitiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<JobQualification> jobQualificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personidPerson")
    private Collection<Contact> contactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Personstagestatus> personstagestatusCollection;

    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Person(Integer idPerson, Date lastUpdateDate, int lastUpdatePersonID) {
        this.idPerson = idPerson;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatePersonID = lastUpdatePersonID;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getLastUpdatePersonID() {
        return lastUpdatePersonID;
    }

    public void setLastUpdatePersonID(int lastUpdatePersonID) {
        this.lastUpdatePersonID = lastUpdatePersonID;
    }

    @XmlTransient
    public Collection<HrisAccount> getHrisAccountCollection() {
        return hrisAccountCollection;
    }

    public void setHrisAccountCollection(Collection<HrisAccount> hrisAccountCollection) {
        this.hrisAccountCollection = hrisAccountCollection;
    }

    @XmlTransient
    public Collection<Endorsement> getEndorsementCollection() {
        return endorsementCollection;
    }

    public void setEndorsementCollection(Collection<Endorsement> endorsementCollection) {
        this.endorsementCollection = endorsementCollection;
    }

    @XmlTransient
    public Collection<NsbPersonActivities> getNsbPersonActivitiesCollection() {
        return nsbPersonActivitiesCollection;
    }

    public void setNsbPersonActivitiesCollection(Collection<NsbPersonActivities> nsbPersonActivitiesCollection) {
        this.nsbPersonActivitiesCollection = nsbPersonActivitiesCollection;
    }

    @XmlTransient
    public Collection<JobQualification> getJobQualificationCollection() {
        return jobQualificationCollection;
    }

    public void setJobQualificationCollection(Collection<JobQualification> jobQualificationCollection) {
        this.jobQualificationCollection = jobQualificationCollection;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
    }

    @XmlTransient
    public Collection<Personstagestatus> getPersonstagestatusCollection() {
        return personstagestatusCollection;
    }

    public void setPersonstagestatusCollection(Collection<Personstagestatus> personstagestatusCollection) {
        this.personstagestatusCollection = personstagestatusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Person[ idPerson=" + idPerson + " ]";
    }

}
