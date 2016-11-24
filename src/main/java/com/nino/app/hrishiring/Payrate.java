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
@Table(name = "payrate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payrate.findAll", query = "SELECT p FROM Payrate p"),
    @NamedQuery(name = "Payrate.findByIdpayrate", query = "SELECT p FROM Payrate p WHERE p.idpayrate = :idpayrate"),
    @NamedQuery(name = "Payrate.findByPayrateTxt", query = "SELECT p FROM Payrate p WHERE p.payrateTxt = :payrateTxt"),
    @NamedQuery(name = "Payrate.findByMin", query = "SELECT p FROM Payrate p WHERE p.min = :min"),
    @NamedQuery(name = "Payrate.findByMax", query = "SELECT p FROM Payrate p WHERE p.max = :max")})
public class Payrate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpayrate")
    private Integer idpayrate;
    @Size(max = 45)
    @Column(name = "payrate_txt")
    private String payrateTxt;
    @Size(max = 45)
    @Column(name = "min")
    private String min;
    @Size(max = 45)
    @Column(name = "max")
    private String max;

    public Payrate() {
    }

    public Payrate(Integer idpayrate) {
        this.idpayrate = idpayrate;
    }

    public Integer getIdpayrate() {
        return idpayrate;
    }

    public void setIdpayrate(Integer idpayrate) {
        this.idpayrate = idpayrate;
    }

    public String getPayrateTxt() {
        return payrateTxt;
    }

    public void setPayrateTxt(String payrateTxt) {
        this.payrateTxt = payrateTxt;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpayrate != null ? idpayrate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payrate)) {
            return false;
        }
        Payrate other = (Payrate) object;
        if ((this.idpayrate == null && other.idpayrate != null) || (this.idpayrate != null && !this.idpayrate.equals(other.idpayrate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Payrate[ idpayrate=" + idpayrate + " ]";
    }
    
}
