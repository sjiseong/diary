package com.inc.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inc.domain.Tag;

@Repository
public class TagDao {

	@Autowired
	private SqlSession session;
	
	public void add(Tag tag) {
		session.insert("tag.add", tag);
	}
	
}











