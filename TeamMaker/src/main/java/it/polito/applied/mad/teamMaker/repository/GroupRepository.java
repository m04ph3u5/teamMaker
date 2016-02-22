package it.polito.applied.mad.teamMaker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.Group;

public interface GroupRepository extends MongoRepository<Group, String>{

	public Group findById(String id);
	public List<Group> findAll();
	public Group findByNumber(int number);
	
}
