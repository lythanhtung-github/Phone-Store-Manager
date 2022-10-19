package controller;

import dao.*;
import dao.dto.IOrderDTODAO;
import dao.dto.IOrderItemDTODAO;
import dao.dto.impl.OrderDTODAO;
import dao.dto.impl.OrderItemDTODAO;
import dao.impl.*;
import model.Order;
import model.OrderItem;
import model.User;
import model.dto.OrderDTO;
import model.dto.OrderItemDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = UserDAO.getInstance();
    private final IRoleDAO roleDAO = RoleDAO.getInstance();
    private final IProductDAO productDAO = ProductDAO.getInstance();
    private final ICustomerDAO customerDAO = CustomerDAO.getInstance();
    private final IOrderDAO orderDAO = OrderDAO.getInstance();
    private final IOrderItemDAO orderItemDAO = OrderItemDAO.getInstance();
    private final IStatusDAO statusDAO = StatusDAO.getInstance();
    private final IOrderDTODAO orderDTODAO = OrderDTODAO.getInstance();

    private final IOrderItemDTODAO orderItemDTODAO = OrderItemDTODAO.getInstance();

    @Override
    public void init() {
        if (this.getServletContext().getAttribute("listUser") == null) {
            this.getServletContext().setAttribute("listUser", userDAO.selectAllUser());
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

        if (this.getServletContext().getAttribute("listOrder") == null) {
            this.getServletContext().setAttribute("listOrder", orderDAO.selectAllOrder());
        } else {
            updateListOrder();
        }

        if (this.getServletContext().getAttribute("listStatus") == null) {
            this.getServletContext().setAttribute("listStatus", statusDAO.selectAllStatus());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("account")==null){
            response.sendRedirect("/login?type=user");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                showViewForm(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;
            case "testship":
                listOrderNoShip(request, response);
                break;
            case "toship":
                changeStatusToShip(request, response);
                break;
            case "testcomplete":
                listOrderNoComplete(request, response);
                break;
            case "tocomplete":
                changeStatusToComplete(request, response);
                break;
            default:
                listOrder(request, response);
                break;
        }
    }

    private void showViewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        OrderDTO orderDTO = orderDTODAO.selectOrder(orderId);
        if (orderDTO == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        } else {
            List<OrderItemDTO> orderItemDTOList = orderItemDTODAO.selectOrderItemByOrderId(orderId);
            request.setAttribute("orderDTO", orderDTO);
            request.setAttribute("orderItemDTOList", orderItemDTOList);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/order/view.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void listOrderNoComplete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        String q = "";
        int statusId = 3;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }

        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<OrderDTO> orderDTOList = orderDTODAO.selectAllOrdersPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, statusId);
        int noOfRecords = orderDTODAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listOrderDTO", orderDTOList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("q", q);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/order/testcomplete.jsp");
        dispatcher.forward(request, response);
    }


    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String id = request.getParameter("id");
        Order order = orderDAO.selectOrder(id);
        if (order == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        }
        List<OrderItem> orderItemList = orderItemDAO.selectOrderItemByOrderId(id);
        for (OrderItem orderItem : orderItemList) {
            orderItemDAO.deleteOrderItem(orderItem.getId());
        }
        orderDAO.deleteOrder(id);
        response.sendRedirect("/order");
    }

    private void listOrderNoShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        String q = "";
        int statusId = 2;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }

        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<OrderDTO> orderDTOList = orderDTODAO.selectAllOrdersPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, statusId);
        int noOfRecords = orderDTODAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listOrderDTO", orderDTOList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("q", q);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/order/testship.jsp");
        dispatcher.forward(request, response);
    }

    private void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        String q = "";
        int statusId = -1;
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }
        if (request.getParameter("statusId") != null) {
            statusId = Integer.parseInt(request.getParameter("statusId"));
        }
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<OrderDTO> orderDTOList = orderDTODAO.selectAllOrdersPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, statusId);
        int noOfRecords = orderDTODAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listOrderDTO", orderDTOList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("q", q);
        request.setAttribute("statusId", statusId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/order/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void changeStatusToShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        String orderId = request.getParameter("id");
        Order order = orderDAO.selectOrder(orderId);
        if (order == null) {
            request.setAttribute("error", "Hóa đơn không tồn tại");
        } else {
            User user = (User) session.getAttribute("account");
            orderDAO.updateStatusAndConfirm(orderId, user.getId(), 3);
            updateListOrder();
            request.setAttribute("message", "Hóa đơn" + " ' " + orderId + " ' " + "đã được duyệt sang trạng thái 'vận chuyển'");
        }
        requestDispatcher = request.getRequestDispatcher("/order?action=testship");
        requestDispatcher.forward(request, response);
    }

    private void changeStatusToComplete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        String orderId = request.getParameter("id");
        Order order = orderDAO.selectOrder(orderId);
        if (order == null) {
            request.setAttribute("error", "Hóa đơn không tồn tại");
        } else {
            User user = (User) session.getAttribute("account");
            orderDAO.updateStatusAndConfirm(orderId, user.getId(), 4);
            updateListOrder();
            request.setAttribute("message", "Hóa đơn" + " ' " + orderId + " ' " + "đã được duyệt sang trạng thái 'hoàn thành'");
        }
        requestDispatcher = request.getRequestDispatcher("/order?action=testcomplete");
        requestDispatcher.forward(request, response);
    }

    private void updateListOrder() {
        this.getServletContext().removeAttribute("listOrder");
        this.getServletContext().setAttribute("listOrder", orderDAO.selectAllOrder());
    }
}
