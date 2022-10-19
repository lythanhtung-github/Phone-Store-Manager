<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <jsp:param name="page" value="QUẢN LÝ HÓA ĐƠN"/>
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
                            <strong>Lỗi!</strong>
                                ${requestScope.error}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-sm-12">
                    <div class="card card-table">
                        <div class="col-12" style="display: flex; justify-content: space-between;">
                            <div style="font-size: 20px; font-weight: bold"
                                 class="card-header">Hóa đơn #${requestScope.orderDTO.getId()}</div>
                            <div style="font-size: 20px; font-weight: bold"
                                 class="card-header">Tổng tiền:
                                <fmt:formatNumber type="number" maxFractionDigits="3"
                                                  value="${requestScope.orderDTO.getGrandTotal()}"/> VNĐ
                            </div>
                        </div>
                        <div class="col-12" style="margin-left: 40px">
                            <div class="col-md-11"  style="display: flex; justify-content: space-between">
                                <h4>Khách hàng: ${requestScope.orderDTO.getCustomerFullName()}</h4>
                                <h4>Email: ${requestScope.orderDTO.getCustomerEmail()}</h4>
                            </div>
                            <div class="col-md-11" style="display: flex; justify-content: space-between">
                                <h4>Số điện thoại: ${requestScope.orderDTO.getCustomerPhone()}</h4>
                                <h4>Địa chỉ: ${requestScope.orderDTO.getCustomerAddress()}</h4>
                            </div>
                            <div class="col-md-11" style="display: flex; justify-content: space-between">
                                <h4>Trạng thái: ${requestScope.orderDTO.getType()}</h4>
                                <h4>Thời gian tạo: ${requestScope.orderDTO.getCreateTime()}</h4>
                            </div>
                            <div class="col-md-11" style="display: flex; justify-content: space-between">
                                <h4><c:forEach items="${applicationScope.listUser}" var="userS">
                                    <c:if test="${userS.getId()==requestScope.orderDTO.getUserId()}">
                                       Nhân viên: ${userS.getFullName()}
                                    </c:if>
                                </c:forEach>
                                </h4>
                            </div>
                        </div>
                        <hr>

                        <div class="card-body">
                            <table class="table table-striped table-hover table-fw-widget" id="table4">
                                <thead style="background-color: #2b2b2b; color: white">
                                <tr>
                                    <th class="text-center">Id</th>
                                    <th class="text-center">Sản phẩm</th>
                                    <th class="text-center">Giá (VNĐ)</th>
                                    <th class="text-center">Số lượng</th>
                                    <th class="text-center">Thành tiền (VNĐ)</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="orderItemDTO" items="${requestScope.orderItemDTOList}">
                                    <tr class="odd gradeX">
                                        <td class="text-center">
                                                ${orderItemDTO.getId()}
                                        </td>
                                        <td class="text-center">
                                                ${orderItemDTO.getProductName()}
                                        </td>
                                        <td class="text-center">
                                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                                              value="${orderItemDTO.getProductPrice()}"/>
                                        </td>
                                        <td class="text-center">
                                                ${orderItemDTO.getQuantity()}
                                        </td>
                                        <td class="text-center">
                                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                                              value="${orderItemDTO.getTotal()}"/>
                                        </td>
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

