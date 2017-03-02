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
import javax.persistence.Lob;
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
@Table(name = "personstagestatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personstagestatus.findAll", query = "SELECT p FROM Personstagestatus p"),
    @NamedQuery(name = "Personstagestatus.findByIdPersonStageStatus", query = "SELECT p FROM Personstagestatus p WHERE p.personstagestatusPK.idPersonStageStatus = :idPersonStageStatus"),
    @NamedQuery(name = "Personstagestatus.findByPersonidPerson", query = "SELECT p FROM Personstagestatus p WHERE p.personstagestatusPK.personidPerson = :personidPerson")})
public class Personstagestatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonstagestatusPK personstagestatusPK;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "Stage_idHRISStage", referencedColumnName = "idHRISStage")
    @ManyToOne
    private Stage stageidHRISStage;
    @JoinColumn(name = "StageStatus_idStageStatus", referencedColumnName = "idStageStatus")
    @ManyToOne
    private Stagestatus stageStatusidStageStatus;
    @JoinColumn(name = "person_idPerson", referencedColumnName = "idPerson", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;

    public Personstagestatus() {
    }

    public Personstagestatus(PersonstagestatusPK personstagestatusPK) {
        this.personstagestatusPK = personstagestatusPK;
    }

    public Personstagestatus(int idPersonStageStatus, int personidPerson) {
        this.personstagestatusPK = new PersonstagestatusPK(idPersonStageStatus, personidPerson);
    }

    public PersonstagestatusPK getPersonstagestatusPK() {
        return personstagestatusPK;
    }

    public void setPersonstagestatusPK(PersonstagestatusPK personstagestatusPK) {
        this.personstagestatusPK = personstagestatusPK;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Stage getStageidHRISStage() {
        return stageidHRISStage;
    }

    public void setStageidHRISStage(Stage stageidHRISStage) {
        this.stageidHRISStage = stageidHRISStage;
    }

    public Stagestatus getStageStatusidStageStatus() {
        return stageStatusidStageStatus;
    }

    public void setStageStatusidStageStatus(Stagestatus stageStatusidStageStatus) {
        this.stageStatusidStageStatus = stageStatusidStageStatus;
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
        hash += (personstagestatusPK != null ? personstagestatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personstagestatus)) {
            return false;
        }
        Personstagestatus other = (Personstagestatus) object;
        if ((this.personstagestatusPK == null && other.personstagestatusPK != null) || (this.personstagestatusPK != null && !this.personstagestatusPK.equals(other.personstagestatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Personstagestatus[ personstagestatusPK=" + personstagestatusPK + " ]";
    }
    
}
