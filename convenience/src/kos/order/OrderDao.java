package kos.order;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;

public class OrderDao {
	Logger logger = Logger.getLogger(OrderDao.class);
	SqlSessionFactory sqlMapper = null;
	SqlSession sqlSes = null; 
	String resource = "kos/myBatis/mapperConfig.xml";
	public List<Map<String, Object>> getAirList(Map<String, Object> pMap) { 
		logger.info("getAirList()");
		List<Map<String, Object>> airList = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			airList = sqlSes.selectList("getAirList",pMap);
			logger.info("result : "+airList.size()); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		
		return airList;
	}
	public int airInsert(Map<String, Object> airInfo) {
		logger.info("airInsert");
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("airInsert",airInfo);
			sqlSes.commit();
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public int imageInsert(Map<String, Object> pMap) {
		logger.info("imageInsert");
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("airImgInsert",pMap);
			sqlSes.commit();
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public int getAirNo() {
		logger.info("getAirNo()");
		int air_no = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			air_no = sqlSes.selectOne("getAirNo");
			logger.info("result : "+air_no); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return air_no;
	}
	public int imageDelete(int air_no) {
		logger.info("imageDelete");
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.delete("airImgDelete",air_no);
			sqlSes.commit();
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public int airDelete(int air_no) {
		logger.info("airDelete");
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			air_no = sqlSes.delete("airDelete",air_no);
			sqlSes.commit();
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		} 
		return result;
	}
	public List<Map<String, Object>> getCarList() {
		List<Map<String,Object>> carList = new ArrayList<Map<String,Object>>();
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			carList = sqlSes.selectList("getCarList");
			logger.info("result : "+carList.size()); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return carList;
	}
	public List<Map<String, Object>> getRentList(Map<String, Object> pMap) {
		List<Map<String,Object>> rentList = new ArrayList<Map<String,Object>>();
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			rentList = sqlSes.selectList("getRentList",pMap);
			logger.info("result : "+rentList.size()); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return rentList;
	}
	public int carOrder(Map<String, Object> pMap) {
		logger.info("carOrder");
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("carOrder",pMap);
			sqlSes.commit();
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public List<Map<String, Object>> getCarOrder(Map<String, Object> pMap) {
		List<Map<String,Object>> carOrderList = new ArrayList<Map<String,Object>>();
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			carOrderList = sqlSes.selectList("getCarOrder",pMap);
			logger.info("result : "+carOrderList.size()); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return carOrderList;
	}
	public String getCarOrderNo() {
		String pno = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			pno = sqlSes.selectOne("getCarOrderNo");
			logger.info("pno : "+pno); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return pno;
	}
	public int airOrder(Map<String, Object> pMap) {
		int result = 0 ;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("airOrder",pMap);
			sqlSes.commit();
			logger.info("result : "+result); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public List<Map<String, Object>> getAirOrder(Map<String, Object> pMap) {
		List<Map<String, Object>> airOrderList = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			airOrderList = sqlSes.selectList("getAirOrder",pMap);
			logger.info("airOrderList : "+airOrderList.size()); 
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return airOrderList;
	}
	public String getAirOrderNo(Map<String, Object> pMap) {
		String pno = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			pno = sqlSes.selectOne("getAirOrderNo",pMap);
			logger.info("pno : "+pno);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return pno;
	}
	public List<Map<String, Object>> reserveList(String user_id) {
		List<Map<String, Object>> reserveList = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader); 
			sqlSes = sqlMapper.openSession();
			reserveList = sqlSes.selectList("orderList",user_id);
			logger.info("reserveList : "+reserveList.size());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return reserveList;
	}
	public List<Map<String, Object>> getRentOrder(Map<String, Object> pMap) {
		List<Map<String, Object>> getRentOrder = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			getRentOrder = sqlSes.selectList("getRentOrder",pMap);
			logger.info("reserveList : "+getRentOrder.size());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return getRentOrder;
	}
	public int airOrderDelete(int air_no) {
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.delete("airOrderDelete",air_no);
			sqlSes.commit();
			logger.info("result : "+result);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}
	public List<Map<String, Object>> getHotelList(int hotel_no) {
		List<Map<String, Object>> hotelList = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			hotelList = sqlSes.selectList("getHotelList", hotel_no);
			logger.info("hotelList.size() : "+hotelList.size());
			for(int i=0;i<hotelList.size();i++){
				logger.info(hotelList.get(i).get("HOTEL_PRICE"));
			}
			
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return hotelList;
	}

	public int hotelInsert(Map<String, Object> pMap) {
		logger.info("hotelInsert");
		for(String key:pMap.keySet()){
			logger.info(key+" : "+pMap.get(key));
		}
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("hotelInsert", pMap);
			sqlSes.commit();
			logger.info("result : "+result);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result;
	}

	public int getHotelNo() {
		int hotelNo = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			hotelNo = sqlSes.selectOne("getHotelNo");
			logger.info("hotelNo : "+hotelNo);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return hotelNo;
	}

	public void hotelStepUpdate(Map<String, Object> pMap) {
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			sqlSes.update("hotelStepUpdate", pMap);
			sqlSes.commit();
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
	}

	public int imgInsert(Map<String, Object> pMap) {
		logger.info("imgInsert");
		for(String key:pMap.keySet()){
			logger.info(key+" : "+pMap.get(key));
		}
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("imgInsert", pMap);
			sqlSes.commit();
			logger.info("result : "+result);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result; 
	}
 
	public String hotelIDCheck(String userID) {  
		logger.info("hotelIDCheck");
		String user_id = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			user_id = sqlSes.selectOne("hotelIDCheck", userID);
			sqlSes.commit();
			logger.info("result : "+user_id.length());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return user_id;
	}

	public String getLicense(String user_id) {
		String license = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			license = sqlSes.selectOne("getLicense", user_id);
			sqlSes.commit();
			logger.info("result : "+user_id.length());
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return license;
	}

	public int reservationIns(Map<String, Object> pMap) {
		logger.info("reservationIns");
		int result = 0;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			result = sqlSes.insert("reservationIns", pMap);
			sqlSes.commit();
			logger.info("result : "+result);
		} catch (Exception e) {
			logger.info("Exception:"+e.getMessage());
		}
		return result; 
	}

	public List<Map<String, Object>> reserveCheck(int hotel_pno) {
		List<Map<String, Object>> reserveList = null;
		logger.info(hotel_pno);
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			reserveList = sqlSes.selectList("reserveList", hotel_pno);
			logger.info("hotelList.size() : "+reserveList.size());
			
		} catch (Exception e) { 
			logger.info("Exception:"+e.getMessage());
		}
		return reserveList;
	}

	public List<Map<String, Object>> getHotelOrder(String hotel_rno) {
		List<Map<String, Object>> getHotelOrder = null;
		try {
			Reader reader = null;
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			sqlSes = sqlMapper.openSession();
			getHotelOrder = sqlSes.selectList("getHotelOrder", hotel_rno);
			logger.info("hotelList.size() : "+getHotelOrder.size());
			
		} catch (Exception e) { 
			logger.info("Exception:"+e.getMessage());
		}
		return getHotelOrder;
		}
}