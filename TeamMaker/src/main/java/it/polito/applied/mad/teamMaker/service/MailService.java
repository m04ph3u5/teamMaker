package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import it.polito.applied.mad.teamMaker.pojo.User;

public interface MailService {
	
	public void sendConfirmationMessage(String studentId, String token, List<User> users, String groupName);

}
