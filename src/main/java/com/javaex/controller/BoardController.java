package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardList(Model model) {
		System.out.println("/board/list-----------------");
		model.addAttribute("blist", boardService.getBoardList());
		return "board/list";
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardwriteForm() {
		System.out.println("/board/writeForm-----------------");
		return "board/writeForm";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardWrite(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("/board/write-----------------");
		boardService.Boardwrite(boardVo, session);
		return "redirect:/board/list";
	}
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardRead(Model model,@RequestParam("no") int no) {
		System.out.println("/board/Read-----------------"+no);
		model.addAttribute("boardVo", boardService.getBoard(no));
		return "board/read";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardmodifyForm(Model model,@RequestParam("no") int no) {
		System.out.println("/board/modifyForm-----------------");
		model.addAttribute("boardVo", boardService.getreadBoard(no));
		return "board/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String Boardmodify(@ModelAttribute BoardVo boardVo) {
		System.out.println("/board/modify-----------------");
		
		boardService.Boardmodify(boardVo);
		return "redirect:/board/list";
		
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String Boarddelete(@RequestParam("no") int no) {
		System.out.println("/board/delete-----------------");
		
		boardService.Boarddelete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardSearch(@RequestParam("action") String action,@RequestParam("str") String str,Model model) {
		System.out.println("/board/search-----------------");
		model.addAttribute("blist", boardService.getBoardsearch(str));

		return "board/list";
	}

}
