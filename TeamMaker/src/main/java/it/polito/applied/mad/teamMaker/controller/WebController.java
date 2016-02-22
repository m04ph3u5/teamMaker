package it.polito.applied.mad.teamMaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.polito.applied.mad.teamMaker.pojo.Group;
import it.polito.applied.mad.teamMaker.pojo.RequestDTO;
import it.polito.applied.mad.teamMaker.pojo.Status;
import it.polito.applied.mad.teamMaker.pojo.User;
import it.polito.applied.mad.teamMaker.repository.UserRepository;
import it.polito.applied.mad.teamMaker.service.OperationService;

@Controller
public class WebController {
	
	@Autowired
	private OperationService service;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getIndex(Model m) {
		System.out.println("index");
		List<Group> groups = service.getAllGroups();
		m.addAttribute("groups", groups);
	    return "index";
	}
	
	@RequestMapping(value="/addTeam", method=RequestMethod.GET)
	public String getAddTeam(Model m) {
		System.out.println("addTeam");
		List<User> users = userRepo.findByStatus(Status.FREE);
		m.addAttribute("users", users);
	    return "addTeam";
	}
	
	@RequestMapping(value="/createTeam", method=RequestMethod.POST)
	public String postAddTeam(@ModelAttribute RequestDTO request, Model m) {
		if(service.createTeam(request))
			return "requestCreated";
		else
			return "creationError";
	}
	
}
