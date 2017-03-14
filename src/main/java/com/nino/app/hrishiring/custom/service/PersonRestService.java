/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.NsbPersonActivities;
import com.nino.app.hrishiring.Person;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("personProfile")
@Stateless
public class PersonRestService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PersonRestService() {
    }

    @POST
    @Path("save")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(Person person
    ) {
        try {
            System.out.println("createNew " + person.getFirstName());
            person.setLastUpdateDate(new Timestamp(new Date().getTime()));
            //person.setLastUpdatePersonID(1);
            //person.setDateOfBirth(dt.parse);
            em.persist(person);
            em.flush();
            System.out.println(person.getIdPerson());
            
            NsbPersonActivities ent = new NsbPersonActivities();
            //ent.setEntityName("person");
            //ent.setEntityId(person.getIdPerson());
            ent.setPersonidPerson(person);
            em.persist(ent);
            em.flush();
            return Response.ok(person).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }
    
    @GET
    @Path("searchByNames")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Person> searchByNames(
            Person p
    ) {
        List personres = null;
        try {
            String sq = "SELECT p FROM Person p WHERE ";
            System.out.println("searchByNames sql: "+p.getFirstName());
            if(p.getFirstName()!=""){
                sq = sq + "p.firstName LIKE :firstName ";
            }
            System.out.println("searchByNames sql--: "+p.getLastName());
            if(p.getLastName()!=""){
                sq = sq + "OR p.lastName LIKE :lastName ";
            }
            System.out.println("searchByNames sql: "+sq);
            Query q = em.createQuery(sq);

            if(p.getFirstName()!=""){
                q.setParameter("firstName", "%"+p.getFirstName()+"%");
            }
            if(p.getLastName()!=""){
                q.setParameter("lastName", "%"+p.getLastName()+"%");
            }
            personres = q.getResultList();
            System.out.println("Num of results: "+personres.size());
            return personres;
        } catch (Exception e) {
            e.printStackTrace();
            return personres;
        }
        
    }    
    
        @GET
    @Path("searchByLastname/{lname}")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Person> searchByLastname(@PathParam("lname") String lname
    ) {
        List personres = null;
        try {
            String sq = "SELECT p FROM Person p WHERE ";
            //if(null != person.getFirstName()){
                sq = sq + "p.lastName LIKE :lastName";
            //}
            Query q = em.createQuery(sq);
            q.setParameter("lastName", "%"+lname+"%");
            
            personres = q.getResultList();
            System.out.println("Num of results: "+personres.size());
            return personres;
        } catch (Exception e) {
            e.printStackTrace();
            return personres;
        }
    }    
 
    @GET
    @Path("searchByName/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Person> searchByName(@PathParam("name") String name
    ) {
        List personres = null;
        try {
            String sq = "SELECT p FROM Person p WHERE ";
            //if(null != person.getFirstName()){
                sq = sq + "p.name LIKE :name";
            //}
            Query q = em.createQuery(sq);
            q.setParameter("name", "%"+name+"%");
            
            personres = q.getResultList();
            System.out.println("Num of results: "+personres.size());
            return personres;
        } catch (Exception e) {
            e.printStackTrace();
            return personres;
        }
    }    
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Person get(@PathParam("id") String id
    ){
        return em.find(Person.class, id);
    } 
    
}
