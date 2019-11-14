<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<body>
<div id="wrapper" class="toggled">


    <%@include file="/WEB-INF/jsp/common/nav.jsp"%>

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">

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
                <c:forEach var="girl" items="${page.list}" varStatus="st">
                    <tr>
                        <td>${girl.id}</td>
                        <td>${girl.buyerName}</td>
                        <td>${girl.buyerPhone}</td>
                        <td>${girl.buyerAddress}</td>
                        <td>${girl.orderAmount}</td>
                        <td>${girl.getOrderStatusEnum().message}</td>
                        <td>${girl.getPayStatusEnum().message}</td>
                        <td>${girl.createTime}</td>
                        <td>${girl.updateTime}</td>
                        <td><a href="editGirl?id=${girl.id}">Detail</a> </td>
                        <td><a href="delGirl?id=${girl.id}">Cancel</a> </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

                    </div>
                </div>
            </div>

</div>
</body>
</html>
