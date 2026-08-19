<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Đăng nhập</title></head>
<body>
<h2>Đăng nhập khu vực quản trị</h2>

<% if (request.getAttribute("loi") != null) { %>
    <p style="color:red;"><%= request.getAttribute("loi") %></p>
<% } %>

<form method="post" action="${pageContext.request.contextPath}/login">
    <p>Tài khoản: <input type="text" name="username" required></p>
    <p>Mật khẩu: <input type="password" name="password" required></p>
    <button type="submit">Đăng nhập</button>
</form>
<p><em>Tài khoản mẫu: admin / 123456</em></p>
<p><a href="${pageContext.request.contextPath}/index.jsp">Về trang chủ</a></p>
</body>
</html>
