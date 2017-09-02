package com.cct;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet3 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5748448658454533L;

	public Servlet3() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	 	pw.println("This is web application 3.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String name = (String) request.getAttribute("webapp2");
		
		PrintWriter pw = response.getWriter();
		pw.println(name);
	 	pw.println("This is web application 3.");
	 	
	}
	
	
	
}
