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
public class JobPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idjob")
    private int idjob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "client_idclient")
    private int clientIdclient;

    public JobPK() {
    }

    public JobPK(int idjob, int clientIdclient) {
        this.idjob = idjob;
        this.clientIdclient = clientIdclient;
    }

    public int getIdjob() {
        return idjob;
    }

    public void setIdjob(int idjob) {
        this.idjob = idjob;
    }

    public int getClientIdclient() {
        return clientIdclient;
    }

    public void setClientIdclient(int clientIdclient) {
        this.clientIdclient = clientIdclient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idjob;
        hash += (int) clientIdclient;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobPK)) {
            return false;
        }
        JobPK other = (JobPK) object;
        if (this.idjob != other.idjob) {
            return false;
        }
        if (this.clientIdclient != other.clientIdclient) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.JobPK[ idjob=" + idjob + ", clientIdclient=" + clientIdclient + " ]";
    }
    
}
