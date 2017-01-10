/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.Contact;
import com.nino.app.hrishiring.NsbActivities;
import com.nino.app.hrishiring.NsbEntityActivities;
import com.nino.app.hrishiring.NsbRemarks;
import com.nino.app.hrishiring.Person;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Path("activities")
@Stateless
public class ActivitiesServices {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ActivitiesServices() {
    }

    @POST
    @Path("save")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(NsbActivities entity) {
        try {
            System.out.println("createNew " + entity.getIdSourcingActivities());
            //entity.setPersonidPerson(p);
            em.persist(entity);
            em.flush();
            //System.out.println("Create new job qualification with ID: " + entity.getIdcontact());
            return Response.ok(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }

    @GET
    @Path("{id}")
    public Response getActivities(@PathParam("id") int id) {
        try {
            System.out.println("Activity search by id");
            //Person p = new Person(id);
            NsbEntityActivities jq = (NsbEntityActivities) em.createQuery("SELECT n FROM NsbEntityActivities n WHERE n.ididentityActivities = :ididentityActivities ORDER BY n.ididentityActivities DESC")
                    .setParameter("ididentityActivities", id)
                    .getSingleResult();
            //System.out.println("Contact #:" + jq.getIdcontact());

            return Response.ok(jq).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("noresult").build();
        }
    }

    @GET
    @Path("act/{id}")//by person ID
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<NsbActivities> getActivities1(@PathParam("id") int id) {
        List jq = null;
        try {
            System.out.println("Activity search by id");
            //Person p = new Person(id);
            //NsbActivities jq = em.find(NsbActivities.class, id);

            NsbEntityActivities entity_act = (NsbEntityActivities) em.createQuery("SELECT n FROM NsbEntityActivities n WHERE n.entityId = :entityId")
                    .setParameter("entityId", id)
                    .getSingleResult();

            jq = em.createQuery("SELECT n FROM NsbActivities n WHERE n.nsbEntityActivities = :nsbEntityActivities ORDER BY n.idSourcingActivities DESC")
                    .setParameter("nsbEntityActivities", entity_act)
                    .getResultList();
            //System.out.println("Contact #:" + jq.getIdcontact());

        } catch (Exception e) {
            e.printStackTrace();
            //return Response.ok("noresult").build();
        }
        return jq;
    }

    @GET
    @Path("remarks/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<NsbRemarks> getRemarksByActivity(@PathParam("id") int id) {
        try {
            System.out.println("Remarks search by activity id");
            //Person p = new Person(id);
            NsbActivities na = new NsbActivities();
            na.setIdSourcingActivities(Integer.valueOf(id));

            List jq = (List) em.createQuery("SELECT n FROM NsbRemarks n WHERE n.nsbactivitiesidSourcingActivities = :nsbactivitiesidSourcingActivities ORDER BY n.idremarks DESC")
                    .setParameter("nsbactivitiesidSourcingActivities", na)
                    .getResultList();
            //System.out.println("Contact #:" + jq.getIdcontact());

            return jq;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("remarksByPerson/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<NsbRemarks> getRemarksByPerson(@PathParam("id") int id) {
        List remarks = null;
        try {
            System.out.println("Activity search by id");
            //Person p = new Person(id);
            //NsbActivities jq = em.find(NsbActivities.class, id);

            NsbEntityActivities entity_act = (NsbEntityActivities) em.createQuery("SELECT n FROM NsbEntityActivities n WHERE n.entityId = :entityId")
                    .setParameter("entityId", id)
                    .getSingleResult();

            List jq = em.createQuery("SELECT n FROM NsbActivities n WHERE n.nsbEntityActivities = :nsbEntityActivities ORDER BY n.idSourcingActivities DESC")
                    .setParameter("nsbEntityActivities", entity_act)
                    .getResultList();

            remarks = (List) em.createQuery("SELECT n FROM NsbRemarks n WHERE n.nsbactivitiesidSourcingActivities IN :nsbactivitiesidSourcingActivities ORDER BY n.idremarks DESC")
                    .setParameter("nsbactivitiesidSourcingActivities", jq)
                    .getResultList();

            //System.out.println("Contact #:" + jq.getIdcontact());
        } catch (Exception e) {
            e.printStackTrace();
            //return Response.ok("noresult").build();
        }
        return remarks;
    }

    @PUT
    @Path("remarks/add")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addRemarks(NsbRemarks remarks) {
        try {
            em.persist(remarks);
            em.flush();
            //System.out.println("Create new job qualification with ID: " + entity.getIdcontact());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, NsbActivities entity) {
        try {
            /*Person p = new Person();
            p.setIdPerson(id);
            entity.setPersonidPerson(p);
             */
            System.out.println("Edit for Activities :" + id);
            em.merge(entity);
            return Response.ok(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("notok").build();
        }
    }

}
