package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Company;
import com.nino.app.hrishiring.Endorsement;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-17T08:53:53")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile CollectionAttribute<Job, Endorsement> endorsementCollection;
    public static volatile SingularAttribute<Job, Integer> idjobpk;
    public static volatile SingularAttribute<Job, String> descriptionLong;
    public static volatile SingularAttribute<Job, Date> dateRecieved;
    public static volatile SingularAttribute<Job, Date> lastUpdatedDt;
    public static volatile SingularAttribute<Job, String> description;
    public static volatile SingularAttribute<Job, Integer> ptargetToMatch;
    public static volatile SingularAttribute<Job, String> title;
    public static volatile SingularAttribute<Job, Date> closingDate;
    public static volatile SingularAttribute<Job, Company> companyIdclient;
    public static volatile SingularAttribute<Job, Integer> openPosition;
    public static volatile SingularAttribute<Job, String> qualifications;
    public static volatile SingularAttribute<Job, String> responsibility;
    public static volatile SingularAttribute<Job, Integer> lastUpdateUser;
    public static volatile SingularAttribute<Job, String> location;
    public static volatile SingularAttribute<Job, String> remarks;
    public static volatile SingularAttribute<Job, String> pAssignement;
    public static volatile SingularAttribute<Job, Integer> ptargetToEndorse;
    public static volatile SingularAttribute<Job, String> status;

}