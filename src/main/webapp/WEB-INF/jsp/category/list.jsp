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
                    <th>Id</th>
                    <th>Name</th>
                    <th>类型</th>

                    <th>CreateTime</th>
                    <th>UpdateTime</th>

                    <th colcategoryan="2">操作</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="category" items="${categories}" varStatus="st">
                    <tr>
                        <td>${category.categoryId}</td>
                        <td>${category.categoryName}</td>
                        <td>${category.categoryType}</td>
                        <td>${category.createTime}</td>
                        <td>${category.updateTime}</td>
                        <td><a href="edit?id=${category.categoryId}">修改</a>
                        </td>


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
