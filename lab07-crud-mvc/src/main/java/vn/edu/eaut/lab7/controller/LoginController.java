package vn.edu.eaut.lab7.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet({"/login", "/logout"})
public class LoginController extends HttpServlet {
    // Tài khoản mẫu (Bài 5)
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getServletPath().equals("/logout")) {
            HttpSession session = req.getSession(false);
            if (session != null) session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/admin/index.jsp");
        } else {
            req.setAttribute("loi", "Sai tên đăng nhập hoặc mật khẩu");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
