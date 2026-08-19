package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.LopHoc;
import java.util.*;
import java.util.stream.Collectors;

public class LopHocRepository {
    private static final List<LopHoc> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new LopHoc(1, "DCCNTT15.10.1", "CNTT K15 - Lớp 1", "ThS. Lê Văn C", 45));
        data.add(new LopHoc(2, "DCCNTT15.10.2", "CNTT K15 - Lớp 2", "ThS. Phạm Thị D", 42));
    }

    public List<LopHoc> findAll() { return data; }

    public LopHoc findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public void add(LopHoc lh) { lh.setId(autoId++); data.add(lh); }

    public void update(LopHoc lh) {
        LopHoc old = findById(lh.getId());
        if (old != null) {
            old.setMaLop(lh.getMaLop());
            old.setTenLop(lh.getTenLop());
            old.setCoVanHocTap(lh.getCoVanHocTap());
            old.setSoLuongSinhVien(lh.getSoLuongSinhVien());
        }
    }

    public void delete(int id) { data.removeIf(x -> x.getId() == id); }

    public List<LopHoc> search(String key) {
        if (key == null || key.trim().isEmpty()) return data;
        String k = key.toLowerCase();
        return data.stream().filter(x -> x.getMaLop().toLowerCase().contains(k) ||
                x.getTenLop().toLowerCase().contains(k)).collect(Collectors.toList());
    }
}
