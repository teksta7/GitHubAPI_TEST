package com.jake;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.*;

public class Main {

    public static void printText(String text)
    {
        //boilerplate function to print text with border
        System.out.println("===============================");
        System.out.println(text);
        System.out.println("===============================");
    }

    //Pass in GitHubAPI URL
    public static String option1() throws Exception
    {
        Scanner input = new Scanner(System.in);
        printText("Enter the github project url(Feel free to copy and paste into this window)");
        String url = input.next();
        URL projectURL = new URL(url);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(projectURL.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                printText(line);
            }
        }
        return "a";
    }

    public static String option2() throws Exception
    {
        return "a";
    }

    HttpClient httpClient = new DefaultHttpClient();
    try {
        // this twitter call returns json results.
        // see this page for more info: https://dev.twitter.com/docs/using-search
        // http://search.twitter.com/search.json?q=%40apple

        // Example URL 1: this yahoo weather call returns results as an rss (xml) feed
        //HttpGet httpGetRequest = new HttpGet("http://weather.yahooapis.com/forecastrss?p=80020&u=f");

        // Example URL 2: this twitter api call returns results in a JSON format
        HttpGet httpGetRequest = new HttpGet("http://search.twitter.com/search.json?q=%40apple");

        // Execute HTTP request
        HttpResponse httpResponse = httpClient.execute(httpGetRequest);

        System.out.println("----------------------------------------");
        System.out.println(httpResponse.getStatusLine());
        System.out.println("----------------------------------------");

        // Get hold of the response entity
        HttpEntity entity = httpResponse.getEntity();

        // If the response does not enclose an entity, there is no need
        // to bother about connection release
        byte[] buffer = new byte[1024];
        if (entity != null) {
            InputStream inputStream = entity.getContent();
            try {
                int bytesRead = 0;
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                while ((bytesRead = bis.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, bytesRead);
                    System.out.println(chunk);
                }
            } catch (IOException ioException) {
                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                ioException.printStackTrace();
            } catch (RuntimeException runtimeException) {
                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying
                // connection immediately.
                httpGetRequest.abort();
                runtimeException.printStackTrace();
            } finally {
                // Closing the input stream will trigger connection release
                try {
                    inputStream.close();
                } catch (Exception ignore) {
                }
            }
        }
    } catch (ClientProtocolException e) {
        // thrown by httpClient.execute(httpGetRequest)
        e.printStackTrace();
    } catch (IOException e) {
        // thrown by entity.getContent();
        e.printStackTrace();
    } finally {
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpClient.getConnectionManager().shutdown();
    }
}

    //Pass in GitHubAPI URL and authenticate
    public static String option3() throws Exception
    {
        Scanner input = new Scanner(System.in);
        printText("Enter the github project url(Feel free to copy and paste into this window)");
        String url = input.next();
        URL projectURL = new URL(url);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(projectURL.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                printText(line);
            }
        }
        return "a";
    }

    public static void menu() throws Exception
    {
        //Menu to choose relevant option
        while(true) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Select one of the following options");
            System.out.println("#1: Retrieve contributor stats of public Git project");
            System.out.println("#2: Retrieve stats from project from a given time period");
            System.out.println("#3: Retrieve contributor stats of private Git project");
            System.out.println("#4: Close program");
            int choice = reader.nextInt();
            switch (choice) {
                case 1:
                    String result = option1();
                    printText(result);


                case 3:
                    
            }
            reader.close();
        }
    }
    public static void main(String[] args) throws Exception {
	// write your code here
        printText("Welcome to the GitLab API Test");
        menu();
    }
}
