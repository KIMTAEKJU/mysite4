package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardPagingFrameWorkDao
{
	@Autowired
	private SqlSession sqlSession;

	public long getTotalCount(String kwd)
	{
		return sqlSession.selectOne("board.getTotalCount", kwd);
	}
}
