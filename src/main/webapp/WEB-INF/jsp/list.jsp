<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lover</title>
</head>
<body>
<h1 align="center">Hi ls' lovers</h1>
<div style="width:1024px;margin: 20px auto; text-align: center">
    <table align="center" border="1" cellspacing="0">
        <tr>
            <th>订单Id</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Address</th>

            <th>Price</th>
            <th>OrderStatus</th>
            <th>PayStatus</th>
            <th>CreateTime</th>
            <th>UpdateTime</th>

            <th>Detail</th>
            <th>Cancel</th>
        </tr>
        <c:forEach var="sp" items="${page.list}" varStatus="st">
            <tr>
                <td>${sp.id}</td>
                <td>${sp.buyerName}</td>
                <td>${sp.buyerPhone}</td>
                <td>${sp.buyerAddress}</td>
                <td>${sp.orderAmount}</td>
                <td>${sp.orderStatus}</td>
                <td>${sp.payStatus}</td>
                <td>${sp.createTime}</td>
                <td>${sp.updateTime}</td>
                <td><a href="editorder?id=${sp.id}">Detail</a> </td>
                <td><a href="delorder?id=${sp.id}">Cancel</a> </td>

            </tr>
        </c:forEach>


    </table>
    <br>
    <div>
        <a href="?start=1">首页</a>
        <a href="?start=${page.pageNum-1}">上一页</a>
        <a href="?start=${page.pageNum+1}">下一页</a>
        <a href="?start=${page.pages}">末页</a>

    </div>
    <form action="addorder" method="post">

        name: <input name="name"> <br>
        price: <input name="price"> <br>

        <button type="submit">提交</button>
    </form>
</div>


</body>
</html>