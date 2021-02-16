package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Service("/galleryService")
public class GalleryService {

	@Autowired
	GalleryDao galleryDao;

	public void uploadFile(MultipartFile file, String content, HttpSession session) {
		// 오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("fileup Service-orgName : " + orgName);

		// 확장
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("fileup Service-exName : " + exName);

		// 서버저장용 이름(서버컴퓨터.)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("fileup Service-saveName : " + saveName);

		// 저장위치
		String saveDir = "C:\\java\\Utill\\study\\upload";

		// 저장경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("fileup Service-filePaht : " + filePath);

		// 파일 사이즈
		long fileSize = file.getSize();

		// 세션에서 유저번호가져오기
		UserVo uservo = (UserVo) session.getAttribute("authUser");
		int userno;
		//로그인을 하지않았을때 확인다하고는 없애기
		if(uservo == null) {
			userno = 1;
		}else {
			userno = uservo.getNo();
		}
		
		// Vo로 묶기
		GalleryVo gVo = new GalleryVo(userno, content, filePath, orgName, saveName, fileSize);

		galleryDao.upload(gVo);

		// 저장경로에 저장하기
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
	}
	
	
	public List<GalleryVo>getGalleryList(){
		
		return galleryDao.getGalleryList();
	}
	
	public int Gallerydelete(int no) {
		return galleryDao.Gallerydelete(no);
	}
}
