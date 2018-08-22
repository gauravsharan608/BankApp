<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">
<script src="JavaScript/generateSummary.js"></script>
</head>
<body>
	<div align="center">
		<form onmouseover="initialize()">
			<table>
				<tr>
					<td colspan=2>Welcome ${user}</td>
				</tr>
				<tr>
					<td><label>Enter a date :</label></td>
					<td><input type="text" name="date" id="date"
						placeholder="dd/MM/yyyy" >
						<span id="dateError" class="error"></span></td>

				</tr>
				<tr><td><input type="button" value="GenerateReport" onclick= "return showReport( )"></td></tr>
			</table>
		</form>
		</div>
		<div id="report"></div>
	
</body>
</html>