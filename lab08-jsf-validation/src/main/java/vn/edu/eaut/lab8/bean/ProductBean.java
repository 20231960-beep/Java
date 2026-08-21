package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Product;
import vn.edu.eaut.lab8.repository.ProductRepository;

import java.io.Serializable;
import java.util.List;

@Named("productBean")
@SessionScoped
public class ProductBean implements Serializable {

    private Product product = new Product();
    private final ProductRepository repo = new ProductRepository();
    private boolean editMode = false;

    public String save() {
        if (editMode) {
            repo.update(product);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã cập nhật sản phẩm"));
            editMode = false;
        } else {
            repo.add(product);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã thêm sản phẩm"));
        }
        product = new Product();
        return "product-list?faces-redirect=true";
    }

    public String edit(int id) {
        Product p = repo.findById(id);
        if (p != null) {
            this.product = new Product(p.getId(), p.getTenSanPham(), p.getGia(), p.getSoLuong());
            this.editMode = true;
        }
        return "product-form?faces-redirect=true";
    }

    public void delete(int id) {
        repo.delete(id);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã xóa sản phẩm"));
    }

    public List<Product> getDsProduct() {
        return repo.findAll();
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public boolean isEditMode() { return editMode; }
    public void setEditMode(boolean editMode) { this.editMode = editMode; }
}
