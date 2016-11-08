package com.nino.app.hrishiring;

import com.nino.app.hrishiring.JobQualification;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-07T11:31:08")
@StaticMetamodel(Payrate.class)
public class Payrate_ { 

    public static volatile SingularAttribute<Payrate, String> min;
    public static volatile SingularAttribute<Payrate, String> payrateTxt;
    public static volatile SingularAttribute<Payrate, String> max;
    public static volatile SingularAttribute<Payrate, Integer> idpayrate;
    public static volatile CollectionAttribute<Payrate, JobQualification> jobQualificationCollection;

}