# Hướng dẫn chạy project bằng NetBeans

## 1. Yêu cầu
- JDK 17 hoặc 21
- NetBeans (bản có hỗ trợ Maven, hầu hết bản mới đều có sẵn)
- Apache Tomcat 10.x đã đăng ký trong NetBeans (Tools → Servers → Add Server)
  > Bắt buộc dùng Tomcat 10.x trở lên vì project dùng Jakarta EE 10 (namespace `jakarta.*`). Tomcat 9 dùng `javax.*` sẽ không chạy được.

## 2. Mở project
1. Mở NetBeans → **File → Open Project**.
2. Chọn thư mục `lab08-jsf-validation` (thư mục chứa file `pom.xml`).
3. NetBeans tự nhận diện đây là Maven Web Project (biểu tượng có chữ "M").
4. Click phải vào project → **Properties → Run** → chọn Server là Tomcat 10.x vừa cấu hình.

## 3. Build và chạy
1. Click phải vào project → **Clean and Build** (Maven sẽ tự tải các dependency: `jakarta.faces`, `weld-servlet-shaded`, `hibernate-validator`).
2. Click phải vào project → **Run** (hoặc Shift+F6).
3. NetBeans sẽ deploy file `.war` lên Tomcat và mở trình duyệt tại:
   ```
   http://localhost:8080/lab08-jsf-validation/index.xhtml
   ```

## 4. Các trang có thể kiểm thử
| Trang | Đường dẫn |
|---|---|
| Trang chủ | `/index.xhtml` |
| Thêm/sửa sinh viên | `/sinhvien-form.xhtml` |
| Danh sách + tìm kiếm sinh viên | `/sinhvien-list.xhtml` |
| Thêm/sửa sách | `/sach-form.xhtml` |
| Danh sách sách | `/sach-list.xhtml` |
| Thêm/sửa sản phẩm | `/product-form.xhtml` |
| Danh sách sản phẩm | `/product-list.xhtml` |
| Đăng nhập (tài khoản mẫu: `admin` / `123456`) | `/login.xhtml` |

## 5. Lỗi thường gặp
- **404 khi mở trang**: kiểm tra `Context Path` của project (mặc định là tên thư mục `lab08-jsf-validation`) và đảm bảo Tomcat đã start.
- **Không nhận `jakarta.faces.webapp.FacesServlet`**: đảm bảo Maven đã tải đủ dependency (chạy `mvn clean install` một lần trong Terminal nếu NetBeans báo thiếu thư viện).
- **Lỗi CDI/Weld khi deploy trên GlassFish/Payara**: các server này đã có sẵn CDI + JSF, có thể xóa 2 dependency `jakarta.faces` và `weld-servlet-shaded` trong `pom.xml` để tránh xung đột phiên bản.
