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
                    <div class="col-md-6 column">
                        <form role="form" method="post" action="/seller/category/update">
                            <div class="form-group">
                                <label>名称</label>
                                <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)}"/>
                            </div>
                            <div class="form-group">
                                <label>type</label>
                                <input name="categoryType" type="text" class="form-control" value="${(category.categoryType)}"/>
                            </div>

                    <input hidden type="text" name="categoryId" value="${category.categoryId}">
                    <button type="submit" class="btn btn-default">提交</button>
                    </form>
                    </div>
                </div>
            </div>

</div>
</body>
</html>
