<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- nav-header -->
<nav class="navbar navbar-expand fixed-top be-top-header">
    <div class="container-fluid">
        <div class="be-navbar-header"><a class="navbar-brand" href="/home"></a>
        </div>

            <div class="page-title"><span>
                  <%=request.getParameter("page") %>
            </span></div>

        <div class="be-right-navbar">
            <div class="page-title">
        <span>
        <%=request.getParameter("userFulName") %>
      </span>
            </div>
            <ul class="nav navbar-nav float-right be-user-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
                       role="button" aria-expanded="false">
                        <img src="<%=request.getParameter("userImage") %>"
                             alt="Avatar">
                    </a>
                    <div class="dropdown-menu" role="menu">
                        <div class="user-info">
                            <div class="user-name">
                                <%=request.getParameter("userFulName") %>
                            </div>
                            <div class="user-position online">Đang hoạt động</div>
                        </div>
                        <a class="dropdown-item" href="#">
                            <span class="icon mdi mdi-face"></span>Thông tin tài khoản
                        </a>
                        <a class="dropdown-item" href="#">
                            <span class="icon mdi mdi-settings"></span>Cài đặt
                        </a>
                        <a class="dropdown-item" href="#">
                            <span class="icon mdi mdi-power"></span>Đăng xuất
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- nav-header -->
