<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Form lớp học</title></head>
<body>
<h2>Form lớp học</h2>
<form method="post" action="${pageContext.request.contextPath}/lop-hoc">
    <input type="hidden" name="id" value="${lh.id}">
    <p>Mã lớp: <input name="maLop" value="${lh.maLop}" required></p>
    <p>Tên lớp: <input name="tenLop" value="${lh.tenLop}" required></p>
    <p>Cố vấn học tập: <input name="coVanHocTap" value="${lh.coVanHocTap}"></p>
    <p>Số lượng sinh viên: <input name="soLuongSinhVien" value="${lh.soLuongSinhVien}" type="number"></p>
    <button type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/lop-hoc">Hủy</a>
</form>
</body>
</html>
