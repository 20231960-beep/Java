# Báo cáo Lab 8 — Chuyển form sang JSF, thêm validation và message

**Học phần:** Công nghệ Java (IT3242)
**Bài lab:** Lab 8 — Chương 3: Phát triển ứng dụng đa lớp trong Jakarta EE

## 1. Mục tiêu đạt được

Project đã hoàn thành toàn bộ 13 bài tập của Lab 8:

| Bài | Nội dung | Trạng thái |
|---|---|---|
| 1 | Trang JSF đầu tiên (`index.xhtml`) chạy qua FacesServlet | Hoàn thành |
| 2 | Model + Repository `SinhVien` (dữ liệu trong `List`) | Hoàn thành |
| 3 | Managed Bean `SinhVienBean` (`@Named` + `@SessionScoped`) | Hoàn thành |
| 4 | Form JSF cho sinh viên: `h:form`, `h:inputText`, `h:message`, `h:messages` | Hoàn thành |
| 5 | Danh sách sinh viên bằng `h:dataTable`, có nút xóa | Hoàn thành |
| 6 | Chuyển form Sách sang JSF (`Sach`, `SachBean`, `sach-form.xhtml`) | Hoàn thành |
| 7 | Chuyển form Sản phẩm sang JSF (`Product`, `ProductBean`, `product-form.xhtml`) | Hoàn thành |
| 8 | Form đăng nhập JSF (`LoginBean`, `login.xhtml`) | Hoàn thành |
| 9 | Sửa sinh viên: nút "Sửa" trên danh sách, đưa dữ liệu lên form, cập nhật | Hoàn thành |
| 10 | Tìm kiếm sinh viên theo họ tên/lớp | Hoàn thành |
| 11 | Layout dùng chung (`ui:composition` + `ui:include` cho header/menu/footer) | Hoàn thành |
| 12 | `h:selectOneMenu` cho trường Lớp trong form sinh viên | Hoàn thành |
| 13 | Báo cáo so sánh Servlet/JSP và JSF | Hoàn thành (mục 3 bên dưới) |

## 2. Cấu trúc project

```
lab08-jsf-validation/
├── pom.xml
└── src/main/
    ├── java/vn/edu/eaut/lab8/
    │   ├── bean/          SinhVienBean, SachBean, ProductBean, LoginBean
    │   ├── model/         SinhVien, Sach, Product (dùng Bean Validation)
    │   └── repository/    SinhVienRepository, SachRepository, ProductRepository (CRUD trong List)
    └── webapp/
        ├── index.xhtml
        ├── sinhvien-form.xhtml, sinhvien-list.xhtml
        ├── sach-form.xhtml, sach-list.xhtml
        ├── product-form.xhtml, product-list.xhtml
        ├── login.xhtml
        └── WEB-INF/
            ├── web.xml, beans.xml
            └── templates/ layout.xhtml, header.xhtml, footer.xhtml
```

## 3. Luồng xử lý JSF

1. Trình duyệt gửi request tới file `.xhtml` → `FacesServlet` (khai báo trong `web.xml`, mapping `*.xhtml`) tiếp nhận và điều phối vòng đời JSF.
2. Facelets dựng cây component (`h:form`, `h:inputText`...) từ trang `.xhtml`, có thể kế thừa `layout.xhtml` dùng chung qua `ui:composition`/`ui:insert`.
3. Khi submit `h:commandButton`, JSF thực hiện tuần tự: *Apply Request Values → Process Validations → Update Model Values → Invoke Application → Render Response*.
4. Ở bước *Process Validations*, JSF kiểm tra `required`/`requiredMessage` của thẻ và các ràng buộc Bean Validation (`@NotBlank`, `@Size`, `@Email`, `@Min`, `@Max`...) khai báo trực tiếp trên model.
5. Nếu có lỗi, JSF dừng lại, không gọi action method, và hiển thị lỗi qua `h:message`/`h:messages` ngay tại field tương ứng.
6. Nếu hợp lệ, JSF gán dữ liệu vào Managed Bean (`Update Model`), gọi action method (`save()`, `edit()`, `delete()`...), bean gọi Repository để lưu/xóa/cập nhật, tạo `FacesMessage` thông báo kết quả, rồi điều hướng bằng outcome kèm `faces-redirect=true`.
7. `h:dataTable` lặp qua danh sách lấy từ bean để hiển thị dữ liệu, không cần vòng lặp JSTL thủ công như JSP.

## 4. So sánh cách xử lý form: Servlet/JSP (Lab 7) và JSF (Lab 8)

