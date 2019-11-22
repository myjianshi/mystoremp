<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%@include file="/WEB-INF/jsp/common/header.jsp"%>


<body>
<div id="wrapper" class="toggled">




        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-6 column">
                        <form role="form" method="post" action="/seller/user/login">
                            <div class="form-group">
                                <label>Openid</label>
                                <input name="openid" type="text" class="form-control" />
                            </div>


                            <button type="submit" class="btn btn-default">登录</button>
                        </form>
                    </div>
                </div>
            </div>

</div>
</body>
</html>
