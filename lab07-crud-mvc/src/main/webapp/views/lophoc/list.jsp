<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Danh sách lớp học</title></head>
<body>
<h2>Danh sách lớp học</h2>

<form method="get" action="${pageContext.request.contextPath}/lop-hoc">
    <input name="keyword" placeholder="Tìm theo mã hoặc tên lớp">
    <button type="submit">Tìm</button>
</form>

<p><a href="${pageContext.request.contextPath}/lop-hoc?action=new">Thêm lớp học</a> |
   <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></p>

<table border="1" cellpadding="6">
    <tr><th>ID</th><th>Mã lớp</th><th>Tên lớp</th><th>Cố vấn học tập</th><th>Số lượng SV</th><th>Thao tác</th></tr>
    <c:forEach var="lh" items="${dsLopHoc}">
        <tr>
            <td>${lh.id}</td><td>${lh.maLop}</td><td>${lh.tenLop}</td>
            <td>${lh.coVanHocTap}</td><td>${lh.soLuongSinhVien}</td>
            <td>
                <a href="${pageContext.request.contextPath}/lop-hoc?action=edit&id=${lh.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/lop-hoc?action=delete&id=${lh.id}"
                   onclick="return confirm('Xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty dsLopHoc}">
        <tr><td colspan="6">Không có dữ liệu</td></tr>
    </c:if>
</table>
</body>
</html>
