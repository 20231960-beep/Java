<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Bảng điểm sinh viên</title></head>
<body>
<h2>Bảng điểm sinh viên</h2>

<p><a href="${pageContext.request.contextPath}/diem?action=new">Nhập điểm mới</a> |
   <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></p>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>Mã SV</th><th>Họ tên</th>
        <th>Chuyên cần</th><th>Giữa kỳ</th><th>Cuối kỳ</th>
        <th>Tổng kết</th><th>Xếp loại</th><th>Thao tác</th>
    </tr>
    <c:forEach var="d" items="${dsDiem}">
        <tr>
            <td>${d.id}</td><td>${d.maSinhVien}</td><td>${d.hoTen}</td>
            <td>${d.diemChuyenCan}</td><td>${d.diemGiuaKy}</td><td>${d.diemCuoiKy}</td>
            <td><b>${d.diemTongKet}</b></td>
            <td>${d.xepLoai}</td>
            <td>
                <a href="${pageContext.request.contextPath}/diem?action=edit&id=${d.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/diem?action=delete&id=${d.id}"
                   onclick="return confirm('Xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty dsDiem}">
        <tr><td colspan="9">Không có dữ liệu</td></tr>
    </c:if>
</table>
<p><em>Công thức: Tổng kết = Chuyên cần x 10% + Giữa kỳ x 30% + Cuối kỳ x 60%.
Xếp loại: A (&ge;8.5), B (&ge;7), C (&ge;5.5), D (&ge;4), F (&lt;4).</em></p>
</body>
</html>
