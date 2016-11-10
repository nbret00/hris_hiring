/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

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
    
    public PersonRestService(){}

    @POST
    @Path("save")
    @Consumes("application/x-www-form-urlencoded")
    public Response save(
            @FormParam("Name") String name,
            @FormParam("FirstName") String fname,
            @FormParam("LastName") String lname,
            @FormParam("DateOfBirth") String dob,
            @FormParam("Gender") String gender
    ){
        System.out.println("createNew "+name+"-"+fname+"-"+lname+"-"+dob+"-"+gender);
        
        
        return Response.ok("ok").build();
    }
    
    
    //@Path("")
    
    
    
    
}
