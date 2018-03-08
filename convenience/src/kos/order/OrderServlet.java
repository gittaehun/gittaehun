package kos.order;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kos.controller.Action;
import kos.controller.ActionForward;
import kos.util.KoreanConversion;
    
public class OrderServlet implements Action {
	Logger logger = Logger.getLogger(OrderServlet.class); 
	String mode = "";
	    
	public OrderServlet(String mode) {
		this.mode=mode;
	}
 
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		OrderLogic orderLogic = new OrderLogic();
		HttpSession session = req.getSession();
		Map<String, Object> pMap = new HashMap<String, Object>();
		if("getAirList".equals(mode)){
			logger.info("getAirList 호출");
			List<Map<String,Object>> airList = new ArrayList<Map<String,Object>>();
			int air_no=0;
			if(req.getParameter("air_no")!=null){
				air_no=Integer.parseInt(req.getParameter("air_no").toString());
				forward.setPath("./airBuy.jsp");
				req.setAttribute("air_no",air_no);
			}
			else{
				forward.setPath("./air.jsp");
			}
			pMap.put("air_no", air_no);
			
			airList = orderLogic.getAirList(pMap);
			req.setAttribute("airList", airList);
			forward.setRedirect(false);
		}
		else if("airBuy".equals(mode)){
			logger.info("airBuy 호출");
			pMap.put("air_license", req.getParameter("air_license"));
			pMap.put("air_name", KoreanConversion.toKor(req.getParameter("air_name")));
			pMap.put("air_time", KoreanConversion.toKor(req.getParameter("air_time")));
			pMap.put("air_title", KoreanConversion.toKor(req.getParameter("air_title")));
			
			int count = Integer.parseInt(req.getParameter("product_quantity"));
			pMap.put("count", count);
			
			String price = KoreanConversion.toKor(req.getParameter("air_price"));
			pMap.put("old_price", price);
			String price2=  price.replaceAll(",","");
			int price3 = Integer.parseInt(price2.replaceAll("원","").trim());
			pMap.put("air_price", price3);
			int total= price3*count;
		    DecimalFormat df = new DecimalFormat("#,##0");
		    logger.info(df.format(total));
		    pMap.put("total", df.format(total)); // 총가격
			
			StringTokenizer st = new StringTokenizer(req.getParameter("air_date"),"~");
			String start_date = st.nextToken();
			String end_date = st.nextToken();
			
			pMap.put("start_date", start_date);
			pMap.put("end_date", end_date);
			
			st = new StringTokenizer(KoreanConversion.toKor(req.getParameter("air_point")), "⇔");
			
			String start_point = st.nextToken();
			String end_point = st.nextToken();
			
			pMap.put("start_point", start_point);
			pMap.put("end_point", end_point);
			
			String starting_time = req.getParameter("starting_time");
			String st_time = starting_time.replace("&#160;", "");
			st = new StringTokenizer(st_time, "/");
			String start_time = st.nextToken();
			String end_time = st.nextToken();
				
			pMap.put("start_time",start_time);
			pMap.put("end_time",end_time);
			pMap.put("air_no", req.getParameter("air_no"));
			
			req.setAttribute("airInfo", pMap);
			forward.setRedirect(false);
			forward.setPath("./payment_AIR.jsp");
		}
		else if("airIns".equals(mode)){
			logger.info("airIns");
            Map<String, Object> fileList = new HashMap<String, Object>(); // 파일 이름담을 map

			String realFolder = "D:\\dev_kosmo\\FinalProject\\WebContent\\pds";
			String encType = "euc-kr";
			int maxSize = 20*1024*1024; // 20MB
			MultipartRequest multi = new MultipartRequest(req, realFolder, maxSize,encType, new DefaultFileRenamePolicy());
			Enumeration<String> files = multi.getFileNames();
			File file = null;
			String filename = null;
			
			Map<String,Object> airInfo = new HashMap<String,Object>();
			String air_name = multi.getParameter("air_name");
			String product_name = multi.getParameter("product_name");
			int product_quantity = Integer.parseInt(multi.getParameter("product_quantity"));
			int product_price = Integer.parseInt(multi.getParameter("product_price"));
			String product_content = multi.getParameter("product_content");
			String start_date = multi.getParameter("start_date");
			String end_date = multi.getParameter("end_date");
			String start_time = multi.getParameter("start_time");
			String end_time = multi.getParameter("end_time");
			String start_point = multi.getParameter("start_point");
			String end_point =   multi.getParameter("end_point");
			int air_time = Integer.parseInt(multi.getParameter("air_time"));
			
			airInfo.put("air_quantity",product_quantity);
			airInfo.put("air_name",air_name);
			airInfo.put("air_pname",product_name);
			airInfo.put("air_price",product_price);
			airInfo.put("air_content",product_content);
			airInfo.put("air_startingpoint_time",start_date+"/"+start_time);
			airInfo.put("air_destination_time",end_date+"/"+end_time);
			airInfo.put("air_startingpoint",start_point);
			airInfo.put("air_destination",end_point);
			airInfo.put("air_time", air_time);
			
			Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
			airInfo.put("user_id", memInfo.get("USER_ID"));
			
			int Lresult = orderLogic.airInsert(airInfo);
			logger.info("Lresult = "+Lresult);
			
			// 가장 최근 air_no구하기
			int air_no = orderLogic.getAirNo();
			logger.info(air_no); 
		
			// 디비에 삽입

			int Fresult = 0;
			pMap.put("air_no", air_no);
			
			// 이미지 삽입
			while(files.hasMoreElements()){
				String fname=files.nextElement();
				filename = multi.getFilesystemName(fname);
				file=multi.getFile(fname);
				logger.info("첨부파일 : "+filename);
				pMap.put("image", filename);
	            fileList.put(fname, filename);
			}
   
			pMap.put("im_name", fileList.get("img_s_0"));
			pMap.put("im_step", 0);
            Fresult = orderLogic.imageInsert(pMap);
            logger.info("fr1 : "+Fresult);
            pMap.put("im_name", fileList.get("img_s_1"));
            pMap.put("im_step", 1);
            Fresult = orderLogic.imageInsert(pMap);
            logger.info("fr2 : "+Fresult);
            pMap.put("im_name", fileList.get("img_s_2"));
            pMap.put("im_step", 2);
            Fresult = orderLogic.imageInsert(pMap);
            logger.info("fr3 : "+Fresult);
			
			long size = 0;
			if(file !=null){
				size = file.length();
				logger.info("파일 크기 : "+size);
			}

			logger.info(multi.getParameter("air_name"));
			logger.info(multi.getParameter("product_name"));
			logger.info(multi.getParameter("product_quantity"));
			logger.info(multi.getParameter("product_price"));
			logger.info(multi.getParameter("product_content"));
			logger.info(multi.getParameter("start_date"));
			logger.info(multi.getParameter("end_date"));
			logger.info(multi.getParameter("start_time"));
			logger.info(multi.getParameter("end_time"));
			logger.info(multi.getParameter("start_point"));
			logger.info(multi.getParameter("end_point"));
			logger.info(multi.getParameter("air_time"));
			
			forward.setRedirect(true);
			forward.setPath("./getAirList.kos");
		}
		else if("airDel".equals(mode)){
			logger.info("airDel");
			List<String> fnames = new ArrayList<String>(); 
			fnames.add(KoreanConversion.toKor(req.getParameter("dimg_1")));
			fnames.add(KoreanConversion.toKor(req.getParameter("dimg_2")));
			fnames.add(KoreanConversion.toKor(req.getParameter("dimg_3")));
			logger.info(KoreanConversion.toKor(req.getParameter("dimg_1")));
			logger.info(KoreanConversion.toKor(req.getParameter("dimg_2")));
			logger.info(KoreanConversion.toKor(req.getParameter("dimg_3")));
			
			// 실제 이미지 삭제
			String path =  "D:\\dev_kosmo\\FinalProject\\WebContent\\pds\\";;
			String filename = null;
			if(fnames!=null){
				for(int i=0; i<fnames.size(); i++){
					filename = fnames.get(i);
					File file = new File(path+filename);
					if(file.exists()){
						boolean isOk = file.delete();
						logger.info("삭제 유무 : "+isOk);
					}
				}
			}
			int air_no = Integer.parseInt(req.getParameter("dair_no"));
 
			// 이미지 테이블에서 삭제, 글 삭제  
			int result = 0;
			result = orderLogic.airOrderDelete(air_no);
			result = orderLogic.airDelete(air_no);	
 			
			forward.setPath("./getAirList.kos");
			forward.setRedirect(true);
		}
		else if("airOrder".equals(mode)){
		   String air_tprice   = req.getParameter("air_tprice");
		   String air_kname    = KoreanConversion.toKor(req.getParameter("air_kname"));
		   String air_ename    = req.getParameter("air_ename");
		   String air_birth    = req.getParameter("air_birth");
		   String air_email    = req.getParameter("air_mail");
		   String air_contact  = req.getParameter("air_contact");
		   String air_request  = KoreanConversion.toKor(req.getParameter("req"));
		   int air_no          = Integer.parseInt(req.getParameter("air_no"));
		    
		   pMap.put("air_tprice",air_tprice);
		   pMap.put("air_kname",air_kname);
		   pMap.put("air_ename",air_ename);
		   pMap.put("air_birth",air_birth);
		   pMap.put("air_email",air_email);
		   pMap.put("air_contact",air_contact);
		   pMap.put("air_request",air_request);
		   pMap.put("air_no",air_no);
		   
	       logger.info(pMap.get("air_tprice"));
	       logger.info(pMap.get("air_kname"));
	       logger.info(pMap.get("air_ename"));
	       logger.info(pMap.get("air_birth"));
	       logger.info(pMap.get("air_email"));
	       logger.info(pMap.get("air_contact"));
	       logger.info(pMap.get("air_request"));
	       logger.info(pMap.get("air_no"));
		   
		   Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
		   pMap.put("user_id", memInfo.get("USER_ID"));
		   int result = 0;
		   result = orderLogic.airOrder(pMap);
		   if(result == 1){
			   String pno = orderLogic.getAirOrderNo(pMap);
			   req.setAttribute("air_pno", pno);
			   forward.setPath("./getAirOrder.kos");
		   }
		   forward.setRedirect(false);
		}
		else if("getAirOrder".equals(mode)){
			List<Map<String,Object>> airOrderList = new ArrayList<Map<String,Object>>();
			Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
			pMap.put("user_id", memInfo.get("USER_ID"));
			
			if(req.getAttribute("air_pno")!=null){
				String air_pno = req.getAttribute("air_pno").toString();
				pMap.put("air_pno", air_pno);
			}
			airOrderList = orderLogic.getAirOrder(pMap);
			
			if(airOrderList.size()>0){
				forward.setPath("./airReserveCheck.jsp");
				req.setAttribute("airOrderList", airOrderList);
			}
			forward.setRedirect(false);
		}
		
