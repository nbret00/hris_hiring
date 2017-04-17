/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.NsbPersonActivities;
import com.nino.app.hrishiring.Person;
import com.nino.app.hrishiring.data.AllPersonActivity;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Path("reports")
@Stateless
public class ReportsRestService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ReportsRestService() {
    }

    @GET
    @Path("allPersonActivities/{act}/{acttp}/{ecomp}/{jo}")
    public List<AllPersonActivity> allPersonActivitiesFiltered(
            @PathParam("act") String act,
            @PathParam("acttp") String acttp,
            @PathParam("ecomp") String ecomp,
            @PathParam("jo") String jo
    ) {
        String query = "SELECT "
                + "p.idPerson, "
                + "p.Name, "
                + "p.FirstName, "
                + "p.LastName, "
                + "j.JobTitle, "
                + "j.company, "
                + "j.YrsOfExperience, "
                + "a.nsb_activity_tp, "
                + "a.nsb_activity_status_tp, "
                + "c.CompanyName, "
                + "jo.Title "
                + "FROM "
                + "hris_hiring.nsb_person_activities pa "
                + "RIGHT OUTER JOIN hris_hiring.nsb_activities a "
                + "ON a.nsb_entity_activities = pa.idpersonactivities, "
                + "hris_hiring.person p "
                + "LEFT OUTER JOIN	hris_hiring.job_qualification j "
                + "ON j.person_id = p.idPerson "
                + "LEFT OUTER JOIN	hris_hiring.endorsement e "
                + "ON p.idPerson = e.person_idPerson "
                + "LEFT OUTER JOIN hris_hiring.company c "
                + "ON e.company_idclient = c.idclient "
                + "LEFT OUTER JOIN hris_hiring.job jo "
                + "ON e.job_idjobpk = jo.idjobpk "
                + "WHERE "
                + "p.idPerson = pa.person_idPerson ";

        String wherec = new String("");

        System.out.println("Input: " + act + "-" + acttp + "-" + ecomp + "-" + jo);

        if (!act.equalsIgnoreCase("nah")) {
            wherec = wherec + ("AND a.nsb_activity_tp = " + Integer.parseInt(act) + " ");
        }
        if (!acttp.equalsIgnoreCase("nah")) {
            wherec = wherec + ("AND a.nsb_activity_status_tp = " + Integer.parseInt(acttp) + " ");
        }
        if (!ecomp.equalsIgnoreCase("nah")) {
            wherec = wherec + ("AND c.CompanyName = \"" + ecomp + "\" ");
        }
        if (!jo.equalsIgnoreCase("nah")) {
            wherec = wherec + ("AND jo.Title=\"" + jo + "\" ");
        }

        query = query + wherec;

        System.out.println("SQL Where clause for SEARCH: " + wherec);

        List alsearchresult = new ArrayList();

        try {

            List<Object[]> jq = em.createNativeQuery(query)
                    //.setParameter("personIds", "(" + ids + ")")
                    .getResultList();

            System.out.println("Reports - Person Activities #:" + jq.size());

            //ArrayList alsearchresult = new ArrayList();
            //Iterator i = jq.iterator();
            //while (i.hasNext()) {
            for (Object[] a : jq) {
                //Object o = i.next();
                //System.out.println("testing --- " + a[0]);

                AllPersonActivity sr = new AllPersonActivity(
                        (int) a[0], (String) a[1], (String) a[2], (String) a[3], (String) a[4], (String) a[5], (String) a[6], (int) a[7], (int) a[8], (String) a[9], (String) a[10]);

                alsearchresult.add(sr);
            }

            System.out.println("object size: " + alsearchresult.size());

            return alsearchresult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    /*

    @GET
    @Path("allPersonActivities")
    public List<AllPersonActivity> allPersonActivities() {
        List alsearchresult = new ArrayList();

        try {

            List<Object[]> jq = em.createNativeQuery(query)
                    //.setParameter("personIds", "(" + ids + ")")
                    .getResultList();

            System.out.println("Reports - Person Activities #:" + jq.size());

            //ArrayList alsearchresult = new ArrayList();
            //Iterator i = jq.iterator();
            //while (i.hasNext()) {
            for (Object[] a : jq) {
                //Object o = i.next();
                System.out.println("testing --- " + a[0]);

                AllPersonActivity sr = new AllPersonActivity(
                        (int) a[0], (String) a[1], (String) a[2], (String) a[3], (String) a[4], (String) a[5], (int) a[6], (int) a[7], (String) a[8], (String) a[9]);

                alsearchresult.add(sr);
            }

            System.out.println("object size: " + alsearchresult.size());

            return alsearchresult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/
}
