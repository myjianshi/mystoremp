<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%@include file="/WEB-INF/jsp/common/header.jsp"%>


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
                <c:forEach var="order" items="${page.list}" varStatus="st">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.buyerName}</td>
                        <td>${order.buyerPhone}</td>
                        <td>${order.buyerAddress}</td>
                        <td>${order.orderAmount}</td>
                        <td>${order.getOrderStatusEnum().message}</td>
                        <td>${order.getPayStatusEnum().message}</td>
                        <td>${order.createTime}</td>
                        <td>${order.updateTime}</td>
                        <td><a href="detail?id=${order.id}">Detail</a> </td>
                        <td>
                            <c:if test="${order.getOrderStatusEnum().message=='新订单'}">
                                <a href="cancel?id=${order.id}">Cancel</a>
                            </c:if>

                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

                    </div>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <li>
                            <a href="?start=${page.pageNum-1}">Prev</a>
                        </li>
                        <c:forEach var="n" begin="1" end="${page.pages}">
                           <li>
                               <c:if test="${page.pageNum==n}">

                               </c:if>
                               <c:choose>
                                   <c:when test="${page.pageNum==n}">
                                       <a class="disabled" href="#">${n}</a>
                                   </c:when>
                                   <c:otherwise>
                                       <a  href="?start=${n}">${n}</a>
                                   </c:otherwise>
                               </c:choose>

                           </li>
                       </c:forEach>

                        <li>
                            <a href="?start=${page.pageNum+1}">Next</a>
                        </li>
                    </ul>
                </div>
            </div>

</div>
</body>
</html>
