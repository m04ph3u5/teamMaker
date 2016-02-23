package it.polito.applied.mad.teamMaker.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GroupRequest {

	@Id
	private String id;
	private List<String> toConfirmRequestsId;
	/*StudentID lists*/
	private List<String> usersIdConfirmed;
	@Indexed(expireAfterSeconds=86400)
	private Date date;
	private String groupName;
	private String cancelingToken;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<String> getToConfirmRequestsId() {
		return toConfirmRequestsId;
	}
	public void setToConfirmRequestsId(List<String> toConfirmRequestsId) {
		this.toConfirmRequestsId = toConfirmRequestsId;
	}
	public List<String> getUsersIdConfirmed() {
		return usersIdConfirmed;
	}
	public void setUsersIdConfirmed(List<String> usersIdConfirmed) {
		this.usersIdConfirmed = usersIdConfirmed;
	}
	public String getCancelingToken() {
		return cancelingToken;
	}
	public void setCancelingToken(String cancelingToken) {
		this.cancelingToken = cancelingToken;
	}
	
	
} 
