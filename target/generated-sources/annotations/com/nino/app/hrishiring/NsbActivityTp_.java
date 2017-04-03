package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import com.nino.app.hrishiring.NsbActivityStatusTp;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-04T04:11:17")
@StaticMetamodel(NsbActivityTp.class)
public class NsbActivityTp_ { 

    public static volatile CollectionAttribute<NsbActivityTp, NsbActivityStatusTp> nsbActivityStatusTpCollection;
    public static volatile SingularAttribute<NsbActivityTp, Integer> tpOrder;
    public static volatile SingularAttribute<NsbActivityTp, String> name;
    public static volatile SingularAttribute<NsbActivityTp, Integer> idActivityTp;
    public static volatile SingularAttribute<NsbActivityTp, String> description;
    public static volatile CollectionAttribute<NsbActivityTp, NsbActivities> nsbActivitiesCollection;

}