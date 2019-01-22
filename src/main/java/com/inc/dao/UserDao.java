package com.inc.dao;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inc.domain.User;
@Repository
public class UserDao {
	@Autowired
	private SqlSession session;
	
	public User findOne(String id) {
		return session.selectOne("user.findOne", id);
	}

	public User findOneByEmail(String email) {
		return session.selectOne("user.findOneByEmail", email);
	}

	public void signup(@Valid User user) {
		session.insert("user.signup", user);
		
	}
}






