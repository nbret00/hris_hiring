package com.nino.app.hrishiring;

import com.nino.app.hrishiring.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-17T08:53:53")
@StaticMetamodel(HrisAccount.class)
public class HrisAccount_ { 

    public static volatile SingularAttribute<HrisAccount, String> secretQuestion;
    public static volatile SingularAttribute<HrisAccount, Person> personidPerson;
    public static volatile SingularAttribute<HrisAccount, String> password;
    public static volatile SingularAttribute<HrisAccount, String> role;
    public static volatile SingularAttribute<HrisAccount, String> profile;
    public static volatile SingularAttribute<HrisAccount, Integer> idhrisAccount;
    public static volatile SingularAttribute<HrisAccount, String> email;
    public static volatile SingularAttribute<HrisAccount, String> secretAnswer;
    public static volatile SingularAttribute<HrisAccount, String> username;

}