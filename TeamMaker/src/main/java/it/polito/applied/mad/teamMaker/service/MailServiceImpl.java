package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.polito.applied.mad.teamMaker.pojo.User;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendConfirmationMessage(String studentId, String token, List<User> users, String groupName) {
		
	}
	
	
	
	

}
