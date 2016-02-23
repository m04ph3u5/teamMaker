package it.polito.applied.mad.teamMaker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.UserRequest;

public interface UserRequestRepository extends MongoRepository<UserRequest, String>, CustomUserRequestRepository {

	public UserRequest findByTokenAndStudentId(String token, String studentId);

	public UserRequest findByStudentId(String studentId);

	
}
