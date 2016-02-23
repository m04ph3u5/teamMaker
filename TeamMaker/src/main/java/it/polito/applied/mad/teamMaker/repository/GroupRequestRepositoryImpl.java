package it.polito.applied.mad.teamMaker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import it.polito.applied.mad.teamMaker.pojo.GroupRequest;

public class GroupRequestRepositoryImpl implements CustomGroupRequestRepository{
	
	@Autowired
	private MongoOperations mongoOp;

	@Override
	public GroupRequest addToConfirmed(String groupRequestId, String studentId) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(groupRequestId));
		Update u = new Update();
		u.pull("toConfirmRequestsId", studentId);
		u.push("usersIdConfirmed", studentId);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		return mongoOp.findAndModify(q, u, options, GroupRequest.class);
	}

}
