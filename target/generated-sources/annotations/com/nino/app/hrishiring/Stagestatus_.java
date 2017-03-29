package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Personstagestatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-29T23:41:50")
@StaticMetamodel(Stagestatus.class)
public class Stagestatus_ { 

    public static volatile SingularAttribute<Stagestatus, String> note;
    public static volatile SingularAttribute<Stagestatus, String> name;
    public static volatile SingularAttribute<Stagestatus, String> description;
    public static volatile SingularAttribute<Stagestatus, Integer> idStageStatus;
    public static volatile CollectionAttribute<Stagestatus, Personstagestatus> personstagestatusCollection;

}