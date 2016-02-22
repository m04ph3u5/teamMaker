package it.polito.applied.mad.teamMaker.service;

import java.util.List;

import it.polito.applied.mad.teamMaker.pojo.Group;
import it.polito.applied.mad.teamMaker.pojo.RequestDTO;
import it.polito.applied.mad.teamMaker.pojo.User;

public interface OperationService {

	List<Group> getAllGroups();
	List<User> getAllAvailableUsers();
	boolean createTeam(RequestDTO request);

}
