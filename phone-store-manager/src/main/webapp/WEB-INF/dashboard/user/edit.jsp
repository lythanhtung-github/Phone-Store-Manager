<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>

<%--<head>--%>
<%--  <!-- link-head -->--%>
<%--  <jsp:include page="/layout/link-head.jsp"></jsp:include>--%>
<%--  <!-- link-head -->--%>
<%--  <style>--%>
<%--    #imageProduct {--%>
<%--      width: 400px;--%>
<%--      border-radius: 10px;--%>
<%--      transition: 0.5s ease;--%>
<%--    }--%>
<%--    #imageProduct:hover {--%>
<%--      width: 420px;--%>
<%--    }--%>
<%--  </style>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div class="be-wrapper be-fixed-sidebar">--%>
<%--  <!-- nav-header -->--%>
<%--  <jsp:include page="/layout/nav-header.jsp">--%>
<%--    <jsp:param name="page" value="QUẢN LÝ SẢN PHẨM"/>--%>
<%--    <jsp:param name="userFulName" value="Lý Thanh Tùng"/>--%>
<%--    <jsp:param name="userImage" value="https://cdn-icons-png.flaticon.com/512/61/61205.png"/>--%>
<%--  </jsp:include>--%>
<%--  <!-- nav-header -->--%>

<%--  <!-- be-left-sidebar -->--%>
<%--  <jsp:include page="/layout/left-sidebar.jsp"></jsp:include>--%>
<%--  <!-- be-left-sidebar -->--%>
<%--  <!-- be-content -->--%>
<%--  <div class="be-content">--%>
<%--    <div class="main-content container-fluid">--%>
<%--      <!--show-total-->--%>
<%--      <jsp:include page="/layout/show-total.jsp">--%>
<%--        <jsp:param name="usersTotal" value="${applicationScope.listUser.size()}"/>
<%--        <jsp:param name="ordersTotal" value="1000"/>--%>
<%--        <jsp:param name="productsTotal" value="${applicationScope.listProduct.size()}"/>--%>
<%--        <jsp:param name="customersTotal" value="1000"/>--%>
<%--      </jsp:include>--%>
<%--      <!--show-total-->--%>

<%--      <c:if test="${!requestScope.errors.isEmpty()&&requestScope.errors!=null }">--%>
<%--        <c:forEach items="${requestScope.errors}" var="item">--%>
<%--          <div class="alert alert-warning" role="alert">--%>
<%--            <div class="alert alert-danger alert-dismissible" role="alert">--%>
<%--              <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span--%>
<%--                      class="mdi mdi-close" aria-hidden="true"></span></button>--%>
<%--              <div class="icon"><span class="mdi mdi-close-circle-o"></span></div>--%>
<%--              <div class="message">--%>
<%--                <strong>Error!</strong>--%>
<%--                  ${item}--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </c:forEach>--%>
<%--      </c:if>--%>


<%--      <c:if test="${requestScope.message!=null}">--%>
<%--        <div class="alert alert-success alert-dismissible" role="alert">--%>
<%--          <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span--%>
<%--                  class="mdi mdi-close" aria-hidden="true"></span></button>--%>
<%--          <div class="icon"><span class="mdi mdi-check"></span></div>--%>
<%--          <div class="message">--%>
<%--            <strong>Good!</strong>--%>
<%--              ${requestScope.message}--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </c:if>--%>

