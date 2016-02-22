package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import it.polito.applied.mad.teamMaker.pojo.User;
import it.polito.applied.mad.teamMaker.pojo.UserRequest;

@Service
public class AsyncExecutorImpl implements AsyncExecutor{

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private MailService mailService;

	@Override
	public void sendConfirmEmail(List<UserRequest> requests, List<User> users, String groupName) {
		SendConfirmEmail runnable = new SendConfirmEmail(requests, users, groupName);
		taskExecutor.execute(runnable);
	}
	
	private class SendConfirmEmail implements Runnable{

		private List<UserRequest> requests;
		private List<User> users;
		private String groupName;
		
		public SendConfirmEmail(List<UserRequest> requests, List<User> users, String groupName) {
			this.requests = requests;
			this.users = users;
			this.groupName = groupName;
		}
		
		@Override
		public void run() {
			for(UserRequest r : requests){
				mailService.sendConfirmationMessage(r.getStudentId(), r.getToken(), users, groupName);
			}
		}
		
	}
}
