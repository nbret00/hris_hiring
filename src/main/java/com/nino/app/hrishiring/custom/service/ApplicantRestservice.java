/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Path("applicant")
@Stateless
public class ApplicantRestservice {
    
    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Path("removeSession")
    public Response removeSession(@CookieParam("applicantid") Cookie cookie ){
        if (cookie == null){
            return Response.ok("ok").build();
        }
        
        System.out.println("remove cookie/session "+cookie.getValue());
        NewCookie nc = new NewCookie(cookie,"no comment",0,false);
        return Response.ok("ok").cookie(nc).build();
    }
    
    
    //@Path("")
    
    
    
    
}
