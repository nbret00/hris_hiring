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
public class PersonstagestatusPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPersonStageStatus")
    private int idPersonStageStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "person_idPerson")
    private int personidPerson;

    public PersonstagestatusPK() {
    }

    public PersonstagestatusPK(int idPersonStageStatus, int personidPerson) {
        this.idPersonStageStatus = idPersonStageStatus;
        this.personidPerson = personidPerson;
    }

    public int getIdPersonStageStatus() {
        return idPersonStageStatus;
    }

    public void setIdPersonStageStatus(int idPersonStageStatus) {
        this.idPersonStageStatus = idPersonStageStatus;
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
        hash += (int) idPersonStageStatus;
        hash += (int) personidPerson;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonstagestatusPK)) {
            return false;
        }
        PersonstagestatusPK other = (PersonstagestatusPK) object;
        if (this.idPersonStageStatus != other.idPersonStageStatus) {
            return false;
        }
        if (this.personidPerson != other.personidPerson) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.PersonstagestatusPK[ idPersonStageStatus=" + idPersonStageStatus + ", personidPerson=" + personidPerson + " ]";
    }
    
}
