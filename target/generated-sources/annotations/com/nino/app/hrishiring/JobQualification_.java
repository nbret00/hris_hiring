package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Industries;
import com.nino.app.hrishiring.IndustryLevel;
import com.nino.app.hrishiring.Payrate;
import com.nino.app.hrishiring.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-17T21:15:22")
@StaticMetamodel(JobQualification.class)
public class JobQualification_ { 

    public static volatile SingularAttribute<JobQualification, Person> personidPerson;
    public static volatile SingularAttribute<JobQualification, String> skillsCategorymo;
    public static volatile SingularAttribute<JobQualification, String> targetSalary;
    public static volatile SingularAttribute<JobQualification, String> currentSalary;
    public static volatile SingularAttribute<JobQualification, String> yrsOfExperience;
    public static volatile SingularAttribute<JobQualification, Payrate> payrateIdpayrate;
    public static volatile SingularAttribute<JobQualification, String> jobTitle;
    public static volatile SingularAttribute<JobQualification, String> qualificationSummary;
    public static volatile SingularAttribute<JobQualification, String> priority;
    public static volatile SingularAttribute<JobQualification, Integer> idJobQualification;
    public static volatile SingularAttribute<JobQualification, String> skills;
    public static volatile SingularAttribute<JobQualification, Industries> industriesIdindustries;
    public static volatile SingularAttribute<JobQualification, String> searchText;
    public static volatile SingularAttribute<JobQualification, String> targetPosition;
    public static volatile SingularAttribute<JobQualification, IndustryLevel> industryLevelIdindustryLevel;
    public static volatile SingularAttribute<JobQualification, String> joblevelmo;

}