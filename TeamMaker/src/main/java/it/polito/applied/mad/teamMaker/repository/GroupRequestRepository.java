package it.polito.applied.mad.teamMaker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.GroupRequest;

public interface GroupRequestRepository extends MongoRepository<GroupRequest, String>, CustomGroupRequestRepository{

	public GroupRequest findById(String id);

}
