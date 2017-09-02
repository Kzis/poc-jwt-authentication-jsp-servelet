package com.cct;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		System.out.println("### Servlet : doPost");
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		try {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			PrintWriter writer = resp.getWriter();
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<h1>Hello World Servlet doPost() " + username + "," + password + "</h1>");
			writer.println("</body>");
			writer.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
