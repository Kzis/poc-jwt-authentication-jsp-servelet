package com.cct;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1074208821778568056L;

	public Servlet2() {
		super();
	}

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
//        String name = (String) request.getAttribute("name");
        pw.println("This is web application 2.");
//        pw.println("<br>The value received from web application one is: " + name);
        pw.println("<br>xxx: " + (String)  request.getParameter("xxx"));
        pw.println("<br>yyy:" + (String)  request.getParameter("yyy"));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        
        //�ç����繢ͧ Servlet1 call Servlet2
        String name = (String) request.getAttribute("name");
        pw.println("This is web application 2.");
        pw.println("<br>The value received from web application one is: " + name);
        pw.println("<br>aaa: " + (String) request.getAttribute("aaa"));
        pw.println("<br>bbb" + (String) request.getAttribute("bbb"));
        
        
        //�ç����繢ͧ Servlet1 call Servlet2 ���� call Servlet3 ���
//        request.setAttribute("webapp2", "pass webapp2");
//        ServletContext context = getServletContext().getContext("/Project3");
//        RequestDispatcher rd = context.getRequestDispatcher("/Servlet3");
//        rd.forward(request, response);
	}
	
	

	
	
}
