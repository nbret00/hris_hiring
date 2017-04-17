/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.Company;
import com.nino.app.hrishiring.Endorsement;
import com.nino.app.hrishiring.Job;
import com.nino.app.hrishiring.Person;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Stateless
@Path("endorsements")
public class EndorsementRestService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EndorsementRestService() {

    }

    @GET
    @Path("{cid}/{jid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Endorsement> getEndorsementByCompany(@PathParam("cid") int cid, @PathParam("jid") int jib) {

        Company c = new Company();
        c.setIdclient(cid);

        Job j = new Job();
        j.setIdjobpk(jib);

        List<Endorsement> acc = (List<Endorsement>) em.createQuery("SELECT e FROM Endorsement e WHERE e.companyIdclient = :companyIdclient AND e.jobIdjobpk = :jobIdjobpk")
                .setParameter("companyIdclient", c)
                .setParameter("jobIdjobpk", j)
                .getResultList();

        System.out.println("acc size: " + acc.size());
        return acc;
    }
    
        @GET
    @Path("byperson/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Endorsement> getEndorsementByPersonID(@PathParam("id") int id) {

        Person p = new Person();
        p.setIdPerson(id);
       
        List<Endorsement> acc = (List<Endorsement>) em.createQuery("SELECT e FROM Endorsement e WHERE e.personidPerson = :personidPerson")
                .setParameter("personidPerson", p)
                .getResultList();

        System.out.println("acc size: " + acc.size());
        return acc;
    }

    @PUT
    @Path("save")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response newEndorsement(Endorsement e) {
        try {
            //System.out.println("createNew " + jobqualification.getJobTitle());
            e.setStatus("Matched");
            Date d = new Date();
            e.setEndorsedDate(d);
            em.persist(e);
            em.flush();
            System.out.println("Save Endorsement: " + e.getIdendorsement());
            return Response.ok(e).build();
        } catch (Exception em) {
            em.printStackTrace();
            return Response.ok(em.getMessage()).build();
        }

    }

    @PUT
    @Path("saveUnique")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response newEndorsementUnique(Endorsement e) {
        String emsg = "";
        try {
            System.out.print("newEndorsementUnique");
            Company c = e.getCompanyIdclient();
            Job j = e.getJobIdjobpk();
            Person p = e.getPersonidPerson();
            //System.out.println("createNew " + jobqualification.getJobTitle());
            List<Endorsement> acc = (List<Endorsement>) em.createQuery("SELECT e FROM Endorsement e WHERE e.companyIdclient = :companyIdclient AND e.jobIdjobpk = :jobIdjobpk AND e.personidPerson = :personidPerson")
                    .setParameter("companyIdclient", c)
                    .setParameter("jobIdjobpk", j)
                    .setParameter("personidPerson", p)
                    .getResultList();
            
            Company ic = em.find(Company.class, c.getIdclient());
            if (null == ic.getIdclient()){
                System.out.println("Company does not exist");
                return Response.ok(e).build();//just return without ID
            }
            
            Job ij = em.find(Job.class, j.getIdjobpk());
            if (null == ij.getIdjobpk()){
                System.out.println("Job does not exist");
                return Response.ok(e).build();//just return without ID
            }
            
            System.out.println("acc+"+acc.size());
            if (acc.size() == 0) {
                e.setStatus("Matched");
                Date d = new Date();
                e.setEndorsedDate(d);
                em.persist(e);
                em.flush();
               
                System.out.println("Save Endorsement: " + e.getIdendorsement());
            }
            //will return no pk id if not created
            return Response.ok(e).build();
        } catch (Exception em) {
            em.printStackTrace();
            System.out.println("responding okayyy");
            return Response.ok(e).build();
        }
    }

}
