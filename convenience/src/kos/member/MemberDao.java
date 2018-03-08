package kos.member;

import java.io.Reader;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;

import kos.vo.MemberVO;

public class MemberDao {
	Logger logger = Logger.getLogger(MemberDao.class);
	SqlSessionFactory sqlMapper = null;
	SqlSession sqlSes = null; 
	String resource = "kos/myBatis/mapperConfig.xml";
	public int memberInsert(MemberVO mVO) {
		logger.info("MemberDao");
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("memInsert", mVO);
			sqlSes.commit();
			logger.info("result:"+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public String checkID(String id) {
		String result = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.selectOne("memCheck", id);
			
			logger.info("result"+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public Map<String, Object> login(Map<String, String> info) {
		Map<String, Object> result = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.selectOne("memLogin", info);
			
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public int memUpdate(Map<String, Object> mem_info) {
		int result=0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("memUpdate", mem_info);
			sqlSes.commit();
			logger.info("result:"+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}	
}