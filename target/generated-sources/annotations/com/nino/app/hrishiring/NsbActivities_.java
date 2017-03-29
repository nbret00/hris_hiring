package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivityStatusTp;
import com.nino.app.hrishiring.NsbActivityTp;
import com.nino.app.hrishiring.NsbPersonActivities;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-29T23:41:50")
@StaticMetamodel(NsbActivities.class)
public class NsbActivities_ { 

    public static volatile SingularAttribute<NsbActivities, String> createdByName;
    public static volatile SingularAttribute<NsbActivities, Integer> updatedBy;
    public static volatile SingularAttribute<NsbActivities, Integer> actOrder;
    public static volatile SingularAttribute<NsbActivities, String> updatedByName;
    public static volatile SingularAttribute<NsbActivities, Date> lastUpdatedDt;
    public static volatile SingularAttribute<NsbActivities, String> description;
    public static volatile SingularAttribute<NsbActivities, Date> endDt;
    public static volatile SingularAttribute<NsbActivities, Integer> idSourcingActivities;
    public static volatile SingularAttribute<NsbActivities, Date> createdDt;
    public static volatile SingularAttribute<NsbActivities, NsbPersonActivities> nsbEntityActivities;
    public static volatile SingularAttribute<NsbActivities, NsbActivityStatusTp> nsbActivityStatusTp;
    public static volatile SingularAttribute<NsbActivities, String> createdBy;
    public static volatile SingularAttribute<NsbActivities, Date> startDt;
    public static volatile SingularAttribute<NsbActivities, NsbActivityTp> nsbActivityTp;
    public static volatile SingularAttribute<NsbActivities, Integer> actOwner;

}