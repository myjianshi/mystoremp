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
                  <table class="table table-bordered table-hover table-condensed">
                      <thead>
                      <tr>
                          <th>
                              订单ID
                          </th>
                          <th>
                              购买者
                          </th>
                          <th>
                              Phone
                          </th>
                          <th>
                              订单总金额
                          </th>
                          <th>
                              订单状态
                          </th>
                          <th>
                              支付状态
                          </th>
                          <th>
                              创建时间
                          </th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>

                      <tr class="success">
                          <td>
                              ${order.id}
                          </td>
                          <td>
                              ${order.buyerName}
                          </td>
                          <td>
                              ${order.buyerPhone}
                          </td>
                          <td>
                              ${order.orderAmount}
                          </td>
                          <td>
                              ${order.getOrderStatusEnum().message}
                          </td>
                          <td>
                              ${order.getPayStatusEnum().message}
                          </td>
                          <td>
                              ${order.getOkCreateTime()}
                          </td>
                      </tr>


                      </tbody>
                  </table>
              </div>
          </div>


          <div class="row clearfix">
              <div class="col-md-12 column">
                  <table class="table table-striped table-hover">
                      <thead>
                      <tr>
                          <th>
                              商品编号
                          </th>
                          <th>
                              商品名称
                          </th>
                          <th>
                              单价
                          </th>
                          <th>
                              数量
                          </th>
                          <th>
                              总额
                          </th>
                      </tr>
                      </thead>
                      <tbody>

                      <c:forEach var="sp" items="${order.orderDetailList}">
                          <tr class="info">
                              <td>
                                      ${sp.id}
                              </td>
                              <td>
                                      ${sp.productName}
                              </td>
                              <td>
                                      ${sp.productPrice}
                              </td>
                              <td>
                                      ${sp.productQuantity}
                              </td>
                              <td>
                                      ${sp.productQuantity*sp.productPrice}
                              </td>
                          </tr>
                      </c:forEach>
                      </tbody>
                  </table>
              </div>
          </div>

          <c:if test="${order.getOrderStatusEnum().message=='新订单'}">
              <div class="row clearfix">
                  <div class="col-md-12 column">
                      <a href="finish?id=${order.id}" type="button" class="btn btn-default btn-primary">完结订单</a>
                      <a href="cancel?id=${order.id}" type="button" class="btn btn-default btn-danger">取消订单</a>
                  </div>
              </div>
          </c:if>
      </div>
    </div>

</div>
</body>
</html>
