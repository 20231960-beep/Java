package vn.edu.eaut.lab7.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.SinhVien;
import vn.edu.eaut.lab7.repository.SinhVienRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/sinh-vien")
public class SinhVienController extends HttpServlet {
    private final SinhVienRepository repo = new SinhVienRepository();
    private static final int PAGE_SIZE = 5; // Bài 11: phân trang 5 dòng/trang

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("/views/sinhvien/form.jsp").forward(req, resp);
            return;
        }
        if ("edit".equals(action)) {
            req.setAttribute("sv", repo.findById(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/views/sinhvien/form.jsp").forward(req, resp);
            return;
        }
        if ("detail".equals(action)) {
            req.setAttribute("sv", repo.findById(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/views/sinhvien/detail.jsp").forward(req, resp);
            return;
        }
        if ("delete".equals(action)) {
            repo.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/sinh-vien");
            return;
        }

        // Danh sách + tìm kiếm + phân trang
        String keyword = req.getParameter("keyword");
        List<SinhVien> ketQua = repo.search(keyword);

        int trang = 1;
        try { trang = Integer.parseInt(req.getParameter("trang")); } catch (Exception ignored) {}
        if (trang < 1) trang = 1;

        int tongSoDong = ketQua.size();
        int tongSoTrang = Math.max(1, (int) Math.ceil(tongSoDong / (double) PAGE_SIZE));
        if (trang > tongSoTrang) trang = tongSoTrang;

        int start = (trang - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, tongSoDong);
        List<SinhVien> trangHienTai = start < end ? ketQua.subList(start, end) : List.of();

        req.setAttribute("dsSinhVien", trangHienTai);
        req.setAttribute("keyword", keyword);
        req.setAttribute("trangHienTai", trang);
        req.setAttribute("tongSoTrang", tongSoTrang);
        req.getRequestDispatcher("/views/sinhvien/list.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        SinhVien sv = new SinhVien(id == null || id.isBlank() ? 0 : Integer.parseInt(id), req.getParameter("maSinhVien"),
                req.getParameter("hoTen"), req.getParameter("email"), req.getParameter("lop"));
        if (sv.getId() == 0) repo.add(sv); else repo.update(sv);
        resp.sendRedirect(req.getContextPath() + "/sinh-vien");
    }
}
