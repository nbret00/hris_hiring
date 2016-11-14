/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.Person;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
            person.setLastUpdatePersonID(1);
            //person.setDateOfBirth(dt.parse);
            em.persist(person);
            em.flush();
            System.out.println(person.getIdPerson());
            return Response.ok(person).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(e.getMessage()).build();
        }

    }

    //@Path("")
}
