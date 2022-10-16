//package controller;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
//public class CustomerServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("utf-8");
//        request.setAttribute("customerMenu","/assets/layout/customer-menu.jsp");
//        request.setAttribute("customerTitle","/assets/layout/customer-title.jsp");
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        try {
//            switch (action) {
//                case "create":
////                    showNewForm(request, response);
//                    break;
//                case "edit":
////                    showEditForm(request, response);
//                    break;
//                case "delete":
////                    deleteUser(request, response);
//                    break;
//                default:
//                    listCustomer(request, response);
//                    break;
//            }
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
//        dispatcher.forward(request, response);
//    }
//}
