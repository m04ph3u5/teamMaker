package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import it.polito.applied.mad.teamMaker.pojo.User;
import it.polito.applied.mad.teamMaker.pojo.UserRequest;

public interface AsyncExecutor {
	
	public void sendConfirmEmail(List<UserRequest> requests, List<User> users, String groupName);

}
