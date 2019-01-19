package com.onlinetutorilspoint;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMailController {
	
	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("temphyd77@gmail.com");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail is sent Successfully...";
	}

	@RequestMapping("/sendMailAtt")
	public String sendMailAttachment() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		try {
			helper.setTo("temphyd77@gmail.com");
			helper.setText("Greetings :)\n Please find the attached docuemnt for your reference.");
			helper.setSubject("Mail From Spring Boot");
			//ClassPathResource file = new ClassPathResource("document.PNG");
			//helper.addAttachment("document.PNG", file);
			//ClassPathResource file = new ClassPathResource("CuteBoy.jpg");
			//helper.addAttachment("CuteBoy.jpg", file);
			//ClassPathResource file = new ClassPathResource("Test.doc");
			//helper.addAttachment("Test.doc", file);
			//ClassPathResource file = new ClassPathResource("Reppalanindaa-Love-BGM.mp3");
			//helper.addAttachment("Reppalanindaa-Love-BGM.mp3", file);	
            ClassPathResource file = new ClassPathResource("Sample.pptx");
			helper.addAttachment("Sample.pptx", file);			
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail is sent Successfully...";
	}

}
