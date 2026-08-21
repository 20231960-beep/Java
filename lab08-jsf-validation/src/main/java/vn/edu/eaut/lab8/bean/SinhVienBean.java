package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.SinhVien;
import vn.edu.eaut.lab8.repository.SinhVienRepository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named("sinhVienBean")
@SessionScoped
public class SinhVienBean implements Serializable {

    private SinhVien sinhVien = new SinhVien();
    private final SinhVienRepository repo = new SinhVienRepository();

    // Bai 10 - tim kiem
    private String keyword;

    // Bai 9 - sua: co dang o che do sua hay khong
    private boolean editMode = false;

    // Bai 3 + Bai 4: luu sinh vien (them moi hoac cap nhat)
    public String save() {
        if (editMode) {
            repo.update(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã cập nhật sinh viên"));
            editMode = false;
        } else {
            repo.add(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã lưu sinh viên"));
        }
        sinhVien = new SinhVien();
        return "sinhvien-list?faces-redirect=true";
    }

    // Bai 9 - dua du lieu len form de sua
    public String edit(int id) {
        SinhVien sv = repo.findById(id);
        if (sv != null) {
            // tao ban sao de tranh sua truc tiep vao du lieu trong repo truoc khi bam Luu
            this.sinhVien = new SinhVien(sv.getId(), sv.getMaSinhVien(), sv.getHoTen(), sv.getEmail(), sv.getLop());
            this.editMode = true;
        }
        return "sinhvien-form?faces-redirect=true";
    }

    public String huySua() {
        this.sinhVien = new SinhVien();
        this.editMode = false;
        return "sinhvien-list?faces-redirect=true";
    }

    public void delete(int id) {
        repo.delete(id);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã xóa sinh viên"));
    }

    // Bai 10 - loc danh sach theo tu khoa (ho ten hoac lop)
    public List<SinhVien> getDsSinhVien() {
        List<SinhVien> all = repo.findAll();
        if (keyword == null || keyword.trim().isEmpty()) {
            return all;
        }
        String kw = keyword.trim().toLowerCase();
        return all.stream()
                .filter(sv -> sv.getHoTen().toLowerCase().contains(kw)
                        || sv.getLop().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    // Bai 12 - danh sach lop de hien thi trong h:selectOneMenu
    public List<String> getDanhSachLop() {
        return Arrays.asList("DCCNTT15.10.1", "DCCNTT15.10.2", "DCCNTT15.10.3", "DCCNTT15.10.4");
    }

    public SinhVien getSinhVien() { return sinhVien; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public boolean isEditMode() { return editMode; }
    public void setEditMode(boolean editMode) { this.editMode = editMode; }
}
