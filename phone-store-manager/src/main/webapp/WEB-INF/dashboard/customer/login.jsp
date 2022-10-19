<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/layout/link-login-head.jsp"></jsp:include>
    <title>Đăng nhập</title>
</head>

<body>
<div class="login-card-container">
    <div class="login-card">
        <div class="login-card-logo">
            <img src="/assets/img/logo-2.png"
                 alt="logo">
        </div>
        <div class="login-card-header">
            <h1>Đăng nhập</h1>
            <div>Vui lòng đăng nhập để sử dụng</div>
        </div>
        <form class="login-card-form">
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-user-pen"></i></span>
                <input type="email" placeholder="Nhập email" required autofocus>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-key"></i></span>
                <input type="password" id="ip-password-login" placeholder="Nhập mật khẩu" required>
                <span class="show-password" onclick="showPassword1()"><i class="fa-solid fa-eye"></i></span>
            </div>
            <div class="form-item-other">
                <div class="checkbox">
                    <input type="checkbox" id="rememberMeCheckBox">
                    <label for="rememberMeCheckBox">Nhớ tài khoản</label>
                </div>
                <a href="#">Quên mật khẩu</a>
            </div>
            <button type="submit">Đăng nhập</button>
        </form>
        <div class="login-card-footer">
            Bạn chưa có tài khoản? <a href="/register?type=customer">Tạo tài khoản mới</a>.
        </div>
    </div>
</div>
</body>
</html>