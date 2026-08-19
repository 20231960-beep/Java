<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Danh sách sách</title></head>
<body>
<h2>Danh sách sách</h2>

<form method="get" action="${pageContext.request.contextPath}/sach">
    <input name="keyword" placeholder="Tìm theo tên hoặc tác giả">
    <button type="submit">Tìm</button>
</form>

<p><a href="${pageContext.request.contextPath}/sach?action=new">Thêm sách</a> |
   <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></p>

<table border="1" cellpadding="6">
    <tr><th>ID</th><th>Mã sách</th><th>Tên sách</th><th>Tác giả</th><th>NXB</th><th>Năm XB</th><th>Thao tác</th></tr>
    <c:forEach var="s" items="${dsSach}">
        <tr>
            <td>${s.id}</td><td>${s.maSach}</td><td>${s.tenSach}</td>
            <td>${s.tacGia}</td><td>${s.nhaXuatBan}</td><td>${s.namXuatBan}</td>
            <td>
                <a href="${pageContext.request.contextPath}/sach?action=edit&id=${s.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/sach?action=delete&id=${s.id}"
                   onclick="return confirm('Xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty dsSach}">
        <tr><td colspan="7">Không có dữ liệu</td></tr>
    </c:if>
</table>
</body>
</html>
