package com.kh.spring.sharing.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.sharing.model.vo.Attachment;
import com.kh.spring.sharing.model.vo.Board;
import com.kh.spring.sharing.model.vo.BoardComment;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<Board> selectBoardList(int offset, int limit) {
		RowBounds rowBounds = new RowBounds(offset, limit);
		return session.selectList("board.selectBoardList", null, rowBounds);
	}

	@Override
	public int selectBoardTotalCount() {
		return session.selectOne("board.selectBoardTotalCount");
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return session.insert("board.insertAttachment", attach);
	}

	@Override
	public Board selectOneBoard(int no) {
		return session.selectOne("board.selectOneBoard", no);
	}

	@Override
	public List<Attachment> selectAttachmentListByBoardNo(int boardNo) {
		return session.selectList("board.selectAttachmentListByBoardNo", boardNo);
	}

	@Override
	public Board selectOneBoardCollection(int no) {
		return session.selectOne("board.selectOneBoardCollection", no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		log.debug("no = {}", no);
		return session.selectOne("board.selectOneAttachment", no);
	}

	@Override
	public int deleteBoardAttachment(int attachNo) {
		return session.delete("board.deleteOneBoard", attachNo);
	}

	@Override
	public List<Attachment> selectAttachmentByBoardNo(int no) {
		return session.selectList("board.selectAttachmentByBoardNo", no);
	}

	@Override
	public int deleteOneBoard(int no) {
		return session.delete("board.deleteOneBoard", no);
	}

	@Override
	public List<BoardComment> selectCommentList(int boardNo) {
		return session.selectList("board.selectCommentList", boardNo);
	}

	@Override
	public int insertBoardComment(BoardComment boardComment) {
		return session.insert("board.insertBoardComment", boardComment);
	}

	@Override
	public int deleteBoardComment(int no) {
		return session.delete("board.deleteBoardComment",no);
	}

	@Override
	public int updateBoard(Board board) {
		return session.update("board.updateBoard", board);
	}

	@Override
	public int updateBoardReadCount(int no) {
		return session.update("board.updateBoardReadCount", no);
	}

	
}
