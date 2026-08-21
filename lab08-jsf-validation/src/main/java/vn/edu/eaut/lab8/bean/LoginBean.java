package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    // Tai khoan mau, thuc te se kiem tra qua repository/database
    private static final String TAI_KHOAN_DUNG = "admin";
    private static final String MAT_KHAU_DUNG = "123456";

    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    public String login() {
        if (TAI_KHOAN_DUNG.equals(username) && MAT_KHAU_DUNG.equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đăng nhập thành công"));
            return "index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lỗi", "Sai tài khoản hoặc mật khẩu"));
            return null;
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
