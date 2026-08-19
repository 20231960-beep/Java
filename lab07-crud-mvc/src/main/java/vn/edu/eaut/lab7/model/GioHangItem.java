package vn.edu.eaut.lab7.model;

import java.io.Serializable;

public class GioHangItem implements Serializable {
    private int sanPhamId;
    private String tenSanPham;
    private double gia;
    private int soLuong;

    public GioHangItem() {}

    public GioHangItem(int sanPhamId, String tenSanPham, double gia, int soLuong) {
        this.sanPhamId = sanPhamId; this.tenSanPham = tenSanPham;
        this.gia = gia; this.soLuong = soLuong;
    }

    public double getThanhTien() { return gia * soLuong; }

    public int getSanPhamId() { return sanPhamId; }
    public void setSanPhamId(int sanPhamId) { this.sanPhamId = sanPhamId; }
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
