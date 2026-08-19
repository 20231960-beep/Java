<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Form sản phẩm</title></head>
<body>
<h2>Form sản phẩm</h2>

<c:if test="${not empty errors}">
    <div style="color:red; border:1px solid red; padding:8px; margin-bottom:10px;">
        <b>Vui lòng kiểm tra lại:</b>
        <ul>
            <c:forEach var="e" items="${errors}">
                <li>${e.value}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/san-pham">
    <input type="hidden" name="id" value="${sp.id}">
    <p>Mã sản phẩm: <input name="maSanPham" value="${sp.maSanPham}" required></p>
    <p>Tên sản phẩm: <input name="tenSanPham" value="${sp.tenSanPham}" required></p>
    <p>Mô tả: <input name="moTa" value="${sp.moTa}"></p>
    <p>Giá (đ): <input name="gia" value="${sp.gia}" type="number" step="0.01" required></p>
    <p>Số lượng: <input name="soLuong" value="${sp.soLuong}" type="number" required></p>
    <button type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/san-pham">Hủy</a>
</form>
</body>
</html>