		else if("getCarList".equals(mode)){
			logger.info("getCarList 호출");
			List<Map<String,Object>> carList = new ArrayList<Map<String,Object>>();
			
			int rent_no=0;
			if(req.getParameter("rent_no")!=null){
				rent_no=Integer.parseInt(req.getParameter("rent_no").toString());
				forward.setPath("./airBuy.jsp");
			}
			else{
				forward.setPath("./carRental.jsp");
			}
			pMap.put("rent_no", rent_no);
			
			carList = orderLogic.getCarList();
			req.setAttribute("carList", carList);
			forward.setRedirect(false);
		}
		else if("carBuy".equals(mode)){
			logger.info("carBuy호출");
			List<Map<String,Object>> rentList = new ArrayList<Map<String,Object>>();

			String rent_kind = KoreanConversion.toKor(req.getParameter("h_name"));
			String start_date = req.getParameter("start_date");
			String end_date =   req.getParameter("end_date");
			String start_time = req.getParameter("start_time");
			String end_time =   req.getParameter("end_time");
	
			pMap.put("rent_kind", rent_kind);
			rentList = orderLogic.getRentList(pMap);
			int price = Integer.parseInt(rentList.get(0).get("RENT_PRICE").toString());
			
			StringTokenizer st = new StringTokenizer(start_date, "-");
			st.nextToken();
			st.nextToken();
			int st_date =  Integer.parseInt( st.nextToken());
			st  = new StringTokenizer(end_date, "-");
			st.nextToken();
			st.nextToken();
			int ed_date = Integer.parseInt(st.nextToken());
			
			int d_count = ed_date - st_date;
			logger.info("dc : "+d_count);
			int dprice = 0;
			if(d_count>0){
				dprice = d_count * price;
			}
			
			st = new StringTokenizer(start_time, ":");
			int st_hour = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(end_time, ":");
			int ed_hour = Integer.parseInt(st.nextToken());
			int count = (ed_hour-st_hour);
			
			pMap.put("start_date", start_date);
			pMap.put("end_date", end_date);
			pMap.put("start_time", start_time);
			pMap.put("end_time", end_time);
			pMap.put("price", count*(price/10)+dprice);
			
			logger.info("price : "+price);
			
			req.setAttribute("rentList", rentList);
			req.setAttribute("rentInfo", pMap);
			
			forward.setPath("./carReserve.jsp");
			forward.setRedirect(false);
		}
		else if("carOrder".equals(mode)){
			String start_date = req.getParameter("start_date"); 
			String start_time = req.getParameter("start_time"); 
			String end_date = req.getParameter("end_date"); 
			String end_time = req.getParameter("end_time"); 
			String rname = KoreanConversion.toKor(req.getParameter("rname"));
			String tprice = req.getParameter("tprice");
			String tel1 = req.getParameter("tel1");
			String tel2 = req.getParameter("tel2");
			String request = KoreanConversion.toKor(req.getParameter("request"));
			String age = KoreanConversion.toKor(req.getParameter("age"));
			String rent_kind = KoreanConversion.toKor(req.getParameter("rent_kind"));
			int rent_no = Integer.parseInt(req.getParameter("rent_no"));
			
			pMap.put("rent_take", start_date+"/"+start_time);
			pMap.put("rent_return", end_date+"/"+end_time);
			pMap.put("rent_reserved_name", rname);
			pMap.put("rent_tel1", tel1);
			pMap.put("rent_tel2", tel2); 
			pMap.put("rent_tprice", tprice); 
			pMap.put("rent_request", request);
			pMap.put("rent_driver_age", age);
			pMap.put("rent_kind", rent_kind);
			pMap.put("rent_no", rent_no);
			
		    logger.info("인수 : "+pMap.get("rent_take"));
			logger.info("반납 : "+pMap.get("rent_return"));
			logger.info(pMap.get("rent_reserved_name"));
			logger.info("tel1 : "+pMap.get("rent_tel1"));
			logger.info("tel2 : "+pMap.get("rent_tel2"));
			logger.info("가격"+pMap.get("rent_tprice"));
			logger.info(pMap.get("rent_request"));
			logger.info(pMap.get("rent_driver_age"));
			logger.info("kind : "+pMap.get("rent_kind"));
			logger.info(pMap.get("rent_no"));
			 
			Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
			pMap.put("user_id", memInfo.get("USER_ID"));
			
			int result = orderLogic.carOrder(pMap);

			if(result==1){
				String pno = orderLogic.getCarOrderNo();
				pMap.put("rent_pno", pno);
				List<Map<String,Object>> carOrderList = new ArrayList<Map<String,Object>>(); 
				carOrderList = orderLogic.getCarOrder(pMap);
				
				req.setAttribute("carOrderList", carOrderList);
				forward.setPath("./carReserveCheck.jsp");
			}
			forward.setRedirect(false);
		}  
		if("getHotelList".equals(mode)){
			String path = null;
			List<Map<String,Object>> hotelList = new ArrayList<Map<String,Object>>();
			Map<String, Object> hotelMap = new HashMap<String, Object>(); 
		
			if("buy".equals(req.getParameter("cmd"))){
				int hotel_no=0;
				logger.info("buy");
				logger.info(req.getParameter("hotel_no"));
				hotel_no=Integer.parseInt(req.getParameter("hotel_no"));

				hotelList = orderLogic.getHotelList(hotel_no);
				hotelList.get(0).put("HOTEL_PRICE", hotelList.get(0).get("HOTEL_PRICE").toString().trim());
				req.setAttribute("hotelList", hotelList);
				path="./buyHotel.jsp";
			}else{
				int hotel_no = 0;
				hotelList = orderLogic.getHotelList(hotel_no);
				req.setAttribute("hotelList", hotelList);
				path="./hotel.jsp";
			}
			forward.setPath(path);				
			forward.setRedirect(false); 
		} 
		else if("hotelIDCheck".equals(mode)){
			String path = null;
			String user_id=null;
			String userID ="null";
			if(req.getParameter("user_id")!=null){
				userID=req.getParameter("user_id");
			}
			user_id = orderLogic.hotelIDCheck(userID);
			if("fail".equals(user_id)){
				path="../fail/hotelFail.jsp";
			}else{
				path="./writeHotel.jsp";
			}
			forward.setPath(path);
			forward.setRedirect(true);
		}
		else if("hotelInsert".equals(mode)){
			logger.info("hotelInsert");
			Map<String, Object> fileList = new HashMap<String, Object>();
			int result = 0;
			String realFolder = "D:\\dev_kosmo\\FinalProject\\WebContent\\pds";
			String encType = "euc-kr";
			int maxSize = 100*1024*1024; // 20MB
			MultipartRequest multi = new MultipartRequest(req, realFolder, maxSize,encType, new DefaultFileRenamePolicy());
			
			
			if(multi.getParameter("hotel_name")!=null && multi.getParameter("hotel_name").length()>0){
				pMap.put("hotel_name",multi.getParameter("hotel_name")); 
			}
			if(multi.getParameter("hotel_address")!=null && multi.getParameter("hotel_address").length()>0){
				pMap.put("hotel_address", multi.getParameter("hotel_address"));
			}
			if(multi.getParameter("hotel_sdate")!=null && multi.getParameter("hotel_sdate").length()>0){
				pMap.put("hotel_sdate", multi.getParameter("hotel_sdate"));
			}
			if(multi.getParameter("hotel_edate")!=null && multi.getParameter("hotel_edate").length()>0){
				pMap.put("hotel_edate", multi.getParameter("hotel_edate"));
			}
			if(multi.getParameter("hotel_peopleNum")!=null && multi.getParameter("hotel_peopleNum").length()>0){
				pMap.put("hotel_peopleNum", multi.getParameter("hotel_peopleNum"));
			}
			if(multi.getParameter("hotel_productNum")!=null && multi.getParameter("hotel_productNum").length()>0){
				pMap.put("hotel_productNum", multi.getParameter("hotel_productNum"));
			}
			if(multi.getParameter("hotel_price")!=null && multi.getParameter("hotel_price").length()>0){
				pMap.put("hotel_price", multi.getParameter("hotel_price").trim());
			}
			if(multi.getParameter("hotel_content")!=null && multi.getParameter("hotel_content").length()>0){
				pMap.put("hotel_content", multi.getParameter("hotel_content"));
			}
			if(multi.getParameter("user_id")!=null && multi.getParameter("user_id").length()>0){
				pMap.put("user_id", multi.getParameter("user_id"));
			}

			result = orderLogic.hotelInsert(pMap);
	
	         int hotelNo = orderLogic.getHotelNo();
	         logger.info("hotelNo : "+hotelNo);
	         pMap.put("hotel_no", hotelNo);
	         if(multi.getFileNames() !=null){
		         Enumeration<String> files = multi.getFileNames();  
		        	 logger.info("실행안되지?");
		         File file = null;
		         String filename = null;
		         while(files.hasMoreElements()){
		        	 String fname = files.nextElement();
		        	 filename = multi.getFilesystemName(fname);
		             file=multi.getFile(fname);//파일 저장 경로
		             fileList.put(fname, filename);
		         }
		         pMap.put("im_step", 0);
		         pMap.put("im_name", fileList.get("hotel_mainImg"));
		         result = orderLogic.imgInsert(pMap);
		         pMap.put("im_name", fileList.get("hotel_subImg1"));
		         result = orderLogic.imgInsert(pMap);
		         pMap.put("im_name", fileList.get("hotel_subImg2"));
		         result = orderLogic.imgInsert(pMap);
		         long size = 0;
		          if(file !=null){
		             size = file.length();
		             logger.info("파일 크기 : "+size);
		          }    
	         }
	         forward.setPath("./getHotelList.kos");
	         forward.setRedirect(true);
		}
	         else if("reservationIns".equals(mode)){
	        	 logger.info("reservationIns호출");
	        	 int result=0;
	        	 String path = null;
	        	 //hotel_rname="+hotel_rname+"&total_price="+total_price+"&hp="+hp+"&mail="+mail+"&ephone="+ephone
	        	 if(req.getParameter("hotel_rname")!=null && req.getParameter("hotel_rname").length()>0){
	        		 pMap.put("hotel_rname", req.getParameter("hotel_rname"));
	        	 }
	        	 if(req.getParameter("total_price")!=null && req.getParameter("total_price").length()>0){
	        		 pMap.put("hotel_tprice", req.getParameter("total_price"));
	        	 }
	        	 if(req.getParameter("hp")!=null && req.getParameter("hp").length()>0){
	        		 pMap.put("hotel_rhp1", req.getParameter("hp"));
	        	 }
	        	 if(req.getParameter("mail")!=null && req.getParameter("mail").length()>0){
	        		 pMap.put("hotel_remail", req.getParameter("mail"));
	        	 }
	        	 if(req.getParameter("ephone")!=null && req.getParameter("ephone").length()>0){
	        		 pMap.put("hotel_rhp2", req.getParameter("ephone"));
	        	 }
	        	 if(req.getParameter("user_id")!=null && req.getParameter("user_id").length()>0){
	        		 pMap.put("user_id", req.getParameter("user_id"));
	        	 }
	        	 if(req.getParameter("hotel_pno")!=null && req.getParameter("hotel_pno").length()>0){
	        		 pMap.put("hotel_pno", req.getParameter("hotel_pno"));
	        	 }
	        	 
	        	/* for(String key:pMap.keySet()){
	        		 logger.info(key);
	        		 logger.info(pMap.get(key));
	        	 }*/
	        	 
	        	 result =  orderLogic.reservationIns(pMap);
	        	 
	        	 List<Map<String,Object>> reserveList = new ArrayList<Map<String, Object>>();
	        	 boolean a = true;
	        	 if(result==1){
	        		reserveList = orderLogic.reserveCheck(Integer.parseInt(pMap.get("hotel_pno").toString()));
	        		logger.info(reserveList.size());
	        		req.setAttribute("reserveList", reserveList);
	        		a= false;
	        		 path="./hotelReserveCheck.jsp"; 
	        	 }else{
	        		 path="../fail/reservationFail.jsp";
	        	 }
	        	 forward.setPath(path);
	        	 forward.setRedirect(a); 
	        	 
	         }
	         else if("hotelOrderOk".equals(mode)){
	        	 List<Map<String,Object>> reserveList = new ArrayList<Map<String, Object>>();
	        	 reserveList = orderLogic.getHotelOrder(req.getParameter("hotel_rno"));
	        	 logger.info(reserveList.size());
	        	 req.setAttribute("reserveList", reserveList);
	        	 forward.setRedirect(false);
	        	 forward.setPath("./hotelReserveCheck.jsp");
	         }

