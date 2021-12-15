package com.kh.spring.memo.model.service;

import java.util.List;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoService {

	int insertMemo(Memo memo);

	List<Memo> selectMemoList();

	int deleteMemo(int no, String password);

	Memo selectOneMemo(int no);

}
