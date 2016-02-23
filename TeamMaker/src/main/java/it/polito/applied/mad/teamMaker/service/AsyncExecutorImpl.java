package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import it.polito.applied.mad.teamMaker.pojo.GroupRequest;
import it.polito.applied.mad.teamMaker.pojo.User;
import it.polito.applied.mad.teamMaker.pojo.UserRequest;

@Service
public class AsyncExecutorImpl implements AsyncExecutor{

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private MailService mailService;

	@Override
	public void sendConfirmEmail(List<UserRequest> requests, List<User> users, String groupName, String cancelingToken) {
		SendConfirmEmail runnable = new SendConfirmEmail(requests, users, groupName, cancelingToken);
		taskExecutor.execute(runnable);
	}
	
	@Override
	public void sendCancelingEmail(GroupRequest gr, List<String> studentsId) {
		SendCancelingEmail runnable = new SendCancelingEmail(studentsId, gr);
		taskExecutor.execute(runnable);
	}
	
	private class SendConfirmEmail implements Runnable{

		private List<UserRequest> requests;
		private List<User> users;
		private String groupName;
		private String cancelingToken;
		
		public SendConfirmEmail(List<UserRequest> requests, List<User> users, String groupName, String cancelingToken) {
			this.requests = requests;
			this.users = users;
			this.groupName = groupName;
			this.cancelingToken = cancelingToken;
		}
		
		@Override
		public void run() {
			for(UserRequest r : requests){
				mailService.sendConfirmationMessage(r.getStudentId(), r.getToken(), users, groupName, cancelingToken);
			}
		}
		
	}
	
	private class SendCancelingEmail implements Runnable{
		
		private List<String> studentsId;
		private GroupRequest groupRequest;
		
		public SendCancelingEmail(List<String> studentsId, GroupRequest groupRequest) {
			this.groupRequest = groupRequest;
			this.studentsId = studentsId;
		}

		@Override
		public void run() {
			for(String s : studentsId){
				mailService.sendCancelingMessage(s, groupRequest);
			}
		}
		
	}

	
}
