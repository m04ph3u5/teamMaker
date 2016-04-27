package it.polito.applied.mad.teamMaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.polito.applied.mad.teamMaker.pojo.RequestDTO;
import it.polito.applied.mad.teamMaker.pojo.Status;
import it.polito.applied.mad.teamMaker.pojo.Team;
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
		List<Team> teams = service.getAllGroups();
		m.addAttribute("groups", teams);
	    return "index";
	}
	
	@RequestMapping(value="/addTeam", method=RequestMethod.GET)
	public String getAddTeam(Model m) {
		System.out.println("addTeam");
		List<User> users = userRepo.findAllUserAvailable();
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
	
	@RequestMapping(value="/confirm", method=RequestMethod.GET)
	public String confirmAttendeePartecipation(Model m, @RequestParam(value="studentId", required=true) String studentId, @RequestParam(value="token", required=true) String token) {
		int groupNumber = service.confirmUser(studentId, token);
		System.out.println(groupNumber);
		if(groupNumber!=0){
			if(groupNumber>0)
				m.addAttribute("groupNumber",groupNumber);
			else{
				m.addAttribute("groupNumber",0);
				m.addAttribute("missingAttendees", groupNumber*(-1));
			}
			return "confirmed";
		}
		else
			return "404";
	}
	
	@RequestMapping(value="/cancel", method=RequestMethod.GET)
	public String cancelGroupRequest(@RequestParam(value="studentId", required=true) String studentId, @RequestParam(value="token", required=true) String token) {
		boolean canceled = service.cancelGroupRequest(studentId, token);
		if(canceled)
			return "canceled";
		else
			return "404";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String errorPage() {
		return "error";
	}
	
}
