package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import it.polito.applied.mad.teamMaker.pojo.Team;
import it.polito.applied.mad.teamMaker.pojo.RequestDTO;
import it.polito.applied.mad.teamMaker.pojo.User;

public interface OperationService {

	List<Team> getAllGroups();
	List<User> getAllAvailableUsers();
	boolean createTeam(RequestDTO request);
	int confirmUser(String studentId, String token);
	boolean cancelGroupRequest(String studentId, String token);

}
