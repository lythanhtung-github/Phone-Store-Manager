<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- link-head -->
    <jsp:include page="/layout/link-head.jsp"></jsp:include>
    <!-- link-head -->
    <style>
        #imageUser {
            width: 400px;
            border-radius: 10px;
            transition: 0.5s ease;
        }
        #imageUser:hover {
            width: 420px;
        }
    </style>
</head>

<body>
<div class="be-wrapper be-fixed-sidebar">
    <!-- nav-header -->
    <jsp:include page="/layout/nav-header.jsp">
        <jsp:param name="page" value="QUẢN LÝ KHÁCH HÀNG"/>
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

            <c:if test="${!requestScope.errors.isEmpty()&&requestScope.errors!=null }">
                <c:forEach items="${requestScope.errors}" var="item">
                    <div class="alert alert-warning" role="alert">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span
                                    class="mdi mdi-close" aria-hidden="true"></span></button>
                            <div class="icon"><span class="mdi mdi-close-circle-o"></span></div>
                            <div class="message">
                                <strong>Error!</strong>
                                    ${item}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

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

            <div class="row">
                <div class="col-md-12">
                    <div class="card card-border-color card-border-color-primary">
                        <div class="card-header card-header-divider">Chỉnh sửa thông tin</div>
                        <form method="post" style="display:flex">
                            <div class="card-body col-md-7">
                                <div class="form-group row">
                                    <div class="form-group row" style ="display:none">
                                        <input class="form-control" id="id" name="id" type="text"
                                               value="${requestScope.customer.getId()}">
                                    </div>
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right" for="fullName">
                                        Họ tên:
                                    </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input class="form-control" id="fullName" name="fullName" type="text"
                                               placeholder="Nhập họ tên"
                                               value="${requestScope.customer.getFullName()}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right" for="phoneNumber">
                                        Số điện thoại:
                                    </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input class="form-control" id="phoneNumber" name="phoneNumber" type="text"
                                               placeholder="Nhập số điện thoại"
                                               value="${requestScope.customer.getPhoneNumber()}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right" for="email">
                                        Email:
                                    </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input class="form-control" id="email" name="email" type="email"
                                               placeholder="Nhập email"
                                               value="${requestScope.customer.getEmail()}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right" for="address">
                                        Địa chỉ:
                                    </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input class="form-control" id="address" name="address" type="text"
                                               placeholder="Nhập địa chỉ"
                                               value="${requestScope.customer.getAddress()}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right" for="image">
                                        Hình ảnh:
                                    </label>
                                    <div class="col-12 col-sm-6">
                                        <input class="form-control" id="image" type="text" name="image"
                                               placeholder="Nhập hình ảnh"
                                               onchange="chooseFile()"
                                               value="${requestScope.customer.getImage()}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right"></label>
                                    <button class="btn btn-space btn-success col-12 col-sm-6" type="submit"
                                            style="height: 40px; font-size: 18px">
                                        <span class="mdi mdi-edit"> Chỉnh sửa</span>
                                    </button>
                                </div>
                            </div>
                            <div class="card-body col-md-7">
                                <div class="form-group row">
                                    <img id="imageUser"
                                         src="${requestScope.customer.getImage()}"
                                         alt="user image">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!--content-->
</div>

<script>
    function chooseFile() {
        let image_src = document.querySelector("#image").value;
        let image = document.querySelector("#imageUser");
        image.setAttribute("src", image_src);
    }
</script>
<!-- js-footer -->
<jsp:include page="/layout/js-footer.jsp">
    <jsp:param name="page" value="index"/>
</jsp:include>
<!-- js-footer -->

</body>
</html>

