package com.jake;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit 
{
	 	private String ref;
	 	private String node_id;
	 	private String url;
	 	private CommitObject object;

	    public Commit() {
	    }

	    public String getRef() {
	        return this.ref;
	    }
	    
	    public void setRef(String ref) {
	        this.ref = ref;
	    }
	    
	    public String getNode_Id() {
	        return this.node_id;
	    }
	    
	    public void setNode_Id(String node_id) {
	        this.node_id = node_id;
	    }
	    
	    public String getUrl() {
	        return this.url;
	    }
	    
	    public void setUrl(String url) {
	        this.url = url;
	    }
	    
	    public CommitObject getCommitObject() {
	        return this.object;
	    }
	    
	    public void setObject(CommitObject object) {
	        this.object = object;
	    }

	    @Override
	    public String toString() {
	        return "Commit{" +
	                "ref=" + ref +
	                ", URL='" + url + '\'' +
	                ", CommitDetails='" + object + '\'' +
	                '}';
	    }

}
