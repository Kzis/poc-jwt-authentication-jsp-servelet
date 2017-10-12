package com.cct;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebApplication extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		//ถ้ามีค่า คือ Validate เสร็จแล้วจะคืนหน้าไปที่ Web Portal
		if(req.getAttribute("validateToken") != null){
			
			//ตรวจสอบ Token แล้วผ่าน
			if(req.getAttribute("validateToken") == "Valid"){	
				req.setAttribute("validateTokenResult", "Valid");
				
				req.setAttribute("Name",req.getAttribute("Firstname"));
    			req.setAttribute("Lastname", req.getAttribute("Lastname"));
    			req.setAttribute("DOB", req.getAttribute("DOB"));
    			req.setAttribute("Sex", req.getAttribute("Sex"));
			}
			//ตรวจสอบ Token แล้วไม่ผ่าน
			else if(req.getAttribute("validateToken") == "Invalid"){
				req.setAttribute("validateTokenResult", "Inalid");
			}
			System.out.println("xxxxxxxx");
			ServletContext context = getServletContext().getContext("/Project1");
            RequestDispatcher rd = context.getRequestDispatcher("/Servlet1");
            rd.forward(req, resp);
			
		}else{
			
			//ถ้าไม่มีค่า คือ พึ่งยื่น Token มาจาก Web Portal ต้องส่งไปตรวจที่ WebAuthen ก่อน
			req.setAttribute("tokenJKT", req.getParameter("token"));
			
			req.setAttribute("Firstname",req.getParameter("Firstname"));
			req.setAttribute("Lastname", req.getParameter("Lastname"));
			req.setAttribute("DOB", req.getParameter("DOB"));
			req.setAttribute("Sex", req.getParameter("Sex"));
			
			req.setAttribute("flagValidate", "Y");
			
			ServletContext context = getServletContext().getContext("/Project2");
	        RequestDispatcher rd = context.getRequestDispatcher("/WebAuthenServer");
	        rd.forward(req, resp);
		}
		
		
		
	}

	
	
}
