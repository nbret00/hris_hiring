/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@XmlRootElement
public class Credential {
    
    private int accountID;
    private String Username;
    private String Role;
    
    public Credential(){}
    
    public Credential(int accountID,String Username, String Role){
        this.Role = Role;
        this.Username = Username;
        this.accountID = accountID;
    }
    
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    

    
    public String getUsername() {
        return Username;
    }

    //@XmlElement
    public void setUsername(String Username) {
        this.Username = Username;
    }


    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

           
    
}
