package it.polito.applied.mad.teamMaker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.UserRequest;

public interface UserRequestRepository extends MongoRepository<UserRequest, String> {

	public UserRequest findByTokenAndStudentId(String token, String tokenId);
	
}
