package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserDao userDao;

	// 회원가입 폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("/user/joinForm-----------------");

		return "user/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join-----------------");
		
		System.out.println("join controller :" + userVo);
		userDao.insert(userVo);

		return "user/joinOk";
	}

	// 로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("/user/loginForm-----------------");
		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login-----------------");
		System.out.println("loginForm controller: " + userVo);

		UserVo authUser = userDao.selectUser(userVo);
		System.out.println("uesr/selectUser:-----------------");
		// 성공
		if (authUser != null) {
			
			System.out.println("login ok:" + authUser);
			session.setAttribute("authUser", authUser);

			return "redirect:/";
		}
		// 실패
		else {
			System.out.println("login fales:" + authUser);
			return "redirect:/user/loginForm?result=fail";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("uesr/logout:-----------------");
		session.removeAttribute("authUser");
		System.out.println("/user/logoutOk");
		return "redirect:/";
	}

	// 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("uesr/modifyForm:-----------------");
		
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		UserVo authUser = userDao.selectOne(userVo);
		model.addAttribute("authUser", authUser);
		return "user/modifyForm";
	}

	// 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("uesr/modify:-----------------");
		userDao.update(userVo);
		
		UserVo authUser = userDao.selectUser(userVo);
		session.setAttribute("authUser",authUser);
		
		return "redirect:/";
	}

}