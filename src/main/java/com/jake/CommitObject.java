package com.jake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitObject 
{
	 	private String sha;

	    public CommitObject() {
	    }

	    public String getSha() {
	        return this.sha;
	    }
	    
	    public void setId(String sha) {
	        this.sha = sha;
	    }

	    @Override
	    public String toString() {
	        return "CommitObject{" +
	                "commitHash=" + sha +
	                '}';
	    }

}
