package com.cognizant.report;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendMailSSLWithAttachment {

	public static void sendReport (String message) throws EmailException{

		HtmlEmail email = new HtmlEmail();
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		
		email.setAuthenticator(new DefaultAuthenticator("bittuautomation@gmail.com", ""));
		email.setSSLOnConnect(true);
		email.setFrom("bittuautomation@gmail.com");
		email.setSubject("Jafra Automation Execution");
		email.setHtmlMsg(message);
		email.addTo("bittu.shill@cognizant.com");

		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(ResultGenerator.logFileName);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Jafra execution");
		attachment.setName("Jafra execution");
		email.attach(attachment);
		email.send();
		System.out.println("********************Email sent************************");
	}

	public static void main(String[] arg) throws EmailException{
		HtmlEmail email = new HtmlEmail();
		email.setHostName("APACSMTP.CTS.COM");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("bittu.shill@cognizant.com", "Jun@2018"));
		email.setSSLOnConnect(true);
//		email.setStartTLSEnabled(true);
		email.setFrom("bittu.shill@cognizant.com");
		email.setSubject("Jafra Automation Execution");
		email.setHtmlMsg("<html><body><h2>JavaScript Arrays</h2></body></html>");
		email.addTo("bittu.shill@cognizant.com");
		email.send();
		System.out.println("********************Email sent************************");
	}

	//	public static void main(String[] args) {
	//
	//		// Create object of Property file
	//		Properties props = new Properties();
	//
	//		// this will set host of server- you can change based on your requirement 
	//		props.put("mail.smtp.host", "smtp.gmail.com");
	//
	//		// set the port of socket factory 
	//		props.put("mail.smtp.socketFactory.port", "465");
	//
	//		// set socket factory
	//		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	//
	//		// set the authentication to true
	//		props.put("mail.smtp.auth", "true");
	//
	//		// set the port of SMTP server
	//		props.put("mail.smtp.port", "465");
	//
	//		// This will handle the complete authentication
	//		Session session = Session.getDefaultInstance(props,
	//
	//				new javax.mail.Authenticator() {
	//
	//					protected PasswordAuthentication getPasswordAuthentication() {
	//
	//					return new PasswordAuthentication("bittushil@gmail.com", "BITTUFNXPSSHILL3218Da1947");
	//
	//					}
	//
	//				});
	//
	//		try {
	//
	//			// Create object of MimeMessage class
	//			Message message = new MimeMessage(session);
	//
	//			// Set the from address
	//			message.setFrom(new InternetAddress("bittushil@gmail.com"));
	//
	//			// Set the recipient address
	//			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("bittu.shill@cognizant.com"));
	//            
	//                        // Add the subject link
	//			message.setSubject("Testing Subject");
	//
	//			// Create object to add multimedia type content
	//			BodyPart messageBodyPart1 = new MimeBodyPart();
	//
	//			// Set the body of email
	//			messageBodyPart1.setText("This is message body");
	//
	//			// Create another object to add another content
	//			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
	//
	//			// Mention the file which you want to send
	//			String filename = "C:/Users/372815/Desktop/result.html";
	//
	//			// Create data source and pass the filename
	//			DataSource source = new FileDataSource(filename);
	//
	//			// set the handler
	//			messageBodyPart2.setDataHandler(new DataHandler(source));
	//
	//			// set the file
	//			messageBodyPart2.setFileName(filename);
	//
	//			// Create object of MimeMultipart class
	//			Multipart multipart = new MimeMultipart();
	//
	//			// add body part 1
	//			multipart.addBodyPart(messageBodyPart2);
	//
	//			// add body part 2
	//			multipart.addBodyPart(messageBodyPart1);
	//
	//			// set the content
	//			message.setContent(multipart);
	//
	//			// finally send the email
	//			Transport.send(message);
	//
	//			System.out.println("=====Email Sent=====");
	//
	//		} catch (MessagingException e) {
	//
	//			throw new RuntimeException(e);
	//
	//		}
	//
	//	}

}