package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	
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
		
		userService.join(userVo);
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
		UserVo authUser = userService.login(userVo);
		
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
		UserVo authUser = userService.modifyForm(userVo);
		model.addAttribute("authUser", authUser);
		return "user/modifyForm";
	}

	// 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("uesr/modify:-----------------");
		
		userService.modify(userVo);
		UserVo authUser = userService.login(userVo);
		session.setAttribute("authUser",authUser);
		
		return "redirect:/";
	}
	
	//중복확인 아이디 체크
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = { RequestMethod.GET, RequestMethod.POST })
	public String idcheck(@RequestParam("id") String id ,@RequestParam("password") String password) {
		System.out.println("uesr/idcheck:-----------------");
			System.out.println("id = " + id);
			System.out.println("password = " + password);
			String result = userService.idcheck(id);
			System.out.println("controller : " +result );
			
		
		return result; //@ResponseBody response 의 body 영역에 data만 보낸다
	}

}
