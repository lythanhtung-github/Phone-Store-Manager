<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/layout/link-login-head.jsp"></jsp:include>
    <title>Đăng ký</title>
</head>

<body>
<div class="register-card-conainer">
    <div class="register-card">
        <div class="register-card-header">
            <h1>Đăng ký</h1>
        </div>
        <form class="register-card-form">
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-regular fa-address-card"></i></span>
                <input type="text" placeholder="Nhập họ tên" required autofocus>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-calendar-days"></i></span>
                <input type="date" required autofocus>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-user-pen"></i></span>
                <input type="email" placeholder="Nhập email" required autofocus>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-phone"></i></span>
                <input type="text" placeholder="Nhập số điện thoại" required autofocus>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-address-book"></i></span>
                <input type="text" placeholder="Nhập địa chỉ" required autofocus>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-key"></i></span>
                <input type="password" id ="ip-password-register-1" placeholder="Nhập mật khẩu" required>
                <span class="show-password" onclick="showPassword2()"><i class="fa-solid fa-eye"></i></span>
            </div>
            <div class="form-item">
                <span class="form-item-icon"><i class="fa-solid fa-key"></i></span>
                <input type="password" id ="ip-password-register-2" placeholder="Nhập lại mật khẩu" required>
                <span class="show-password" onclick="showPassword3()"><i class="fa-solid fa-eye"></i></span>
            </div>
            <button type="submit">Đăng ký</button>
        </form>
        <div class="register-card-footer">
            Bạn đã có tài khoản? <a href="/login?type=customer">Đăng nhập</a>.
        </div>
    </div>
</div>
</body>
</html>
