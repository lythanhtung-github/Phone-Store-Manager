package controller;

import dao.*;
import dao.impl.CustomerDAO;
import dao.impl.ProductDAO;
import dao.impl.RoleDAO;
import dao.impl.UserDAO;
import model.User;
import utils.AppUtils;
import utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private final ICustomerDAO customerDAO = CustomerDAO.getInstance();

    @Override
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

        if (this.getServletContext().getAttribute("listCustomer") == null) {
            this.getServletContext().setAttribute("listCustomer", customerDAO.selectAllCustomer());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("/login?type=user");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "view":
                showViewForm(request, response);
                break;
            case "reset":
                resetPass(request, response);
                break;
            case "changepass":
                showChangePassForm(request, response);
                break;
            default:
                listUser(request, response);
                break;
        }
    }

    private void showChangePassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        RequestDispatcher requestDispatcher;
        if (userDAO.selectUser(account.getId()) == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/changepass.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void showViewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        User user = userDAO.selectUser(id);
        if (user == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("user", user);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/view.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void resetPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        User user = userDAO.selectUser(id);
        if (user == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
        } else {
            user.setPassword("123456");
            user.setUpdatedTime(LocalDateTime.now());
            userDAO.updateUser(user);
            request.setAttribute("message", "Đặt lại mật khẩu thành công");
            request.setAttribute("user", user);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/edit.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        User user = userDAO.selectUser(id);
        if (user == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("user", user);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/edit.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String id = request.getParameter("id");
        User user = userDAO.selectUser(id);
        if (user == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        }
        userDAO.deleteUser(id);
        response.sendRedirect("/user");
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        String q = "";
        int roleId = -1;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }
        if (request.getParameter("roleId") != null) {
            roleId = Integer.parseInt(request.getParameter("roleId"));
        }
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<User> userList = userDAO.selectAllUsersPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, roleId);
        int noOfRecords = userDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listUser", userList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("q", q);
        request.setAttribute("roleId", roleId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "search":
                searchUser(request, response);
                break;
            case "changepass":
                changePassword(request, response);
                break;
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        RequestDispatcher requestDispatcher;
        String password = request.getParameter("password");
        String passwordReEnter = request.getParameter("passwordReEnter");

        if (password.trim().equals("")) {
            errors.add("Mật khẩu không được để trống");
        }
        if (!ValidateUtils.isPasswordValid(password)) {
            errors.add("Mật khẩu có độ dài 8-20 ký tự, chứa ít nhất 1 chữ cái viết hoa, viết thường, chữ số, ký t đặc biệt");
        }

        if (passwordReEnter.trim().equals("")) {
            errors.add("Mật khẩu không được để trống");
        }
        if (!ValidateUtils.isPasswordValid(passwordReEnter)) {
            errors.add("Mật khẩu có độ dài 8-20 ký tự, chứa ít nhất 1 chữ cái viết hoa, viết thường, chữ số, ký t đặc biệt");
        }

        if (!password.equals(passwordReEnter)) {
            errors.add("Mật khẩu không trùng nhau");
        }

        if (errors.isEmpty()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            user.setPassword(password);
            userDAO.updateUser(user);
            request.setAttribute("message", "Đổi mật khẩu thành công");
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("password",password);
            request.setAttribute("passwordReEnter",passwordReEnter);
        }
        requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/changepass.jsp");
        requestDispatcher.forward(request, response);

    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        List<String> errors = new ArrayList<>();
        String id, fullName, phoneNumber, email, address, image;
        LocalDate birthDay;
        int role;

        id = request.getParameter("id");
        User oldUser = userDAO.selectUser(id);
        User newUser = new User();
        if (oldUser == null) {
            errors.add("Nhân viên không tồn tại");
        } else {
            newUser.setId(id);
            fullName = request.getParameter("fullName");
            if (fullName.trim().equals("")) errors.add("Họ tên không được để trống");
            newUser.setFullName(fullName);

            try {
                birthDay = AppUtils.stringToLocalDate(request.getParameter("birthDay"));
                newUser.setBirthDay(birthDay);
            } catch (Exception e) {
                errors.add("Định dạng ngày sinh không hợp lệ");
            }

            phoneNumber = request.getParameter("phoneNumber");
            if (!ValidateUtils.isPhoneValid(phoneNumber))
                errors.add("Số điện thoại không hợp lệ (Số điện thoại bao gồm 10 chữ số)");
            if (!oldUser.getPhoneNumber().equals(phoneNumber)) {
                if (userDAO.selectUserByPhoneNumber(phoneNumber) != null)
                    errors.add("Số điện thoại đã tồn tại trong hệ thống)");
            }
            newUser.setPhoneNumber(phoneNumber);

            email = request.getParameter("email");
            if (!ValidateUtils.isEmailValid(email))
                errors.add("Email không hợp lệ (Số điện thoại bao gồm 10 chữ số)");
            if (!oldUser.getEmail().equals(email)) {
                if (userDAO.selectUserByEmail(email) != null)
                    errors.add("Email đã tồn tại trong hệ thống)");
            }
            newUser.setEmail(email);

            address = request.getParameter("address");
            if (address.trim().equals("")) errors.add("Địa chỉ không được để trống");
            newUser.setAddress(address);

            try {
                role = Integer.parseInt(request.getParameter("role"));
                if (roleDAO.selectRole(role) == null) {
                    errors.add("Quyền hạn không tồn tại trong hệ thống)");
                } else {
                    newUser.setRole(role);
                }
            } catch (NumberFormatException e) {
                errors.add("Định dạng quyền không hợp lệ");
            }

            image = request.getParameter("image");
            if (!ValidateUtils.isImageValid(image))
                errors.add("Đường dẫn ảnh không đúng (Đường dẫn ảnh phải có đuôi là jpg/png/jpeg)");
            newUser.setImage(image);
        }

        if (errors.isEmpty()) {
            newUser.setPassword(oldUser.getPassword());
            newUser.setUpdatedTime(LocalDateTime.now());
            userDAO.updateUser(newUser);
            request.setAttribute("message", "Cập nhật nhân viên" + " ' " + oldUser.getFullName() + " ' " + "thành công");
            updateListUser();
            request.setAttribute("user", newUser);
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("user", oldUser);
        }
        requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User newUser = new User();
        RequestDispatcher requestDispatcher;
        List<String> errors = new ArrayList<>();
        String id = "US" + System.currentTimeMillis() / 1000;
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
        } else {
            request.setAttribute("user", newUser);
            request.setAttribute("errors", errors);
        }
        requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/create.jsp");
        requestDispatcher.forward(request, response);
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
