<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <jsp:param name="page" value="Trang chủ"/>
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

            <div class="row">
                <div class="col-12 col-lg-6">
                    <div class="card card-table">
                        <div class="card-header">
                            <div class="tools dropdown">
                                <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"><span
                                        class="icon mdi mdi-more-vert"></span></a>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                    <a class="dropdown-item" href="/user">Quản lý nhân viên</a>
                                </div>
                            </div>
                            <div class="title">Nhân viên tiêu biểu</div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">Họ tên</th>
                                    <th>Địa chỉ</th>
                                    <th>SĐT</th>
                                    <th>Đơn hàng đã duyệt</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.listUserRank}" var="userRank">
                                    <tr>
                                        <td class="user-avatar text-success">
                                            <img src="${userRank.getUserImage()}"
                                                 alt="User image">
                                                ${userRank.getUserFullName()}
                                        </td>
                                        <td>${userRank.getUserAddress()}</td>
                                        <td>${userRank.getUserPhoneNumber()}</td>
                                        <td class="text-center text-success">${userRank.getOrderCount()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="card card-table">
                        <div class="card-header">
                            <div class="tools dropdown">
                                <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"><span
                                        class="icon mdi mdi-more-vert"></span></a>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                    <a class="dropdown-item" href="#">Quản lý khách hàng</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Thống kê khách hàng</a>
                                </div>
                            </div>
                            <div class="title">Khách hàng thân thiết</div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center">Họ tên</th>
                                    <th>Địa chỉ</th>
                                    <th>SĐT</th>
                                    <th>Số đơn hàng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.listCustomerRank}" var="customerRank">
                                    <tr>
                                        <td class="user-avatar text-success">
                                            <img src="${customerRank.getCustomerImage()}"
                                                 alt="Customer image">
                                                ${customerRank.getCustomerFullName()}
                                        </td>
                                        <td>${customerRank.getCustomerAddress()}</td>
                                        <td>${customerRank.getCustomerPhoneNumber()}</td>
                                        <td class="text-center text-success">${customerRank.getOrderCount()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-lg-6">
                    <div class="card card-table">
                        <div class="card-header">
                            <div class="tools dropdown">
                                <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"><span
                                        class="icon mdi mdi-more-vert"></span></a>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                    <a class="dropdown-item" href="/product">Quản lý sản phẩm</a>
                                </div>
                            </div>
                            <div class="title">Sản phẩm hot</div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped table-borderless">
                                <thead>
                                <tr>
                                    <th class="text-center">Sản phẩm</th>
                                    <th class="text-center">Giá (VNĐ)</th>
                                    <th class="text-center">Chi tiết</th>
                                    <th class="text-center">Số lượng bán ra</th>
                                </tr>
                                </thead>
                                <tbody class="no-border-x">
                                <c:forEach items="${requestScope.listProductRank}" var="productRank">
                                    <tr>
                                        <td class="user-avatar text-success">
                                            <img src="${productRank.getProductImage()}"
                                                 alt="User image">
                                                ${productRank.getProductName()}
                                        </td>
                                        <td class="text-danger">
                                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                                              value="${productRank.getProductPrice()}"/>
                                        </td>
                                        <td> ${productRank.getProductDescription()}</td>
                                        <td class="text-danger text-center"> ${productRank.getOrderCount()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="card card-table">
                        <div class="card-header">
                            <div class="tools dropdown">
                                <a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown"><span
                                        class="icon mdi mdi-more-vert"></span></a>
                                <div class="dropdown-menu dropdown-menu-right" role="menu">
                                    <a class="dropdown-item" href="/order">Quản lý đơn hàng</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Thống kê đơn hàng</a>
                                </div>
                            </div>
                            <div class="title">Đơn hàng trong ngày</div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped table-borderless">
                                <thead>
                                <tr>
                                    <th>Khách hàng</th>
                                    <th>Địa chỉ</th>
                                    <th>Đơn giá</th>
                                    <th>Trạng thái</th>
                                </tr>
                                </thead>
                                <tbody class="no-border-x">
                                <c:forEach items="${requestScope.listOrderDay}" var="orderDay">
                                    <tr>
                                        <td>${orderDay.getCustomerFullName()}</td>
                                        <td>${orderDay.getCustomerAddress()}</td>
                                        <td>
                                            <fmt:formatNumber type="number"
                                                              maxFractionDigits="3"
                                                              value="${orderDay.getOrderGrandTotal()}"/>
                                        </td>
                                        <td class="text-success">${orderDay.getStatusType()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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