		/**************주문 종합**************/
		else if("reserveSearch".equals(mode)){
			List<Map<String,Object>> reserveList = new ArrayList<Map<String,Object>>();
			
			Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
			String user_id = memInfo.get("USER_ID").toString();
			reserveList = orderLogic.reserveList(user_id);
			
			req.setAttribute("orderList", reserveList);
			forward.setRedirect(false);
			forward.setPath("./reserveSearch.jsp");
		}
		else if("airOrderOk".equals(mode)){
			logger.info("airOrderOk");
			List<Map<String,Object>> airOrderList = null;
			session = req.getSession();
			Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
			String user_id = memInfo.get("USER_ID").toString();
			String air_pno = req.getParameter("air_pno");
			pMap.put("user_id", user_id);
			pMap.put("air_pno", air_pno);
			airOrderList = orderLogic.getAirOrder(pMap);
			
			req.setAttribute("airOrderList", airOrderList);
			forward.setRedirect(false);
			forward.setPath("./airReserveCheck.jsp");
		} 
		else if("rentOrderOk".equals(mode)){
			logger.info("rentOrderOk");
			List<Map<String,Object>> rentOrderList = null;
			session = req.getSession();
			Map<String,Object> memInfo = (Map<String,Object>)session.getAttribute("result");
			String user_id = memInfo.get("USER_ID").toString();
			String rent_pno = req.getParameter("rent_pno");
			pMap.put("user_id", user_id);
			pMap.put("rent_pno", rent_pno);
			rentOrderList = orderLogic.getRentOrder(pMap);
			
			req.setAttribute("carOrderList", rentOrderList);
			forward.setRedirect(false);
			forward.setPath("./carReserveCheck.jsp");
		} 
		/**************주문 종합**************/
		return forward;
	}
}