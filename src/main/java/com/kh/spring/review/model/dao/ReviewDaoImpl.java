package com.kh.spring.review.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.review.model.vo.Review;
import com.kh.spring.review.model.vo.ReviewComment;
import com.kh.spring.review.model.vo.ReviewLike;
import com.kh.spring.sharing.model.vo.Attachment;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<Review> selectReviewList(int offset, int limit) {
		RowBounds rowBounds = new RowBounds(offset, limit);
		return session.selectList("review.selectReviewList", null, rowBounds);
	}

	@Override
	public int selectReviewTotalCount() {
		return session.selectOne("review.selectReviewTotalCount");
	}

	@Override
	public int insertReview(Review review) {
		return session.insert("review.insertReview", review);
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return session.insert("review.insertAttachment", attach);
	}

	@Override
	public Review selectOneReviewCollection(int reviewNo) {
		return session.selectOne("review.selectOneReviewCollection", reviewNo);
	}

	@Override
	public int updateReviewBoardReadCount(int reviewNo) {
		return session.update("review.updateReviewBoardReadCount", reviewNo);
	}

	@Override
	public Attachment selectOneAttachment(int attachNo) {
		return session.selectOne("review.selectOneAttachment", attachNo);
	}

	@Override
	public int deleteAttachment(int attachNo) {
		return session.delete("review.deleteAttachment", attachNo);
	}

	@Override
	public int updateReview(Review review) {
		return session.update("review.updateReview", review);
	}

	@Override
	public int deleteReview(int reviewNo) {
		return session.delete("review.deleteReview", reviewNo);
	}

	@Override
	public List<ReviewComment> selectCommentList(int reviewNo) {
		return session.selectList("review.selectCommentList", reviewNo);
	}

	@Override
	public int insertReviewComment(ReviewComment reviewComment) {
		return session.insert("review.insertReviewComment", reviewComment);
	}

	@Override
	public int deleteReviewComment(int no) {
		return session.delete("review.deleteReviewComment", no);
	}

	@Override
	public int getReviewLike(ReviewLike reviewLike) {
		return session.selectOne("review.getReviewLike", reviewLike);
	}

	@Override
	public int deleteReviewLike(ReviewLike reviewLike) {
		return session.delete("review.deleteReviewLike", reviewLike);
	}

	@Override
	public int insertReviewLike(ReviewLike reviewLike) {
		return session.insert("review.insertReviewLike", reviewLike);
	}

	@Override
	public int updateReviewLike(int reviewNo) {
		return session.update("review.updateReviewLike", reviewNo);
	}

	@Override
	public List<Review> searchReview(Map<String, Object> searchParam) {
		int offset = (int)searchParam.get("start");
		int limit = (int)searchParam.get("end");
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		return session.selectList("review.searchReview", searchParam, rowBounds);
	}
	
	
}
