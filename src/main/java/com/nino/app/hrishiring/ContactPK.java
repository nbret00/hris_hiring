/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nbret00
 */
@Embeddable
public class ContactPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idcontact")
    private int idcontact;
    @Basic(optional = false)
    @NotNull
    @Column(name = "person_idPerson")
    private int personidPerson;

    public ContactPK() {
    }

    public ContactPK(int idcontact, int personidPerson) {
        this.idcontact = idcontact;
        this.personidPerson = personidPerson;
    }

    public int getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(int idcontact) {
        this.idcontact = idcontact;
    }

    public int getPersonidPerson() {
        return personidPerson;
    }

    public void setPersonidPerson(int personidPerson) {
        this.personidPerson = personidPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcontact;
        hash += (int) personidPerson;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactPK)) {
            return false;
        }
        ContactPK other = (ContactPK) object;
        if (this.idcontact != other.idcontact) {
            return false;
        }
        if (this.personidPerson != other.personidPerson) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.ContactPK[ idcontact=" + idcontact + ", personidPerson=" + personidPerson + " ]";
    }
    
}
