package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

//fileupload============
	public void upload(GalleryVo gVo) {
		System.out.println("gallery Dao : "+gVo.toString());
		sqlSession.insert("gallery.insertFile",gVo);
	}
	
	public List<GalleryVo> getGalleryList(){
		return sqlSession.selectList("gallery.selectList");
	}
	
	public int Gallerydelete(int no) {
		return sqlSession.delete("gallery.deleteGallery",no);
	}

}
