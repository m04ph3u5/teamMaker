package it.polito.applied.mad.teamMaker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.Team;

public interface TeamRepository extends MongoRepository<Team, String>{

	public List<Team> findAll();
	public Team findByNumber(int number);
	
}
