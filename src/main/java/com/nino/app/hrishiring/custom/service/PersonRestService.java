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
    @Consumes("application/x-www-form-urlencoded")
    public Response save(
            @FormParam("Name") String name,
            @FormParam("FirstName") String fname,
            @FormParam("LastName") String lname,
            @FormParam("DateOfBirth") String dob,
            @FormParam("Gender") String gender
    ) {
        System.out.println("createNew " + name + "-" + fname + "-" + lname + "-" + dob + "-" + gender);

        Person newPerson = new Person();
        newPerson.setIdPerson(2);
        newPerson.setFirstName(fname);
        newPerson.setLastName(lname);
        newPerson.setName(name);
        newPerson.setGender(gender);
        SimpleDateFormat dt = new SimpleDateFormat("MM/DD/YYYY");
        //SimpleDateFormat dt = new SimpleDateFormat("MM/DD/YYYY");
        try {
        newPerson.setLastUpdateDate(new Timestamp(new Date().getTime()));
        newPerson.setLastUpdatePersonID(1);
            newPerson.setDateOfBirth(dt.parse(dob));
            em.persist(newPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        return Response.ok("notok").build();
    }

    //@Path("")
}
