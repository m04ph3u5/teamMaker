package it.polito.applied.mad.teamMaker.pojo;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Group {

	private String id;
	@Indexed(unique=true)
	private int number;
	private List<String> membersId;
	private String groupName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<String> getMembersId() {
		return membersId;
	}
	public void setMembersId(List<String> membersId) {
		this.membersId = membersId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
