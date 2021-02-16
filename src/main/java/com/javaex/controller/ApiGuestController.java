package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/api/guest/")
public class ApiGuestController{
	
	@Autowired
	private GuestService guestService;
	
	@ResponseBody
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestVo write(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("/api/guest/write-----------------");
		System.out.println(guestVo.getName());
		
		guestVo = guestService.guestInsertSelectKey(guestVo);
		return guestVo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestVo write2(@RequestBody GuestVo guestVo) {
		
		System.out.println("/api/guest/write22222----------");
		System.out.println(guestVo.getName());
		
		guestVo = guestService.guestInsertSelectKey(guestVo);
		return guestVo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestVo> list() {
		System.out.println("/api/guest/list-----------------");
	
		return guestService.getGuestList();
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public int delete(@ModelAttribute GuestVo guestVo) {
		
		
		System.out.println("/api/guest/delete-----------------");
		System.out.println(guestVo.toString());
		
		return guestService.apidelete(guestVo);
	}
	
	
}
