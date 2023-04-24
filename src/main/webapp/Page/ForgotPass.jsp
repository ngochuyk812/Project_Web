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
        .show{
            display: block !important;
        }
        .hidden{
            display: none !important;
        }
    </style>
</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class="wrapper">
        <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link " id="tab-login"  href="login" role="tab" >Login</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="tab-register"  href="register"
                >Register</a>
            </li>
        </ul>
        <!-- Pills navs -->

        <!-- Pills content -->
        <div class="tab-content" style="margin: auto;
    width: 700px;">
            <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                <form  class="form-forgot">
                    <div class="text-center mb-3">
                        <p>Forgot Password</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="text" id="emailName" class="form-control"/>
                        <label id="forgot-user" class="form-label" for="emailName">Username</label>
                    </div>

                    <button id="btn-forgot" type="submit" class="btn btn-primary btn-block mb-4 login">Lấy lại mật khẩu</button>

                    <div class="text-center">
                        <a  id="back-login" class="back-home" href="/">
                            <i class="fa-solid fa-backward"></i>
                            Back to home
                        </a>
                    </div>
                </form>
            </div>
        </div>
        <!-- Pills content -->

    </div>

</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript"
        src="https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/docs-app/js/dist/mdb5/standard/core.min.js"></script>
<script>
    document.querySelector(".upload-item").addEventListener("click", (e) => {
        document.querySelector(".fileupload").click()
    })
</script>
<script>
    $('#login')
    $('.login').bind('click', function (e) {
        e.preventDefault()
        $.ajax({
            url: "/login",
            type: "POST",
            data: {
                "username": $("#loginName").val(),
                "password": $("#loginPassword").val(),
            },
            contentType: "application/x-www-form-urlencoded",
            success: (data) => {
                console.log(data)
                if (data['message'] == "ok") {
                    swal({
                        title: "Thành công",
                        text: "Đăng nhập thành công",

                    }).then(()=>{
                        window.location.pathname = "/"
                    })

                } else {
                    swal({
                        title: "Thất bại",
                        text: "Vui lòng đăng nhập lại",

                    })

                }


            }
        })
    });

</script>
<script>
    const handleRegisNext=()=>{
        $(".form1").css("display", "none")
        $(".form2").css("display", "block")
    }
    const handleRegisRevert=()=>{
        $(".form1").css("display", "block")
        $(".form2").css("display", "none")
    }
    const resetForm = () => {
        $("#registerUsername").val("")
        $("#registerFullname").val("")
        $("#registerEmail ").val("")
        $("#registerSdt ").val("")
        $("#registerPassword ").val("")
        $("#registerRepeatPassword").val("")
        $("#registerAddress").val("")
    }
    const handleForm = (name, fullName, email, sdt, passRepeat, pass, imgRequest, address) => {
        if (pass == passRepeat && name && fullName && email && sdt && pass && imgRequest && address) {
            console.log("cung ok")
            console.log(name)
            console.log(fullName)
            console.log(imgRequest)
            return true
        }
        return false
    }
    var imgRequest='';
    $(".fileupload").bind("change", (e) => {
        var file = document.querySelector(".fileupload").files[0];
        var reader = new FileReader();
        reader.onloadend = function() {
            imgRequest= reader.result
            $(".uploadImg").attr("src",imgRequest)
        }
        reader.readAsDataURL(file);
    })

    var code;
    var name;
    var fullName;
    var email;
    var sdt;
    var pass;
    var passRepeat;
    var address;
    $(".register").bind("click", async function (e) {
        e.preventDefault()
        var codeVl = $(".code").val()
        console.log(name)
        console.log(fullName)
        if (handleForm(name, fullName, email, sdt, passRepeat, pass, imgRequest, address)) {
            dataBody = {
                name: name,
                fullName: encodeURIComponent(fullName),
                email: email,
                phone: sdt,
                pass: pass,
                avatar: imgRequest,
                code: codeVl,
                address: encodeURIComponent(address)
            }
            $.ajax({
                url: "/register",
                type: "POST",
                data: dataBody,
                contentType: 'application/x-www-form-urlencoded',
                success: function (data) {
                    console.log(data['message'])
                    console.log(data['message'] == "register success")
                    if (data['message'] == "register success") {
                        swal({
                            title: "Thành công",
                            text: "Chúc mừng bạn đã đăng ký thành công",

                        })
                        $(".uploadImg").attr("src", "/Img/User/${name}.jpg")
                        handleRegisRevert()
                        window.location.pathname = "Login.jsp"
                        resetForm()
                    } else {
                        if (data['message'] == "code sai") {
                            swal({
                                title: "Thất bại",
                                text: "Vui lòng nhập lại code",

                            })
                            $(".uploadImg").attr("src", "/Img/User/${name}.jpg")
                        } else {
                            swal({
                                title: "Thành công",
                                text: "Chúc mừng bạn đã đăng ký thành công",

                            })
                            window.location.pathname = "Login.jsp"
                            handleRegisRevert()
                            resetForm()
                        }
                    }


                }
            });
        } else {
            swal({
                title: "Thất bại",
                text: "Vui lòng nhập lại thông tin",

            })
        }


    })
    $(".btn-code").click(function (e) {
        e.preventDefault()
        swal({
            title:"",
            text:"Loading...",
            icon: "https://www.boasnotas.com/img/loading2.gif",
            customClass: 'loading',
            buttons: false,
            closeOnClickOutside: false,
            timer: 16000,
            //icon: "success"
        });
        name = $("#registerUsername").val()
        fullName = $("#registerFullname").val()
        email = $("#registerEmail ").val()
        sdt = $("#registerSdt ").val()
        address = $("#registerAddress ").val()
        pass = $("#registerPassword ").val()
        passRepeat = $("#registerRepeatPassword").val()
        fetch("/sendMail?email=" + email)
            .then((resp) => {
                return resp.json()
                console.log(1)
            })
            .then((resp)=>{
                console.log(2)
                handleRegisNext()
            })
            .catch(()=>{
                alert("Email không tồn tại")
            })
    })
    $('#btn-forgot').click((e)=>{
        e.preventDefault();
        let usernameOrEmail=$('#forgot-user').val()
        fetch(`/forgotPass?username=}`+usernameOrEmail)
            .then(()=>{
                alert("Password đã được gửi về email của bạn")
            })
            .catch(()=>{
                alert("username không tồn tại")
            })

    })
    document.querySelector("#back-login").addEventListener("click",(e)=>{
        e.preventDefault()
        document.querySelector(".form-login").classList.toggle("hidden")
        document.querySelector(".form-forgot").classList.toggle("show")
    })
    document.querySelector("#e-forgot").addEventListener("click",(e)=>{
        e.preventDefault()
        document.querySelector(".form-login").classList.add("hidden")
        document.querySelector(".form-forgot").classList.add("show")
    })
</script>
</html>
