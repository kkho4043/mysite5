package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;
import com.javaex.service.GuestService;

@Controller
@RequestMapping("fileup")
public class FileUploadController{
	
	@Autowired
	private FileUploadService fileupsevice;
	
	@RequestMapping(value = "/form", method = {RequestMethod.GET, RequestMethod.POST })
	public String form() {
		System.out.println("fileUpload-form");
		
		return "/fileUpload/form";
	}
	
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST })
	public String upload(@RequestParam("file") MultipartFile file,Model model) {
		
		System.out.println("fileUpload-upload");
		System.out.println(file.getOriginalFilename());
		
		String saveName = fileupsevice.restore(file);
		model.addAttribute("saveName",saveName);
		
		return "";
	}

}
