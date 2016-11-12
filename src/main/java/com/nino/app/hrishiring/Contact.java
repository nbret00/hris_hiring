/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findByIdcontact", query = "SELECT c FROM Contact c WHERE c.contactPK.idcontact = :idcontact"),
    @NamedQuery(name = "Contact.findByIdPerson", query = "SELECT c FROM Contact c WHERE c.idPerson = :idPerson"),
    @NamedQuery(name = "Contact.findByTelNum1", query = "SELECT c FROM Contact c WHERE c.telNum1 = :telNum1"),
    @NamedQuery(name = "Contact.findByContactNum", query = "SELECT c FROM Contact c WHERE c.contactNum = :contactNum"),
    @NamedQuery(name = "Contact.findByCellphoneNum", query = "SELECT c FROM Contact c WHERE c.cellphoneNum = :cellphoneNum"),
    @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
    @NamedQuery(name = "Contact.findByAddress", query = "SELECT c FROM Contact c WHERE c.address = :address"),
    @NamedQuery(name = "Contact.findByCity", query = "SELECT c FROM Contact c WHERE c.city = :city"),
    @NamedQuery(name = "Contact.findByCountry", query = "SELECT c FROM Contact c WHERE c.country = :country"),
    @NamedQuery(name = "Contact.findByPersonidPerson", query = "SELECT c FROM Contact c WHERE c.contactPK.personidPerson = :personidPerson")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContactPK contactPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPerson")
    private int idPerson;
    @Size(max = 45)
    @Column(name = "TelNum1")
    private String telNum1;
    @Size(max = 45)
    @Column(name = "ContactNum")
    private String contactNum;
    @Size(max = 45)
    @Column(name = "CellphoneNum")
    private String cellphoneNum;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "Email")
    private String email;
    @Size(max = 45)
    @Column(name = "Address")
    private String address;
    @Size(max = 45)
    @Column(name = "City")
    private String city;
    @Size(max = 45)
    @Column(name = "Country")
    private String country;
    @JoinColumn(name = "person_idPerson", referencedColumnName = "idPerson", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;

    public Contact() {
    }

    public Contact(ContactPK contactPK) {
        this.contactPK = contactPK;
    }

    public Contact(ContactPK contactPK, int idPerson) {
        this.contactPK = contactPK;
        this.idPerson = idPerson;
    }

    public Contact(int idcontact, int personidPerson) {
        this.contactPK = new ContactPK(idcontact, personidPerson);
    }

    public ContactPK getContactPK() {
        return contactPK;
    }

    public void setContactPK(ContactPK contactPK) {
        this.contactPK = contactPK;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getTelNum1() {
        return telNum1;
    }

    public void setTelNum1(String telNum1) {
        this.telNum1 = telNum1;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getCellphoneNum() {
        return cellphoneNum;
    }

    public void setCellphoneNum(String cellphoneNum) {
        this.cellphoneNum = cellphoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactPK != null ? contactPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contactPK == null && other.contactPK != null) || (this.contactPK != null && !this.contactPK.equals(other.contactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Contact[ contactPK=" + contactPK + " ]";
    }
    
}
