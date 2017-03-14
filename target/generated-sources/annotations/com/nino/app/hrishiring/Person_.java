package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Contact;
import com.nino.app.hrishiring.Endorsement;
import com.nino.app.hrishiring.HrisAccount;
import com.nino.app.hrishiring.JobQualification;
import com.nino.app.hrishiring.NsbPersonActivities;
import com.nino.app.hrishiring.Personstagestatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T07:15:37")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile CollectionAttribute<Person, Endorsement> endorsementCollection;
    public static volatile CollectionAttribute<Person, NsbPersonActivities> nsbPersonActivitiesCollection;
    public static volatile SingularAttribute<Person, String> lastName;
    public static volatile SingularAttribute<Person, Integer> idPerson;
    public static volatile SingularAttribute<Person, String> gender;
    public static volatile SingularAttribute<Person, Integer> lastUpdatePersonID;
    public static volatile SingularAttribute<Person, Date> lastUpdateDate;
    public static volatile CollectionAttribute<Person, Contact> contactCollection;
    public static volatile SingularAttribute<Person, Date> dateOfBirth;
    public static volatile CollectionAttribute<Person, JobQualification> jobQualificationCollection;
    public static volatile SingularAttribute<Person, String> firstName;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile CollectionAttribute<Person, HrisAccount> hrisAccountCollection;
    public static volatile CollectionAttribute<Person, Personstagestatus> personstagestatusCollection;

}