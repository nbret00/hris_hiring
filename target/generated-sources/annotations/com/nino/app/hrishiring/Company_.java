package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Endorsement;
import com.nino.app.hrishiring.Job;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-07T11:31:08")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile CollectionAttribute<Company, Job> jobCollection;
    public static volatile CollectionAttribute<Company, Endorsement> endorsementCollection;
    public static volatile SingularAttribute<Company, String> companyName;
    public static volatile SingularAttribute<Company, Integer> idclient;

}