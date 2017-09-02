# POC-JWT-Authentication-with-jsp-servelet
Proof of concepts : JWT(JSON Web Token) Authentication with jsp/servlet
My problem is : Have a 3 Project standalone but run on 1 server 
- Project 1 : jsp for show UI (Web Portal)
- Project 2 : Servlet(WebAuthentication) for create token(Call by project1 - jsp file) and validate token(Call by project3 - servlet)
- Project 3 : Servlet(WebApplication) for recieve token from webportal then send to Project2 for validate this token if valid return JWT object(Claim) if invalid will not retun JWT object , Last return all result to show in Project1 

![alt text](https://ibb.co/n5pvbv)

### JDK Version : 8 
### Tomcat : 8
