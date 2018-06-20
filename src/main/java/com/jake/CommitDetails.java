package com.jake;

public class CommitDetails 
{
	private String name;
 	private String email;
 	private String date;

    public CommitDetails() {
    }

    public String getName() {
        return this.name;
    }
    
    public void setRef(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "CommitDetails{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
