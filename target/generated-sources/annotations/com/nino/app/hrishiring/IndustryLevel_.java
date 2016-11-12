package com.nino.app.hrishiring;

import com.nino.app.hrishiring.JobQualification;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-12T10:11:01")
@StaticMetamodel(IndustryLevel.class)
public class IndustryLevel_ { 

    public static volatile SingularAttribute<IndustryLevel, String> name;
    public static volatile SingularAttribute<IndustryLevel, String> description;
    public static volatile SingularAttribute<IndustryLevel, Integer> idindustryLevel;
    public static volatile CollectionAttribute<IndustryLevel, JobQualification> jobQualificationCollection;

}