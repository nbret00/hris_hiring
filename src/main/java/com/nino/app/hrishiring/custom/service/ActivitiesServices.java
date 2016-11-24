/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.Contact;
import com.nino.app.hrishiring.NsbActivities;
import com.nino.app.hrishiring.NsbEntityActivities;
import com.nino.app.hrishiring.Person;
import java.util.ArrayList;
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
    @Path("save/{personid}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(Contact entity,
            @PathParam("personid") int personid) {
        try {
            System.out.println("createNew " + entity.getEmail());
            Person p = em.find(Person.class, personid);
            entity.setPersonidPerson(p);
            em.persist(entity);
            em.flush();
            System.out.println("Create new job qualification with ID: " + entity.getIdcontact());
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
            NsbEntityActivities jq = (NsbEntityActivities) em.createQuery("SELECT n FROM NsbEntityActivities n WHERE n.ididentityActivities = :ididentityActivities")
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
    @Path("act/{id}")
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

            jq = em.createQuery("SELECT n FROM NsbActivities n WHERE n.nsbEntityActivities = :nsbEntityActivities")
                    .setParameter("nsbEntityActivities", entity_act)
                    .getResultList();
            //System.out.println("Contact #:" + jq.getIdcontact());
            
        } catch (Exception e) {
            e.printStackTrace();
            //return Response.ok("noresult").build();
        }
        return jq;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Contact entity) {
        try {
            Person p = new Person();
            p.setIdPerson(id);
            entity.setPersonidPerson(p);
            System.out.println("Edit for jobqualification :" + entity.getIdcontact().toString());
            em.merge(entity);
            return Response.ok(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("notok").build();
        }
    }

}
