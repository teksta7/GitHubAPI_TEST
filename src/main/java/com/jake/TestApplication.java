package com.jake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
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
			CommitObject commit = restTemplate.getForObject(
					"https://api.github.com/repos/teksta7/GitHubAPI_TEST/git/refs/heads/master", CommitObject.class);
			log.info(commit.toString());
		};
	}

}