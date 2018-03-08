package com.chatting.test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SearchLogic {
	//데이터 새로고침을 위한 클래스
	public List<ChatVO> selectALL2(ChatVO pvo) {
		List<ChatVO> memberList = 
				new ArrayList<ChatVO>();
		SqlMapClient sqlMap = null;
		try {
			String resource 
				= "com/ibatis/SqlMapConfig.xml";
			Reader reader = null;
			reader = 
			Resources.getResourceAsReader(resource);
			sqlMap = 
			SqlMapClientBuilder
			.buildSqlMapClient(reader);
			reader.close();
			memberList = 
			sqlMap.queryForList("selectALL2");			
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return memberList;
	}
}
