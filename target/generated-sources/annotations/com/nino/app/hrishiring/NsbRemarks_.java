package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-03T09:26:46")
@StaticMetamodel(NsbRemarks.class)
public class NsbRemarks_ { 

    public static volatile SingularAttribute<NsbRemarks, Date> createdDt;
    public static volatile SingularAttribute<NsbRemarks, Integer> createdBy;
    public static volatile SingularAttribute<NsbRemarks, NsbActivities> nsbactivitiesidSourcingActivities;
    public static volatile SingularAttribute<NsbRemarks, Integer> idremarks;
    public static volatile SingularAttribute<NsbRemarks, String> remarks;
    public static volatile SingularAttribute<NsbRemarks, Integer> parentId;
    public static volatile SingularAttribute<NsbRemarks, Integer> status;

}