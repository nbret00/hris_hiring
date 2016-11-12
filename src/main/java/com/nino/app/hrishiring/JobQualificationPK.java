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
public class JobQualificationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_job_qualification")
    private int idJobQualification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "person_idPerson")
    private int personidPerson;

    public JobQualificationPK() {
    }

    public JobQualificationPK(int idJobQualification, int personidPerson) {
        this.idJobQualification = idJobQualification;
        this.personidPerson = personidPerson;
    }

    public int getIdJobQualification() {
        return idJobQualification;
    }

    public void setIdJobQualification(int idJobQualification) {
        this.idJobQualification = idJobQualification;
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
        hash += (int) idJobQualification;
        hash += (int) personidPerson;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobQualificationPK)) {
            return false;
        }
        JobQualificationPK other = (JobQualificationPK) object;
        if (this.idJobQualification != other.idJobQualification) {
            return false;
        }
        if (this.personidPerson != other.personidPerson) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.JobQualificationPK[ idJobQualification=" + idJobQualification + ", personidPerson=" + personidPerson + " ]";
    }
    
}
