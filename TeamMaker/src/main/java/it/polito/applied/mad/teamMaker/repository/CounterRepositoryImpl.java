package it.polito.applied.mad.teamMaker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import it.polito.applied.mad.teamMaker.pojo.Counter;

public class CounterRepositoryImpl implements CustomCounterRepository {
	
	@Autowired
	private MongoOperations mongoOp;

	@Override
	public int getNextSequence(String id) {
		Query q = new Query();
		q.addCriteria(Criteria.where("id").is(id));
		Update u = new Update();
		u.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		return mongoOp.findAndModify(q, u, options, Counter.class).getSeq();
	}

}
