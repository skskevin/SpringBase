<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Page</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/}/queryUser.action" method="post">
<input type="text" name="wanxin" />
<input type="submit" value="查询"/>
</form>

<form action="${pageContext.request.contextPath }/queryUser.action" method="post">
	<table width="100%" border=1>
		<tr>
			<th>名称</th>
			<th>时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${vulCategoryList }" var="item">
		<tr>
			<td>${item.name }</td>
			<td>${item.createTime }</td>
			<td><a href="${pageContext.request.contextPath }/editWanxinUser.action?id=${item.id}">修改</a></td>
		</tr>
		</c:forEach>
	</table>
</form>
</body>
</html>