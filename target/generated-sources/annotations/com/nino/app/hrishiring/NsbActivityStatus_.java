package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-25T10:55:13")
@StaticMetamodel(NsbActivityStatus.class)
public class NsbActivityStatus_ { 

    public static volatile SingularAttribute<NsbActivityStatus, String> updatedBy;
    public static volatile SingularAttribute<NsbActivityStatus, String> statusId;
    public static volatile SingularAttribute<NsbActivityStatus, NsbActivities> nsbactivitiesidSourcingActivities;
    public static volatile SingularAttribute<NsbActivityStatus, Date> updatedDt;
    public static volatile SingularAttribute<NsbActivityStatus, Integer> idactivityStatus;

}