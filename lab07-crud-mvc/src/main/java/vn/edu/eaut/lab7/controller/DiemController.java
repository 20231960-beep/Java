package vn.edu.eaut.lab7.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.DiemSinhVien;
import vn.edu.eaut.lab7.repository.DiemRepository;

import java.io.IOException;

@WebServlet("/diem")
public class DiemController extends HttpServlet {
    private final DiemRepository repo = new DiemRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("/views/diem/form.jsp").forward(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            req.setAttribute("diem", repo.findById(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/views/diem/form.jsp").forward(req, resp);
            return;
        }
        if ("delete".equals(action)) {
            repo.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/diem");
            return;
        }

        req.setAttribute("dsDiem", repo.findAll());
        req.getRequestDispatcher("/views/diem/list.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        double cc = 0, gk = 0, ck = 0;
        try { cc = Double.parseDouble(req.getParameter("diemChuyenCan")); } catch (Exception ignored) {}
        try { gk = Double.parseDouble(req.getParameter("diemGiuaKy")); } catch (Exception ignored) {}
        try { ck = Double.parseDouble(req.getParameter("diemCuoiKy")); } catch (Exception ignored) {}

        DiemSinhVien d = new DiemSinhVien(id == null || id.isBlank() ? 0 : Integer.parseInt(id),
                req.getParameter("maSinhVien"), req.getParameter("hoTen"), cc, gk, ck);

        if (d.getId() == 0) repo.add(d); else repo.update(d);
        resp.sendRedirect(req.getContextPath() + "/diem");
    }
}
