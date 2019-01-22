package com.inc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.inc.domain.Diary;
import com.inc.domain.User;
import com.inc.service.DiaryService;

@Controller
public class DiaryController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private DiaryService diaryService;
	
	
	@GetMapping("/")
	public String main(Model model) {
		User user = (User)session.getAttribute("user");
		model.addAttribute("diaryList", 
						    diaryService.list(
						    		user.getId()));
		return "/main";
	}
	
	@GetMapping("/diary/add")
	public String add(@ModelAttribute Diary diary) {
		return "/diary/add";
	}
	
	@PostMapping("/diary/add")
	public String add(@ModelAttribute @Valid Diary diary,
					  BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/diary/add";
		}
		diary.setUsers_id(
			((User)session.getAttribute("user")).getId());
		diaryService.add(diary);
		return "redirect:/";
	}	
	
	
}







