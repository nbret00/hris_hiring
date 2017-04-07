/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service.jobs;

import com.nino.app.hrishiring.Company;
import com.nino.app.hrishiring.Job;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Path("jobs")
@Stateless
public class JobRestService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public JobRestService() {
    }

    @GET
    @Path("bycompany/{companyid}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Job> byCompany(@PathParam("companyid") int comapanyid) {

        Company c = em.find(Company.class, comapanyid);

        List<Job> jl = em.createQuery("SELECT j FROM Job j WHERE j.companyIdclient = :companyIdclient")
                .setParameter("companyIdclient", c)
                .getResultList();

        return jl;
    }


    @POST
    @Path("save")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response saveNew(Job job) {
        try {
            //System.out.println("createNew " + jobqualification.getJobTitle());
            em.persist(job);
            em.flush();
            System.out.println("Save Job: " + job.getIdjobpk());
            return Response.ok(job).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }

    @POST
    @Path("update/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Integer id, Job job) {
        try {
            //System.out.println("createNew " + jobqualification.getJobTitle());
            job.setIdjobpk(id);
            em.merge(job);
            em.flush();
            System.out.println("Save Job: " + job.getIdjobpk());
            return Response.ok(job).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }

    @POST
    @Path("filter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Job> filter(Job job) {
        try {
            System.out.println("filter " + job.getIdjobpk());
            String sql = "Select ...";
            boolean isFilterSet = false;
            if (job.getTitle() != "") {
                System.out.println("title found: " + job.getTitle());
                isFilterSet = true;
                sql = sql + " / title found ";
            }
            if (job.getDescription() != "") {
                System.out.println("description: " + job.getDescription());
                isFilterSet = true;
                sql = sql + " / description found ";
            }
            if (job.getStatus() != "") {
                isFilterSet = true;
                sql = sql + " / status ID";
            }
            if (isFilterSet) {
                System.out.println("sql: " + sql);
            } else {
                TypedQuery<Job> query
                        = em.createNamedQuery("Job.findAll", Job.class);
                List<Job> results = query.getResultList();
                System.out.println("Size: " + results.size());
                return results;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

}
