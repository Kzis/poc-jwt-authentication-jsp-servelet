package com.cct;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebAuthenServer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -515886250578705800L;
	
	final static String CLAIM_ROLE = "role";

    final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    final static SecretKey SECRET_KEY = MacProvider.generateKey( SIGNATURE_ALGORITHM );
    final static TemporalAmount TOKEN_VALIDITY = Duration.ofHours( 4L );
	
	public WebAuthenServer() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        
        String flag = null;
        
        if(req.getAttribute("flagValidate") != null){
        	 flag = req.getAttribute("flagValidate").toString();
        }
        
        //ถ้าไม่มีค่ามาคือมาจาก Web portal เพื่อ Gen Token
        if(flag == null){
        	
        	//Create Token
        	String token = createToken("test","admin");
             
        	//Set token กลับไป
        	req.setAttribute("tokenJKT", token);
        	
        	//Set user information
        	req.setAttribute("Name", "Chalach");
        	req.setAttribute("Lastname", "Monkhontirapat");
        	req.setAttribute("DOB", "9/9/1992");
        	req.setAttribute("Sex", "M");
             
        	ServletContext context = getServletContext().getContext("/Project1");
        	RequestDispatcher rd = context.getRequestDispatcher("/Servlet1");
        	rd.forward(req, resp);
              
        }
        //ถ้ามีค่าเป็น Y แสดงว่า ถูกส่งมา Validate Token
        else if(flag.equals("Y")){
        	
        	//Validate Token
        	try {
        		//Get token ที่ส่งมา Validate
        		 String token = req.getAttribute("tokenJKT").toString();
        		 
        		 //Validate Token
        		 Jws<Claims> validate = parseToken(token);
        		 
        		 //ถ้าไม่เป็น null มาคือ Validate ผ่าน
        		 if(validate != null){
        			 
        			req.setAttribute("validateToken", "Valid"); 
        			req.setAttribute("Name",req.getAttribute("Firstname"));
        			req.setAttribute("Lastname", req.getAttribute("Lastname"));
        			req.setAttribute("DOB", req.getAttribute("DOB"));
        			req.setAttribute("Sex", req.getAttribute("Sex"));
        			
        			ServletContext context = getServletContext().getContext("/Project3");
     				RequestDispatcher rd = context.getRequestDispatcher("/WebApplication");
     				rd.forward(req, resp);
        		 }
				
        	//ถ้าเกิด Exception คือ validate ไม่ผ่าน
			} catch (Exception e) {
				req.setAttribute("validateToken", "Invalid");
				ServletContext context = getServletContext().getContext("/Project3");
				RequestDispatcher rd = context.getRequestDispatcher("/WebApplication");
				rd.forward(req, resp);
			}
        	
        }

	}
	
	/**
	 * 
	 * @param subject : key ในการสร้าง token 
	 * @param role : ระบุว่าจะให้อยู่ในระดับ role อะไร
	 * @return
	 */
	public static String createToken( final String subject, final String role ) {
        final Instant now = Instant.now();
        final Date expiryDate = Date.from( now.plus( TOKEN_VALIDITY ) );
        return Jwts.builder()
                   .setSubject( subject )
                   .claim( CLAIM_ROLE, role )
                   .setExpiration( expiryDate )
                   .setIssuedAt( Date.from( now ) )
                   .signWith( SIGNATURE_ALGORITHM, SECRET_KEY )
                   .compact();
    }
	
	/**
	 * 
	 * @param compactToken : Token ที่ต้องการจะ Valiadate
	 * @return 2 อย่าง คือ 1. throws Exception ถ้าไม่ผ่าน 2.Object ถ้าผ่าน
	 * @throws ExpiredJwtException
	 * @throws UnsupportedJwtException
	 * @throws MalformedJwtException
	 * @throws SignatureException
	 * @throws IllegalArgumentException
	 */
	public Jws<Claims> parseToken( final String compactToken ) throws ExpiredJwtException,UnsupportedJwtException,MalformedJwtException,SignatureException,IllegalArgumentException {
        
		//ใช้ KEY เดียวกับตอนเข้ารหัส และส่ง Token ตัวเดียวกันมา
		return Jwts.parser()
                   .setSigningKey( SECRET_KEY )
                   .parseClaimsJws( compactToken );
    }
	

}
