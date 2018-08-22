<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/myStyle.css">

</head>
<body>
	<c:if test="${ user ==null}">
		<c:redirect url="login.jsp" />
	</c:if>
	
	<c:choose>
		<c:when test="${sessionScope.user==null }">
			<c:redirect url="login.jsp" />
		</c:when>
		<c:when test="${sessionScope.user !=null }">

			<h2 class="title">An error occured while processing your request</h2>
			<h3 style="color: red">${message }</h3>
		</c:when>
	</c:choose>
</body>
</html>