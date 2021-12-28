package com.kh.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.member.model.vo.Member;
import com.kh.spring.sharing.model.vo.Board;

public interface MemberDao {

	int insertMember(Member member);

	Member selectOneMember(String id);

	int updateMember(Member member);

	List<Member> selectMemberList(String gender);

	List<Member> selectMemberList();

	List<Member> selectMemberList(Map<String, Object> param);

	Member selectOneEmail(String email);

	Member selectOneNickname(String nickname);

	List<Board> selectBoardListByMemberId(int offset, int limit, String id);

	int selectBoardTotalCount();

	int insertGoods(Member member);

	int insertProfileImage(Map<String, Object> param);

}
