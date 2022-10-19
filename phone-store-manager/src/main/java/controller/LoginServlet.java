package controller;

import dao.IUserDAO;
import dao.impl.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserDAO userDAO = UserDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) {
            type = "";
        }
        switch (type) {
            case "user":
                showUserLoginForm(request, response);
                break;
            case "customer":
//                showCustomerLoginForm(request,response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                break;
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        response.sendRedirect("/login?type=user");
    }

    private void showUserLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) {
            type = "";
        }
        switch (type) {
            case "user":
                loginUser(request, response);
                break;
            case "customer":
//                loginCustomer(request, response);
                break;
            default:
                break;
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RequestDispatcher requestDispatcher;
        User user = userDAO.selectUserByEmailAndPassword(email, password);
        if (user == null) {
            request.setAttribute("error", "Sai email hoặc mật khẩu");
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/user/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Cookie emailAcc = new Cookie("email", email);
            Cookie passwordAcc = new Cookie("password", password);
            emailAcc.setMaxAge(60 * 60 * 24);
            passwordAcc.setMaxAge(60 * 60 * 24);
            response.addCookie(passwordAcc);
            response.addCookie(emailAcc);
            HttpSession session = request.getSession();
            session.setAttribute("account", user);
            response.sendRedirect("/home");
        }
    }

}
