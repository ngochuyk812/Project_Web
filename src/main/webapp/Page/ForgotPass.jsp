<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login/Register</title>
    <link rel="stylesheet" href="../Style.css">
    <link rel="stylesheet"
          href="https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/docs-app/css/dist/mdb5/standard/core.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Scripts/swiper-6.3.3/swiper-bundle.min.css">
    <link rel="stylesheet" href="https://oto.com.vn/Styles/v2.0/common.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Styles/v2.0/theme.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/post_news.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/node_modules/@angular/material/prebuilt-themes/indigo-pink.css">
    <link rel="stylesheet" type="text/css" href="/Page/Login_Register.css">
    <style>
        .show {
            display: block !important;
        }

        .hidden {
            display: none !important;
        }
    </style>
</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class="wrapper">
        <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link " id="tab-login" href="login" role="tab">Login</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="tab-register" href="register"
                >Register</a>
            </li>
        </ul>
        <!-- Pills navs -->

        <!-- Pills content -->
        <div class="tab-content" style="margin: auto;">
            <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                <form class="form-forgot">
                    <div class="text-center mb-3">
                        <p>Forgot Password</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="text" id="emailName" class="form-control"/>
                        <label id="forgot-user" class="form-label" for="emailName">Username</label>
                    </div>

                    <button id="btn-forgot" type="submit" class="btn btn-primary btn-block mb-4 login">Get link verify account
                    </button>

                    <div class="text-center">
                        <a id="back-login" class="back-home" href="/">
                            <i class="fa-solid fa-backward"></i>
                            Back to home
                        </a>
                    </div>
                </form>
            </div>
        </div>
        <!-- Pills content -->

    </div>
    <%@include file="../Component/loading/Loading.jsp" %>

</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript"
        src="https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/docs-app/js/dist/mdb5/standard/core.min.js"></script>
<script>
    $('#btn-forgot').click((e) => {
        e.preventDefault();
        let usernameOrEmail = $('#emailName').val()
        const loading = document.getElementById("loading");
        loading.style.display="block"
        fetch(`/forgotPass`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                type: "confirmAccount",
                username: usernameOrEmail
            })
        })
            .then((response) => {
                if (response.ok) {
                    loading.style.display="none"
                    swal({
                        title: "Thành công",
                        text: "Vui lòng kiểm tra email để xác thực và đổi mật khẩu",
                    });
                } else {
                    throw new Error(response.statusText);
                }
            })
            .catch((error) => {
                loading.style.display="none"
                swal({
                    title: "Thất bại",
                    text: "Tài khoản không tồn tại",
                });
            });

    })
    document.querySelector("#back-login").addEventListener("click", (e) => {
        e.preventDefault()
        document.querySelector(".form-login").classList.toggle("hidden")
        document.querySelector(".form-forgot").classList.toggle("show")
    })
    document.querySelector("#e-forgot").addEventListener("click", (e) => {
        e.preventDefault()
        document.querySelector(".form-login").classList.add("hidden")
        document.querySelector(".form-forgot").classList.add("show")
    })
</script>
</html>
