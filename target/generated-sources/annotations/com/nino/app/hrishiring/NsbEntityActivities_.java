package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-29T13:46:03")
@StaticMetamodel(NsbEntityActivities.class)
public class NsbEntityActivities_ { 

    public static volatile SingularAttribute<NsbEntityActivities, Integer> ididentityActivities;
    public static volatile SingularAttribute<NsbEntityActivities, String> entityName;
    public static volatile SingularAttribute<NsbEntityActivities, Integer> entityId;
    public static volatile SingularAttribute<NsbEntityActivities, String> entityTp;
    public static volatile CollectionAttribute<NsbEntityActivities, NsbActivities> nsbActivitiesCollection;
    public static volatile SingularAttribute<NsbEntityActivities, Integer> status;

}