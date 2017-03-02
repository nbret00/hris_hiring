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
public class AllPersonActivity implements Serializable{

    private String fname;
    private String lname;
    private String jobTitle;
    private String company;
    private String yrsOfExp;
    private String endorsedCompany;
    private String endorsedJob;
    private int activity;
    private int activityTp;       
    private int pid;
    
    public AllPersonActivity(){}

    public AllPersonActivity(int pid, String fname, String lname, String jobTitle, String company, String yrsOfExp, int activity, int activityTp, String endorsedCompany, String endorsedJob) {
        this.pid = pid;
        this.fname = fname;
        this.lname = lname;
        this.jobTitle = jobTitle;
        this.company = company;
        this.yrsOfExp = yrsOfExp;
        this.activity = activity;
        this.activityTp = activityTp;
        this.endorsedCompany = endorsedCompany;
        this.endorsedJob = endorsedJob;
    }

    public String getEndorsedCompany() {
        return endorsedCompany;
    }

    public void setEndorsedCompany(String endorsedCompany) {
        this.endorsedCompany = endorsedCompany;
    }

    public String getEndorsedJob() {
        return endorsedJob;
    }

    public void setEndorsedJob(String endorsedJob) {
        this.endorsedJob = endorsedJob;
    }
    
    

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getActivtyTp() {
        return activityTp;
    }

    public void setActivtyTp(int activityTp) {
        this.activityTp = activityTp;
    }
    
    
            
    
}
