package vn.edu.eaut.lab7.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.SanPham;
import vn.edu.eaut.lab7.repository.SanPhamRepository;

import java.io.IOException;
import java.util.Map;

@WebServlet("/san-pham")
public class SanPhamController extends HttpServlet {
    private final SanPhamRepository repo = new SanPhamRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("/views/sanpham/form.jsp").forward(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            req.setAttribute("sp", repo.findById(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/views/sanpham/form.jsp").forward(req, resp);
            return;
        }
        if ("delete".equals(action)) {
            repo.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/san-pham");
            return;
        }

        req.setAttribute("dsSanPham", repo.search(req.getParameter("keyword")));
        req.getRequestDispatcher("/views/sanpham/list.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        double gia = 0;
        int soLuong = 0;
        try { gia = Double.parseDouble(req.getParameter("gia")); } catch (Exception ignored) {}
        try { soLuong = Integer.parseInt(req.getParameter("soLuong")); } catch (Exception ignored) {}

        SanPham sp = new SanPham(id == null || id.isBlank() ? 0 : Integer.parseInt(id),
                req.getParameter("maSanPham"), req.getParameter("tenSanPham"),
                req.getParameter("moTa"), gia, soLuong);

        // Validate nghiệp vụ: giá > 0, số lượng >= 0
        Map<String, String> errors = repo.validate(sp);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("sp", sp);
            req.getRequestDispatcher("/views/sanpham/form.jsp").forward(req, resp);
            return;
        }

        if (sp.getId() == 0) repo.add(sp); else repo.update(sp);
        resp.sendRedirect(req.getContextPath() + "/san-pham");
    }
}
