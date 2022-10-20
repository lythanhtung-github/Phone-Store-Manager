<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="be-left-sidebar">
    <div class="left-sidebar-wrapper">
        <div class="left-sidebar-spacer">
            <div class="left-sidebar-scroll">
                <div class="left-sidebar-content">
                    <ul class="sidebar-elements">
                        <li class="divider">Menu</li>
                        <li class="active"><a href="/home"><i
                                class="icon mdi mdi-home"></i><span>Trang chủ</span></a>
                        </li>
                        <li class="parent"><a href="#"><i class="icon mdi mdi-face"></i><span>Tài khoản</span></a>
                            <ul class="sub-menu">
                                <li><a href="/user?action=view&id=<%=request.getParameter("id") %>">Thông tin tài
                                    khoản</a>
                                </li>
                                <li><a href="/user?action=edit&id=<%=request.getParameter("id") %>">Thay đổi thông
                                    tin</a>
                                </li>
                                <li><a href="/user?action=changepass">Đổi mật khẩu</a>
                                </li>
                            </ul>
                        </li>
                        <li class="parent"><a href="#"><i class="icon mdi mdi-accounts-outline"></i><span><span
                                class="badge badge-primary float-right">New</span>Quản lý nhân
                      viên</span></a>
                            <ul class="sub-menu">
                                <li><a href="/user">Danh sách nhân viên</a>
                                </li>
                                <li><a href="/user?action=create">Thêm mới nhân viên</a>
                                </li>
                            </ul>
                        </li>
                        <li class="parent"><a href="#"><i
                                class="icon mdi mdi-accounts"></i><span>Quản lý khách hàng</span></a>
                            <ul class="sub-menu">
                                <li><a href="/customer"><span class="badge badge-primary float-right">New</span>Danh
                                    sách khách hàng</a>
                                </li>
                            </ul>
                        </li>
                        <li class="parent"><a href="#"><i class="icon mdi mdi-code-smartphone"></i><span>Quản lý sản
                      phẩm</span></a>
                            <ul class="sub-menu">
                                <li><a href="/product">Danh sách sản phẩm</a>
                                </li>
                                <li><a href="/product?action=create">Thêm mới sản phẩm</a>
                                </li>
                            </ul>
                        </li>

                        <li class="parent"><a href="#"><i class="icon mdi mdi-shopping-cart"></i><span>Quản lý đơn
                      hàng</span></a>
                            <ul class="sub-menu">
                                <li><a href="/order"><span>Danh sách đơn hàng</span></a>
                                </li>
                                <li class="parent"><a href="#"><span>Duyệt đơn hàng</span></a>
                                    <ul class="sub-menu">

                                        <li><a href="/order?action=testship"><i
                                                class="icon mdi mdi-undefined"></i><span>Vận chuyển</span></a>
                                        </li>
                                        <li><a href="/order?action=testcomplete"><i
                                                class="icon mdi mdi-undefined"></i><span>Hoàn thành</span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>