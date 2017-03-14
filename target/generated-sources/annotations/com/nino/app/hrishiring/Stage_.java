package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Personstagestatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-15T07:15:37")
@StaticMetamodel(Stage.class)
public class Stage_ { 

    public static volatile SingularAttribute<Stage, Integer> idHRISStage;
    public static volatile SingularAttribute<Stage, String> note;
    public static volatile SingularAttribute<Stage, String> name;
    public static volatile SingularAttribute<Stage, String> description;
    public static volatile CollectionAttribute<Stage, Personstagestatus> personstagestatusCollection;

}