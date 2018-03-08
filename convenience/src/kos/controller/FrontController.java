package kos.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kos.board.BoardServlet;
import kos.member.MemberServlet;
import kos.order.OrderServlet;

public class FrontController extends HttpServlet {
	Logger logger = Logger.getLogger(FrontController.class);
	 
	public void doService(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		logger.info("doService 호출");
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		logger.info(command);
		String mode = "";
		ActionForward forward = new ActionForward();

		if(("/kos/reg.kos").equals(command)){//저장 눌렀을때
			logger.info("reg.kos 호출");
			mode = "insert";
			MemberServlet memServlet = new MemberServlet(mode);		
			try {
				forward = memServlet.execute(req, res);	
			} catch (Exception e){
				logger.info(e.getMessage());  
			}
			
		}
		else if(("/kos/checkMember.kos").equals(command)){
			logger.info("checkMember.kos 호출");
			mode = "check";
			MemberServlet memServlet = new MemberServlet(mode);
			try {
				forward = memServlet.execute(req, res);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(("/kos/login.kos").equals(command)){
			logger.info("login.kos 호출");
			mode = "login";
			MemberServlet memServlet = new MemberServlet(mode);
			try {
				forward = memServlet.execute(req, res);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		} 
		else if(("/kos/logout.kos").equals(command)){
			logger.info("logout.kos 호출");
			mode = "logout";
			MemberServlet memServlet = new MemberServlet(mode);
			try {
				forward = memServlet.execute(req, res);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		} 
		else if(("/kos/memUpdate.kos").equals(command)){
			logger.info("update.kos 호출");
			mode = "memUpdate";
			MemberServlet memServlet = new MemberServlet(mode);
			try {
				forward = memServlet.execute(req, res);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		/*************************board 시작*****************************/
		else if(("/kos/getBoardList.kos").equals(command)){
			logger.info("getBoardList.kos 호출");
			mode = "getBoardList";
			BoardServlet boardServlet = new BoardServlet(mode);
			try {
				forward = boardServlet.execute(req, res);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		else if(("/kos/getCommentList.kos").equals(command)){
			logger.info("getCommentList.kos 호출");
			mode = "getCommentList";
			BoardServlet boardServlet = new BoardServlet(mode);
			try {
				forward = boardServlet.execute(req, res);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		else if(("/kos/boardView.kos").equals(command)){
			logger.info("boardView.kos 호출");
			mode = "boardView";
			int b_no = 0; 
			
			BoardServlet boardServlet = new BoardServlet(mode);
			try {
				forward = boardServlet.execute(req, res);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		} 
	    else if("/kos/boardIns.kos".equals(command)){
	         logger.info("boardIns호출");
	         mode="boardIns";
	         BoardServlet boardServlet = new BoardServlet(mode);
	         try {
	            forward = boardServlet.execute(req, res); 
	         } catch (Exception e) {
	            // TODO: handle exception
	         }
	      }
	    else if("/kos/boardUpdate.kos".equals(command)){
	    	logger.info("boardUpdate호출");
	    	mode="boardUpdate";
	    	BoardServlet boardServlet = new BoardServlet(mode);
	    	try {
	    		forward = boardServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/boardDelete.kos".equals(command)){
	    	logger.info("boardDelete호출");
	    	mode="boardDelete";
	    	BoardServlet boardServlet = new BoardServlet(mode);
	    	try {
	    		forward = boardServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/commentIns.kos".equals(command)){
	    	logger.info("commentIns 호출");
	    	mode="commentIns";
	    	BoardServlet boardServlet = new BoardServlet(mode);
	    	try {
	    		forward = boardServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/commentDelete.kos".equals(command)){
	    	logger.info("commentDelete 호출");
	    	mode="commentDelete";
	    	BoardServlet boardServlet = new BoardServlet(mode);
	    	try {
	    		forward = boardServlet.execute(req, res); 
	    	} catch (Exception e) { 
	    		// TODO: handle exception
	    	}
	    }
		
	      /****************************board 끝**********************/
	       else if("/kos/getAirList.kos".equals(command)){
	    	  logger.info("getAirList호출");
	    	mode="getAirList";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	} 
	    }
	    else if("/kos/airBuy.kos".equals(command)){
	    	logger.info("airBuy호출");
	    	mode="airBuy";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/airIns.kos".equals(command)){
	    	logger.info("airIns호출");
	    	mode="airIns";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/airDel.kos".equals(command)){
	    	logger.info("airDel호출");
	    	mode="airDel";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/airOrder.kos".equals(command)){
	    	logger.info("airOrder호출");
	    	mode="airOrder";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/getAirOrder.kos".equals(command)){
	    	logger.info("getAirOrder호출");
	    	mode="getAirOrder";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/getCarList.kos".equals(command)){
	    	logger.info("getCarList호출");
	    	mode="getCarList";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try { 
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/carBuy.kos".equals(command)){
	    	logger.info("carBuy호출");
	    	mode="carBuy";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    else if("/kos/carOrder.kos".equals(command)){
	    	logger.info("carOrder호출");
	    	mode="carOrder";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    else if("/kos/carOrderCheck.kos".equals(command)){
	    	logger.info("carOrderCheck호출");
	    	mode="carOrderCheck";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
		/*********************hotel 시작 **************************/
	    else if("/kos/getHotelList.kos".equals(command)){ 
	    	logger.info("getHotelList 호출");
	    	mode = "getHotelList";
	    	OrderServlet OrderServlet = new OrderServlet(mode); 
	    	try {
				forward = OrderServlet.execute(req, res);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
	    else if("/kos/hotelIDCheck.kos".equals(command)){ 
	    	logger.info("hotelIDCheck 호출");
	    	mode = "hotelIDCheck";
	    	OrderServlet OrderServlet = new OrderServlet(mode); 
	    	try {
	    		forward = OrderServlet.execute(req, res);
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/hotelInsert.kos".equals(command)){
	    	logger.info("hotelInsert 호출");
	    	mode = "hotelInsert";
	    	OrderServlet OrderServlet = new OrderServlet(mode); 
	    	try {
	    		forward = OrderServlet.execute(req, res);
	    	} catch (Exception e) { 
	    		// TODO: handle exception
	    	}
	    }
	    else if("/kos/reservationIns.kos".equals(command)){
	    	logger.info("reservationIns 호출");
	    	mode = "reservationIns";
	    	OrderServlet OrderServlet = new OrderServlet(mode); 
	    	try {
	    		forward = OrderServlet.execute(req, res);
	    	} catch (Exception e) { 
	    		// TODO: handle exception
	    	}
	    }
		/*********************hotel 끝 **************************/
		
		/****************주문 종합***************/
	    else if("/kos/reserveSearch.kos".equals(command)){
	    	logger.info("reserveSearch 호출");
	    	mode = "reserveSearch";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    else if("/kos/airOrderOk.kos".equals(command)){
	    	logger.info("airOrderOk 호출");
	    	mode = "airOrderOk";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    else if("/kos/rentOrderOk.kos".equals(command)){
	    	logger.info("rentOrderOk 호출");
	    	mode = "rentOrderOk";
	    	OrderServlet orderServlet = new OrderServlet(mode);
	    	try {
	    		forward = orderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    else if("/kos/hotelOrderOk.kos".equals(command)){
	    	logger.info("hotelOrderOk 호출");
	    	mode = "hotelOrderOk";
	    	OrderServlet OrderServlet = new OrderServlet(mode);
	    	try {
	    		forward = OrderServlet.execute(req, res); 
	    	} catch (Exception e) {
	    		
	    	}
	    }
		/****************주문 종합***************/
		
		if(forward.getRedirect()){//너 sendRedirect니?
			res.sendRedirect(forward.getPath());
		}else{//너 forward할거지
			RequestDispatcher view = req.getRequestDispatcher(forward.getPath());
			view.forward(req, res);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		doService(req,res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		doService(req,res);
	}
}