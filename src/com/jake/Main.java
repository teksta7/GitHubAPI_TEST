package com.jake;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void printText(String text)
    {
        //boilerplate function to print text with border
        System.out.println("===============================");
        System.out.println(text);
        System.out.println("===============================");
    }

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
