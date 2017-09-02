package com.cct;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5983254637944418832L;

	public Servlet1() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        request.getParameter("username");
        request.setAttribute("name", "Chalach");
        ServletContext context = getServletContext().getContext("/Project2");
        RequestDispatcher rd = context.getRequestDispatcher("/Servlet2");
        rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
 
        String flag = null;
        if(request.getAttribute("validateTokenResult") != null){
       	 flag = request.getAttribute("validateTokenResult").toString();
        }
        
        String token = request.getAttribute("tokenJKT").toString();
        String firstName = null; 
        String lastName = null;
        String dob = null;
        String sex = null;
        String xxxxxxxxxxxxxxxxxxxxxxxxxxx="";
        request.setAttribute("tokenJKT",token);
    	
        if(flag == null){
        	
        	firstName = request.getAttribute("Name").toString();
        	lastName = request.getAttribute("Lastname").toString();
         	dob = request.getAttribute("DOB").toString();
         	sex = request.getAttribute("Sex").toString();
         	
            //Set user information
            request.setAttribute("Name",firstName);
            request.setAttribute("Lastname", lastName);
            request.setAttribute("DOB", dob);
            request.setAttribute("Sex", sex);
        	
        	GenerateHTML(pw,token,firstName,lastName,dob,sex);
        	
        }else if(flag == "Valid"){
        	
        	firstName = request.getAttribute("Name").toString();
        	lastName = request.getAttribute("Lastname").toString();
         	dob = request.getAttribute("DOB").toString();
         	sex = request.getAttribute("Sex").toString();
         	
            //Set user information
            request.setAttribute("Name",firstName);
            request.setAttribute("Lastname", lastName);
            request.setAttribute("DOB", dob);
            request.setAttribute("Sex", sex);
        	
        	GenerateHTMLValid(pw,token,firstName,lastName,dob,sex);
        	
        }else if(flag == "Inalid"){
        	
        	GenerateHTMLInvalid(pw,token,firstName,lastName,dob,sex);
        	
        }
        
