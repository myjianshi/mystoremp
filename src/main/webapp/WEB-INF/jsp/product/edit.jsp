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
                        <form role="form" method="post" action="/seller/product/update">
                            <div class="form-group">
                                <label>名称</label>
                                <input name="productName" type="text" class="form-control" value="${(product.productName)}"/>
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input name="productPrice" type="text" class="form-control" value="${(product.productPrice)}"/>
                            </div>
                            <div class="form-group">
                                <label>库存</label>
                                <input name="productStock" type="number" class="form-control" value="${(product.productStock)}"/>
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input name="productDescription" type="text" class="form-control" value="${(product.productDescription)}"/>
                            </div>
                            <div class="form-group">
                                <label>图片</label>

                                <c:if test="${!empty product.productIcon}">
                                    <img height="100" width="100" src="/static/imgs/${(product.productIcon)}" alt="">
                                </c:if>

                                <input name="productIcon" type="text" class="form-control" value="${(product.productIcon)}"/>
                            </div>
                            <div class="form-group">
                                <label>类目</label>

                            <select name="categoryType" class="form-control">

                                <c:forEach var="category" items="${categoryList}">

                                    <option value="${category.categoryType}"
                                     <c:if test="${category.categoryType==product.categoryType}">
                                         selected
                                    </c:if>
                                    >
                                        ${category.categoryName}
                                    </option>

                                </c:forEach>
                            </select>
                    </div>
                    <input hidden type="text" name="id" value="${(product.id)}">
                    <button type="submit" class="btn btn-default">提交</button>
                    </form>
                    </div>
                </div>
            </div>

</div>
</body>
</html>
