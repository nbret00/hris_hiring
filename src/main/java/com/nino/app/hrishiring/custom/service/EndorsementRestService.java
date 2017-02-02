/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.Company;
import com.nino.app.hrishiring.Endorsement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nbret00
 */
@Stateless
@Path("endorsements")
public class EndorsementRestService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EndorsementRestService() {

    }

    @GET
    @Path("{cid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Endorsement> getEndorsementByCompany(@PathParam("cid") int cid) {

        Company c = new Company();
        c.setIdclient(cid);
        
        List<Endorsement> acc = (List<Endorsement>) em.createQuery("SELECT e FROM Endorsement e WHERE e.companyIdclient = :companyIdclient")
                .setParameter("companyIdclient", c)
                .getResultList();

        System.out.println("acc size: " + acc.size());
        return acc;
    }


}
