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
        <c:forEach var="girl" items="${page.list}" varStatus="st">
            <tr>
                <td>${girl.id}</td>
                <td>${girl.buyerName}</td>
                <td>${girl.buyerPhone}</td>
                <td>${girl.buyerAddress}</td>
                <td>${girl.orderAmount}</td>
                <td>${girl.orderStatus}</td>
                <td>${girl.payStatus}</td>
                <td>${girl.createTime}</td>
                <td>${girl.updateTime}</td>
                <td><a href="editGirl?id=${girl.id}">Detail</a> </td>
                <td><a href="delGirl?id=${girl.id}">Cancel</a> </td>

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
    <form action="addGirl" method="post">

        name: <input name="name"> <br>
        price: <input name="price"> <br>

        <button type="submit">提交</button>
    </form>
</div>


</body>
</html>