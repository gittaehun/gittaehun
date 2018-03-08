package kos.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class OrderLogic {
	Logger logger = Logger.getLogger(OrderLogic.class);
	OrderDao orderDao = new OrderDao();
	public List<Map<String, Object>> getAirList(Map<String,Object> pMap) {
		logger.info("getAirList()");
		List<Map<String, Object>> airList = null;
		airList = orderDao.getAirList(pMap);
		return airList;
	}
	public int airInsert(Map<String, Object> airInfo) {
		logger.info("airInsert");
		int result = 0;
		result = orderDao.airInsert(airInfo);
		return result;
	}
	public int imageInsert(Map<String, Object> pMap) {
		logger.info("ImageInsert");
		int result = 0;
		
		result = orderDao.imageInsert(pMap);
		
		return result;
	}
	public int getAirNo() {
		logger.info("getAirNo()");
		int air_no = 0;
		air_no = orderDao.getAirNo();
		return air_no;
	}
	public int airDelete(int air_no) {
		logger.info("airDelete()");
		int Iresult = 0;
		int Aresult = 0;
		Iresult = orderDao.imageDelete(air_no);
		Aresult = orderDao.airDelete(air_no);
		return Aresult; 
	}
	public List<Map<String, Object>> getCarList() {
		logger.info("getCarList()");
		List<Map<String,Object>> carList = new ArrayList<Map<String,Object>>();
		carList = orderDao.getCarList();
		return carList;
	}
	public List<Map<String, Object>> getRentList(Map<String, Object> pMap) {
		logger.info("getRentList()");
		List<Map<String,Object>> rentList = new ArrayList<Map<String,Object>>();
		rentList = orderDao.getRentList(pMap);
		return rentList;
	}
	public int carOrder(Map<String, Object> pMap) {
		logger.info("carOrder()");
		int result = 0;
		result = orderDao.carOrder(pMap);
		return result; 
	}
	public List<Map<String,Object>> getCarOrder(Map<String, Object> pMap) {
		logger.info("getCarOrder()");
		List<Map<String,Object>> carOrderList = null;
		carOrderList = orderDao.getCarOrder(pMap);
		return carOrderList; 
	}
	public String getCarOrderNo(){
		logger.info("getCarOrderNo()");
		String pno = null;
		pno = orderDao.getCarOrderNo();
		return pno; 
	} 
	public int airOrder(Map<String, Object> pMap) {
		logger.info("airOrder()");
		int result = 0;
		result = orderDao.airOrder(pMap);
		return result; 
	}
	public List<Map<String, Object>> getAirOrder(Map<String, Object> pMap) {
		List<Map<String, Object>> airOrderList = null;
		airOrderList = orderDao.getAirOrder(pMap);
		return airOrderList;
	}
	public String getAirOrderNo(Map<String, Object> pMap) {
		String pno = null;
		pno = orderDao.getAirOrderNo(pMap);
		return pno; 
	}
	public List<Map<String, Object>> reserveList(String user_id) {
		List<Map<String, Object>> reserveList = null;
		reserveList = orderDao.reserveList(user_id);
		return reserveList;
	}
	public List<Map<String, Object>> getRentOrder(Map<String, Object> pMap) {
		List<Map<String, Object>> getRentOrder = null;
		getRentOrder = orderDao.getRentOrder(pMap);
		return getRentOrder;
	}
	public int airOrderDelete(int air_no) {
		int result = 0;
		result = orderDao.airOrderDelete(air_no);
		return result;
	}
	public List<Map<String, Object>> getHotelList(int hotel_no) {
		List<Map<String, Object>> hotelList = new ArrayList<Map<String,Object>>();
			hotelList = orderDao.getHotelList(hotel_no);
		return hotelList;
	}
	public int hotelInsert(Map<String, Object> pMap) {
		logger.info("hotelInsert");
		int result = 0;
			result = orderDao.hotelInsert(pMap);
		return result;
	}
	public int imgInsert(Map<String, Object> pMap) {
		int result = 0;
			 orderDao.hotelStepUpdate(pMap);
			 pMap.put("im_step", Integer.parseInt(pMap.get("im_step").toString())+1);
			result = orderDao.imgInsert(pMap);
		return result;
	}
	public int getHotelNo() {
		int hotelNo = 0;
		hotelNo = orderDao.getHotelNo();
		return hotelNo;
	}
	public String hotelIDCheck(String userID) {
		String user_id = null; 
		user_id=orderDao.hotelIDCheck(userID);
		return user_id;
	}
	public String getLicense(String user_id) {
		String license = null;
		license = orderDao.getLicense(user_id);
		return license;
	}
	public int reservationIns(Map<String, Object> pMap) {
		int result = 0;
		result = orderDao.reservationIns(pMap);
		return result;
	}
	public List<Map<String, Object>> reserveCheck(int hotel_pno) {
		List<Map<String, Object>> reserveCheck = new ArrayList<Map<String, Object>>();
		reserveCheck=orderDao.reserveCheck(hotel_pno);
		return reserveCheck;
	}
	public List<Map<String, Object>> getHotelOrder(String hotel_rno) {
		List<Map<String, Object>> getHotelOrder = new ArrayList<Map<String, Object>>();
		getHotelOrder=orderDao.getHotelOrder(hotel_rno);
		return getHotelOrder;
	}

}