package com.nino.app.hrishiring;

import com.nino.app.hrishiring.JobQualification;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-14T23:21:26")
@StaticMetamodel(Industries.class)
public class Industries_ { 

    public static volatile SingularAttribute<Industries, String> name;
    public static volatile SingularAttribute<Industries, Integer> idindustries;
    public static volatile CollectionAttribute<Industries, JobQualification> jobQualificationCollection;
    public static volatile SingularAttribute<Industries, Integer> parentId;

}