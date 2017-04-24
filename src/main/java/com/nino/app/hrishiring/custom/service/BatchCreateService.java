/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.custom.service;

import com.nino.app.hrishiring.Company;
import com.nino.app.hrishiring.Contact;
import com.nino.app.hrishiring.Endorsement;
import com.nino.app.hrishiring.HrisAccount;
import com.nino.app.hrishiring.Job;
import com.nino.app.hrishiring.JobQualification;
import com.nino.app.hrishiring.NsbActivities;
import com.nino.app.hrishiring.NsbActivityStatusTp;
import com.nino.app.hrishiring.NsbActivityTp;
import com.nino.app.hrishiring.NsbPersonActivities;
import com.nino.app.hrishiring.Person;
import com.nino.app.hrishiring.data.BatchUploadData;
import com.nino.app.hrishiring.data.Credential;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nbret00
 */
@Path("batchCreate")
@Stateless
public class BatchCreateService {

    @PersistenceContext(unitName = "com.nino.app_HRISHiring_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Context
    ResourceContext resourceContext;

    public BatchCreateService() {
    }

    @POST
    @Path("create/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(BatchUploadData data, @PathParam("name") String loggedname) {
        String message = "";
        try {

            if (data.getName() != "") {
                PersonRestService prs = resourceContext.getResource(PersonRestService.class);
                List<Person> pr = prs.searchByName(data.getName());

                if (pr.size() == 0) {//unique
                    Person p = new Person();

                    p.setFirstName(data.getFname());
                    p.setLastName(data.getLname());
                    p.setName(data.getName());
                    p.setLastUpdateDate(new Date());
                    p.setIdPerson(1);
                    //prs.save(p);
                    em.persist(p);
                    //em.flush();

                    NsbPersonActivities ent = new NsbPersonActivities();
                    //ent.setEntityName("person");
                    //ent.setEntityId(person.getIdPerson());
                    ent.setPersonidPerson(p);
                    em.persist(ent);
                    //em.flush();

                    Integer pid = p.getIdPerson();
                    System.out.print("Person created! id " + pid);
                    data.setPid(pid);
                    //create Job qualification
                    //JobQualificationRestService jqr = resourceContext.getResource(JobQualificationRestService.class);
                    
                    JobQualification jq = new JobQualification();
                    jq.setJobTitle(data.getJobTitle());
                    jq.setCompany(data.getCompany());
                    jq.setYrsOfExperience(data.getYrsOfExp());
                    jq.setPersonId(p);
                    em.persist(jq);
                    
                    //Response jqrr = jqr.save(jq, pid);
                    //JobQualification jqrrr = (JobQualification) jqrr.getEntity();
                    //System.out.print("JobQualification! id" + jqrrr.getIdJobQualification());
                    //contact
                    //ContactRestService crs = resourceContext.getResource(ContactRestService.class);
                    Contact c = new Contact();
                    c.setEmail(data.getEmail());
                    c.setCellphoneNum(data.getMobileNum());
                    c.setPersonidPerson(p);
                    em.persist(c);
                    //crs.save(c, pid);
                    //activity

                    //ActivitiesServices acs = resourceContext.getResource(ActivitiesServices.class);
                    NsbActivities activity = new NsbActivities();
                    activity.setNsbEntityActivities(ent);
                    activity.setCreatedByName(loggedname);
                    activity.setCreatedDt(new Date());
                    activity.setDescription("Created from batch upload.");
                    activity.setUpdatedByName(loggedname);
                    activity.setLastUpdatedDt(new Date());

                    NsbActivityTp acttp = new NsbActivityTp();
                    acttp.setIdActivityTp(1);
                    activity.setNsbActivityTp(acttp);

                    NsbActivityStatusTp acttpst = new NsbActivityStatusTp();
                    acttpst.setIdactivityStatus(1);
                    activity.setNsbActivityStatusTp(acttpst);
                    em.persist(activity);
                    //acs.save(activity);
                    em.flush();

                    message = message + "Record Added ID#: " + pid;
                    System.out.println("Company and job: " + data.getEndorsedCompanyID() + "-" + data.getEndorsedJobID());
                    if (data.getEndorsedCompanyID() == 0 || data.getEndorsedJobID() == 0) {
                        data.setStatusID(110);
                        message = message + "; No endorsement to add.";
                    } else {
                        message = addEndorsement(data, p, message);
                    }
                    data.setStatusID(100);
                    data.setTransactionLog(message);
                } else {
                    message = message + "Rec already exist ";
                    Person p1 = pr.get(0);//get the first
                    if (data.getEndorsedCompanyID() == 0 || data.getEndorsedJobID() == 0) {
                        data.setStatusID(500);
                        message = message + "; Input has no endorsement.";
                    } else {
                        message = addEndorsement(data, p1, message);
                        if (message.endsWith(" ;Endorsement Added")) {
                            data.setStatusID(100);
                        } else {
                            data.setStatusID(500);
                        }
                    }
                    data.setTransactionLog(message);
                    
                }
            }
            System.out.println("message: " + message);
            return Response.ok(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            data.setTransactionLog("Warning: Unknown Data invalid");
            data.setStatusID(500);
            return Response.ok(data).build();
        }
    }

    @GET
    @Path("test")
    @Consumes({MediaType.APPLICATION_JSON})
    public BatchUploadData getTest() {
        BatchUploadData bu = new BatchUploadData(123, "Transaction Log text",
                "name text", "fname text", "lastname text", "Job Title Text", "Company Text",
                "yrs of exp text", "mobile num text", "email@text", 1, 2, 100);
        return bu;
    }

    private String addEndorsement(BatchUploadData data, Person p, String msg) throws Exception {
        String message = msg;
        EndorsementRestService ers = resourceContext.getResource(EndorsementRestService.class);
        //TODO need to search for existing endorsement first to detect invalid endorsement
        Endorsement e = new Endorsement();
        Company co = new Company();
        co.setIdclient(data.getEndorsedCompanyID());
        e.setCompanyIdclient(co);
        e.setEndorsedDate(new Date());
        e.setPersonidPerson(p);
        Job j = new Job();
        j.setIdjobpk(data.getEndorsedJobID());
        e.setJobIdjobpk(j);
        Response er = ers.newEndorsementUnique(e);
        Endorsement responsee = (Endorsement) er.getEntity();
        if (null == responsee.getIdendorsement()) {
            message = message + " ;Endorsement invalid/already exist.";
        } else if (responsee.getIdendorsement() != 0) {
            message = message + " ;Endorsement Added";
        } else {
            message = message + " ;Endorsement already exist.";
        }
        return message;
    }

}
