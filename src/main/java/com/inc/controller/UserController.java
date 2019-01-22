package com.inc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inc.domain.User;
import com.inc.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "/user/signup";
	}
	
	@PostMapping("/user/dupcheck")
	@ResponseBody
	public String dupcheck(@RequestParam String id) {
		User user = userService.findOne(id);
		if(user != null) {
			return "exist";
		}else {
			return "notexist";
		}
	}
	
	@PostMapping("/user/emailcertify")
	@ResponseBody
	public String emailcertify(@RequestParam String email,
							   HttpSession session) {
		if(email.length() == 0) {
			return "null";
		}
		if(userService.emailDupCheck(email)) {
			return "duplicated";
		}
		
		String emailCode = "";
		try {
			emailCode = userService.sendCertifyEmail(email);
		} catch (Exception e) {
			return "error";
		}
		session.setAttribute("email", email);
		session.setAttribute("emailCode", emailCode);
		return "success";
	}
	
	@PostMapping("/user/signup")
	public String signup(@ModelAttribute @Valid User user,
						 BindingResult bindingResult,
						 HttpSession session,
						 Model model) {
		if(userService.findOne(user.getId()) != null) {
			bindingResult.addError(
					new FieldError("existId", "id", user.getId(), false, null, null,
								   "이미 존재하는 아이디 입니다"));
		}
		if(!user.getPassword().equals(
				user.getPasswordConfirm())) {
			bindingResult.addError(
					new FieldError(
							"notSame","passwordConfirm",
						    user.getPasswordConfirm(),false,
						    null, null, "패스워드와 패스워드확인이 불일치"));
		}
		if(!user.getEmailCode().equals(
				session.getAttribute("emailCode"))){
			bindingResult.addError(
				new FieldError(
						"codeError","emailCode",
					    user.getEmailCode(), false,
					    null, null, "인증코드 불일치"));
		}
		if(!user.getEmail().equals(
				session.getAttribute("email"))){
			bindingResult.addError(
						new FieldError(
							"emailError","email",
							user.getEmail(), false,
							null, null, "인증받은 이메일로 가입을 해주세요"));
		}
		if(bindingResult.hasErrors()) {
			return "/user/signup";
		}
		session.invalidate();
		userService.signup(user);
		
		return "redirect:/user/signin";
	}
	
	@GetMapping("/user/signin")
	public String signin(Model model) {
		model.addAttribute("user", new User());
		return "/user/signin";
	}
	
	@PostMapping("/user/signin")
	public String signin(@ModelAttribute User user,
						 BindingResult bindingResult,
						 HttpSession session) {
		User existUser = 
				userService.findOne(user.getId());
		if(existUser == null) {
			bindingResult.addError(
				new FieldError(
						"idError", "id", user.getId(),
						false, null, null, "존재하지 않는 아이디"));
		}else if(!existUser.getPassword().equals(
					user.getPassword())) {
			bindingResult.addError(
				new FieldError(
						"passwordError", "password", user.getPassword(),
						false, null, null, "비밀번호 불일치"));
		}
		
		if(bindingResult.hasErrors()) {
			return "/user/signin";
		}
		
		session.setAttribute("user", existUser);
		
		return "redirect:/";
	}
	
	@GetMapping("/user/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/signin";
	}
}





