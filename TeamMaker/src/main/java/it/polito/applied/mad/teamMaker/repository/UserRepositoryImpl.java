package it.polito.applied.mad.teamMaker.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import it.polito.applied.mad.teamMaker.pojo.Status;
import it.polito.applied.mad.teamMaker.pojo.User;

public class UserRepositoryImpl implements CustomUserRepository{
	
	@Autowired
	private MongoOperations mongoOp;
	
	@Override
	public List<User> findAllUserAvailable() {
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, 1);
		d = c.getTime();
		Query q = new Query();
		Criteria c1 = new Criteria();
		Criteria c2 = new Criteria();
		c1.andOperator(Criteria.where("status").is(Status.PENDING), Criteria.where("lastGroupRequest").gt(d));
		c2.orOperator(Criteria.where("status").is(Status.FREE),c1);
		q.addCriteria(c2);
		return mongoOp.find(q, User.class);
	}

	@Override
	public List<User> findUsersAvailable(List<String> attendees) {
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, 1);
		d = c.getTime();
		Query q = new Query();
		Criteria c1 = new Criteria();
		Criteria c2 = new Criteria();
		Criteria c3 = new Criteria();
		c1.andOperator(Criteria.where("status").is(Status.PENDING), Criteria.where("lastGroupRequest").gt(d));
		c2.orOperator(Criteria.where("status").is(Status.FREE), c1);
		q.addCriteria(c3.andOperator(Criteria.where("id").in(attendees), c2));
		return mongoOp.find(q, User.class);
	}

}
