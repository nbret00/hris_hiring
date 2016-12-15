package com.nino.app.hrishiring;

import com.nino.app.hrishiring.NsbActivitySuccessCriteria;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-14T22:21:20")
@StaticMetamodel(NsbActivity.class)
public class NsbActivity_ { 

    public static volatile SingularAttribute<NsbActivity, String> outputName;
    public static volatile SingularAttribute<NsbActivity, String> dateFinished;
    public static volatile SingularAttribute<NsbActivity, String> plannedDateTime;
    public static volatile SingularAttribute<NsbActivity, Integer> idnsbActivity;
    public static volatile SingularAttribute<NsbActivity, String> output;
    public static volatile CollectionAttribute<NsbActivity, NsbActivitySuccessCriteria> nsbActivitySuccessCriteriaCollection;
    public static volatile SingularAttribute<NsbActivity, String> dateCreated;
    public static volatile SingularAttribute<NsbActivity, String> performedBy;
    public static volatile SingularAttribute<NsbActivity, String> createdBy;
    public static volatile SingularAttribute<NsbActivity, String> performedDate;
    public static volatile SingularAttribute<NsbActivity, String> name;
    public static volatile SingularAttribute<NsbActivity, String> action;
    public static volatile SingularAttribute<NsbActivity, String> location;
    public static volatile SingularAttribute<NsbActivity, String> remarks;
    public static volatile SingularAttribute<NsbActivity, String> actionName;
    public static volatile SingularAttribute<NsbActivity, String> status;

}