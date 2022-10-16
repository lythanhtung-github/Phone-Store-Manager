package controller;

import dao.IProductDAO;
import dao.IUserDAO;
import dao.ProductDAO;
import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MainServlet", urlPatterns = "/home")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProductDAO productDAO = ProductDAO.getInstance();
    private final IUserDAO userDAO = UserDAO.getInstance();
    public void init() {
        updateListProductInApplication();
        updateListUserInApplication();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateListProductInApplication(){
        if (this.getServletContext().getAttribute("listProduct") == null) {
            this.getServletContext().setAttribute("listProduct", productDAO.selectAllProduct());
        }
        else{
            this.getServletContext().removeAttribute("listProduct");
            this.getServletContext().setAttribute("listProduct", productDAO.selectAllProduct());
        }
    }

    private void updateListUserInApplication(){
        if (this.getServletContext().getAttribute("listUser") == null) {
            this.getServletContext().setAttribute("listUser", userDAO.selectAllUser());
        }
        else{
            this.getServletContext().removeAttribute("listUser");
            this.getServletContext().setAttribute("listUser",  userDAO.selectAllUser());
        }
    }
}
