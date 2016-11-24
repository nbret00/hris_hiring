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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "industries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Industries.findAll", query = "SELECT i FROM Industries i"),
    @NamedQuery(name = "Industries.findByIdindustries", query = "SELECT i FROM Industries i WHERE i.idindustries = :idindustries"),
    @NamedQuery(name = "Industries.findByParentId", query = "SELECT i FROM Industries i WHERE i.parentId = :parentId"),
    @NamedQuery(name = "Industries.findByName", query = "SELECT i FROM Industries i WHERE i.name = :name")})
public class Industries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idindustries")
    private Integer idindustries;
    @Column(name = "parent_id")
    private Integer parentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;

    public Industries() {
    }

    public Industries(Integer idindustries) {
        this.idindustries = idindustries;
    }

    public Industries(Integer idindustries, String name) {
        this.idindustries = idindustries;
        this.name = name;
    }

    public Integer getIdindustries() {
        return idindustries;
    }

    public void setIdindustries(Integer idindustries) {
        this.idindustries = idindustries;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindustries != null ? idindustries.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Industries)) {
            return false;
        }
        Industries other = (Industries) object;
        if ((this.idindustries == null && other.idindustries != null) || (this.idindustries != null && !this.idindustries.equals(other.idindustries))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.Industries[ idindustries=" + idindustries + " ]";
    }
    
}
