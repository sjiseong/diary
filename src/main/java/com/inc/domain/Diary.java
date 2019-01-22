package com.inc.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Diary {
	private int id;
	private String users_id;
	@Size(min=1, message="한 글자 이상 작성해 주세요")
	@Size(max=15, message="15글자 이내로 작성해 주세요")
	private String title;
	@NotNull(message="한 글자 이상 작성해 주세요")
	@Size(max=500, message="500글자 이내로 작성해 주세요")
	private String content;
	private String regdate;
	
	private List<Tag> tags;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
	
	
}
