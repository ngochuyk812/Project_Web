<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet"
          href="https://staticfile.oto.com.vn/dist/web/styles/listtinrao.min.css.gz?v=638031797131449271">
    <link rel="stylesheet" href="https://staticfile.oto.com.vn/dist/web/styles/theme.min.css.gz?v=638031797131449271">
    <link rel="stylesheet"
          href="https://staticfile.oto.com.vn/dist/web/styles/listtinrao.min.css.gz?v=638031797131449271">
    <link rel="stylesheet" href="https://staticfile.oto.com.vn/dist/web/styles/fillte.min.css.gz?v=638031797131449271">
    <link rel="stylesheet" href="https://staticfile.oto.com.vn/dist/web/styles/footer.min.css.gz?v=638031797131449271">
    <link rel="stylesheet"
          href="https://staticfile.oto.com.vn/dist/web/styles/css-boxright.min.css.gz?v=638031797131449271">
    <style>
        <%@include file="./header.css" %>
    </style>
</head>
<body>
<div class="header-fix header-footer-container" id="header-container">
    <div class="header">
        <div class="header-top">
            <div class="container">
                <div class="left-head"> Hotline: 09793459242</div>
                <div class="head-right">
                    <div class="box-search-head">
                        <div class="box-search-head-element"><label for="txtKeyword"></label> <input
                                style="vertical-align: center" id="txtKeyword"
                                class="input-text ui-autocomplete-input"
                                maxlength="100"
                                autocomplete="off"
                                placeholder="Tìm kiếm theo hãng xe, dòng xe"
                                type="text">
                            <ul class="sub-search" id="sub-search"></ul>
                        </div>
                    </div>
                    <div class="noti" id="auto_save"><a rel="nofollow" id="showautosaved" href="cart"
                                                        title="Xe đã lưu"><i class="fa-solid fa-cart-shopping"></i></a>
                        <span
                                id="countproduct" class=""></span>
                        <div id="box-show-noti" class="scroll-list box-show-noti hide"></div>
                    </div>
                    <%--                    hậu--%>
                    <div class="box-login-acc" id="login">
                        <span style="margin-right: 15px">
        <a href="login" rel="nofollow" id="dangky">Đăng ký/ Đăng nhập
        </a>
    </span>
                        <div style="display: none" id="profile">
                            <a href="/profile"><i class="fa-solid fa-user"></i></a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="navi navi-fix menu-v2">
            <div class="container"><a class="logo" href="/">
            </a>
                <ul class="menu-top">
                    <li><a style="cursor: pointer" onclick="home()" data-key="/"><h2>Trang chủ</h2></a>

                    <li><a href="product" data-key="/mua-ban-xe"><h2>Sản phẩm</h2></a>
                    <li><a href="pricecar" data-key="/bang-gia-xe-o-to"><h2>Giá xe ô tô</h2></a>

                    </li>

                    </li>

                    <li id="checkIsAdmin"><a href="/admin?page=index"><h2>Admin</h2></a>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="../EventJs/HeaderActive.js">
</script>
<script>
    var Login_Res = document.querySelector("#login>span")
    var account = document.querySelector("#account")
    var profile = document.querySelector("#profile")
    const home = () => {
        window.location.pathname = "/"
    }


    const findCookieByname = (name) => {
        const cookies = document.cookie.split("; ")
        for (const i in cookies) {
            if ((cookies[i].split("=")[0]) == name) {
                return cookies[i].split("=")[1]
            }
        }

    }

    function del_cookie(name) {
        document.cookie = name +
            '=; expires=Thu, 01-Jan-70 00:00:01 GMT;';
    }

    const checkAccountExist = () => {
        console.log("get User")
        $.ajax({
            type: "Get",
            url: "/check/UserIsExist",
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                Login_Res.classList.add("hidden")
                profile.classList.add("show")
            }
        });
        // if(findCookieByname("isAdmin") !=1){
        //     document.querySelector("#checkIsAdmin").style.display = 'none'
        // }
    }
    checkAccountExist()
</script>
</html>

