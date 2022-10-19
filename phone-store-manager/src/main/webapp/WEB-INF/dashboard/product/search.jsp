<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- link-head -->
    <jsp:include page="/layout/link-head.jsp"></jsp:include>
    <!-- link-head -->
</head>

<body>
<div class="be-wrapper be-fixed-sidebar">
    <!-- nav-header -->
    <jsp:include page="/layout/nav-header.jsp">
        <jsp:param name="page" value="QUẢN LÝ SẢN PHẨM"/>
        <jsp:param name="userFulName" value="${sessionScope.account.getFullName()}"/>
        <jsp:param name="userImage" value="${sessionScope.account.getImage()}"/>
    </jsp:include>
    <!-- nav-header -->

    <!-- be-left-sidebar -->
    <jsp:include page="/layout/left-sidebar.jsp">
        <jsp:param name="id" value="${sessionScope.account.getId()}"/>
    </jsp:include>
    <!-- be-left-sidebar -->
    <!-- be-content -->
    <div class="be-content">
        <div class="main-content container-fluid">
            <!--show-total-->
            <jsp:include page="/layout/show-total.jsp">
                <jsp:param name="usersTotal" value="${applicationScope.listUser.size()}"/>
                <jsp:param name="ordersTotal" value="${applicationScope.listOrder.size()}"/>
                <jsp:param name="productsTotal" value="${applicationScope.listProduct.size()}"/>
                <jsp:param name="customersTotal" value="${applicationScope.listCustomer.size()}"/>
            </jsp:include>
            <!--show-total-->
            <c:if test="${requestScope.message!=null}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                            class="mdi mdi-close" aria-hidden="true"></span></button>
                    <div class="icon"><span class="mdi mdi-check"></span></div>
                    <div class="message">
                        <strong>Good!</strong>
                            ${requestScope.message}
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.error!=null}">
                <div class="alert alert-warning" role="alert">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                                class="mdi mdi-close" aria-hidden="true"></span></button>
                        <div class="icon"><span class="mdi mdi-close-circle-o"></span></div>
                        <div class="message">
                            <strong>Error!</strong>
                                ${requestScope.error}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-sm-12">
                    <div class="card card-table">
                        <div class="card-header">Danh sách sản phẩm</div>
                        <div id="table4_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                            <div class="row be-datatable-header">
                                <div class="col-sm-6">
                                    <div class="dataTables_length" id="table4_length">
                                        <label>Show
                                            <select name="table4_length" aria-controls="table4"
                                                    class="custom-select custom-select-sm form-control form-control-sm">
                                                <option value="10">10</option>
                                                <option value="25">25</option>
                                                <option value="50">50</option>
                                                <option value="100">100</option>
                                            </select> entries</label></div>
                                </div>
                                <div class="col-sm-6">
                                    <form action="/product?action=search" method = "post">
                                        <div id="table4_filter" class="dataTables_filter" style="display: flex">
                                            <input type="search"
                                                   class="form-control form-control-sm"
                                                   placeholder=""
                                                   aria-controls="table4"
                                                   name = "inputSearch"
                                                    value = "${requestScope.searchStr}" />
                                            <button type ="submit" class="btn btn-space btn-primary"
                                                    style="margin: auto 0">
                                                <i class="icon icon-left mdi mdi-search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped table-hover table-fw-widget" id="table4">
                                <thead style = "background-color: #2b2b2b; color: white">
                                <tr>
                                    <th class="text-center">Id</th>
                                    <th class="text-center">Sản phẩm</th>
                                    <th class="text-center">Giá (VNĐ)</th>
                                    <th class="text-center">Số lượng</th>
                                    <th class="text-center" style="width:200px">Chi tiết</th>
                                    <th class="text-center">Hình ảnh</th>
                                    <th class="text-center">Thời gian tạo</th>
                                    <th class="text-center">Thời gian chỉnh sửa</th>
                                    <th class="text-center" style="width:100px">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${requestScope.productListSearch}">
                                    <tr class="odd gradeX">
                                        <td class="text-center">${user.getId()}</td>
                                        <td class="text-start">${user.getName()}</td>
                                        <td class="text-end">
                                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                                              value="${user.getPrice()}"/>
                                        </td>
                                        <td class="text-end">
                                                ${user.getQuantity()}
                                        </td>
                                        <td class="text-center">
                                                ${user.getDescription()}
                                        </td>
                                        <td class="text-center">
                                            <img style="width:60px" src="${user.getImage()}"
                                                 alt="Product image">
                                        </td>
                                        <td class="text-center">
                                                ${user.getCreatedTime()}
                                        </td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${user.getUpdatedTime()==null}">
                                                    <span>Chưa cập nhật</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${user.getUpdatedTime()}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <a href="#" type="button" class="btn btn-primary ">
                                                <span class="mdi mdi-edit"></span>
                                            </a>
                                            <a href="/product?action=delete&id=${user.getId()}"
                                               type="button" class="btn btn-danger">
                                                <span class="mdi mdi-delete"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row be-datatable-footer">
                            <div class="col-sm-12">
                                <div class="dataTables_paginate paging_simple_numbers" id="table1_paginate">
                                    <ul class="pagination">
                                        <li class="paginate_button page-item previous disabled" id="table1_previous">
                                            <a href="#" aria-controls="table1" data-dt-idx="0" tabindex="0"
                                               class="page-link">
                                                Previous
                                            </a>
                                        </li>
                                        <li class="paginate_button page-item active">
                                            <a href="#" aria-controls="table1" data-dt-idx="1" tabindex="0"
                                               class="page-link">
                                                1
                                            </a>
                                        </li>
                                        <li class="paginate_button page-item ">
                                            <a href="#" aria-controls="table1" data-dt-idx="2" tabindex="0"
                                               class="page-link">
                                                2
                                            </a>
                                        </li>
                                        <li class="paginate_button page-item disabled" id="table1_ellipsis">
                                            <a href="#" aria-controls="table1" data-dt-idx="3" tabindex="0"
                                               class="page-link">
                                                …
                                            </a>
                                        </li>
                                        <li class="paginate_button page-item ">
                                            <button style="border:none"><a href="#" aria-controls="table1"
                                                                           data-dt-idx="4" tabindex="0"
                                                                           class="page-link">
                                                6
                                            </a></button>

                                        </li>
                                        <li class="paginate_button page-item next" id="table1_next">
                                            <a href="#" aria-controls="table1" data-dt-idx="5" tabindex="0"
                                               class="page-link">
                                                Next
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--content-->
</div>

<!-- js-footer -->
<jsp:include page="/layout/js-footer.jsp">
    <jsp:param name="page" value="index"/>
</jsp:include>
<!-- js-footer -->

</body>
</html>