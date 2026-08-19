<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Form nhập điểm</title></head>
<body>
<h2>Form nhập điểm sinh viên</h2>
<form method="post" action="${pageContext.request.contextPath}/diem">
    <input type="hidden" name="id" value="${diem.id}">
    <p>Mã SV: <input name="maSinhVien" value="${diem.maSinhVien}" required></p>
    <p>Họ tên: <input name="hoTen" value="${diem.hoTen}" required></p>
    <p>Điểm chuyên cần (0-10): <input name="diemChuyenCan" value="${diem.diemChuyenCan}" type="number" step="0.1" min="0" max="10" required></p>
    <p>Điểm giữa kỳ (0-10): <input name="diemGiuaKy" value="${diem.diemGiuaKy}" type="number" step="0.1" min="0" max="10" required></p>
    <p>Điểm cuối kỳ (0-10): <input name="diemCuoiKy" value="${diem.diemCuoiKy}" type="number" step="0.1" min="0" max="10" required></p>
    <button type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/diem">Hủy</a>
</form>
</body>
</html>
