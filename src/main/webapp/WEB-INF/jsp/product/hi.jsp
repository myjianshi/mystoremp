<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>


<%@include file="common/header.jsp"%>

<body>
<div id="wrapper" class="toggled">


    <%@include file="common/nav.jsp"%>

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
<h1>Hi jsp</h1>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>商品Id</th>
                    <th>Name</th>
                    <th>图片</th>
                    <th>单价</th>

                    <th>库存</th>
                    <th>描述</th>
                    <th>类目</th>

                    <th>CreateTime</th>
                    <th>UpdateTime</th>

                    <th colspan="2">操作</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="sp" items="${page.list}" varStatus="st">
                    <tr>
                        <td>${sp.id}</td>
                        <td>${sp.productName}</td>
                        <td><img width="75" height="100" src="/static/imgs/${sp.productIcon}"></td>
                        <td>${sp.productPrice}</td>
                        <td>${sp.productStock}</td>
                        <td>${sp.productDescription}</td>
                        <td>${sp.categoryTypeName}</td>
                        <td>${sp.createTime}</td>
                        <td>${sp.updateTime}</td>
                        <td><a href="edit?id=${sp.id}">修改</a>

                            <c:choose>
                                <c:when test="${sp.getProductStatusEnum().message=='上架'}">
                                    <a href="offsale?id=${sp.id}">下架</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="onsale?id=${sp.id}">上架</a>
                                </c:otherwise>
                            </c:choose>

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
