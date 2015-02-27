<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1><spring:message code="category.view"/></h1>

<table border="1">
    <caption>CATEGORY</caption>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PARENT ID</th>
        <th>EDIT</th>
        <th>REMOVE</th>
    </tr>
    <c:forEach items="${selectResult}" var="row">
        <tr>
            <td>
                <c:out value="${row.get(\"id\")}"/>
            </td>
            <td>
                <c:out value="${row.get(\"name\")}"/>
            </td>
            <td>
                <c:out value="${row.get(\"parent_id\")}"/>
            </td>
            <td>
                <a href="<spring:url value="/category_update_form.html/?id=${row.get(\"id\")}&name=${row.get(\"name\")}&parent_id=${row.get(\"parent_id\")}" />"><spring:message
                        code="link.update"/></a>
            </td>
            <td>
                <a href="<spring:url value="/category_delete.html/${row.get(\"id\")}" />"><spring:message
                        code="link.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="<spring:url value="/category_insert.html" />"><spring:message code="category.insert"/></a>
</body>
</html>