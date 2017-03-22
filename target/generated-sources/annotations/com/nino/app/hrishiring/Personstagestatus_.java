package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Person;
import com.nino.app.hrishiring.PersonstagestatusPK;
import com.nino.app.hrishiring.Stage;
import com.nino.app.hrishiring.Stagestatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-22T20:56:34")
@StaticMetamodel(Personstagestatus.class)
public class Personstagestatus_ { 

    public static volatile SingularAttribute<Personstagestatus, String> note;
    public static volatile SingularAttribute<Personstagestatus, Person> person;
    public static volatile SingularAttribute<Personstagestatus, Stagestatus> stageStatusidStageStatus;
    public static volatile SingularAttribute<Personstagestatus, Stage> stageidHRISStage;
    public static volatile SingularAttribute<Personstagestatus, PersonstagestatusPK> personstagestatusPK;

}