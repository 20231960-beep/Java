<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Trang quản trị</title></head>
<body>
<h2>Trang quản trị</h2>
<p>Xin chào, <b>${sessionScope.username}</b>! Bạn đã đăng nhập thành công.</p>
<p>Khu vực này (<code>/admin/*</code>) được bảo vệ bởi <code>LoginFilter</code> — chỉ truy cập được khi đã đăng nhập.</p>
<ul>
    <li><a href="${pageContext.request.contextPath}/sinh-vien">Quản lý sinh viên</a></li>
    <li><a href="${pageContext.request.contextPath}/sach">Quản lý sách</a></li>
    <li><a href="${pageContext.request.contextPath}/san-pham">Quản lý sản phẩm</a></li>
    <li><a href="${pageContext.request.contextPath}/lop-hoc">Quản lý lớp học</a></li>
    <li><a href="${pageContext.request.contextPath}/diem">Quản lý điểm sinh viên</a></li>
</ul>
<p><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></p>
</body>
</html>
