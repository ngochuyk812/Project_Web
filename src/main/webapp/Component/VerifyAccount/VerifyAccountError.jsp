
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Verify Account</title>
</head>
<% String url = String.valueOf(request.getRequestURL());
  url = "http://" + url.split("\\/")[2] + "/register";
%>
<body style="background-color: rgb(233, 230, 230);">
<div style="display: flex;  justify-content: center; align-items: center; margin-top: 150px;" >
  <div style="display: flex; flex-direction: column;background-color: white;box-shadow: 15px 15px 20px rgb(73, 73, 73); justify-content: center; align-items: center; padding: 80px; border: 1px solid lightgray; border-radius: 10px; ">
    <h1 style="margin: 15px;">Xác thực tài khoản không thành công</h1>
    <p>Token đã hết hạn hoặc token không tồn tại</p>
    <a href="<%=url%>" style="padding: 10px 20px; background-color: rgb(0, 123, 255); color: white; text-decoration: none;">Tới trang đăng ký</a>
  </div>
</div>
</body>

</html>