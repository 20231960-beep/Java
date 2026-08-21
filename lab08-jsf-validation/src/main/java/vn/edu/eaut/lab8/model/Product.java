package vn.edu.eaut.lab8.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public class Product implements Serializable {

    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String tenSanPham;

    @Positive(message = "Giá phải lớn hơn 0")
    private double gia;

    @PositiveOrZero(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private int soLuong;

    public Product() {}

    public Product(int id, String tenSanPham, double gia, int soLuong) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
