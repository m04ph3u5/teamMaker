package it.polito.applied.mad.teamMaker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.polito.applied.mad.teamMaker.pojo.Group;
import it.polito.applied.mad.teamMaker.pojo.GroupRequest;
import it.polito.applied.mad.teamMaker.pojo.RequestDTO;
import it.polito.applied.mad.teamMaker.pojo.Status;
import it.polito.applied.mad.teamMaker.pojo.User;
import it.polito.applied.mad.teamMaker.pojo.UserRequest;
import it.polito.applied.mad.teamMaker.repository.GroupRepository;
import it.polito.applied.mad.teamMaker.repository.GroupRequestRepository;
import it.polito.applied.mad.teamMaker.repository.UserRepository;
import it.polito.applied.mad.teamMaker.repository.UserRequestRepository;

@Service
public class OperationServiceImpl implements OperationService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRequestRepository userRequestRepo;
	
	@Autowired
	private GroupRepository groupRepo;
	
	@Autowired
	private GroupRequestRepository groupRequestRepo;
	
	@Autowired
	private AsyncExecutor asyncExecutor;
	
	
	@Override
	public List<Group> getAllGroups() {
		return groupRepo.findAll();
	}
	
	@Override
	public List<User> getAllAvailableUsers() {
		return userRepo.findAllUserAvailable();
	}

	@Override
	public boolean createTeam(RequestDTO request) {
		List<String> usersId = new ArrayList<String>();
		usersId.add(request.getFirstAttendee());
		usersId.add(request.getSecondAttendee());
		usersId.add(request.getThirdAttendee());
		usersId.add(request.getFourthAttendee());
//		for(String s : usersId)
//			System.out.println(s);
		List<User> users = userRepo.findUsersAvailable(usersId);
		if(users.size()==usersId.size()){
			Date date = new Date();
			GroupRequest g = new GroupRequest();
			g.setDate(date);
			g.setGroupName(request.getGroupName());
			g.setToConfirmRequestsId(usersId);
			g = groupRequestRepo.save(g);
			List<UserRequest> userRequests = new ArrayList<UserRequest>();
			for(String s : usersId){
				UserRequest ur = new UserRequest();
				ur.setDate(date);
				ur.setGroupRequestId(g.getId());
				User u = userRepo.findById(s);
				ur.setStudentId(u.getStudentId());
				ur.setToken(UUID.randomUUID().toString());
				userRequests.add(ur);
				u.setStatus(Status.PENDING);
				u.setLastGroupRequest(date);
				userRepo.save(u);
				userRequestRepo.save(ur);
			}
			asyncExecutor.sendConfirmEmail(userRequests, users, request.getGroupName());
			return true;
		}else
			return false;
	}
	

}
