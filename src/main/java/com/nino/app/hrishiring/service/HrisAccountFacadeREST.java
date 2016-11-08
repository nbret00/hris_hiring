/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.service;

import com.nino.app.hrishiring.HrisAccount;
import com.nino.app.hrishiring.data.Credential;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
//@Stateless
@Path("com.nino.app.hrishiring.hrisaccount")
public class HrisAccountFacadeREST extends AbstractFacade<HrisAccount> {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public HrisAccountFacadeREST() {
        super(HrisAccount.class);
    }

    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addUser(
            @FormParam("Username") String user,
            @FormParam("Password") String pass) {

        
        return Response.status(200)
                .entity("addUser is called, name : " + user + ", age : " + pass)
                .build();

    }
    
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(HrisAccount entity) {
        super.create(entity);
    }
    
    @POST
    @Path("readme")
    @Consumes(MediaType.APPLICATION_XHTML_XML)
    public void readme(Credential entity) {
        System.out.println("entity:"+entity.getUsername());
        //super.create(entity);
    }    

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, HrisAccount entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public HrisAccount find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HrisAccount> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HrisAccount> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    } 

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
