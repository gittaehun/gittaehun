package kos.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AdminServlet extends HttpServlet {
	Logger logger = Logger.getLogger(AdminServlet.class);
	
	public void doService(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		logger.info("doService »£√‚");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{ 
		doService(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		doService(req,res);
	}
}
