package vn.edu.eaut.lab7.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.GioHangItem;
import vn.edu.eaut.lab7.model.SanPham;
import vn.edu.eaut.lab7.repository.SanPhamRepository;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/gio-hang")
public class GioHangController extends HttpServlet {
    private final SanPhamRepository sanPhamRepo = new SanPhamRepository();

    @SuppressWarnings("unchecked")
    private Map<Integer, GioHangItem> layGioHang(HttpSession session) {
        Map<Integer, GioHangItem> gioHang = (Map<Integer, GioHangItem>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new LinkedHashMap<>();
            session.setAttribute("gioHang", gioHang);
        }
        return gioHang;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(); // tạo session nếu chưa có
        Map<Integer, GioHangItem> gioHang = layGioHang(session);
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            int spId = Integer.parseInt(req.getParameter("id"));
            int soLuong = 1;
            try { soLuong = Integer.parseInt(req.getParameter("soLuong")); } catch (Exception ignored) {}
            SanPham sp = sanPhamRepo.findById(spId);
            if (sp != null) {
                if (gioHang.containsKey(spId)) {
                    gioHang.get(spId).setSoLuong(gioHang.get(spId).getSoLuong() + soLuong);
                } else {
                    gioHang.put(spId, new GioHangItem(sp.getId(), sp.getTenSanPham(), sp.getGia(), soLuong));
                }
            }
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;
        }

        if ("update".equals(action)) {
            int spId = Integer.parseInt(req.getParameter("id"));
            int soLuong = Integer.parseInt(req.getParameter("soLuong"));
            if (gioHang.containsKey(spId)) {
                if (soLuong <= 0) gioHang.remove(spId);
                else gioHang.get(spId).setSoLuong(soLuong);
            }
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;
        }

        if ("remove".equals(action)) {
            int spId = Integer.parseInt(req.getParameter("id"));
            gioHang.remove(spId);
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;
        }

        if ("clear".equals(action)) {
            gioHang.clear();
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;
        }

        double tongTien = gioHang.values().stream().mapToDouble(GioHangItem::getThanhTien).sum();
        req.setAttribute("gioHang", gioHang.values());
        req.setAttribute("tongTien", tongTien);
        req.setAttribute("dsSanPham", sanPhamRepo.findAll());
        req.getRequestDispatcher("/views/giohang/list.jsp").forward(req, resp);
    }
}
