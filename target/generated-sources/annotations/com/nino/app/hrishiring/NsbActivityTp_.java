package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-27T23:42:53")
@StaticMetamodel(NsbActivityTp.class)
public class NsbActivityTp_ { 

    public static volatile SingularAttribute<NsbActivityTp, Integer> tpOrder;
    public static volatile SingularAttribute<NsbActivityTp, String> name;
    public static volatile SingularAttribute<NsbActivityTp, Integer> idActivityTp;
    public static volatile SingularAttribute<NsbActivityTp, String> description;
    public static volatile CollectionAttribute<NsbActivityTp, NsbActivities> nsbActivitiesCollection;

}