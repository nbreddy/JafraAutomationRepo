package com.cognizant.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.mail.EmailException;

import com.cognizant.support.DriverScript;


public class CreateHtmlResult {
	static String environment="";
	public static void createHtmlResult(String logFilePath) throws IOException, EmailException{
		if(DriverScript.env.contains("https://vwdcqv-uxapp124"))
			environment="QA1";
		if(DriverScript.env.contains("https://qa-www"))
			environment="QA2";
		if(DriverScript.env.contains("https://www"))
			environment="Production";
		int totalTC = ResultGenerator.passCount+ResultGenerator.failCount;
		StringBuilder htmlString = new StringBuilder();
		htmlString.append("<html><head><title>Jafra Automation</title></head>");
		htmlString.append("<body align=\"center\">");
		htmlString.append("<h2 align=\"center\"><b>JAFRA AUTOMATION EXECUTION REPORT</h2>");
		htmlString.append("<h4 align=\"center\">Environment: "+environment+"</h4>");
		htmlString.append("<table width=\"40%\" border=\"1\" bordercolor=\"#000000\" bgcolor=\"#EBEEF4\">"
				+ "<tr><td width=\"30%\"><b>Total Number of Test Cases Executed</td><td width=\"10%\" align=\"center\">"+totalTC+"</td>"
						+ "<tr><td width=\"30%\"><b>Total Number of Test Cases Pass</td><td width=\"10%\" align=\"center\"><font color=\"#50864D\">"+ResultGenerator.passCount+"</td></tr>"
								+ "<tr><td width=\"30%\"><b>Total Number of Test Cases Fail</td><td width=\"10%\" align=\"center\"><font color=\"ff0000\">"+ResultGenerator.failCount+"</td></tr>"
				+ "</table>");
		htmlString.append("</br>");
		htmlString.append("<table width=\"90%\" border=\"1\" bordercolor=\"#000000\" bgcolor=\"#EBEEF4\">");
		htmlString.append("<tr  align=\"center\">");
		htmlString.append("<td><b> TEST CASE </td>");
		htmlString.append("<td><b> DESCRIPTION </td>");
		htmlString.append("<td><b> STATUS </td>");
		htmlString.append("<td><b> DATE-TIME (PST)</td>");
		htmlString.append("</tr>");
		htmlString.append(ResultGenerator.htmlString.toString());
		htmlString.append("</body></html>");
		
		File htmlFile = new File(logFilePath);
		OutputStream outFile = new FileOutputStream(htmlFile);
		Writer writer = new OutputStreamWriter(outFile);
		writer.write(htmlString.toString());
		writer.close();
		outFile.close();
		
		SendMailSSLWithAttachment.sendReport(htmlString.toString());
		
	}
	
	public static void writeToDocument(){
		
	}
}























//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Date;
//
//
//public class CreateHtmlResult{
//	public static void main(String arg[]) throws IOException{
//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//String d = dateFormat.format(new Date());
//String c = "Bittu";
//		StringBuilder htmlString = new StringBuilder();
//		htmlString.append("<html><head><title>Jafra Automation</title></head>");
//		htmlString.append("<body>");
//		htmlString.append("<p style = background-color:\"#0B4DF2\" align=\"center\" width=\"100%\" >JAFRA AUTOMATION EXECUTION STATUS</p>");
//		htmlString.append("<table width=\"100%\" border=\"1\" bgcolor=\"#415A76\">");
//		htmlString.append("<tr ><td width=\"10%\"><font color=\"white\"><b> TEST CASE </td>");
//		htmlString.append("<td width=\"60%\"><font color=\"white\"> <b>TEST DESCRIPTION </td>");
//		htmlString.append("<td width=\"10%\"><font color=\"white\"> <b>TEST STATUS</font></td>");
//		htmlString.append("<td width=\"20%\"><font color=\"white\"> <b>DATE</font></td></tr>");
//
//		
//		htmlString.append("<tr><td ><font color=\"#03FDDF\"> TC_001B2B </td>");
//		htmlString.append("<td bgcolor><font color=\"#E3FD03\"> ncjasnc nsjkcnkfnjkn</td>");
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> Pass</font></td>");	
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> (<%= name%>) </font></td></tr>");	
//
//htmlString.append("<tr><td ><font color=\"#EA03FD\"> TC_001B2B </td>");
//		htmlString.append("<td bgcolor><font color=\"#E3FD03\"> ncjasnc ncjasnc ncjasnc ncjasnc ncjasnc ncjasnc </td>");
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> Pass</font></td>");	
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> dateFormat.format(new Date()).toString()</font></td></tr>");	
//htmlString.append("<tr><td ><font color=\"#E303FD\"> TC_001B2B </td>");
//		htmlString.append("<td bgcolor><font color=\"#E3FD03\"> ncjasnc nsjkcnkfnjkn</td>");
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> Pass</font></td>");	
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> dateFormat.format(new Date()).toString()</font></td></tr>");	
//htmlString.append("<tr><td ><font color=\"#E303FD\"> TC_001B2B </td>");
//		htmlString.append("<td bgcolor><font color=\"#E3FD03\"> ncjasnc nsjkcnkfnjkn</td>");
//		htmlString.append("<td bgcolor><font color=\"ff0000\"> Fail</font></td>");	
//		htmlString.append("<td bgcolor><font color=\"00ff00\">new Date();</font></td></tr>");	
//htmlString.append("<tr><td ><font color=\"#E303FD\"> TC_001B2B </td>");
//		htmlString.append("<td bgcolor><font color=\"#E3FD03\"> ncjasnc nsjkcnkfnjkn</td>");
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> Pass</font></td>");
//
//		htmlString.append("<td bgcolor><font color=\"00ff00\"> ");
//
//		htmlString.append("<script>var fruits, addr;"
//				+ "fruits = [\"Banana\", \"Orange\", \"Apple\", \"Mango\"]; "
//				+ "addr = [];"
//				+ "addr.push(\"address1\");"
//				+ "addr.push(\"address2\");"
//				+ "document.write(fruits[1]);"
//				+ "document.write(addr[1]);");
//		
////		htmlString.append("var fruits, addr;");
////		htmlString.append("fruits = [\"Banana\", \"Orange\", \"Apple\", \"Mango\"];");
////		htmlString.append("document.write(fruits[1]);");
//		
//		htmlString.append("</script></font></td></tr>");
//
//
//	
//
//
//		htmlString.append("<p id=\"demo\"></p>");
//htmlString.append("<script>var d = new Date().toLocaleDateString(\"en-US\");"
//		+ "var t = new Date().toLocaleTimeString(\"en-US\");");
//htmlString.append("document.getElementById(\"demo\").innerHTML = d;"
//		+ ""
//		+ "</script>");
//		
//		htmlString.append("</body></html>");
//		
//		File htmlFile = new File("C:\\Users\\372815\\Desktop\\result.html");
//		OutputStream outFile = new FileOutputStream(htmlFile);
//		Writer writer = new OutputStreamWriter(outFile);
//		writer.write(htmlString.toString());
//		writer.close();
//		outFile.close();
//		
//	}
//	
//	public static void writeToDocument(){
//		
//	}
//}
//
//
