<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Danh sách sản phẩm</title></head>
<body>
<h2>Danh sách sản phẩm</h2>

<form method="get" action="${pageContext.request.contextPath}/san-pham">
    <input name="keyword" placeholder="Tìm theo tên sản phẩm">
    <button type="submit">Tìm</button>
</form>

<p><a href="${pageContext.request.contextPath}/san-pham?action=new">Thêm sản phẩm</a> |
   <a href="${pageContext.request.contextPath}/gio-hang">Xem giỏ hàng</a> |
   <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></p>

<table border="1" cellpadding="6">
    <tr><th>ID</th><th>Mã SP</th><th>Tên SP</th><th>Mô tả</th><th>Giá</th><th>Số lượng</th><th>Thao tác</th></tr>
    <c:forEach var="sp" items="${dsSanPham}">
        <tr>
            <td>${sp.id}</td><td>${sp.maSanPham}</td><td>${sp.tenSanPham}</td>
            <td>${sp.moTa}</td>
            <td><fmt:formatNumber value="${sp.gia}" type="number"/> đ</td>
            <td>${sp.soLuong}</td>
            <td>
                <a href="${pageContext.request.contextPath}/san-pham?action=edit&id=${sp.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/san-pham?action=delete&id=${sp.id}"
                   onclick="return confirm('Xóa?')">Xóa</a> |
                <a href="${pageContext.request.contextPath}/gio-hang?action=add&id=${sp.id}&soLuong=1">Thêm vào giỏ</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty dsSanPham}">
        <tr><td colspan="7">Không có dữ liệu</td></tr>
    </c:if>
</table>
</body>
</html>
