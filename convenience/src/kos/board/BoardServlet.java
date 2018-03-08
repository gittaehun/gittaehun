package kos.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import kos.controller.Action;
import kos.controller.ActionForward;
import kos.util.KoreanConversion;

public class BoardServlet extends HttpServlet implements Action{
	Logger logger = Logger.getLogger(BoardServlet.class);
	String mode = null;
	public BoardServlet(){}
	public BoardServlet(String mode){
		this.mode = mode;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BoardLogic boardLogic = new BoardLogic();
		ActionForward forward = new ActionForward();
		if("getBoardList".equals(mode)){
			List<Map<String,Object>> boardList = new ArrayList<Map<String,Object>>();
			boardList = boardLogic.getBoardList();
			req.setAttribute("boardList", boardList);
			forward.setPath("./board.jsp");
			forward.setRedirect(false);
		}
		/*else if("getCommentList".equals(mode)){
			logger.info("getCommentList Å½");
			List<Map<String,Object>> commentList = new ArrayList<Map<String,Object>>();
			commentList = boardLogic.getCommentList();
			req.setAttribute("boardList", commentList);
			forward.setPath("./read.jsp");
			forward.setRedirect(false);
		}*/
		else if("boardView".equals(mode)){
			logger.info("boardView()");
			int b_no =0;
			if(req.getParameter("b_no") !=null){
				b_no = Integer.parseInt(req.getParameter("b_no"));
			}
			logger.info(b_no);
			List<Map<String,Object>> boardList = new ArrayList<Map<String,Object>>();
			boardList = boardLogic.getBoardList(b_no);
			logger.info("boardList °¡Á®¿È");
			
			/*if(cookie.getValue()!=null){
				cookie = new Cookie("board_no",null);
				cookie.setMaxAge(0);
				res.addCookie(cookie);
			}
			cookie = new Cookie("board_no", req.getParameter("b_no"));
			cookie.setMaxAge(60*60*24);
			res.addCookie(cookie);*/
			
			req.setAttribute("boardList", boardList);
			forward.setPath("./read.jsp");
			forward.setRedirect(false);
		}
		else if("boardIns".equals(mode)){
			logger.info("boardIns Å½");
			String path = null;
			Map<String, Object> pMap = new HashMap<String, Object>(); //ÆÄ¶ó¹ÌÅÍ·Î ¹Þ¾Æ ³Ñ±æ ¸Ê
			//pMap.put("board_category", req.getParameter("board_category"));
			pMap.put("b_no", req.getParameter("b_no"));
			if(req.getParameter("subject") !=null && req.getParameter("subject").length()>0){
				pMap.put("board_title", KoreanConversion.toKor(req.getParameter("subject")));				
			}
			if(req.getParameter("content") !=null && req.getParameter("content").length()>0){
				pMap.put("board_contents",KoreanConversion.toKor(req.getParameter("content")));
			}   
			if(req.getParameter("board_pw") !=null && req.getParameter("board_pw").length()>0){
				pMap.put("board_pw", req.getParameter("board_pw"));
			}  
			if(req.getParameter("user_id") !=null && req.getParameter("user_id").length()>0){
				pMap.put("user_id", req.getParameter("user_id")); 
			}
			if(req.getParameter("b_group") !=null && req.getParameter("b_group").length()>0){
				pMap.put("b_group", req.getParameter("b_group"));
			}
			if(req.getParameter("b_step") !=null && req.getParameter("b_step").length()>0){
				pMap.put("b_step", req.getParameter("b_step"));
			}
			for(String key : pMap.keySet()){
				logger.info("key : "+key);
				logger.info(pMap.get(key));
			}
			//pMap.put("board_date", req.getParameter("board_date"));
			//pMap.put("board_file", req.getParameter("board_file"));
			int result=0;
			
			result = boardLogic.boardIns(pMap);
			
				
				path = "./getBoardList.kos";
			 
			forward.setRedirect(true);
			forward.setPath(path);
		}
		else if("boardUpdate".equals(mode)){
			logger.info("boardUpdate Å½");
			String path = null;
			Map<String, Object> pMap = new HashMap<String, Object>(); //ÆÄ¶ó¹ÌÅÍ·Î ¹Þ¾Æ ³Ñ±æ ¸Ê
			//pMap.put("board_category", req.getParameter("board_category"));
			pMap.put("b_no", req.getParameter("b_no"));
			if(req.getParameter("subject") !=null && req.getParameter("subject").length()>0){
				pMap.put("board_title", KoreanConversion.toKor(req.getParameter("subject")));				
			}
			if(req.getParameter("content") !=null && req.getParameter("content").length()>0){
				pMap.put("board_contents",KoreanConversion.toKor(req.getParameter("content")));
			}   
			if(req.getParameter("board_pw") !=null && req.getParameter("board_pw").length()>0){
				pMap.put("board_pw", req.getParameter("board_pw"));
			}  
			if(req.getParameter("user_id") !=null && req.getParameter("user_id").length()>0){
				pMap.put("user_id", req.getParameter("user_id")); 
			}
			//pMap.put("board_date", req.getParameter("board_date"));
			//pMap.put("board_file", req.getParameter("board_file"));
			int result=0;
			
			result = boardLogic.boardUpdate(pMap);
			
			if(result==1){
				path = "./boardView.kos?b_no="+pMap.get("b_no");				
			}else if(result==999){
				path="../fail/updateFail.jsp?result="+result;
			}else{
				path="../fail/updateFail.jsp";				
			}
			
			forward.setRedirect(true);
			forward.setPath(path);
		}
		else if("boardDelete".equals(mode)){
			logger.info("boardDelete()");
			int result=0;
			int board_no=0;
			if(req.getParameter("board_no")!=null){
				board_no = Integer.parseInt(req.getParameter("board_no"));
			}
			result = boardLogic.boardDelete(board_no);
			forward.setPath("./getBoardList.kos");
			forward.setRedirect(true);
		}
		else if("commentIns".equals(mode)){ 
			logger.info("commentIns Å½");
			String path = null;
			Map<String, Object> pMap = new HashMap<String, Object>(); //ÆÄ¶ó¹ÌÅÍ·Î ¹Þ¾Æ ³Ñ±æ ¸Ê
			//pMap.put("board_category", req.getParameter("board_category"));
			pMap.put("b_no", req.getParameter("b_no"));
			if(req.getParameter("subject") !=null && req.getParameter("subject").length()>0){
				pMap.put("board_title", req.getParameter("subject"));				
			}
			if(req.getParameter("content") !=null && req.getParameter("content").length()>0){
				pMap.put("board_contents",req.getParameter("content"));
			}   
			if(req.getParameter("board_pw") !=null && req.getParameter("board_pw").length()>0){
				pMap.put("board_pw", req.getParameter("board_pw"));
			}  
			if(req.getParameter("user_id") !=null && req.getParameter("user_id").length()>0){
				pMap.put("user_id", req.getParameter("user_id")); 
			}
			if(req.getParameter("b_group") !=null && req.getParameter("b_group").length()>0){
				pMap.put("b_group", req.getParameter("b_group"));
			}
			if(req.getParameter("b_step") !=null && req.getParameter("b_step").length()>0){
				pMap.put("b_step", req.getParameter("b_step"));
			}
			for(String key : pMap.keySet()){
				logger.info("key : "+key);
				logger.info(pMap.get(key)); 
			}
			//pMap.put("board_date", req.getParameter("board_date"));
			//pMap.put("board_file", req.getParameter("board_file"));
			int result=0;
			
			result = boardLogic.boardComment(pMap);
			
			int b_no = Integer.parseInt(pMap.get("b_no").toString());
			
				path="./boardView.kos?b_no="+b_no;
			
			forward.setRedirect(true);
			forward.setPath(path);
		}
		else if("getCommentList".equals(mode)){
			logger.info("getCommentList()");
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("b_group", req.getParameter("b_group"));
			List<Map<String,Object>> commentList = new ArrayList<Map<String,Object>>();
			commentList = boardLogic.getCommentList(pMap);
			for(int i=0;i<commentList.size();i++){
				for(String key:commentList.get(i).keySet()){
					logger.info(commentList.get(i).get(key));
				}
			}
			req.setAttribute("commentList", commentList);
			forward.setPath("./comment.jsp");
			forward.setRedirect(false);
		} 
		else if("commentDelete".equals(mode)){
			logger.info("commentDelete()");
			int result=0;
			int b_no = 0;
			int board_no=0;
			if(req.getParameter("board_no")!=null){
				board_no = Integer.parseInt(req.getParameter("board_no"));
			}
			if(req.getParameter("b_no")!=null){
				b_no = Integer.parseInt(req.getParameter("b_no"));				
			}
			result = boardLogic.commentDelete(b_no);
			forward.setPath("./boardView.kos?b_no="+board_no);
			forward.setRedirect(true);
		}
		return forward;
	}
	
}