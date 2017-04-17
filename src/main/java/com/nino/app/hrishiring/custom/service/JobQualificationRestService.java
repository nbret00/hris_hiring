/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.JobQualification;
import com.nino.app.hrishiring.Person;
import com.nino.app.hrishiring.data.SearchResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
            @PathParam("personid") int personid) {
        try {
            System.out.println("createNew " + jobqualification.getJobTitle());
            Person p = em.find(Person.class, personid);
            jobqualification.setPersonId(p);
            em.persist(jobqualification);
            em.flush();
            System.out.println("Create new job qualification with ID: " + jobqualification.getIdJobQualification());
            return Response.ok(jobqualification).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }

    @GET
    @Path("{id}")
    public Response getJobQualification(@PathParam("id") int id) {
        try {
            System.out.println("Job qualification search by person id");

            Person p = new Person();
            p.setIdPerson(id);

            JobQualification jq = (JobQualification) em.createQuery("SELECT j FROM JobQualification j WHERE j.personId = :personId")
                    .setParameter("personId", p)
                    .getSingleResult();
            System.out.println("Job qualification #:" + jq.getIdJobQualification());
            return Response.ok(jq).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("noresult").build();
        }
    }

    @GET
    @Path("searchFirstname/{fname}")
    public List<SearchResult> getJobQualificationByFirstName(@PathParam("fname") String fname) {
        ArrayList alsearchresult = new ArrayList();
        try {
            System.out.println("Job qualification search by person id");
            List q = em.createQuery("SELECT p FROM Person p WHERE p.firstName LIKE :firstName")
                    .setParameter("firstName", "%" + fname + "%")
                    .getResultList();

            System.out.println("Num of people: " + q.size());
            String ids = new String();
            Iterator iq = q.iterator();
            while (iq.hasNext()) {
                Person p = (Person) iq.next();
                ids = ids + p.getIdPerson() + ",";
            }
            if (ids != null) {
                if (ids.endsWith(",")) {
                    ids = ids.substring(0, ids.length() - 1);
                }

                System.out.println("Person ids to search: " + ids);

                String query = "SELECT p.idPerson, p.FirstName, p.LastName, jq.JobTitle "
                        + "FROM hris_hiring.job_qualification jq right join hris_hiring.person p on p.idPerson = jq.person_id "
                        + "where p.idPerson in (" + ids + ")";
                List<Object[]> jq = em.createNativeQuery(query)
                        //.setParameter("personIds", "(" + ids + ")")
                        .getResultList();

                System.out.println("Job qualification #:" + jq.size());

                //ArrayList alsearchresult = new ArrayList();
                //Iterator i = jq.iterator();
                //while (i.hasNext()) {
                //for (Object[] a : jq) {
                    //Object o = i.next();
                    //System.out.println("testing --- "+a[0]);
                    
                    //SearchResult sr = new SearchResult((int)a[0],(String)a[1],(String)a[2],"",(String)a[3]);
                    //alsearchresult.add(sr);
                //}
                
            }

            return alsearchresult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, JobQualification entity) {
        try {
            Person p = new Person();
            p.setIdPerson(id);
            entity.setPersonId(p);
            System.out.println("Edit for jobqualification :" + entity.getIdJobQualification().toString());
            em.merge(entity);
            return Response.ok(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("notok").build();
        }
    }

}
