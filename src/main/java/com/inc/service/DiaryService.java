package com.inc.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inc.dao.DairyDao;
import com.inc.dao.TagDao;
import com.inc.domain.Diary;
import com.inc.domain.Tag;

@Service
public class DiaryService {
	
	@Autowired
	private DairyDao diaryDao;
	
	@Autowired
	private TagDao tagDao;
	
	public List<Diary> list(String id){
		return diaryDao.list(id);
	}

	@Transactional
	public void add(Diary diary) {
		diaryDao.add(diary);
		for(Tag tag : diary.getTags()) {
			tag.setDiary_id(diary.getId());
			tagDao.add(tag);
		}
	}
}







