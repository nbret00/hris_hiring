/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.HrisAccount;
import com.nino.app.hrishiring.Person;
import com.nino.app.hrishiring.data.Credential;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Stateless
@Path("hrisaccount")
public class AccountRESTService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AccountRESTService() {

    }

    @GET
    @Path("acct/{u}/{p}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HrisAccount> getAccount(@PathParam("u") String u, @PathParam("p") String p) {
        List<HrisAccount> acc = (List<HrisAccount>) em.createNamedQuery("HrisAccount.getAccount").setParameter("username", u)
                .setParameter("password", p).getResultList();

        //System.out.println("acc : " + acc.getUsername());
        return acc;
    }

    @GET
    //@Consumes({MediaType.APPLICATION_XML})
    @Path("checkCredential")
    public Response checkCredential(@CookieParam("credentialid") String user) {
        System.out.println("check credential..." + user);
        if (user == null) {
            //not autheticated
            return Response.ok("not_authenticated").build();
        }
        //create a renewed cookie
        NewCookie nc1 = new NewCookie(
                new Cookie("credentialid", user), "authenticate", 1200, false);

        HrisAccount ha = em.find(HrisAccount.class, Integer.valueOf(user));
        
        Person p = ha.getPersonidPerson();
        System.out.println("Person outhenticated "+p.getIdPerson());
        
        Credential cred = new Credential(ha.getIdhrisAccount().intValue(), ha.getUsername(),ha.getRole(), p);
        
        System.out.println("this the user from cookie " + user);
        return Response.ok("success").entity(cred).cookie(nc1).build();
    }

    @GET
    //@Consumes({MediaType.APPLICATION_XML})
    @Path("logout")
    public Response logout(@CookieParam("credentialid") Cookie user) {
        return Response.ok("success")
                .cookie(new NewCookie(user, "no comment", 0, false))
                .build();
    }

    @GET
    //@Consumes({MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcookies")
    public Response checkCredential(@Context HttpHeaders headers) {
        /*
        System.out.println("check credential...");
        if (user == null) {
            System.out.println("this the user from cookie "+user);
            //not autheticated
            return headers.getCookies();
        }
         */

        System.out.println("headers: " + headers.getCookies().toString());

        Map<String, Cookie> map = headers.getCookies();
        for (Map.Entry<String, Cookie> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue().getValue());
        }

        return Response.ok("ok").build();
    }
    
    

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/validate")
    //public Response validate(@FormParam("user") String u, @FormParam("pass") String p) {
    public Response validate(
            @FormParam("Username") String u,
            @FormParam("Password") String p,
            @CookieParam("credentialid") Cookie user
    ) {

        Credential cred = new Credential();
        if (user == null) {
            System.out.println(" postAccount returned success --------------------" + p + "-" + u);
            List<HrisAccount> acc = (List<HrisAccount>) em.createQuery("SELECT h FROM HrisAccount h WHERE h.username = :username AND h.password = :password")
                    .setParameter("username", u)
                    .setParameter("password", p)
                    .getResultList();
            //System.out.println("size : " + acc.size());

            if (acc.size() > 0) {

                //System.out.println("data" + acc.get(0).getUsername());
                cred.setRole(acc.get(0).getRole());
                cred.setUsername(acc.get(0).getUsername());
                cred.setAccountID(acc.get(0).getIdhrisAccount().intValue());

                String uid = acc.get(0).getIdhrisAccount().toString();

                NewCookie nc1 = new NewCookie(
                        new Cookie("credentialid", uid), "authenticate", 1200, false);

                return Response.ok("success")
                        .cookie(nc1)
                        .build();
            } else {
                //no access                
                return Response.ok("noaccess").build();
            }
        }

        NewCookie nc1 = new NewCookie(
                new Cookie("credentialid", user.getValue()), "authenticate", 1200, false);

        System.out.println("authenticated");

        return Response.ok("success")
                .cookie(nc1)
                .build();
    }
    //public HrisAccount getAccout
/*
    @POST
    @Path("add")
    public Response addUser(
            @FormParam("user") String name,
            @FormParam("pass") int age) {

        return Response.status(200)
                .entity("addUser is called, name : " + name + ", age : " + age)
                .build();

    }
     */

}
