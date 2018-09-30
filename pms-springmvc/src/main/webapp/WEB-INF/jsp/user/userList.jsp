<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>显示所有的用户</p>
<table>
	<thead>
		<tr>
			<td>姓名</td>
			<td>密码</td>
		</tr>
	</thead>
<c:forEach items="${users}" var="user" varStatus="status">
        <tr>  
             <td><c:out value="${status.count}" /></td>
             <td align = "center">${user.name}</td>  
             <td align = "center">${user.password}</td>  
         </tr>  
</c:forEach> 
</table>
</body>
</html>