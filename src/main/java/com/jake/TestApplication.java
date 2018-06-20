package com.jake;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestApplication 
{

	//Setup logger to use for REST response output
	private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(TestApplication.class);
    }
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			String User;
			String Repo;
			String Date;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			System.out.println(currentDate);
			Scanner input = new Scanner(System.in);
	        System.out.println("Enter the user name to search for:");
	        User = input.next();
	        System.out.println("And now enter a repository from that user to analyse:");
	        Repo = input.next();
	        System.out.println("Enter the date to search from(use format YYYY-MM-DD):");
	        Date = input.next();
	        
	        


	        
			//brings back latest commit 
	    	Commit commit = restTemplate.getForObject(
					"https://api.github.com/repos/" + User + "/" + Repo + "/git/refs/heads/master", Commit.class);
			
			//Commit commit = restTemplate.getForObject(
					//"https://api.github.com/search/commits?q=repo:" + User + "/" + Repo + "+committer-date:2018-01-01.." + currentDate, Commit.class);
			log.info(commit.toString());
			
			if(commit.getCommitObject().getSha() != null)
			{
				HttpHeaders headers = new HttpHeaders();
		        headers.set("Accept", "application/vnd.github.cloak-preview");
		        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		        
		        //USE SPRING BELOW TO GIVE RANGE TO CURRENT DATE AND FETCH ALL COMMIT INFO 
		        
		        ///repos/:owner/:repo/commits/:sha
		       //String url = "https://api.github.com/search/commits?q=repo:" + User + "/" + Repo + "+committer-date:2018-01-01.." + currentDate;
		       
		        //Perform Get request with input parameters
		        String url = "https://api.github.com/search/commits?q=repos/" + User + "/" + Repo + "/commits/" + commit.getCommitObject().getSha() + "+committer-date/"+ Date + ".." + currentDate;
		        log.debug(url);
		        ResponseEntity<String> difference = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		    	if(difference.getStatusCodeValue() == 200)
		    	{
		    		System.out.println("Successful response received");
		    		System.out.println("Difference between latest commit and previous commits in range shown below...");
		    		System.out.println("========================================");
		    		System.out.println(difference.getBody());
		    		System.out.println("========================================");

		    	}
		    	else
		    		{
		    			System.out.println("Request to GitLab not good with status code: " + difference.getStatusCodeValue());
		    			System.out.println(difference.getBody());

		    		} 	
			}
			else
			{
				System.out.println("No Commit hash detected, Most likely no commit made yet");
			}
		};
	}

}