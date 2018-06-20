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
	        System.out.println("Enter the repository to analyse:");
	        Repo = input.next();
	       // System.out.println("Enter the date to search from(use format YYYY-MM-DD):");
	        //Date = input.next();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Accept", "application/vnd.github.cloak-preview");
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	        
	        //USE SPRING BELOW TO GIVE RANGE TO CURRENT DATE AND FETCH ALL COMMIT INFO 
	        
	        //String url = "\\https://api.github.com/search/commits?q=repo:" + User + "/" + Repo + "+committer-date:2018-01-01.." + currentDate;
	        
	       // ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    	
	    	//System.out.println(result);


	        
			//brings back latest commit 
	    	Commit commit = restTemplate.getForObject(
					"https://api.github.com/repos/" + User + "/" + Repo + "/git/refs/heads/master", Commit.class);
			
			//Commit commit = restTemplate.getForObject(
					//"https://api.github.com/search/commits?q=repo:" + User + "/" + Repo + "+committer-date:2018-01-01.." + currentDate, Commit.class);
			log.info(commit.toString());
		};
	}

}