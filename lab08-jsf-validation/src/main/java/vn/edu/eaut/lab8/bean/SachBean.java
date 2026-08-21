package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Sach;
import vn.edu.eaut.lab8.repository.SachRepository;

import java.io.Serializable;
import java.util.List;

@Named("sachBean")
@SessionScoped
public class SachBean implements Serializable {

    private Sach sach = new Sach();
    private final SachRepository repo = new SachRepository();
    private boolean editMode = false;

    public String save() {
        if (editMode) {
            repo.update(sach);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã cập nhật sách"));
            editMode = false;
        } else {
            repo.add(sach);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã thêm sách"));
        }
        sach = new Sach();
        return "sach-list?faces-redirect=true";
    }

    public String edit(int id) {
        Sach s = repo.findById(id);
        if (s != null) {
            this.sach = new Sach(s.getId(), s.getTenSach(), s.getTacGia(), s.getNamXuatBan());
            this.editMode = true;
        }
        return "sach-form?faces-redirect=true";
    }

    public void delete(int id) {
        repo.delete(id);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã xóa sách"));
    }

    public List<Sach> getDsSach() {
        return repo.findAll();
    }

    public Sach getSach() { return sach; }
    public void setSach(Sach sach) { this.sach = sach; }

    public boolean isEditMode() { return editMode; }
    public void setEditMode(boolean editMode) { this.editMode = editMode; }
}
