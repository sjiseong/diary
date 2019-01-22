package com.inc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inc.domain.Diary;

@Repository
public class DairyDao {
	
	@Autowired
	private SqlSession session;
	
	public List<Diary> list(String id){
		return session.selectList("diary.list", id);
	}

	public void add(Diary diary) {
		session.insert("diary.add", diary);
	}
}




