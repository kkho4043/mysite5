package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.UserDao;

@Service("/FileUploadService")
public class FileUploadService {
	
	@Autowired
	UserDao userDao;
	
	public String restore(MultipartFile file) {
		System.out.println("fileservice-jpg.name = " + file.getOriginalFilename());		
		
		//오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("fileup Service-orgName : "+orgName);
		
		//확장
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("fileup Service-exName : "+exName);
		
		//서버저장용 이름
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println("fileup Service-saveName : "+saveName);
		
		String saveDir = "C:\\java\\Utill\\study\\upload";
		//서버저장?
		String filePath = saveDir + "\\" + saveName;
		System.out.println("fileup Service-filePaht : "+filePath);
		
		long fileSize = file.getSize();
		System.out.println("fileup Service-fileSize : "+fileSize);
		
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileDate);
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
	}
	
	
	
	
	

}
