package it.polito.applied.mad.teamMaker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.polito.applied.mad.teamMaker.pojo.GroupRequest;
import it.polito.applied.mad.teamMaker.pojo.RequestDTO;
import it.polito.applied.mad.teamMaker.pojo.Status;
import it.polito.applied.mad.teamMaker.pojo.Team;
import it.polito.applied.mad.teamMaker.pojo.User;
import it.polito.applied.mad.teamMaker.pojo.UserRequest;
import it.polito.applied.mad.teamMaker.repository.CounterRepository;
import it.polito.applied.mad.teamMaker.repository.GroupRequestRepository;
import it.polito.applied.mad.teamMaker.repository.TeamRepository;
import it.polito.applied.mad.teamMaker.repository.UserRepository;
import it.polito.applied.mad.teamMaker.repository.UserRequestRepository;

@Service
public class OperationServiceImpl implements OperationService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRequestRepository userRequestRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private GroupRequestRepository groupRequestRepo;
	
	@Autowired
	private CounterRepository counterRepo;
	
	@Autowired
	private AsyncExecutor asyncExecutor;
	
	
	@Override
	public List<Team> getAllGroups() {
		return teamRepo.findAll();
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
		for(String s : usersId)
			System.out.println(s);
		List<User> users = userRepo.findUsersAvailable(usersId);
		if(users.size()==usersId.size()){
			Date date = new Date();
			GroupRequest g = new GroupRequest();
			g.setDate(date);
			g.setGroupName(request.getGroupName());
			g.setToConfirmRequestsId(usersId);
			g.setCancelingToken(UUID.randomUUID().toString());
			g = groupRequestRepo.save(g);
			List<UserRequest> userRequests = new ArrayList<UserRequest>();
			for(String s : usersId){
				UserRequest ur = new UserRequest();
				ur.setDate(date);
				ur.setGroupRequestId(g.getId());
				User u = userRepo.findByStudentId(s);
				ur.setStudentId(u.getStudentId());
				ur.setToken(UUID.randomUUID().toString());
				userRequests.add(ur);
				u.setStatus(Status.PENDING);
				u.setLastGroupRequest(date);
				userRepo.save(u);
				userRequestRepo.save(ur);
			}
			asyncExecutor.sendConfirmEmail(userRequests, users, request.getGroupName(), g.getCancelingToken());
			return true;
		}else
			return false;
	}

	@Override
	public int confirmUser(String studentId, String token) {
		UserRequest request = userRequestRepo.findByTokenAndStudentId(token, studentId);
		if(request==null)
			return 0;
		GroupRequest gr = groupRequestRepo.addToConfirmed(request.getGroupRequestId(), studentId);
		if(gr!=null){
			userRequestRepo.delete(request.getId());
			if(gr.getToConfirmRequestsId().isEmpty()){
				Team team = new Team();
				team.setGroupName(gr.getGroupName());
				List<User> members = new ArrayList<User>();
				for(String s : gr.getUsersIdConfirmed())
					members.add(userRepo.findByStudentId(s));
				team.setMembers(members);
				team.setNumber(""+counterRepo.getNextSequence("teamNumber"));
				teamRepo.save(team);
				groupRequestRepo.delete(gr.getId());
				userRepo.setToConfirmed(gr.getUsersIdConfirmed());
				return Integer.parseInt(team.getNumber());
			}else
				return (-1)*gr.getToConfirmRequestsId().size();
		}else
			return 0;
	}

	@Override
	public boolean cancelGroupRequest(String studentId, String token) {
		UserRequest request = userRequestRepo.findByStudentId(studentId);
		if(request==null)
			return false;
		GroupRequest gr = groupRequestRepo.findById(request.getGroupRequestId());
		if(gr==null || !gr.getCancelingToken().equals(token))
			return false;
		List<String> studentsId = new ArrayList<String>();
		if(gr.getToConfirmRequestsId()!=null){
			for(String s : gr.getToConfirmRequestsId())
				studentsId.add(s);
		}
		if(gr.getUsersIdConfirmed()!=null){
			for(String s: gr.getUsersIdConfirmed())
				studentsId.add(s);
		}
		userRequestRepo.deleteUsersRequest(studentsId);
		userRepo.setToFree(studentsId);
		groupRequestRepo.delete(gr.getId());
		asyncExecutor.sendCancelingEmail(gr, studentsId);
		return true;
	}
	

}
