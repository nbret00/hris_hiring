package com.nino.app.hrishiring;

import com.nino.app.hrishiring.ContactPK;
import com.nino.app.hrishiring.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-12T10:11:01")
@StaticMetamodel(Contact.class)
public class Contact_ { 

    public static volatile SingularAttribute<Contact, String> contactNum;
    public static volatile SingularAttribute<Contact, String> cellphoneNum;
    public static volatile SingularAttribute<Contact, String> country;
    public static volatile SingularAttribute<Contact, Integer> idPerson;
    public static volatile SingularAttribute<Contact, String> address;
    public static volatile SingularAttribute<Contact, ContactPK> contactPK;
    public static volatile SingularAttribute<Contact, String> telNum1;
    public static volatile SingularAttribute<Contact, String> city;
    public static volatile SingularAttribute<Contact, Person> person;
    public static volatile SingularAttribute<Contact, String> email;

}