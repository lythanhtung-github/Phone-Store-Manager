package controller;

import dao.*;
import dao.impl.CustomerDAO;
import dao.impl.OrderDAO;
import dao.impl.ProductDAO;
import dao.impl.UserDAO;
import model.Customer;
import utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    private final IUserDAO userDAO = UserDAO.getInstance();
    private final IProductDAO productDAO = ProductDAO.getInstance();
    private final ICustomerDAO customerDAO = CustomerDAO.getInstance();
    private final IOrderDAO orderDAO = OrderDAO.getInstance();

    @Override
    public void init() {
        if (this.getServletContext().getAttribute("listUser") == null) {
            this.getServletContext().setAttribute("listUser", userDAO.selectAllUser());
        }

        if (this.getServletContext().getAttribute("listProduct") == null) {
            this.getServletContext().setAttribute("listProduct", productDAO.selectAllProduct());
        }


        if (this.getServletContext().getAttribute("listOrder") == null) {
            this.getServletContext().setAttribute("listOrder", orderDAO.selectAllOrder());
        }

        if (this.getServletContext().getAttribute("listCustomer") == null) {
            this.getServletContext().setAttribute("listCustomer", customerDAO.selectAllCustomer());
        } else {
            updateListCustomer();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
//        HttpSession session = request.getSession();
//        if(session.getAttribute("account")==null){
//            response.sendRedirect("/login?type=user");
//            return;
//        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                case "view":
                    showViewForm(request, response);
                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void updateListCustomer() {
        this.getServletContext().removeAttribute("listCustomer");
        this.getServletContext().setAttribute("listCustomer", customerDAO.selectAllCustomer());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if (action.equals("edit"))
            editCustomer(request, response);
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        List<String> errors = new ArrayList<>();
        String id, fullName, phoneNumber, email, address, image;

        id = request.getParameter("id");
        Customer oldCustomer = customerDAO.selectCustomer(id);
        Customer newCustomer = new Customer();
        if (oldCustomer == null) {
            errors.add("Nh??n vi??n kh??ng t???n t???i");
        } else {
            newCustomer.setId(id);
            fullName = request.getParameter("fullName");
            if (fullName.trim().equals("")) errors.add("H??? t??n kh??ng ???????c ????? tr???ng");
            newCustomer.setFullName(fullName);

            phoneNumber = request.getParameter("phoneNumber");
            if (!ValidateUtils.isPhoneValid(phoneNumber))
                errors.add("S??? ??i???n tho???i kh??ng h???p l??? (S??? ??i???n tho???i bao g???m 10 ch??? s???)");
            if (!oldCustomer.getPhoneNumber().equals(phoneNumber)) {
                if (customerDAO.selectCustomerByPhoneNumber(phoneNumber) != null)
                    errors.add("S??? ??i???n tho???i ???? t???n t???i trong h??? th???ng)");
            }
            newCustomer.setPhoneNumber(phoneNumber);

            email = request.getParameter("email");
            if (!ValidateUtils.isEmailValid(email))
                errors.add("Email kh??ng h???p l??? (S??? ??i???n tho???i bao g???m 10 ch??? s???)");
            if (!oldCustomer.getEmail().equals(email)) {
                if (customerDAO.selectCustomer(email) != null)
                    errors.add("Email ???? t???n t???i trong h??? th???ng)");
            }
            newCustomer.setEmail(email);

            address = request.getParameter("address");
            if (address.trim().equals("")) errors.add("?????a ch??? kh??ng ???????c ????? tr???ng");
            newCustomer.setAddress(address);

            image = request.getParameter("image");
            if (!ValidateUtils.isImageValid(image))
                errors.add("???????ng d???n ???nh kh??ng ????ng (???????ng d???n ???nh ph???i c?? ??u??i l?? jpg/png/jpeg)");
            newCustomer.setImage(image);
        }

        if (errors.isEmpty()) {
            newCustomer.setPassword(oldCustomer.getPassword());
            newCustomer.setUpdatedTime(LocalDateTime.now());
            customerDAO.updateCustomer(newCustomer);
            request.setAttribute("message", "C???p nh???t nh??n vi??n" + " ' " + oldCustomer.getFullName() + " ' " + "th??nh c??ng");
            updateListCustomer();
            request.setAttribute("customer", newCustomer);
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("customer", newCustomer);
        }
        requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/customer/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String id = request.getParameter("id");
        Customer customer = customerDAO.selectCustomer(id);
        if (customer == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        }
        customerDAO.deleteCustomer(id);
        response.sendRedirect("/customer");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        Customer customer = customerDAO.selectCustomer(id);
        if (customer == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("customer", customer);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/customer/edit.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void showViewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RequestDispatcher requestDispatcher;
        Customer customer = customerDAO.selectCustomer(id);
        if (customer == null) {
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/404.jsp");
        } else {
            request.setAttribute("customer", customer);
            requestDispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/customer/view.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        String q = "";
        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if (request.getParameter("q") != null) {
            q = request.getParameter("q");
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Customer> customerList = customerDAO.selectAllCustomersPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q);
        int noOfRecords = customerDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listCustomer", customerList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("q", q);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/dashboard/customer/list.jsp");
        dispatcher.forward(request, response);
    }
}
