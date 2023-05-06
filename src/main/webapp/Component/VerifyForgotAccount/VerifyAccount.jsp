<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify Account</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<% String url = String.valueOf(request.getRequestURL());
    url = "http://" + url.split("\\/")[2] + "/login";
%>

<body style="background-color: rgb(233, 230, 230);">
<div style="display: flex;  justify-content: center; align-items: center; margin-top: 150px;">
    <div style="display: flex; flex-direction: column;background-color: white;box-shadow: 15px 15px 20px rgb(73, 73, 73); justify-content: center; align-items: center; padding: 80px; border: 1px solid lightgray; border-radius: 10px; ">
        <h1 style="margin: 15px;">Xác thực tài khoản thành công</h1>
        <h2>Vui lòng nhập mật khẩu mới</h2>
        <div style="width: 80%;display: flex;align-items: center;justify-content: space-between;">
            <label for="password">Password: </label><input id="password" type="text">
        </div>
        <div style="width: 80%;margin-top:10px;display: flex;align-items: center;justify-content: space-between;">
            <label for="rePassword">Repeat Password: </label><input id="rePassword" type="text">
        </div>
        <button style="cursor: pointer;padding: 10px 20px;margin-top: 20px;background: blue;border-radius: 10px;border: none;color: white;"
                class="<%= request.getAttribute("idUser") %>" id="changePassword">Thay đổi mật
            khẩu
        </button>
        <div style="margin-top: 20px;display: flex;align-items: center" ;>
            <i style="margin-right: 5px;color: #3b71ca" class="fa-solid fa-rotate-left"></i><a
                style="outline: none;color: #3b71ca;" href="<%=url%>">Tới
            trang đăng nhập</a>
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script !src="">
    $('#changePassword').click((e) => {
        e.preventDefault();
        let password = $('#password').val()
        let repassword = $('#rePassword').val()
        let idUser = $('#changePassword').attr("class")
        if (password != repassword) {
            swal({
                title: "Thất bại",
                text: "Pass word không trùng khớp",
            })
        } else {
            if (!checkPassword(password)) {
                swal({
                    title: "Thất bại",
                    text: "Pass word phải có chiều dài lớn hơn 8 và có vừa chữ vừa số",
                })
            } else {
                fetch(`/forgotPass`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                        type: "forgotPassword",
                        idUser,
                        password
                    })
                })
                    .then(() => {
                        const loading = document.getElementById("loading");
                        swal({
                            title: "Thành công",
                            text: "Thay đổi mật khẩu thành công",
                        })
                    })
            }
        }
    })
    const checkPassword = (password) => {
        const pattern = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
        return pattern.test(password);
    }
</script>

</html>