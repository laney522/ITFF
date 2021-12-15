package com.kh.spring.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	
	@Override
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}


	@Override
	public Member selectOneMember(String id) {
		return memberDao.selectOneMember(id);
	}


	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}


	@Override
	public List<Member> selectMemberList(String gender) {
		return memberDao.selectMemberList(gender);
	}


	@Override
	public List<Member> selectMemberList() {
		return memberDao.selectMemberList();
	}


	@Override
	public List<Member> selectMemberList(Map<String, Object> param) {
		return memberDao.selectMemberList(param);
	}
	
	
}
