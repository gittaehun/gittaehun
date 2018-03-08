package kos.member;

import java.util.Map;

import org.apache.log4j.Logger;

import kos.vo.MemberVO;

public class MemberLogic {
	Logger logger = Logger.getLogger(MemberLogic.class);
	MemberDao memberDao = new MemberDao();
	
	public Map<String, Object> login(Map<String, String> info) {
		logger.info("login ");
		logger.info(info);
		Map<String, Object> result = null;;
		result = memberDao.login(info);
		return result; 
	}
	
	public int memberInsert(MemberVO mVO) {
		logger.info("memberIns ");
		int result = 0;
		result = memberDao.memberInsert(mVO);
		 
		return result;
	}

	public String checkID(String id) {
		logger.info("checkID ");
		String result = null;
		result = memberDao.checkID(id);
		return result; 
	}
	
	public int memUpdate(Map<String, Object> mem_info) {
		logger.info("memUpdate ");
		int result = 0;
		result = memberDao.memUpdate(mem_info);
		return result;
	}
	
	
	
	
}