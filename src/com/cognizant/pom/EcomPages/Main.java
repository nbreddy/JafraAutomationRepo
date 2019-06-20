package com.cognizant.pom.EcomPages;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.math.*;


public class Main {
    @SuppressWarnings("deprecation")
	public static void main(String arg[]) throws IOException, ParseException{
    	System.out.println("Enter the cheque date :");
    	Scanner scan = new Scanner(System.in);
    	
    	String d = scan.next();
    	
    	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    	
    	Date date = df.parse(d);
    	
    	Date bDate = df.parse("01-06-2017");
    	
    	if(date.after(bDate))
    		System.out.println("The cheque is post dated ");
    	else
    		System.out.println("The cheque is not post dated");
    	
    }
}
