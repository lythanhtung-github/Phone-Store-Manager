<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                                <div class="user-display-avatar"><img src="${sessionScope.account.getImage()}"
                                                                      alt="Avatar"></div>
                                <div class="user-display-info">
                                    <div class="name">${sessionScope.account.getFullName()}</div>
                                </div>
                            </div>
                        </div>

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

                        <div class="user-info-list card">
                            <div class="card-header card-header-divider">Đổi mật khẩu</div>
                            <form action="/user" method="post" class="col-md-12">
                                <div class="card-body" style="display: flex; justify-content: space-around">
                                    <div style="">
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"
                                                   for="password">Nhập mật khẩu mới: </label>
                                            <input class="form-control" id="password" name="password" type="password"
                                                   value="${requestScope.password}">
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12 col-sm-3 col-form-label text-sm-right"
                                                   for="passwordReEnter">Nhập lại mật khẩu: </label>
                                            <input class="form-control" id="passwordReEnter" name="passwordReEnter"
                                                   type="password"
                                                   value="${requestScope.passwordReEnter}">
                                        </div>
                                        <div class="form-group row">
                                            <input type="text" name="action" value="changepass" style="display: none">
                                            <button class="btn btn-space btn-primary col-12 col-sm-6" type="submit"
                                                    style="height: 50px; font-size: 20px">
                                                <span class="mdi mdi-refresh-sync"> Đổi mật khẩu</span>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <img style="width:300px" src="${sessionScope.account.getImage()}"
                                             alt="User image">
                                    </div>

                                </div>
                            </form>
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