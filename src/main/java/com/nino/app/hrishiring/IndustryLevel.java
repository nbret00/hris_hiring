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
@Table(name = "industry_level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndustryLevel.findAll", query = "SELECT i FROM IndustryLevel i"),
    @NamedQuery(name = "IndustryLevel.findByIdindustryLevel", query = "SELECT i FROM IndustryLevel i WHERE i.idindustryLevel = :idindustryLevel"),
    @NamedQuery(name = "IndustryLevel.findByName", query = "SELECT i FROM IndustryLevel i WHERE i.name = :name"),
    @NamedQuery(name = "IndustryLevel.findByDescription", query = "SELECT i FROM IndustryLevel i WHERE i.description = :description")})
public class IndustryLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idindustry_level")
    private Integer idindustryLevel;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Description")
    private String description;

    public IndustryLevel() {
    }

    public IndustryLevel(Integer idindustryLevel) {
        this.idindustryLevel = idindustryLevel;
    }

    public Integer getIdindustryLevel() {
        return idindustryLevel;
    }

    public void setIdindustryLevel(Integer idindustryLevel) {
        this.idindustryLevel = idindustryLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindustryLevel != null ? idindustryLevel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryLevel)) {
            return false;
        }
        IndustryLevel other = (IndustryLevel) object;
        if ((this.idindustryLevel == null && other.idindustryLevel != null) || (this.idindustryLevel != null && !this.idindustryLevel.equals(other.idindustryLevel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.IndustryLevel[ idindustryLevel=" + idindustryLevel + " ]";
    }
    
}