<%--      <div class="row">--%>
<%--        <div class="col-md-12">--%>
<%--          <div class="card card-border-color card-border-color-primary">--%>
<%--            <div class="card-header card-header-divider">Chỉnh sửa sản phẩm</div>--%>
<%--            <form method="post" style="display:flex">--%>
<%--              <div class="card-body col-md-7">--%>
<%--                <div class="form-group row" style ="display:none">--%>
<%--                  <input class="form-control" id="id" name="id" type="text"--%>
<%--                         value="${requestScope.product.getId()}">--%>
<%--                </div>--%>
<%--                <div class="form-group row">--%>
<%--                  <label class="col-12 col-sm-3 col-form-label text-sm-right" for="productName">--%>
<%--                    Tên sản phẩm:--%>
<%--                  </label>--%>
<%--                  <div class="col-12 col-sm-8 col-lg-6">--%>
<%--                    <input class="form-control" id="productName" name="productName" type="text"--%>
<%--                           placeholder="Nhập tên sản phẩm"--%>
<%--                           value="${requestScope.product.getName()}">--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="form-group row">--%>
<%--                  <label class="col-12 col-sm-3 col-form-label text-sm-right" for="price">--%>
<%--                    Giá:--%>
<%--                  </label>--%>
<%--                  <div class="col-12 col-sm-8 col-lg-6">--%>
<%--                    <input class="form-control" id="price" name="price"--%>
<%--                           type="number" min="500000" step="1000"--%>
<%--                           placeholder="Nhập giá"--%>
<%--                           value="${requestScope.product.getPrice()}"/>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="form-group row">--%>
<%--                  <label class="col-12 col-sm-3 col-form-label text-sm-right" for="quantity">--%>
<%--                    Số lượng:--%>
<%--                  </label>--%>
<%--                  <div class="col-12 col-sm-8 col-lg-6">--%>
<%--                    <input class="form-control" id="quantity" name="quantity" type="number" min="1"--%>
<%--                           placeholder="Nhập số lượng"--%>
<%--                           value="${requestScope.product.getQuantity()}">--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="form-group row">--%>
<%--                  <label class="col-12 col-sm-3 col-form-label text-sm-right"--%>
<%--                         for="description">Chi tiết:--%>
<%--                  </label>--%>
<%--                  <div class="col-12 col-sm-8 col-lg-6">--%>
<%--                            <textarea class="form-control" rows="4" id="description"--%>
<%--                                      name="description">${requestScope.product.getDescription()}--%>
<%--                            </textarea>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="form-group row">--%>
<%--                  <label class="col-12 col-sm-3 col-form-label text-sm-right" for="image">--%>
<%--                    Hình ảnh:--%>
<%--                  </label>--%>
<%--                  <div class="col-12 col-sm-6">--%>
<%--                    <input class="form-control" id="image" type="text" name="image"--%>
<%--                           placeholder="Nhập hình ảnh"--%>
<%--                           onchange="chooseFile()"--%>
<%--                           value="${requestScope.product.getImage()}">--%>
<%--                  </div>--%>
<%--                </div>--%>

<%--                <div class="form-group row">--%>
<%--                  <label class="col-12 col-sm-3 col-form-label text-sm-right"></label>--%>
<%--                  <button class="btn btn-space btn-success col-12 col-sm-6" type="submit"--%>
<%--                          style="height: 50px; font-size: 20px">--%>
<%--                    <span class="mdi mdi-plus-square"> Chỉnh sửa sản phẩm</span>--%>
<%--                  </button>--%>
<%--                </div>--%>
<%--              </div>--%>
<%--              <div class="card-body col-md-7">--%>
<%--                <div class="form-group row">--%>
<%--                  <img id="imageProduct"--%>
<%--                       src="${requestScope.user.getImage()}"--%>
<%--                       alt="Ảnh sản phẩm">--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </form>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>

<%--    </div>--%>
<%--  </div>--%>
<%--  <!--content-->--%>
<%--</div>--%>

<%--<script>--%>
<%--  function chooseFile() {--%>
<%--    let image_src = document.querySelector("#image").value;--%>
<%--    let image = document.querySelector("#imageProduct");--%>
<%--    image.setAttribute("src", image_src);--%>
<%--  }--%>
<%--</script>--%>
<%--<!-- js-footer -->--%>
<%--<jsp:include page="/layout/js-footer.jsp">--%>
<%--  <jsp:param name="page" value="index"/>--%>
<%--</jsp:include>--%>
<%--<!-- js-footer -->--%>

<%--</body>--%>
<%--</html>--%>