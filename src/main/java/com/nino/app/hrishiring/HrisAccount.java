/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "hris_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HrisAccount.findAll", query = "SELECT h FROM HrisAccount h"),
    @NamedQuery(name = "HrisAccount.findByIdhrisAccount", query = "SELECT h FROM HrisAccount h WHERE h.idhrisAccount = :idhrisAccount"),
    @NamedQuery(name = "HrisAccount.findByRole", query = "SELECT h FROM HrisAccount h WHERE h.role = :role"),
    @NamedQuery(name = "HrisAccount.findByProfile", query = "SELECT h FROM HrisAccount h WHERE h.profile = :profile"),
    @NamedQuery(name = "HrisAccount.findByUsername", query = "SELECT h FROM HrisAccount h WHERE h.username = :username"),
    @NamedQuery(name = "HrisAccount.findByPassword", query = "SELECT h FROM HrisAccount h WHERE h.password = :password"),
    @NamedQuery(name = "HrisAccount.findByEmail", query = "SELECT h FROM HrisAccount h WHERE h.email = :email"),
    @NamedQuery(name = "HrisAccount.findBySecretQuestion", query = "SELECT h FROM HrisAccount h WHERE h.secretQuestion = :secretQuestion"),
    @NamedQuery(name = "HrisAccount.findBySecretAnswer", query = "SELECT h FROM HrisAccount h WHERE h.secretAnswer = :secretAnswer")})
public class HrisAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhris_account")
    private Integer idhrisAccount;
    @Size(max = 40)
    @Column(name = "Role")
    private String role;
    @Size(max = 40)
    @Column(name = "Profile")
    private String profile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Email")
    private String email;
    @Size(max = 45)
    @Column(name = "SecretQuestion")
    private String secretQuestion;
    @Size(max = 10)
    @Column(name = "SecretAnswer")
    private String secretAnswer;
    @JoinColumn(name = "person_idPerson", referencedColumnName = "idPerson")
    @ManyToOne
    private Person personidPerson;

    public HrisAccount() {
    }

    public HrisAccount(Integer idhrisAccount) {
        this.idhrisAccount = idhrisAccount;
    }

    public HrisAccount(Integer idhrisAccount, String username, String password, String email) {
        this.idhrisAccount = idhrisAccount;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getIdhrisAccount() {
        return idhrisAccount;
    }

    public void setIdhrisAccount(Integer idhrisAccount) {
        this.idhrisAccount = idhrisAccount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
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
        hash += (idhrisAccount != null ? idhrisAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrisAccount)) {
            return false;
        }
        HrisAccount other = (HrisAccount) object;
        if ((this.idhrisAccount == null && other.idhrisAccount != null) || (this.idhrisAccount != null && !this.idhrisAccount.equals(other.idhrisAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.HrisAccount[ idhrisAccount=" + idhrisAccount + " ]";
    }
    
}
