package kos.board;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;

public class BoardDao {
	Logger logger = Logger.getLogger(BoardDao.class);
	SqlSessionFactory sqlMapper = null;
	SqlSession sqlSes = null; 
	String resource = "kos/myBatis/mapperConfig.xml";
	public List<Map<String, Object>> getBoardList() {
		logger.info("getBoardList()");
		List<Map<String, Object>> boardList = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			boardList = sqlSes.selectList("getBoardList");
			logger.info("boardList.size() : "+boardList.size());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return boardList;
	}
	public List<Map<String, Object>> getBoardList(int b_no) {
		logger.info("getBoardList()");
		List<Map<String, Object>> boardList = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			boardList = sqlSes.selectList("boardView",b_no);
			logger.info("boardList.size() : "+boardList.size());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage()); 
		}
		return boardList;
	}
	public int boardIns(Map<String, Object> pMap) {
		logger.info("boardIns 호출 성공"); 
		for(String key:pMap.keySet()){
			logger.info(key);
			logger.info(pMap.get(key));
		}
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("boardIns",pMap);
			sqlSes.commit();
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
		
	}
	public void bStepUpdate(Map<String, Object> pMap) {
		logger.info("bStepUpdate 호출 성공"); 
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result=sqlSes.update("stepUpdate", pMap);
			sqlSes.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception:"+e.getMessage());
		}
	}
	public int getBgroup() {
		logger.info("getBgroup 호출 성공"); 
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.selectOne("getBgroup");
			logger.info(result);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
		
		
	}
	public List<Map<String, Object>> getCommentList(Map<String, Object> pMap) {
		List<Map<String, Object>> commentList = null;
		logger.info("getCommentList 호출 성공"); 
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			commentList = sqlSes.selectList("getCommentList", pMap);
			logger.info("commentList.size() : "+commentList.size());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage()); 
		}
		return commentList;
	}
	public int commentDelete(int b_no) {
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result=sqlSes.delete("commentDelete", b_no);
			sqlSes.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public int boardDelete(int board_no) {
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result=sqlSes.delete("boardDelete", board_no);
			sqlSes.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public String getPassword(Map<String, Object> pMap) {
		String b_pw = null;
		logger.info("getPassword 호출 성공");  
		try {
			Reader reader = null; 
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			b_pw = sqlSes.selectOne("getPassword", pMap); 
			logger.info("b_pw : "+b_pw);
		} catch (Exception e) { 
			logger.info("Exception:"+e.getMessage()); 
		}
		return b_pw;
	}
	public int boardUpdate(Map<String, Object> pMap) {
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result=sqlSes.update("boardUpdate", pMap);
			sqlSes.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	
}