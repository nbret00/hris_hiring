package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivities;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-21T20:59:13")
@StaticMetamodel(NsbActivityDoc.class)
public class NsbActivityDoc_ { 

    public static volatile SingularAttribute<NsbActivityDoc, Integer> lastUpdatedBy;
    public static volatile SingularAttribute<NsbActivityDoc, String> createdDt;
    public static volatile SingularAttribute<NsbActivityDoc, Integer> createdBy;
    public static volatile SingularAttribute<NsbActivityDoc, Integer> idnsbActivityDoc;
    public static volatile SingularAttribute<NsbActivityDoc, String> documentation;
    public static volatile SingularAttribute<NsbActivityDoc, Date> lastUpdatedDt;
    public static volatile SingularAttribute<NsbActivityDoc, NsbActivities> nsbactivitiesidSourcingActivities;
    public static volatile SingularAttribute<NsbActivityDoc, String> status;

}