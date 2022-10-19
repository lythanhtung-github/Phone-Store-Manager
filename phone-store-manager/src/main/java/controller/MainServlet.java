package controller;

import dao.*;
import dao.dto.IOrderDayDTODAO;
import dao.dto.IProductDTODAO;
import dao.dto.IUserDTODAO;
import dao.dto.impl.CustomerDTODAO;
import dao.dto.ICustomerDTODAO;
import dao.dto.impl.OrderDayDTODAO;
import dao.dto.impl.ProductDTODAO;
import dao.dto.impl.UserDTODAO;
import dao.impl.CustomerDAO;
import dao.impl.OrderDAO;
import dao.impl.ProductDAO;
import dao.impl.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/home")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProductDAO productDAO = ProductDAO.getInstance();
    private final IUserDAO userDAO = UserDAO.getInstance();
    private final ICustomerDAO customerDAO = CustomerDAO.getInstance();
    private final IOrderDAO orderDAO = OrderDAO.getInstance();

    private final IUserDTODAO userDTODAO = UserDTODAO.getInstance();

    private final ICustomerDTODAO customerDTODAO = CustomerDTODAO.getInstance();

    private final IProductDTODAO productDTODAO = ProductDTODAO.getInstance();

    private final IOrderDayDTODAO orderDayDTODAO = OrderDayDTODAO.getInstance();
    public void init() {
        updateListProductInApplication();
        updateListUserInApplication();
        updateListCustomerInApplication();
        updateListOrderInApplication();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("account")==null){
            response.sendRedirect("/login?type=user");
        }
        else{
            request.setAttribute("listOrderDay",orderDayDTODAO.selectOrderDayRank());
            request.setAttribute("listProductRank",productDTODAO.selectProductRank());
            request.setAttribute("listUserRank",userDTODAO.selectUserRank());
            request.setAttribute("listCustomerRank",customerDTODAO.selectCustomerRank());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
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

    private void updateListCustomerInApplication(){
        if (this.getServletContext().getAttribute("listCustomer") == null) {
            this.getServletContext().setAttribute("listCustomer", customerDAO.selectAllCustomer());
        }
        else{
            this.getServletContext().removeAttribute("listCustomer");
            this.getServletContext().setAttribute("listCustomer",  customerDAO.selectAllCustomer());
        }
    }

    private void updateListOrderInApplication(){
        if (this.getServletContext().getAttribute("listOrder") == null) {
            this.getServletContext().setAttribute("listOrder", orderDAO.selectAllOrder());
        }
        else{
            this.getServletContext().removeAttribute("listOrder");
            this.getServletContext().setAttribute("listOrder", orderDAO.selectAllOrder());
        }
    }
}
