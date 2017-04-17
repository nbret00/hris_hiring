/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nino.app.hrishiring.data;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbret00
 */
@XmlRootElement
public class BatchUploadData implements Serializable {

    private int pid;
    private String transactionLog;
    private String name;
    private String fname;
    private String lname;
    private String jobTitle;
    private String company;
    private String yrsOfExp;
    private String mobileNum;
    private String email;
    private int endorsedCompanyID;
    private int endorsedJobID;
    
    private int statusID;

    public BatchUploadData() {
    }

    public BatchUploadData(
            int pid, String transactionLog, String name,
            String fname, String lname, String jobTitle, String company,
            String yrsOfExp, String mobilenum, String email, int endorsedCompanyID,
            int endorsedJobID, int statusID) {
        this.pid = pid;
        this.name = name;
        this.fname = fname;
        this.lname = lname;
        this.jobTitle = jobTitle;
        this.company = company;
        this.yrsOfExp = yrsOfExp;
        this.mobileNum = mobilenum;
        this.email = email;
        this.endorsedCompanyID = endorsedCompanyID;
        this.endorsedJobID = endorsedJobID;
        this.transactionLog = transactionLog;
        this.statusID = statusID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(String transactionLog) {
        this.transactionLog = transactionLog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYrsOfExp() {
        return yrsOfExp;
    }

    public void setYrsOfExp(String yrsOfExp) {
        this.yrsOfExp = yrsOfExp;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEndorsedCompanyID() {
        return endorsedCompanyID;
    }

    public void setEndorsedCompanyID(int endorsedCompanyID) {
        this.endorsedCompanyID = endorsedCompanyID;
    }

    public int getEndorsedJobID() {
        return endorsedJobID;
    }

    public void setEndorsedJobID(int endorsedJobID) {
        this.endorsedJobID = endorsedJobID;
    }

    
}
