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
                        <div class="card-header">Danh sách khách hàng</div>
                        <div id="table4_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                            <div class="row be-datatable-header">
                                <form action="/customer" method="get" style="display: flex;" class="col-12">

                                    <div class="col-sm-12" style="display: flex; justify-content: flex-end;">
                                        <div>
                                            <input class="form-control" type="text" placeholder="Tìm kiếm..." name="q"
                                                   value="${requestScope.q}">
                                        </div>
                                        <div>
                                            <input type="submit" value="Tìm kiếm"
                                                   class="form-control btn-primary"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped table-hover table-fw-widget" id="table4">
                                <thead style="background-color: #2b2b2b; color: white">
                                <tr>
                                    <th class="text-center">Id</th>
                                    <th class="text-center">Họ tên</th>
                                    <th class="text-center">Email</th>
                                    <th class="text-center">SĐT</th>
                                    <th class="text-center">Địa chỉ</th>
                                    <th class="text-center">Hình ảnh</th>
                                    <th class="text-center">Thời gian tạo</th>
                                    <th class="text-center">Thời gian chỉnh sửa</th>
                                    <th class="text-center" style="width:100px">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="customer" items="${requestScope.listCustomer}">
                                    <tr class="odd gradeX">
                                        <td class="text-center">
                                                ${customer.getId()}
                                        </td>
                                        <td class="text-start">
                                            <a href="/customer?action=view&id=${customer.getId()}"
                                               class="link-info"> ${customer.getFullName()}</a>
                                        </td>
                                        <td class="text-center">
                                                ${customer.getEmail()}
                                        </td>
                                        <td class="text-end">
                                                ${customer.getPhoneNumber()}
                                        </td>
                                        <td class="text-center">
                                                ${customer.getAddress()}
                                        </td>
                                        <td class="text-center">
                                            <img style="width:60px" src="${customer.getImage()}"
                                                 alt="User image">
                                        </td>
                                        <td class="text-center">
                                                ${customer.getCreatedTime()}

                                        </td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${customer.getUpdatedTime()==null}">
                                                    <span>Chưa cập nhật</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${customer.getUpdatedTime()}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <a href="/customer?action=edit&id=${customer.getId()}" type="button"
                                               class="btn btn-primary ">
                                                <span class="mdi mdi-edit"></span>
                                            </a>
                                            <a href="/customer?action=delete&id=${customer.getId()}"
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
                            <div>
                                <ul class="pagination pagination-split justify-content-end">
                                    <c:if test="${requestScope.currentPage != 1}">
                                        <li class="page-item disabled">
                                            <a href="/customer?page=${requestScope.currentPage - 1}&q=${requestScope.q}"
                                               class="page-link">
                                                <span class="mdi mdi-chevron-left"></span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                                        <c:choose>
                                            <c:when test="${requestScope.currentPage eq i}">
                                                <li class="page-item active">
                                                    <a class="page-link">${i}</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a href="/customer?page=${i}&q=${requestScope.q}"
                                                       class="page-link">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                        <li class="page-item">
                                            <a href="/customer?page=${requestScope.currentPage + 1}&q=${requestScope.q}"
                                               class="page-link">
                                                <span class="mdi mdi-chevron-right"></span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
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