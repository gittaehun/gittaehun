package kos.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class BoardLogic {
Logger logger = Logger.getLogger(BoardLogic.class);
BoardDao boardDao = new BoardDao();
	public List<Map<String, Object>> getBoardList() {
		List<Map<String, Object>> boardList = null;
		boardList = boardDao.getBoardList();
		return boardList;
	}
	
	public List<Map<String, Object>> getBoardList(int b_no) {
		List<Map<String, Object>> boardList = null;
		boardList
		
		
		= boardDao.getBoardList(b_no); 
		return boardList;
	}
	public int boardIns(Map<String, Object> pMap) {
		int result=0;  

			int b_group =0;
			b_group = boardDao.getBgroup();
			pMap.put("b_group", b_group);
			pMap.put("b_step", 0);
		
		result = boardDao.boardIns(pMap);
		return result;
	}

	public int boardUpdate(Map<String, Object> pMap) {
		int result=0;  
		String board_pw = null;
		board_pw = boardDao.getPassword(pMap);
		if("null".equals(board_pw)){
			result=999;
		}else if(board_pw != null){
			result = boardDao.boardUpdate(pMap);
		}
		return result;
	}
	
	public List<Map<String, Object>> getCommentList(Map<String, Object> pMap) {
		List<Map<String, Object>> commentList = null;
		commentList = boardDao.getCommentList(pMap);
		return commentList;
	}


	public int boardComment(Map<String, Object> pMap) {
		logger.info("b_no:"+Integer.parseInt(pMap.get("b_no").toString()));
			//만일 상세보기한 글의 댓글이 존재하면 b_step을 1씩 증가한 값으로 수정
			int result=0;

			boardDao.bStepUpdate(pMap);
			pMap.put("b_step", Integer.parseInt(pMap.get("b_step").toString())+1);
			result = boardDao.boardIns(pMap);
		return result;
	}

	public int commentDelete(int b_no) {
		int result =0;
		result = boardDao.commentDelete(b_no);
		return result;
	}

	public int boardDelete(int board_no) {
		int result =0;
		result = boardDao.boardDelete(board_no);
		return result;
	}

	


	
}