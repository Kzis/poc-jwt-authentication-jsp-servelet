<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Portal</title>
</head>
<body>
	
	<form  method=post id="form1">
		<table>
			<tr>
				<td>
				 	<input type = "submit" value = "Request JWT" onclick="form.action='/Project2/WebAuthenServer';"  />
				</td>
				
				<td>
					<input type="text" style="width:1000px;"  name="token" value="" placeholder="this is token" />
				</td>
			</tr>
			
			<tr>
				<td>
				 	<input type = "submit" value = "Call Servlet" onclick="form.action='/Project3/WebApplication';" />
				</td>
				
				<td>
					
				</td>
			</tr>
		
	 	</table>
	</form>
	
</body>
</html>