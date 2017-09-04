![687474703a2f2f692e696d6775722e636f6d2f71444f4f75346f2e6a7067](https://user-images.githubusercontent.com/25294734/30025699-dd70b04c-91a3-11e7-8b05-9bdf9830a44f.jpg)

# POC-JWT-Authentication-with-jsp-servelet
Proof of concepts : JWT(JSON Web Token) Authentication with jsp/servlet
My problem is : Have a 3 Project standalone but run on 1 server 
- Project 1 : jsp for show UI (Web Portal)
- Project 2 : Servlet(WebAuthentication) for create token(Call by project1 - jsp file) and validate token(Call by project3 - servlet)
- Project 3 : Servlet(WebApplication) for recieve token from webportal then send to Project2 for validate this token if valid return JWT object(Claim) if invalid will not retun JWT object , Last return all result to show in Project1 
As show below.

# Environment
- JDK Version : 8 
- Tomcat : 8
- Encrypt algorithm : HMAC256 , HS256

# Reference
Thank a lot for idea to create this project
- https://github.com/cerker/jwt-example/blob/master/src/main/java/de/akquinet/jbosscc/jwt/auth/JwtManager.java

# DOCS
- https://jwt.io/