//        request.getRequestDispatcher("webportal.jsp").forward(request, response);
        
	}
	
	public static void GenerateHTML(PrintWriter pw,String token,String firstName,String lastName,String dob,String sex){
		
		String urlWebAuthen = "form.action='/Project2/WebAuthenServer';";
        String urlWebApp = "form.action='/Project3/WebApplication';"; 
		
		  pw.println("<HTML>");
	        pw.println("<HEAD><TITLE> Web Portal </TITLE></HEAD>");
	        pw.println("<BODY>");
	        
	        pw.println("<form method=post id='form1'>");
	        pw.println("<table>");
	        
	        	pw.println("<tr>");
	        	
	        		pw.println("<td>");
	        			pw.println("<input type = 'submit' value = 'Request JWT' onclick="+ urlWebAuthen +" />");
	        		pw.println("</td>");
	        		
	        		pw.println("<td>");
	    				pw.println("<input type='text' style='width:1000px;' name='token' value='" + token +"' placeholder='this is token' />");
	    			pw.println("</td>");
	    			
	        	pw.println("</tr>");
	        	
	        	pw.println("<tr>");
	        	
		        	pw.println("<td>");
						pw.println("<input type = 'submit' value = 'Call Servlet' onclick="+ urlWebApp +" />");
					pw.println("</td>");
	        	
	        	pw.println("</tr>");

	        	
	        pw.println("</table>");
	        
	        pw.println("<input type='hidden' name='Firstname' id='firstName' value=' " + firstName + "'></input>");
	        pw.println("<input type='hidden' name='Lastname' id='lastName' value='" + lastName + "'></input>");
	        pw.println("<input type='hidden' name='DOB' id='DOB' value='" + dob + "'></input>");
	        pw.println("<input type='hidden' name='Sex' id='sex' value='" + sex + "'></input>");
	        
	        pw.println("</form>");
	        
	        pw.println("</BODY></HTML>");
	}
	
	public static void GenerateHTMLValid(PrintWriter pw,String token,String firstName,String lastName,String dob,String sex){
		
		String urlWebAuthen = "form.action='/Project2/WebAuthenServer';";
        String urlWebApp = "form.action='/Project3/WebApplication';"; 
		
		  pw.println("<HTML>");
	        pw.println("<HEAD><TITLE> Web Portal </TITLE></HEAD>");
	        pw.println("<BODY>");
	        
	        pw.println("<form method=post id='form1'>");
	        pw.println("<h1> Valiad Token Passs Jaaaaa </h1>");
	        pw.println("<table>");
	        
	        	pw.println("<tr>");
	        	
	        		pw.println("<td>");
	        			pw.println("<input type = 'submit' value = 'Request JWT' onclick="+ urlWebAuthen +" />");
	        		pw.println("</td>");
	        		
	        		pw.println("<td>");
	    				pw.println("<input type='text' style='width:1000px;' name='token' value='" + token +"' placeholder='this is token' />");
	    			pw.println("</td>");
	    			
	        	pw.println("</tr>");
	        	
	        	pw.println("<tr>");
	        	
		        	pw.println("<td>");
						pw.println("<input type = 'submit' value = 'Call Servlet' onclick="+ urlWebApp +" />");
					pw.println("</td>");
					
//					pw.println("<td style='text-align: right;'>");
//						pw.println("Name </br>");
//				      	pw.println("Lastname </br>");
//				        pw.println("DOB </br>");
//				        pw.println("Sex </br>");
//					pw.println("</td>");
						
					pw.println("<td>");
						pw.println("<table>");
							pw.println("<tr>");
							
								pw.println("<td style='text-align: right;'>");
									pw.println("Name </br>");
							      	pw.println("Lastname </br>");
							        pw.println("DOB </br>");
							        pw.println("Sex </br>");
								pw.println("</td>");
								
								pw.println("<td>");
									pw.println(" : <input type='text' name='Firstname' id='firstName' value='" + firstName + "'></input> </br>");
							      	pw.println(" : <input type='text' name='Lastname' id='lastName' value='" + lastName + "'></input> </br>");
							        pw.println(" : <input type='text' name='DOB' id='DOB' value='" + dob + "'></input> </br>");
							        pw.println(" : <input type='text' name='Sex' id='sex' value='" + sex + "'></input> </br>");
						        pw.println("</td>");
						        
							pw.println("</tr>");
						pw.println("</table>");
					
					pw.println("</td>");
	        	
	        	pw.println("</tr>");

	        	
	        pw.println("</table>");

	        pw.println("</form>");
	        
	        pw.println("</BODY></HTML>");
	}
	
	public static void GenerateHTMLInvalid(PrintWriter pw,String token,String firstName,String lastName,String dob,String sex){
		
		String urlWebAuthen = "form.action='/Project2/WebAuthenServer';";
        String urlWebApp = "form.action='/Project3/WebApplication';"; 
		
		  pw.println("<HTML>");
	        pw.println("<HEAD><TITLE> Web Portal </TITLE></HEAD>");
	        pw.println("<BODY>");
	        
	        pw.println("<form method=post id='form1'>");
	        pw.println("<h1> Invaliad Token Krub EiEi </h1>");
	        pw.println("<table>");
	        
	        	pw.println("<tr>");
	        	
	        		pw.println("<td>");
	        			pw.println("<input type = 'submit' value = 'Request JWT' onclick="+ urlWebAuthen +" />");
	        		pw.println("</td>");
	        		
	        		pw.println("<td>");
	    				pw.println("<input type='text' style='width:1000px;' name='token' value='" + token +"' placeholder='this is token' />");
	    			pw.println("</td>");
	    			
	        	pw.println("</tr>");
	        	
	        	pw.println("<tr>");
	        	
		        	pw.println("<td>");
						pw.println("<input type = 'submit' value = 'Call Servlet' onclick="+ urlWebApp +" />");
					pw.println("</td>");
	        	
	        	pw.println("</tr>");

	        	
	        pw.println("</table>");
	        
	        pw.println("<input type='hidden' name='Firstname' id='firstName' value=' " + firstName + "'></input>");
	        pw.println("<input type='hidden' name='Lastname' id='lastName' value='" + lastName + "'></input>");
	        pw.println("<input type='hidden' name='DOB' id='DOB' value='" + dob + "'></input>");
	        pw.println("<input type='hidden' name='Sex' id='sex' value='" + sex + "'></input>");
	        
	        pw.println("</form>");
	        
	        pw.println("</BODY></HTML>");
	}
		
}
