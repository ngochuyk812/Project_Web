<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../Style.css">
    <link rel="stylesheet"
          href="https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/docs-app/css/dist/mdb5/standard/core.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Scripts/swiper-6.3.3/swiper-bundle.m
    <title>Login/Register</title>in.css">
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
        #loginWithGG,.fb_iframe_widget {
            min-width: 200px;
            min-height: 50px;
            padding: 10px !important;
            border: 2px solid #4b7bca !important;
            border-radius: 10px;
            margin-bottom: 20px;
            margin-right: 20px;
         }

        #loginWithGG i,.fb_iframe_widget i{
            color: #3b71ca;
            margin-right: 10px;
        }
        #loginWithGG:hover,.fb_iframe_widget:hover{
            background-color:#3b71ca;
            color: white;
            cursor: pointer;
        }
        #loginWithGG:hover i,.fb_iframe_widget:hover i{
            color: white;
        }
        #login{
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;

        }

    </style>
</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class="wrapper">
        <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link active" id="tab-login"  href="login" role="tab" >Login</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="tab-register"  href="register"
                   >Register</a>
            </li>
        </ul>
        <!-- Pills navs -->

        <!-- Pills content -->
        <div class="tab-content" style="margin: auto;">
            <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                <form class="form-login">
                    <div class="text-center mb-3">
                        <p>Sign in with</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="text" id="loginName" class="form-control"/>
                        <label class="form-label" for="loginName">Email or username</label>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <input type="password" id="loginPassword" class="form-control"/>
                        <label class="form-label" for="loginPassword">Password</label>
                    </div>

                    <!-- 2 column grid layout -->
                    <div class="row mb-4">
                        <div class="col-md-6 d-flex justify-content-center">
                            <!-- Checkbox -->
                            <div class="form-check mb-3 mb-md-0">
                                <input class="form-check-input" type="checkbox" value="" id="loginCheck" checked/>
                                <label class="form-check-label" for="loginCheck"> Remember me </label>
                            </div>
                        </div>

                        <div class="col-md-6 d-flex justify-content-center">
                            <!-- Simple link -->
                            <a  class="link-forgot" href="forgotPass">Forgot password?</a>
                        </div>
                    </div>

                    <!-- Submit button -->
                    <button type="submit" class="btn btn-primary btn-block mb-4 login">Login</button>
                    <div class="text-center">
                        <div id="login">
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:3000/login/loginWithGg&response_type=code
		   &client_id=108677386257-pqkllmloi7d9f60ipgvb053sfg9eosrr.apps.googleusercontent.com&approval_prompt=force" id="loginWithGG"><i class="fa-brands fa-google"></i>Login with google</a>
                            <fb:login-button id="loginWithFb" scope="public_profile,email" onlogin="checkLoginState();">
                            </fb:login-button>
                        </div>
                    </div>
                    <!-- Register buttons -->
                    <div class="text-center">
                        <a  href="/">
                            <i class="fa-solid fa-backward"></i>
                            Back to home
                        </a>
                    </div>
                </form>
<%--                 forgot--%>

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
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
<script>
    // document.querySelector(".upload-item").addEventListener("click", (e) => {
    //     document.querySelector(".fileupload").click()
    // })
</script>
<script>
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
                    setTimeout(()=>{
                        window.location.pathname = "/"
                    },500)

                } else {
                    swal({
                        title: "Thất bại",
                        text: "Vui lòng đăng nhập lại( Có thể tài khoản của bạn đã bị khóa hoặc sai thông tin)",

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
<%--Login with fb--%>
<script>
    function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
        console.log('statusChangeCallback');
        console.log(response);                   // The current login status of the person.
        if (response.status === 'connected') {   // Logged into your webpage and Facebook.
            testAPI();
        } else {                                 // Not logged into your webpage or we are unable to tell.
            document.getElementById('status').innerHTML = 'Please log ' +
                'into this webpage.';
        }
    }


    function checkLoginState() {               // Called when a person is finished with the Login Button.
        FB.getLoginStatus(function(response) {   // See the onlogin handler
            statusChangeCallback(response);
        });
    }


    window.fbAsyncInit = function() {
        FB.init({
            appId      : '2159772147554215',
            cookie     : true,                     // Enable cookies to allow the server to access the session.
            xfbml      : true,                     // Parse social plugins on this webpage.
            version    : 'v16.0'           // Use this Graph API version for this call.
        });


        FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
            statusChangeCallback(response);        // Returns the login status.
        });
    };

    function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', function(response) {
            console.log(response)
            $.ajax({
                url: "/login/LoginWithFb",
                type: "POST",
                data: {
                userName:response.id
                },
                contentType: 'application/x-www-form-urlencoded',
                success: function (data) {

                }
        });
    })
    }
</script>

</html>
