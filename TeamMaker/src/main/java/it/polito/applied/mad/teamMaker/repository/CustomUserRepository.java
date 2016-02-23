package it.polito.applied.mad.teamMaker.repository;

import java.util.List;

import it.polito.applied.mad.teamMaker.pojo.User;

public interface CustomUserRepository {
	public List<User> findAllUserAvailable();
	public List<User> findUsersAvailable(List<String> attendees);
	public void setToConfirmed(List<String> usersIdConfirmed);
	public void setToFree(List<String> studentsId);

}
