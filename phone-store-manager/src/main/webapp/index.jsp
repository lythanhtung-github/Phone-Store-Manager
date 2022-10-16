<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <jsp:param name="userFulName" value="Lý Thanh Tùng"/>
        <jsp:param name="userImage" value="https://cdn-icons-png.flaticon.com/512/61/61205.png"/>
    </jsp:include>
    <!-- nav-header -->

    <!-- be-left-sidebar -->
    <jsp:include page="/layout/left-sidebar.jsp"></jsp:include>
    <!-- be-left-sidebar -->
    <!-- be-content -->
    <div class="be-content">
        <div class="main-content container-fluid">
            <!--show-total-->
            <jsp:include page="/layout/show-total.jsp">
                <jsp:param name="usersTotal" value="${applicationScope.listUser.size()}" />
                <jsp:param name="ordersTotal" value="1000" />
                <jsp:param name="productsTotal" value="${applicationScope.listProduct.size()}" />
                <jsp:param name="customersTotal" value="1000" />
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
                                    <a class="dropdown-item" href="#">Quản lý đơn hàng</a>
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
                                    <th style="width:30%;">Khách hàng</th>
                                    <th style="width:30%;">Địa chỉ</th>
                                    <th style="width:15%;">Đơn giá</th>
                                    <th style="width:25%;">Trạng thái</th>
                                </tr>
                                </thead>
                                <tbody class="no-border-x">
                                <tr>
                                    <td>Sony Xperia M4</td>
                                    <td>Thừa Thiên Huế</td>
                                    <td class="number">1000000</td>
                                    <td class="text-success">Completed</td>
                                </tr>
                                <tr>
                                    <td>HTC One M9</td>
                                    <td>Thừa Thiên Huế</td>
                                    <td class="number">1000</td>
                                    <td class="text-warning">Pending</td>
                                </tr>
                                <tr>
                                    <td>Sony Xperia Z5</td>
                                    <td>Thừa Thiên Huế</td>
                                    <td class="number">1000</td>
                                    <td class="text-danger">Cancelled</td>
                                </tr>
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
                                    <a class="dropdown-item" href="#">Quản lý nhân viên</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Thống kê nhân viên</a>
                                </div>
                            </div>
                            <div class="title">Danh sách nhân viên</div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th style="width:37%;">Họ tên</th>
                                    <th style="width:36%;">Địa chỉ</th>
                                    <th>Quyền hạn</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td class="user-avatar"><img src="assets\img\avatar3.png" alt="Avatar">Lý Thanh Tùng
                                    </td>
                                    <td>Thừa Thiên Huế</td>
                                    <td class="actions"><i class="icon mdi mdi-assignment-account"></i></td>
                                </tr>

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
                                    <a class="dropdown-item" href="#">Quản lý sản phẩm</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Thống kê sản phẩm</a>
                                </div>
                            </div>
                            <div class="title">Danh sách sản phẩm</div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped table-borderless">
                                <thead>
                                <tr>
                                    <th style="width:20%;">Sản phẩm</th>
                                    <th class="text-center" style="width:20%;">Giá</th>
                                    <th class="text-center" style="width:20%;">Số lượng</th>
                                    <th class="text-center" style="width:40%;">Chi tiết</th>
                                </tr>
                                </thead>
                                <tbody class="no-border-x">
                                <tr>
                                    <td>Sony Xperia M4</td>
                                    <td class="number">1000000</td>
                                    <td class="number">100</td>
                                    <td>Completed ssssssssssssssssssssssssssssssssss</td>
                                </tr>

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
                                    <th style="width:30%;">Họ tên</th>
                                    <th style="width:30%;">Địa chỉ</th>
                                    <th>SĐT</th>
                                    <th>Số đơn hàng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="user-avatar"><img src="assets\img\avatar3.png" alt="Avatar">Lý Thanh Tùng
                                    </td>
                                    <td>Thừa Thiên Huế</td>
                                    <td>0987654321</td>
                                    <td>200</td>
                                </tr>
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