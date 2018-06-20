package com.jake;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitObject {
	private String sha;
 	private String type;
 	private String url;

    public CommitObject() {
    }

    public String getSha() {
        return this.sha;
    }
    
    public void setSha(String sha) {
        this.sha = sha;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "Commit{" +
                "Last commit hash=" + sha +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
