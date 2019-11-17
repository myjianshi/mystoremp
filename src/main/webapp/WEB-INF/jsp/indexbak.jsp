<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>orderOrder</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-bordered table-hover">
                <thead>
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
                </thead>
                <tbody>
                <c:forEach var="order" items="${page.list}" varStatus="st">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.buyerName}</td>
                        <td>${order.buyerPhone}</td>
                        <td>${order.buyerAddress}</td>
                        <td>${order.orderAmount}</td>
                        <td>${order.orderStatus}</td>
                        <td>${order.payStatus}</td>
                        <td>${order.createTime}</td>
                        <td>${order.updateTime}</td>
                        <td><a href="editorder?id=${order.id}">Detail</a> </td>
                        <td><a href="delorder?id=${order.id}">Cancel</a> </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
