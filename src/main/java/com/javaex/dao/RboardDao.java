package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	@Autowired
	private SqlSession sqlSession;

//리스트
	public List<RboardVo> getRboardList() {
		List<RboardVo> list = sqlSession.selectList("rboard.rboardList");
		return list;
	}

//글쓰기.
	public void rboardwrite(RboardVo rboardVo) {
		sqlSession.insert("rboard.rboardInsert", rboardVo);
	}

//댓글달기
	public void rboardcommnet(RboardVo rboardVo) {
		System.out.println("dao order = " + rboardVo.getOrderno());
		sqlSession.insert("rboard.rboardcommnet", rboardVo);
	}

//댓글 단후에 다른 댓글 오더값 +
	public void rboardorderplus(RboardVo rboardVo) {
		System.out.println("dao order = " + rboardVo.getOrderno());
		sqlSession.update("rboard.rboardorderplus", rboardVo);
	}

//read 정보============
	public RboardVo getRboard(int no) {
		RboardVo rboardVo = sqlSession.selectOne("rboard.rboardread", no);
		System.out.println("orderno= " + rboardVo.getOrderno());
		return rboardVo;
	}

//조회수 증가============
	public void hit(int no) {
		sqlSession.update("rboard.rboardhit", no);
	}

//수정============ 
	public void RboardUpedate(RboardVo rboardVo) {
		sqlSession.update("rboard.rboardupdate", rboardVo);
	}

// 삭제============
	public void Rboarddelete(int no) {

		sqlSession.delete("rboard.rboarddelete", no);
	}
// 검색===========
	public List<RboardVo> getRboardsearch(String str) {
		return sqlSession.selectList("rboard.rboardsearch", str);
	}

}
