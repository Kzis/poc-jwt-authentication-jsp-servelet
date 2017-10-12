package com.cct.webapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cct.webapp.model.user.domain.UserInfo;
import com.cct.webapp.model.user.service.AuthenUserManager;
import com.google.gson.Gson;

/**
 * Servlet implementation class WebAuthenServlet
 */
@WebServlet(name = "WebAuthenServlet", urlPatterns = "/WebAuthenServlet")
public class WebAuthenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticationInfo(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticationInfo(request, response);
	}
	
	
	private void authenticationInfo(HttpServletRequest request, HttpServletResponse response){
		
		try {
			
			String tokenServer = null;
			UserInfo user = null;
			
			AuthenUserManager manager = new AuthenUserManager();
			
			
			// WEBAPP1 ATTRIBUTE
			String contextFrom = request.getParameter("contextFrom");
			
			
			// WEBAPP2 ATTRIBUTE
			String contextFromWebapp3 = (String)request.getAttribute("contextFromWebapp3");
			
			
			RequestDispatcher rd = null;
			
			if(contextFrom.equals("webapp1") && contextFromWebapp3 == null){
				
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				if(!username.isEmpty() || !password.isEmpty()){
					
					user = manager.authenUser(username, password);
					if(user != null){
						
						// GENERATE TOKEN SERVER
						tokenServer = manager.gennerateTokenServer(user);
						System.out.println(tokenServer);
						
					}
				}
				
				request.setAttribute("tokenServerKey", tokenServer);
				request.setAttribute("username", user.getSurname());
				request.setAttribute("password", user.getPassword());
				
				ServletContext otherContext = request.getServletContext().getContext("/webapp1");
				rd = otherContext.getRequestDispatcher("/index.jsp");
				
				
			}else if(contextFromWebapp3.equals("webapp3")){
				tokenServer = (String)request.getAttribute("tokenServerKey"); 
				UserInfo userinfo = manager.verifyTokenServer(tokenServer);
				String jsonUserinfo = new Gson().toJson(userinfo);
				
				request.setAttribute("userinfo", jsonUserinfo);
				request.setAttribute("contextFrom", "webapp2");
				ServletContext otherContext = request.getServletContext().getContext("/webapp3");
				rd = otherContext.getRequestDispatcher("/WebAuthenTokenServlet");
			}

			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
