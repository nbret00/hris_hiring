/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nbret00
 */
@Entity
@Table(name = "nsb_activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NsbActivity.findAll", query = "SELECT n FROM NsbActivity n"),
    @NamedQuery(name = "NsbActivity.findByIdnsbActivity", query = "SELECT n FROM NsbActivity n WHERE n.idnsbActivity = :idnsbActivity"),
    @NamedQuery(name = "NsbActivity.findByName", query = "SELECT n FROM NsbActivity n WHERE n.name = :name"),
    @NamedQuery(name = "NsbActivity.findByOutput", query = "SELECT n FROM NsbActivity n WHERE n.output = :output"),
    @NamedQuery(name = "NsbActivity.findByAction", query = "SELECT n FROM NsbActivity n WHERE n.action = :action"),
    @NamedQuery(name = "NsbActivity.findByActionName", query = "SELECT n FROM NsbActivity n WHERE n.actionName = :actionName"),
    @NamedQuery(name = "NsbActivity.findByOutputName", query = "SELECT n FROM NsbActivity n WHERE n.outputName = :outputName"),
    @NamedQuery(name = "NsbActivity.findByStatus", query = "SELECT n FROM NsbActivity n WHERE n.status = :status"),
    @NamedQuery(name = "NsbActivity.findByDateCreated", query = "SELECT n FROM NsbActivity n WHERE n.dateCreated = :dateCreated"),
    @NamedQuery(name = "NsbActivity.findByPerformedDate", query = "SELECT n FROM NsbActivity n WHERE n.performedDate = :performedDate"),
    @NamedQuery(name = "NsbActivity.findByDateFinished", query = "SELECT n FROM NsbActivity n WHERE n.dateFinished = :dateFinished"),
    @NamedQuery(name = "NsbActivity.findByPlannedDateTime", query = "SELECT n FROM NsbActivity n WHERE n.plannedDateTime = :plannedDateTime"),
    @NamedQuery(name = "NsbActivity.findByLocation", query = "SELECT n FROM NsbActivity n WHERE n.location = :location"),
    @NamedQuery(name = "NsbActivity.findByCreatedBy", query = "SELECT n FROM NsbActivity n WHERE n.createdBy = :createdBy"),
    @NamedQuery(name = "NsbActivity.findByPerformedBy", query = "SELECT n FROM NsbActivity n WHERE n.performedBy = :performedBy"),
    @NamedQuery(name = "NsbActivity.findByRemarks", query = "SELECT n FROM NsbActivity n WHERE n.remarks = :remarks")})
public class NsbActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnsb_activity")
    private Integer idnsbActivity;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Output")
    private String output;
    @Size(max = 45)
    @Column(name = "Action")
    private String action;
    @Size(max = 45)
    @Column(name = "ActionName")
    private String actionName;
    @Size(max = 45)
    @Column(name = "OutputName")
    private String outputName;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "DateCreated")
    private String dateCreated;
    @Size(max = 45)
    @Column(name = "PerformedDate")
    private String performedDate;
    @Size(max = 45)
    @Column(name = "DateFinished")
    private String dateFinished;
    @Size(max = 45)
    @Column(name = "PlannedDateTime")
    private String plannedDateTime;
    @Size(max = 45)
    @Column(name = "Location")
    private String location;
    @Size(max = 45)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Size(max = 45)
    @Column(name = "PerformedBy")
    private String performedBy;
    @Size(max = 45)
    @Column(name = "Remarks")
    private String remarks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nsbActivityIdnsbActivity")
    private Collection<NsbActivitySuccessCriteria> nsbActivitySuccessCriteriaCollection;

    public NsbActivity() {
    }

    public NsbActivity(Integer idnsbActivity) {
        this.idnsbActivity = idnsbActivity;
    }

    public Integer getIdnsbActivity() {
        return idnsbActivity;
    }

    public void setIdnsbActivity(Integer idnsbActivity) {
        this.idnsbActivity = idnsbActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(String performedDate) {
        this.performedDate = performedDate;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(String plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @XmlTransient
    public Collection<NsbActivitySuccessCriteria> getNsbActivitySuccessCriteriaCollection() {
        return nsbActivitySuccessCriteriaCollection;
    }

    public void setNsbActivitySuccessCriteriaCollection(Collection<NsbActivitySuccessCriteria> nsbActivitySuccessCriteriaCollection) {
        this.nsbActivitySuccessCriteriaCollection = nsbActivitySuccessCriteriaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnsbActivity != null ? idnsbActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NsbActivity)) {
            return false;
        }
        NsbActivity other = (NsbActivity) object;
        if ((this.idnsbActivity == null && other.idnsbActivity != null) || (this.idnsbActivity != null && !this.idnsbActivity.equals(other.idnsbActivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nino.app.hrishiring.NsbActivity[ idnsbActivity=" + idnsbActivity + " ]";
    }
    
}
