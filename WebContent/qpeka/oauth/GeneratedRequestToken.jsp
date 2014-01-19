<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Map<String, Object> tokenMap =  (Map<String, Object>)request.getAttribute("generatedToken");
	if(tokenMap != null && !tokenMap.isEmpty()) {
		%>
		<input type="text" value="<%=tokenMap.get("token")%>" readonly="readonly">
		<input type="text" value="<%=tokenMap.get("key")%>" readonly="readonly">
		<input type="text" value="<%=tokenMap.get("secret")%>" readonly="readonly">	
	<%
	}
	
	%>
	 
</body>
</html>