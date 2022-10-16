package controller;

import dao.*;
import model.Product;
import model.User;
import utils.AppUtils;
import utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserDAO userDAO = UserDAO.getInstance();
    private final IRoleDAO roleDAO = RoleDAO.getInstance();

    private final IProductDAO productDAO = ProductDAO.getInstance();

    public void init() {
        if (this.getServletContext().getAttribute("listUser") == null) {
            this.getServletContext().setAttribute("listUser", userDAO.selectAllUser());
        } else {
            updateListUser();
        }

        if (this.getServletContext().getAttribute("listRole") == null) {
            this.getServletContext().setAttribute("listRole", roleDAO.selectAllRole());
        }

        if (this.getServletContext().getAttribute("listProduct") == null) {
            this.getServletContext().setAttribute("listProduct", productDAO.selectAllProduct());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
//                showEditForm(request, response);
                break;
            case "delete":
//                deleteUser(request, response);
                break;
            default:
                listUser(request, response);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "edit":
//                editUser(request, response);
                break;
            case "search":
                searchUser(request, response);
                break;
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User newUser = new User();
        RequestDispatcher requestDispatcher;
        List<String> errors = new ArrayList<>();
        String id = "US" + System.currentTimeMillis() / 1000;
        LocalDateTime createdTime = LocalDateTime.now();
        LocalDate birthDay = null;
        int role = -1;
        String fullName = request.getParameter("fullName");
        if (fullName.trim().equals("")) errors.add("Họ tên không được để trống");
        newUser.setFullName(fullName);
        try {
            birthDay = AppUtils.stringToLocalDate(request.getParameter("birthDay"));
            newUser.setBirthDay(birthDay);
        } catch (Exception e) {
            errors.add("Định dạng ngày sinh không hợp lệ");
        }

        String phoneNumber = request.getParameter("phoneNumber");
        if (!ValidateUtils.isPhoneValid(phoneNumber))
            errors.add("Số điện thoại không hợp lệ (Số điện thoại bao gồm 10 chữ số)");
        if (userDAO.selectUserByPhoneNumber(phoneNumber) != null)
            errors.add("Số điện thoại đã tồn tại trong hệ thống)");
        newUser.setPhoneNumber(phoneNumber);

        String email = request.getParameter("email");
        if (!ValidateUtils.isEmailValid(email))
            errors.add("Email không hợp lệ (Số điện thoại bao gồm 10 chữ số)");
        if (userDAO.selectUserByEmail(email) != null)
            errors.add("Email đã tồn tại trong hệ thống)");
        newUser.setEmail(email);

        String address = request.getParameter("address");
        if (address.trim().equals("")) errors.add("Địa chỉ không được để trống");
        newUser.setAddress(address);

        try {
            role = Integer.parseInt(request.getParameter("role"));
            if (roleDAO.selectRole(role) == null)
                errors.add("Quyền hạn không tồn tại trong hệ thống)");
            newUser.setRole(role);
        } catch (NumberFormatException e) {
            errors.add("Định dạng quyền không hợp lệ");
        }

        String image = request.getParameter("image");
        if (!ValidateUtils.isImageValid(image))
            errors.add("Đường dẫn ảnh không đúng (Đường dẫn ảnh phải có đuôi là jpg/png/jpeg)");
        newUser.setImage(image);

        if (errors.isEmpty()) {
            User user = new User(id, fullName, birthDay, phoneNumber, email, address, image, role);
            user.setPassword("123456");
            user.setCreatedTime(LocalDateTime.now());
            userDAO.insertUser(user);
            request.setAttribute("message", "Thêm mới nhân viên" + " ' " + fullName + " ' " + "thành công (tài khoản:" + " ' " + email + " ' " + "và mật khẩu mặc định: '123456')");
            updateListUser();
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/create.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("user", newUser);
            request.setAttribute("errors", errors);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/create.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchStr = request.getParameter("inputSearch");
        List<User> userListSearch = userDAO.searchUser(searchStr);
        request.setAttribute("userListSearch", userListSearch);
        request.setAttribute("searchStr", searchStr);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/search.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateListUser() {
        this.getServletContext().removeAttribute("listUser");
        this.getServletContext().setAttribute("listUser", userDAO.selectAllUser());
    }
}
