package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping("rboard")
public class RboardController {
	
	@Autowired
	private RboardService rboardService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String RboardList(Model model) {
		System.out.println("/rboard/list-----------------");
		model.addAttribute("rblist", rboardService.getRboardList());
		return "rboard/list";
	}
	
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String RboardwriteForm() {
		System.out.println("/board/writeForm-----------------");
		 
		return "rboard/writeForm";
	}
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String RboardWrite(@ModelAttribute RboardVo rboardVo, HttpSession session) {
		System.out.println("/rboard/write-----------------");
		
		rboardService.Rboardwrite(rboardVo, session);
		return "redirect:/rboard/list";
	}
	
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String RboardRead(Model model,@RequestParam("no") int no) {
		System.out.println("/rboard/Read-----------------");
		model.addAttribute("rboardVo", rboardService.getRboard(no));
		return "rboard/read";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String RboardmodifyForm(Model model,@RequestParam("no") int no) {
		System.out.println("/rboard/modifyForm-----------------");
		model.addAttribute("rboardVo", rboardService.getreadRboard(no));
		return "rboard/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String Rboardmodify(@ModelAttribute RboardVo rboardVo) {
		System.out.println("/rboard/modify-----------------");
		
		rboardService.Rboardmodify(rboardVo);
		return "redirect:/rboard/list";
		
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String Rboarddelete(@RequestParam("no") int no) {
		System.out.println("/rboard/delete-----------------");
		
		rboardService.Rboarddelete(no);
		return "redirect:/rboard/list";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardSearch(@RequestParam("action") String action,@RequestParam("str") String str,Model model) {
		System.out.println("/rboard/search-----------------");
		model.addAttribute("rblist", rboardService.getRboardsearch(str));

		return "rboard/list";
	}
	

}
