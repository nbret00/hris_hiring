/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author nbret00
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.nino.app.hrishiring.custom.service.AccountRESTService.class);
        resources.add(com.nino.app.hrishiring.custom.service.ActivitiesServices.class);
        resources.add(com.nino.app.hrishiring.custom.service.ApplicantRestservice.class);
        resources.add(com.nino.app.hrishiring.custom.service.BatchCreateService.class);
        resources.add(com.nino.app.hrishiring.custom.service.ContactRestService.class);
        resources.add(com.nino.app.hrishiring.custom.service.EndorsementRestService.class);
        resources.add(com.nino.app.hrishiring.custom.service.JobQualificationRestService.class);
        resources.add(com.nino.app.hrishiring.custom.service.PersonRestService.class);
        resources.add(com.nino.app.hrishiring.custom.service.ReportsRestService.class);
        resources.add(com.nino.app.hrishiring.custom.service.jobs.JobRestService.class);
        resources.add(com.nino.app.hrishiring.filter.NewJaxRsFilter.class);
        resources.add(com.nino.app.hrishiring.service.CompanyFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.EndorsementFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.HrisAccountFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.IndustriesFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.IndustryLevelFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.JobFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.JobQualificationFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.NsbActivityStatusTpFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.NsbActivityTpFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.PayrateFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.PersonFacadeREST.class);
        resources.add(com.nino.app.hrishiring.service.SourcingFacadeREST.class);
    }
    
}
