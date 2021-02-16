package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String BoardList(Model model) {
		System.out.println("/gallert/list-----------------");
		List<GalleryVo> gList = galleryService.getGalleryList();
		
		model.addAttribute("gList",gList);
		return "gallery/list";
	}
	
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("content") String content,HttpSession session) {
		System.out.println("/gallert/upload-----------------");
		System.out.println("filename = "+file.getOriginalFilename());
		System.out.println("content = " + content);
		System.out.println("session no = " + session.toString());
		
		
		galleryService.uploadFile(file, content, session);
		return "redirect:/gallery/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public int delete(@RequestParam("no") int no) {
		System.out.println("/gallert/upload-----------------");
		System.out.println("no = " + no);
		
		return galleryService.Gallerydelete(no);
	}
}
