package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.SanPham;
import java.util.*;
import java.util.stream.Collectors;

public class SanPhamRepository {
    private static final List<SanPham> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new SanPham(1, "SP001", "Bàn phím cơ", "Bàn phím cơ switch đỏ", 590000, 20));
        data.add(new SanPham(2, "SP002", "Chuột không dây", "Chuột quang không dây", 250000, 35));
    }

    public List<SanPham> findAll() { return data; }

    public SanPham findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public void add(SanPham sp) { sp.setId(autoId++); data.add(sp); }

    public void update(SanPham sp) {
        SanPham old = findById(sp.getId());
        if (old != null) {
            old.setMaSanPham(sp.getMaSanPham());
            old.setTenSanPham(sp.getTenSanPham());
            old.setMoTa(sp.getMoTa());
            old.setGia(sp.getGia());
            old.setSoLuong(sp.getSoLuong());
        }
    }

    public void delete(int id) { data.removeIf(x -> x.getId() == id); }

    public List<SanPham> search(String key) {
        if (key == null || key.trim().isEmpty()) return data;
        String k = key.toLowerCase();
        return data.stream().filter(x -> x.getTenSanPham().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }

    /** Validate nghiệp vụ: giá > 0 và số lượng >= 0. Trả về map lỗi (rỗng nếu hợp lệ). */
    public Map<String, String> validate(SanPham sp) {
        Map<String, String> errors = new LinkedHashMap<>();
        if (sp.getMaSanPham() == null || sp.getMaSanPham().trim().isEmpty()) {
            errors.put("maSanPham", "Mã sản phẩm không được để trống");
        }
        if (sp.getTenSanPham() == null || sp.getTenSanPham().trim().isEmpty()) {
            errors.put("tenSanPham", "Tên sản phẩm không được để trống");
        }
        if (sp.getGia() <= 0) {
            errors.put("gia", "Giá phải lớn hơn 0");
        }
        if (sp.getSoLuong() < 0) {
            errors.put("soLuong", "Số lượng phải lớn hơn hoặc bằng 0");
        }
        return errors;
    }
}
