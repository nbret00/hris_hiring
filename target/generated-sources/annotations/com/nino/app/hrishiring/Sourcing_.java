package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Job;
import com.nino.app.hrishiring.Person;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-12T10:11:01")
@StaticMetamodel(Sourcing.class)
public class Sourcing_ { 

    public static volatile SingularAttribute<Sourcing, String> interviewer;
    public static volatile SingularAttribute<Sourcing, String> contactedBy;
    public static volatile SingularAttribute<Sourcing, Date> dateOfInterview;
    public static volatile CollectionAttribute<Sourcing, Person> personCollection;
    public static volatile SingularAttribute<Sourcing, String> targetJobCategory;
    public static volatile SingularAttribute<Sourcing, String> source;
    public static volatile SingularAttribute<Sourcing, String> sourcer;
    public static volatile SingularAttribute<Sourcing, Integer> title;
    public static volatile SingularAttribute<Sourcing, String> dataContacted;
    public static volatile SingularAttribute<Sourcing, Integer> idsourcingCampaigne;
    public static volatile SingularAttribute<Sourcing, String> targetJobTitle;
    public static volatile SingularAttribute<Sourcing, Job> job;
    public static volatile SingularAttribute<Sourcing, Date> moDateAcceptedInLinkedin;
    public static volatile SingularAttribute<Sourcing, String> status;

}