package it.polito.applied.mad.teamMaker.repository;

import it.polito.applied.mad.teamMaker.pojo.GroupRequest;

public interface CustomGroupRequestRepository {
	public GroupRequest addToConfirmed(String groupRequestId, String studentId);
}
