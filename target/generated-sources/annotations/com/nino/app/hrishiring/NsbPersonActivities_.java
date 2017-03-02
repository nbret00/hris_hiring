package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import com.nino.app.hrishiring.NsbRemarks;
import com.nino.app.hrishiring.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-28T11:41:47")
@StaticMetamodel(NsbPersonActivities.class)
public class NsbPersonActivities_ { 

    public static volatile CollectionAttribute<NsbPersonActivities, NsbRemarks> nsbRemarksCollection;
    public static volatile SingularAttribute<NsbPersonActivities, Person> personidPerson;
    public static volatile SingularAttribute<NsbPersonActivities, Integer> idpersonactivities;
    public static volatile CollectionAttribute<NsbPersonActivities, NsbActivities> nsbActivitiesCollection;
    public static volatile SingularAttribute<NsbPersonActivities, Integer> status;

}