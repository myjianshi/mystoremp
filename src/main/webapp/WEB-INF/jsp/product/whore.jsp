<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%@include file="/WEB-INF/jsp/common/header.jsp"%>


<body>
<div id="wrapper" class="toggled">


    <%@include file="/WEB-INF/jsp/common/nav.jsp"%>

        <div id="page-content-wrapper">
            <div class="container-fluid">

                <c:if test="${!empty errors}">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div class="alert alert-dismissable alert-warning">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                <h4>
                                    表单校验错误!
                                </h4> <strong>${errors}</strong>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="row clearfix">
                    <div class="col-md-6 column">
                        <form role="form" method="post" action="/whore/reg">
                            <div class="form-group">
                                <label>名称</label>
                                <input name="name" type="text" class="form-control" value="${(whore.name)}"/>
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input name="price" type="number" class="form-control" value="${(whore.price)}"/>
                            </div>

                    <button type="submit" class="btn btn-default">提交</button>
                    </form>
                    </div>
                </div>
            </div>

</div>
</body>
</html>
