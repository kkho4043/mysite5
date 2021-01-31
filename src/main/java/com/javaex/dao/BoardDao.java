package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

//boardlist============
	public List<BoardVo> getBoardList() {
		return sqlSession.selectList("board.boardList");
	}

// boardwrite============
	public void boardwrite(BoardVo boardVo) {
		sqlSession.insert("board.boardInsert", boardVo);
	}

//boardget============
	public BoardVo getBoard(int no) {
		return sqlSession.selectOne("board.boardread", no);
	}

// boardhit============
	public void hit(int no) {
		sqlSession.update("board.boardhit", no);
	}

//boardupdate============
	public void BoardUpedate(BoardVo boardVo) {
		sqlSession.update("board.boardupdate", boardVo);
	}

// boardupdate============
	public void Boarddelete(int no) {

		sqlSession.delete("board.boarddelete", no);
	}

//boardlist============
	public List<BoardVo> getBoardsearch(String str) {
		return sqlSession.selectList("board.boardsearch",str);
	}
}
