package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import com.nino.app.hrishiring.NsbActivityTp;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-10T01:09:51")
@StaticMetamodel(NsbActivityStatusTp.class)
public class NsbActivityStatusTp_ { 

    public static volatile SingularAttribute<NsbActivityStatusTp, Integer> priorityLevel;
    public static volatile SingularAttribute<NsbActivityStatusTp, String> name;
    public static volatile SingularAttribute<NsbActivityStatusTp, String> description;
    public static volatile SingularAttribute<NsbActivityStatusTp, NsbActivityTp> idActivityTp;
    public static volatile CollectionAttribute<NsbActivityStatusTp, NsbActivities> nsbActivitiesCollection;
    public static volatile SingularAttribute<NsbActivityStatusTp, Integer> idactivityStatus;

}