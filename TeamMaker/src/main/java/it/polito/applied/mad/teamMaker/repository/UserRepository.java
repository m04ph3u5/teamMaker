package it.polito.applied.mad.teamMaker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.applied.mad.teamMaker.pojo.Status;
import it.polito.applied.mad.teamMaker.pojo.User;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository{
	
	public User findById(String id);
	public User findByStudentId(String studentId);
	public List<User> findByGroupId(String groupId);
	public List<User> findByStatus(Status status);
}
