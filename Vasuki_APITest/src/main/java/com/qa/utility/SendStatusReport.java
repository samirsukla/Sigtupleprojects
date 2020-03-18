package com.qa.utility;

import org.testng.annotations.Test;

import com.qa.utility.GenerateSystemDate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

//This class will attach the report in the mail and send it to the mentioned email Ids.
public class SendStatusReport {
	static GenerateSystemDate getDate;

	@Test
	public void sendMail() throws FileNotFoundException, IOException {
		getDate = new GenerateSystemDate();
		String todaysDate = getDate.getPresentDate();

		String[] sendTo = new String[] { "samir.ks@sigtuple.com"};  //Mention the mail Ids to be in "To Section"
		String[] sendCC = new String[] { "ravi@sigtuple.com" }; //Mention the mail Ids to be in "CC Section"

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("testing@sigtuple.com", "abcd@1234"); //Senders email Id credentials
			}
		});

		try {
			InternetAddress[] mailAddressTO = new InternetAddress[sendTo.length];
			for (int i = 0; i < sendTo.length; i++) {
				mailAddressTO[i] = new InternetAddress(sendTo[i]);
			}

			InternetAddress[] mailAddressCC = new InternetAddress[sendCC.length];
			for (int i = 0; i < sendCC.length; i++) {
				mailAddressCC[i] = new InternetAddress(sendCC[i]);
			}

			MimeMessage message = new MimeMessage(session);
			message.addRecipients(Message.RecipientType.TO, mailAddressTO);
			message.addRecipients(Message.RecipientType.CC, mailAddressCC);
			String mailSubject = "Vasuki API Sanity Automation Report on " + todaysDate; //Mention the Mail Subject
			message.setSubject(mailSubject);

			
			  /* HTML Embedded Mail Body 

	         StringWriter writer = new StringWriter();
	         IOUtils.copy(new FileInputStream(new File("target/surefire-reports/emailable-report.html")), writer, "ISO-8859-1");
	         MimeBodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setContent(writer.toString(), "text/html");
	         Multipart multipart = new MimeMultipart();
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart); 
	          HTML Embeded Mail Body */
			 

			 //Attachment Section 
			
			  BodyPart messageBodyPart1 = new MimeBodyPart(); 
			  messageBodyPart1.setText("Please find the status report attached for Vasuki APIs Sanity Automation Suite"); //This text will be in the Mail Body.
			  MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			  String filename ="test-output/ExtentReportsTestNG.html";  //Mention the report path 
			  DataSource source = new FileDataSource(filename);
			  messageBodyPart2.setDataHandler(new DataHandler(source)); 
			  messageBodyPart2.setFileName(filename); 
			  Multipart multipart = new MimeMultipart();
			  multipart.addBodyPart(messageBodyPart1);
			  multipart.addBodyPart(messageBodyPart2); 
			  message.setContent(messageBodyPart2,"text/html; charset=utf-8");
			  message.setContent(multipart );
			 
			 //Attachment Section 

			Transport.send(message);
			System.out.println("Mail successfully sent");
		} catch (MessagingException mex) {

			mex.printStackTrace();
			System.out.println("Mail Couldn't be Delivered.");
		}
	}
}
