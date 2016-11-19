/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.JobQualification;
import com.nino.app.hrishiring.Person;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Path("jobqualification")
@Stateless
public class JobQualificationRestService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public JobQualificationRestService() {
    }

    @POST
    @Path("save/{personid}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(JobQualification jobqualification,
            @PathParam("personid") int personid)
    {
        try {
            System.out.println("createNew " + jobqualification.getJobTitle());
            Person p = em.find(Person.class, personid);
            jobqualification.setPersonidPerson(p);
            em.persist(jobqualification);
            em.flush();
            System.out.println("Create new job qualification with ID: "+jobqualification.getIdJobQualification());
            return Response.ok(jobqualification).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }

    @GET
    @Path("jobqualification/{id}")
    public Response getJobQualification(@PathParam("id") int id) {
        try {
            System.out.println("Job qualification search by person id");
            JobQualification jq = (JobQualification) em.createNamedQuery("JobQualification.findByPersonidPerson")
                    .setParameter("personidPerson", id)
                    .getSingleResult();
            System.out.println("Job qualification #:" + jq.getIdJobQualification());
            return Response.ok(jq).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("noresult").build();
        }
    }

    //@Path("")
}
