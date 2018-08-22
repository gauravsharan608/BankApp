<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="JavaScript/transferPage.js">
	
</script>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">
</head>
<body>
	<h1>Third Party transfer</h1>
	<div align="center">
		<h4>Welcome ${user }</h4>
		<form action="processTransfer" method="post">
			<table onmouseup="initialize()">

				<tr>
					<td><label>Select A/C with Balance</label></td>
					<td><select name="fromAc" id="from">
							<option value="select">-select-</option>
							<c:forEach items="${accounts }" var="item">
								<option value="${item.accountNumber}">A/C :
									${item.accountNumber} INR ${item.balance }</option>
							</c:forEach>

					</select> <span id="fError" class="error"></span></td>
				</tr>
				<tr>
					<td><label>Select beneficiary</label></td>
					<td><select onchange="fetchAccountNumber(this)" id="benef"
						name="benefName">
							<option value="select">-select-</option>
							<c:forEach items="${beneficiaries }" var="item">
								<option value="${item}">${item}</option>
							</c:forEach>
					</select> <span id="bError" class="error"></span></td>
					<td><label>DestinationAccount Number</label> <span
						id="msgAjax"></span></td>
				</tr>
				<tr>
					<td><label>Transfer Amount</label></td>
					<td><input type="text" name="amount" id="amount"> <span
						id="aError"></span></td>
				</tr>
				<tr>
					<td><label>Transfer Detail</label></td>
					<td><input type="text" name="detail" id="detail"> <span
						class="error" id="dError"></span></td>

				</tr>
				<tr>
					<td><input type="submit" value="TRANSFER"
						onclick="return validate()"></td>

					<td><input type="button" value="LOGOUT" onclick="logout()"></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>