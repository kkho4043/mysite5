package com.javaex.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("guest/")
public class GuestController{
	
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String guestList(Model model) {
		
		System.out.println("/guest/list-----------------");
		
		List<GuestVo> guestList = guestService.getGuestList();
		
		model.addAttribute("glist",guestList);
		return "guestbook/addList";
	}
	
	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String guestInsert(@ModelAttribute GuestVo guestVo) {
		
		guestService.guestInsert(guestVo);
		
		return "redirect:/guest/list";
	}
	
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String guestDeleteForm() {
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String guestDelete(@RequestParam("pwd") String pwd,@RequestParam("no") int no) {
		
		String pwd2 = guestService.getpassword(no);
		if(pwd.equals(pwd2)) {
			
			System.out.println("비번 맞음");
			guestService.guestdelete(no);
			return "redirect:/guest/list";
			
		}else {
			return "redirect:/guest/deleteForm?no="+no+"&result=fail";
		}
		
	}
	
	@RequestMapping(value = "/ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
	public String ajaxList(Model model) {
		
		System.out.println("/ajax/list-----------------");
		
		return "guestbook/ajaxList";
	}
    
   

}
