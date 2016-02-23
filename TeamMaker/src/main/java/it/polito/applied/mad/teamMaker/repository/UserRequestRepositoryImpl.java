package it.polito.applied.mad.teamMaker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import it.polito.applied.mad.teamMaker.pojo.UserRequest;

public class UserRequestRepositoryImpl implements CustomUserRequestRepository {
	
	@Autowired
	private MongoOperations mongoOp;

	@Override
	public void deleteUsersRequest(List<String> studentsId) {
		Query q = new Query();
		q.addCriteria(Criteria.where("studentId").in(studentsId));
		mongoOp.remove(q, UserRequest.class);
	}

}
