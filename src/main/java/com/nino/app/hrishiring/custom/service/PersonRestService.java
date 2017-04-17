/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.NsbPersonActivities;
import com.nino.app.hrishiring.Person;
import com.nino.app.hrishiring.data.SearchResult;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.ws.rs.core.GenericEntity;
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
            System.out.println("Person created ID:" + person.getIdPerson());

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

    @POST
    @Path("searchByNames")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response searchByNames(
            Person p
    ) {
        
        //Response response;
        try {
            List personres = new ArrayList();
            
            String query = "SELECT p.idPerson, p.Name, p.FirstName, p.LastName, jq.company, jq.JobTitle "
                    + "FROM hris_hiring.job_qualification jq right join hris_hiring.person p on p.idPerson = jq.person_id "
                    + "where ";
            
           if (p.getName() != "") {
                query = query + "p.Name = \""+p.getName()+"\"";
            } else {
                //System.out.println("searchByNames sql: " + p.getFirstName());
                boolean addAND = false;
                if (p.getFirstName() != "") {
                    query = query + "p.firstName = \""+p.getFirstName()+"\"";
                    addAND = true;
                }
                //System.out.println("searchByNames sql--: " + p.getLastName());
                if (p.getLastName() != "") {
                    if (addAND){
                        query = query + " AND ";
                    }
                    query = query + "p.lastName = \""+p.getLastName()+"\"";
                }
            }            
            System.out.println("searchByNames sql: " + query);
            
            List<Object[]> jq = em.createNativeQuery(query)
                    //.setParameter("personIds", "(" + ids + ")")
                    .getResultList();

            System.out.println("Job qualification #:" + jq.size());

            //ArrayList alsearchresult = new ArrayList();
            //Iterator i = jq.iterator();
            //while (i.hasNext()) {
            for (Object[] a : jq) {
                //Object o = i.next();
                //System.out.println("testing --- " + a[0]);

                SearchResult sr = new SearchResult((int) a[0], (String) a[1], (String) a[2], (String) a[3], (String) a[4], (String) a[5]);
                personres.add(sr);
            }
            List<SearchResult> list = personres;
            GenericEntity<List<SearchResult>> entity = new GenericEntity<List<SearchResult>>(list) {
            };
            //response = Response.ok(entity).build();

            System.out.println("Num of results: " + personres.size());
            return Response.ok(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok("notok").build();
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
            q.setParameter("lastName", "%" + lname + "%");

            personres = q.getResultList();
            System.out.println("Num of results: " + personres.size());
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
            q.setParameter("name", "%" + name + "%");

            personres = q.getResultList();
            System.out.println("Num of results: " + personres.size());
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
    ) {
        return em.find(Person.class, id);
    }

}
