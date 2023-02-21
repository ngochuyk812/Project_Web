<%--
  Created by IntelliJ IDEA.
  User: 84348
  Date: 2/20/2023
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <style>
    .loader {
      border: 5px solid #f3f3f3;
      border-radius: 50%;
      border-top: 5px solid #3498db;
      width: 40px;
      height: 40px;
      -webkit-animation: spin 2s linear infinite;
      animation: spin 2s linear infinite;
    }

    @-webkit-keyframes spin {
      0% { -webkit-transform: rotate(0deg); }
      100% { -webkit-transform: rotate(360deg); }
    }

    @keyframes spin {
      0% { transform: rotate(0deg); }
      100% { transform: rotate(360deg); }
    }
  </style>
</head>
<div id="loading" style=" display: none;background-color: #ffffff80; position: absolute; top: 0; left: 0; right: 0; bottom: 0; z-index: 9999">
<div style="display: flex;  justify-content: center; align-items: center; margin-top: 200px; " >
  <div style="display: flex; flex-direction: column;background-color: white;box-shadow: 15px 15px 16px rgb(72, 72, 72); justify-content: center; align-items: center; padding: 15px 50px; border: 1px solid lightgray; ">
    <h3 style="color: rgb(128, 128, 128); margin: 10px;">Đang xử lý!</h3>
    <p style="margin: 0;">Đang xử lý dữ liệu vui lòng chờ trong.</p>
    <div class="loader"></div>
  </div>
</div>
</div>
<script>

</script>
