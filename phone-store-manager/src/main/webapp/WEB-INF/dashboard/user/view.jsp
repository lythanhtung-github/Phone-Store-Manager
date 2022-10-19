<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/layout/link-head.jsp"></jsp:include>
</head>

<body>
<div class="be-wrapper">
    <!-- nav-header -->
    <jsp:include page="/layout/nav-header.jsp">
        <jsp:param name="page" value="QUẢN LÝ NHÂN VIÊN"/>
        <jsp:param name="userFulName" value="${sessionScope.account.getFullName()}"/>
        <jsp:param name="userImage" value="${sessionScope.account.getImage()}"/>
    </jsp:include>
    <!-- nav-header -->
    <!-- be-left-sidebar -->
    <jsp:include page="/layout/left-sidebar.jsp">
        <jsp:param name="id" value="${sessionScope.account.getId()}"/>
    </jsp:include>
    <!-- be-left-sidebar -->

    <!--profile-user-->
    <div class="be-content">
        <div class="main-content container-fluid">
            <div class="user-profile">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="user-display">
                            <div class="user-display-bg"><img src="\assets\img\user-profile-display.png"
                                                              alt="Profile Background">
                            </div>
                            <div class="user-display-bottom">
                                <div class="user-display-avatar"><img src="${requestScope.user.getImage()}"
                                                                      alt="Avatar"></div>
                                <div class="user-display-info">
                                    <div class="name">${requestScope.user.getFullName()}</div>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-list card">
                            <div class="card-header card-header-divider">Thông tin cá nhân
                                <span class="card-subtitle">Xin chào! Tôi là ${requestScope.user.getFullName()} đến từ ${requestScope.user.getAddress()}</span>
                            </div>
                            <div class="card-body">
                                <table class="no-border no-strip skills">
                                    <tbody class="no-border-x no-border-y">
                                    <tr>
                                        <td class="icon"><span class="mdi mdi-case"></span></td>
                                        <td>Quyền hạn</td>
                                        <td class="item">
                                            <c:forEach items="${applicationScope.listRole}" var="role">
                                                <c:if test="${requestScope.user.getRole() == role.getId()}">
                                                    ${role.getType()}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td colspan="2" rowspan="6">
                                            <img style="width:300px" src="${requestScope.user.getImage()}" alt="User image">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="icon"><span class="mdi mdi-cake"></span></td>
                                        <td class="item">Ngày sinh<span class="icon s7-gift"></span></td>
                                        <td>${requestScope.user.getBirthDay()}</td>
                                    </tr>
                                    <tr>
                                        <td class="icon"><span class="mdi mdi-smartphone-android"></span></td>
                                        <td class="item">Số điện thoại<span class="icon s7-phone"></span></td>
                                        <td>(+84) ${requestScope.user.getPhoneNumber()}</td>
                                    </tr>
                                    <tr>
                                        <td class="icon"><span class="mdi mdi-email"></span></td>
                                        <td class="item">Email<span class="icon s7-map-marker"></span></td>
                                        <td>${requestScope.user.getEmail()}</td>
                                    </tr>
                                    <tr>
                                        <td class="icon"><span class="mdi mdi-pin"></span></td>
                                        <td class="item">Địa chỉ<span class="icon s7-global"></span></td>
                                        <td>${requestScope.user.getAddress()}</td>
                                    </tr>
                                    <tr>
                                        <td class="icon"><span class="mdi mdi-time"></span></td>
                                        <td class="item">Ngày tham gia<span class="icon s7-global"></span></td>
                                        <td>${requestScope.user.getCreatedTime()}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--profile-user-->

    <!-- js-footer -->
                <jsp:include page="/layout/js-footer.jsp">
                    <jsp:param name="page" value="index"/>
                </jsp:include>
    <!-- js-footer-->
</body>
</html>