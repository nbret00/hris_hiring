package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Company;
import com.nino.app.hrishiring.Person;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-14T23:21:26")
@StaticMetamodel(Endorsement.class)
public class Endorsement_ { 

    public static volatile SingularAttribute<Endorsement, String> jobOfferStatus;
    public static volatile SingularAttribute<Endorsement, Person> personidPerson;
    public static volatile SingularAttribute<Endorsement, Date> endorsedDate;
    public static volatile SingularAttribute<Endorsement, String> finalInterviewDate;
    public static volatile SingularAttribute<Endorsement, String> jobOfferDate;
    public static volatile SingularAttribute<Endorsement, Integer> priority;
    public static volatile SingularAttribute<Endorsement, Date> startdate;
    public static volatile SingularAttribute<Endorsement, String> finalinterviewResult;
    public static volatile SingularAttribute<Endorsement, Company> companyIdclient;
    public static volatile SingularAttribute<Endorsement, String> hRInterviewResult;
    public static volatile SingularAttribute<Endorsement, Date> hRInterviewDate;
    public static volatile SingularAttribute<Endorsement, Integer> idendorsement;
    public static volatile SingularAttribute<Endorsement, String> status;

}