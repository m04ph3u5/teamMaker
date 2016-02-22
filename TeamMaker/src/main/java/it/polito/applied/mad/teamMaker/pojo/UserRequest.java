package it.polito.applied.mad.teamMaker.pojo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRequest {

	@Id
	private String id;
	private String token;
	private String studentId;
	private String groupRequestId;
	@Indexed(expireAfterSeconds=86400)
	private Date date;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGroupRequestId() {
		return groupRequestId;
	}
	public void setGroupRequestId(String groupRequestId) {
		this.groupRequestId = groupRequestId;
	}
	
	
	
}