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
@Table(name = "nsb_activity_success_criteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivitySuccessCriteria.findAll", query = "SELECT n FROM NsbActivitySuccessCriteria n"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByIdnsbActivityKpi", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.idnsbActivityKpi = :idnsbActivityKpi"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByFormula", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.formula = :formula"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByComFormula", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.comFormula = :comFormula"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByDescription", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.description = :description"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByStatus", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.status = :status"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByComObjectId", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.comObjectId = :comObjectId"),
    @NamedQuery(name = "NsbActivitySuccessCriteria.findByComObjectType", query = "SELECT n FROM NsbActivitySuccessCriteria n WHERE n.comObjectType = :comObjectType")})
public class NsbActivitySuccessCriteria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnsb_activity_kpi")
    private Integer idnsbActivityKpi;
    @Size(max = 45)
    @Column(name = "formula")
    private String formula;
    @Size(max = 45)
    @Column(name = "com_formula")
    private String comFormula;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 45)
    @Column(name = "com_object_id")
    private String comObjectId;
    @Size(max = 45)
    @Column(name = "com_object_type")
    private String comObjectType;
    @JoinColumn(name = "nsb_activity_idnsb_activity", referencedColumnName = "idnsb_activity")
    @ManyToOne(optional = false)
    private NsbActivity nsbActivityIdnsbActivity;

    public NsbActivitySuccessCriteria() {
    }

    public NsbActivitySuccessCriteria(Integer idnsbActivityKpi) {
        this.idnsbActivityKpi = idnsbActivityKpi;
    }

    public Integer getIdnsbActivityKpi() {
        return idnsbActivityKpi;
    }

    public void setIdnsbActivityKpi(Integer idnsbActivityKpi) {
        this.idnsbActivityKpi = idnsbActivityKpi;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getComFormula() {
        return comFormula;
    }

    public void setComFormula(String comFormula) {
        this.comFormula = comFormula;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComObjectId() {
        return comObjectId;
    }

    public void setComObjectId(String comObjectId) {
        this.comObjectId = comObjectId;
    }

    public String getComObjectType() {
        return comObjectType;
    }

    public void setComObjectType(String comObjectType) {
        this.comObjectType = comObjectType;
    }

    public NsbActivity getNsbActivityIdnsbActivity() {
        return nsbActivityIdnsbActivity;
    }

    public void setNsbActivityIdnsbActivity(NsbActivity nsbActivityIdnsbActivity) {
        this.nsbActivityIdnsbActivity = nsbActivityIdnsbActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnsbActivityKpi != null ? idnsbActivityKpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivitySuccessCriteria)) {
            return false;
        }
        NsbActivitySuccessCriteria other = (NsbActivitySuccessCriteria) object;
        if ((this.idnsbActivityKpi == null && other.idnsbActivityKpi != null) || (this.idnsbActivityKpi != null && !this.idnsbActivityKpi.equals(other.idnsbActivityKpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivitySuccessCriteria[ idnsbActivityKpi=" + idnsbActivityKpi + " ]";
    }
    
}
