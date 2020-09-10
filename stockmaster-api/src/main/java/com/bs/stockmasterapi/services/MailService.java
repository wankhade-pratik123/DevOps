package com.bs.stockmasterapi.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bs.stockmasterapi.exceptions.StockMasterException;
import com.bs.stockmasterapi.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

	private final JavaMailSender javaMailSender;
	private final MailContentBuilder mailContentBuilder;
	
	@Async
	void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setFrom("stockmaster@email.com");
			mimeMessageHelper.setTo(notificationEmail.getRecipient());
			mimeMessageHelper.setSubject(notificationEmail.getSubject());
			mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
		};
		
		try {
			javaMailSender.send(messagePreparator);
			log.info("Activation email sent!!");
		}catch (Exception e) {
			log.error("Exception occurred when sending mail", e);
            throw new StockMasterException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
		}
	}
}
