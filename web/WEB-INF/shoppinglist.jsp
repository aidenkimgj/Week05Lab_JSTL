<%-- 
    Document   : shoppinglist
    Created on : Oct 15, 2020, 4:13:27 PM
    Author     : 837033
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${user} <a href="ShoppingList?action=logout">Logout</a></p>
        
        <h2>List</h2>
        <form action="" method="POST">
            <label>Add item: </label>
            <input type="text" name="addItem">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
            <br><br>
        </form>
        
        <form action="" method="POST">
            <c:forEach items="${items}" var="item">
                <label>
                    <input type="radio" name="item" value="${item}">
                    <span>${item}</span>
                </label>
                <br>
            </c:forEach>
            <c:if test="${fn:length(items) gt 0}">
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </c:if>    
        </form> 
    </body>
</html>
