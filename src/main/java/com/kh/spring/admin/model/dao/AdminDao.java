package com.kh.spring.admin.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.admin.model.vo.PointHistory;
import com.kh.spring.goods.model.vo.Goods;
import com.kh.spring.member.model.vo.Member;
import com.kh.spring.sharing.model.vo.Attachment;

public interface AdminDao {

	List<Goods> selectGoodsList(int offset, int limit);

	int insertGoods(Goods goods);

	int insertAttachment(Attachment attach);

	List<Member> selectMemberList(int offset, int limit);

	int selectMemberTotalCount();

	int deleteGoods(int pId);

	Goods selectOneGoods(int pId);

	int updateGoods(Goods goods);

	List<Goods> selectRecentTenGoodsList();

	Member selectOneMember(String id);

	List<PointHistory> selectMemberPointHistoryList(String id);

	int updateMember(Member member);

	List<Member> searchMember(Map<String, Object> param);

	int searchMemberCount(Map<String, Object> param);

	List<Goods> searchGoods(Map<String, Object> param);

	int searchGoodsCount(Map<String, Object> param);

}