| Tiêu chí | Servlet + JSP (Lab 7) | JSF (Lab 8) |
|---|---|---|
| Điều phối request | `Servlet` tự đọc `request.getParameter(...)` | `FacesServlet` + vòng đời chuẩn (6 giai đoạn) |
| Xây dựng giao diện | HTML thuần + JSTL (`<c:forEach>`, EL) | Component-based: `h:form`, `h:inputText`, `h:dataTable`... |
| Ràng buộc dữ liệu vào model | Gán thủ công từng dòng code trong Servlet | Data binding tự động qua EL (`value="#{bean.field}"`) |
| Validation | Viết `if/else` kiểm tra tay trong Servlet | Khai báo qua annotation (`@NotBlank`...) hoặc thuộc tính `required` của thẻ JSF |
| Hiển thị lỗi | Gán lỗi vào `request`, JSP tự in ra bằng EL | `h:message`/`h:messages` tự động bám theo từng field |
| Thông báo kết quả | Set biến `message` vào `request`/`session` | Cơ chế chuẩn `FacesMessage` (`FacesContext.addMessage`) |
| Điều hướng | `RequestDispatcher.forward()` / `response.sendRedirect()` | Trả về outcome String, có thể kèm `faces-redirect=true` |
| Tái sử dụng giao diện | Include JSP thủ công (`<jsp:include>`) | Template Facelets (`ui:composition`, `ui:insert`, `ui:include`) |
| Mức độ tách mã (Controller/View) | View và một phần logic dễ lẫn vào nhau trong JSP | Tách rõ: View (.xhtml) — Managed Bean (controller) — Model — Repository |
| Đường cong học tập | Đơn giản, dễ hiểu luồng HTTP thô | Cần hiểu vòng đời JSF, EL, scope của bean |

**Nhận xét:** JSF giảm đáng kể lượng code lặp lại khi xử lý form (đọc tham số, validate tay, in lỗi ra HTML) nhờ cơ chế component-based và data binding hai chiều. Đổi lại, JSF có vòng đời phức tạp hơn Servlet/JSP thuần, nên cần nắm rõ 6 giai đoạn xử lý để debug hiệu quả.

## 5. Trả lời câu hỏi củng cố (mục 11 đề bài)

**1. JSF xử lý form khác Servlet/JSP ở điểm nào?**
JSF dùng kiến trúc component-based với vòng đời 6 giai đoạn chuẩn hóa (Restore View → Apply Request Values → Process Validations → Update Model Values → Invoke Application → Render Response), thay vì Servlet đọc tham số và JSP hiển thị thủ công.

**2. `h:inputText`, `h:commandButton` và `h:messages` có vai trò gì?**
`h:inputText` là component nhập liệu một dòng, tự binding hai chiều với property của bean qua EL. `h:commandButton` sinh nút submit gắn với action method của bean. `h:messages`/`h:message` hiển thị các `FacesMessage` (lỗi validate hoặc thông báo do bean tạo ra) tương ứng toàn cục hoặc theo từng field.

**3. Managed Bean nhận dữ liệu từ giao diện bằng cơ chế nào?**
Qua Expression Language (`#{bean.property}`) kết hợp cơ chế data binding của JSF: ở giai đoạn *Update Model Values*, JSF tự gọi setter tương ứng trên bean bằng reflection, không cần code đọc `request.getParameter` thủ công.

**4. Bean Validation khác `requiredMessage` của JSF ở điểm nào?**
`requiredMessage` chỉ xử lý trường hợp field trống, khai báo trực tiếp trên thẻ JSF (view). Bean Validation (`@NotBlank`, `@Size`, `@Email`...) khai báo trên model, tái sử dụng được ở nhiều tầng (JSF, REST API, service layer...) và hỗ trợ nhiều loại ràng buộc phức tạp hơn (định dạng, khoảng giá trị, custom constraint).

**5. Vì sao Lab 9 mới nên tích hợp JPA, Entity, Repository và transaction?**
Lab 8 tập trung vào cơ chế UI component-based, validation và message của JSF nên dữ liệu vẫn giữ trong `List` ở tầng bộ nhớ để sinh viên không bị phân tán bởi cấu hình JPA/DataSource/transaction. Việc tách JPA sang Lab 9 giúp đi đúng mạch chương trình: nắm UI trước, sau đó mới học cách một Repository thật sự thao tác với database qua EntityManager và quản lý transaction.

## 6. Ghi chú / lỗi cần lưu ý khi chạy

- Cần server hỗ trợ Servlet 6.0 (Tomcat 10.x trở lên) vì project dùng namespace `jakarta.*`.
- Nếu deploy trên GlassFish/Payara (đã có sẵn JSF + CDI), có thể bỏ bớt dependency `jakarta.faces` và `weld-servlet-shaded` trong `pom.xml`, chỉ giữ `scope=provided`.
- Dữ liệu lưu trong `List` tĩnh của Repository nên sẽ mất khi redeploy — đúng như phạm vi của Lab 8 (JPA/DB để dành Lab 9).
