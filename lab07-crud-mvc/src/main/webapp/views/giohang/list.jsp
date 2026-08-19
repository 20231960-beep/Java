<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Giỏ hàng</title></head>
<body>
<h2>Giỏ hàng của bạn</h2>

<p><a href="${pageContext.request.contextPath}/san-pham">&laquo; Tiếp tục mua sắm</a> |
   <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></p>

<table border="1" cellpadding="6">
    <tr><th>Tên sản phẩm</th><th>Đơn giá</th><th>Số lượng</th><th>Thành tiền</th><th>Thao tác</th></tr>
    <c:forEach var="item" items="${gioHang}">
        <tr>
            <td>${item.tenSanPham}</td>
            <td><fmt:formatNumber value="${item.gia}" type="number"/> đ</td>
            <td>
                <form method="get" action="${pageContext.request.contextPath}/gio-hang" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${item.sanPhamId}">
                    <input type="number" name="soLuong" value="${item.soLuong}" min="0" style="width:60px;">
                    <button type="submit">Cập nhật</button>
                </form>
            </td>
            <td><fmt:formatNumber value="${item.thanhTien}" type="number"/> đ</td>
            <td><a href="${pageContext.request.contextPath}/gio-hang?action=remove&id=${item.sanPhamId}"
                   onclick="return confirm('Xóa khỏi giỏ?')">Xóa</a></td>
        </tr>
    </c:forEach>
    <c:if test="${empty gioHang}">
        <tr><td colspan="5">Giỏ hàng trống</td></tr>
    </c:if>
</table>

<h3>Tổng tiền: <fmt:formatNumber value="${tongTien}" type="number"/> đ</h3>

<c:if test="${not empty gioHang}">
    <p><a href="${pageContext.request.contextPath}/gio-hang?action=clear"
          onclick="return confirm('Xóa toàn bộ giỏ hàng?')">Xóa toàn bộ giỏ hàng</a></p>
</c:if>
</body>
</html>
