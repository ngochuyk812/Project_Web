<%@ page import="Model.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=1519px, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
          integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>


    <style>
        <%@include file="detail.css" %>
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../Component/header/Header.jsp"/>
<div class="container " style="height: auto" id="detailPage">
    <%Product product = (Product) request.getAttribute("product"); %>
    <img src="https://img1.oto.com.vn/Static/Images/otocare/web/477_290622_MuaXeTaiDay_AnTamKiemDinh_1140x140.webp"
         alt="OTOCheck" width="1140" height="140">

    <div class="content-detail" style="margin-top: 10px">
        <div class="left-detail">
            <div class="group-title-detail">
                <h1 class="title-detail"><%=product.getName()%>
                </h1>
                <div class="date-code">
                    <span class="code">Mã tin: <%=product.getId()%> </span>|<span class="date">02/11/2022</span>|<span
                        class="viewed "><i class="icon-eye-1"></i><span class="totalView">107 lượt xem</span> </span>
                </div>
            </div>
            <div class="price-detail">
                <div class="box-price">
                    <span class="price-big blueprice"><span style="font-weight: 600">Giá bán:</span> <span
                            style="font-size: 18px; font-weight: 700; color: #024E9C"> <%=product.getPrice()%> triệu</span> </span>
                    <span>|</span>
                    <span class="price-small" id="chiphilanbanh">Giá lăn bánh: <span
                            style="color: #4DB848"> <%=product.getPrice() + product.getPrice() * 0.1 %> triệu</span> </span>

                </div>
                <div class="share-fbgg">
                    <div>
                        <div class="fb-like fb_iframe_widget"
                             data-href="https://oto.com.vn/mua-ban-xe-mazda-cx-5-hcm/giam-khung-40-trieu-qua-tang-hap-dan-san-giao-ngay-aidxm22661144"
                             data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"
                             fb-xfbml-state="rendered"
                             fb-iframe-plugin-query="action=like&amp;app_id=1619168438343676&amp;container_width=0&amp;href=https%3A%2F%2Foto.com.vn%2Fmua-ban-xe-mazda-cx-5-hcm%2Fgiam-khung-40-trieu-qua-tang-hap-dan-san-giao-ngay-aidxm22661144&amp;layout=button_count&amp;locale=vi_VN&amp;sdk=joey&amp;share=true&amp;show_faces=true"
                             data-gtm-vis-recent-on-screen-13008071_955="3019"
                             data-gtm-vis-first-on-screen-13008071_955="3019"
                             data-gtm-vis-total-visible-time-13008071_955="100" data-gtm-vis-has-fired-13008071_955="1"><span
                                style="vertical-align: bottom; width: 150px; height: 28px;"><iframe
                                name="f2152f12efa2e1"
                                width="1000px" height="1000px" data-testid="fb:like Facebook Social Plugin"
                                title="fb:like Facebook Social Plugin" frameborder="0" allowtransparency="true"
                                allowfullscreen="true" scrolling="no" allow="encrypted-media"
                                src="https://web.facebook.com/v8.0/plugins/like.php?action=like&amp;app_id=1619168438343676&amp;channel=https%3A%2F%2Fstaticxx.facebook.com%2Fx%2Fconnect%2Fxd_arbiter%2F%3Fversion%3D46%23cb%3Df5e9c60c0f2874%26domain%3Doto.com.vn%26is_canvas%3Dfalse%26origin%3Dhttps%253A%252F%252Foto.com.vn%252Ff33c63b3d47e404%26relation%3Dparent.parent&amp;container_width=0&amp;href=https%3A%2F%2Foto.com.vn%2Fmua-ban-xe-mazda-cx-5-hcm%2Fgiam-khung-40-trieu-qua-tang-hap-dan-san-giao-ngay-aidxm22661144&amp;layout=button_count&amp;locale=vi_VN&amp;sdk=joey&amp;share=true&amp;show_faces=true"
                                style="border: none; visibility: visible; width: 150px; height: 28px;"
                                class=""></iframe></span></div>
                    </div>
                </div>
            </div>
            <section id="slide">
                <div class="slideshow-container">
                    <div class="mySlides fade">
                        <div class="numbertext">1 / 3</div>
                        <img src="https://img1.oto.com.vn/crop/575x430/2022/10/30/20221030182457-cb9a_wm.jpg"
                             style="width:100%">
                    </div>
                    <div class="mySlides fade">
                        <div class="numbertext">2 / 3</div>
                        <img src="https://img1.oto.com.vn/crop/575x430/2022/10/30/20221030182457-cb9a_wm.jpg"
                             style="width:100%">
                    </div>
                    <div class="mySlides fade">
                        <div class="numbertext">3 / 3</div>
                        <img src="https://img1.oto.com.vn/crop/575x430/2022/10/30/20221030182457-cb9a_wm.jpg"
                             style="width:100%">
                    </div>
                    <!-- Nút điều khiển mũi tên-->
                </div>
                <div style="text-align:center" class="listdot">
                    <a class="prev" onclick="plusSlides(-1)"><i class="fa-sharp fa-solid fa-angle-up"></i></a>
                    <a class="next" onclick="plusSlides(1)"><i class="fa-sharp fa-solid fa-angle-down"></i></a>

                    <div class="dot" onclick="currentSlide(1)"><img
                            src="https://img1.oto.com.vn/crop/575x430/2022/10/30/20221030182457-cb9a_wm.jpg"
                            style="width:100%"></div>
                    <div class="dot" onclick="currentSlide(2)"><img
                            src="https://img1.oto.com.vn/crop/575x430/2022/10/30/20221030182457-cb9a_wm.jpg"
                            style="width:100%">
                    </div>
                    <div class="dot" onclick="currentSlide(3)"><img
                            src="https://img1.oto.com.vn/crop/575x430/2022/10/30/20221030182457-cb9a_wm.jpg"
                            style="width:100%">

                    </div>
                </div>
            </section>
            <div class="box-info-detail">
                <ul class="list-info">
                    <li><label class="label"><i class="fa-solid fa-calendar-days"></i>Năm sản
                        xuất</label> <%=product.getYearOfManuFacture()%>
                    </li>
                    <li><label class="label"><i class="fa-solid fa-taxi"></i>Kiểu dáng</label><%=product.getBody()%>
                    </li>
                    <li><label class="label"><i class="fa-solid fa-person-breastfeeding"></i>Tình trạng</label>
                        <%=(product.getStatus() == 0) ? "Chưa sử dụng" : "Đã sử dụng"%>
                    </li>
                    <%--                    <li><label class="label"><i class="fa-solid fa-flag-checkered"></i></i>Xuất xứ</label><%=product.getma()%>></li>--%>
                    <%--                    <li><label class="label"><i class="fa-solid fa-hashtag"></i>Hộp số</label> <%=(product.getGear() == 0) ? "Số tay": "Số tự dộng"%></li>--%>
                    <li><label class="label"><i class="fa-solid fa-gas-pump"></i>Nhiên
                        liệu</label> <%=product.getFuel()%>
                    </li>
                </ul>
            </div>
            <br>
            <div>
                <button class="btn btn-primary" onclick="addToCart(<%=product.getId()%>)" type="button">Thêm giỏ hàng
                </button>
            </div>
            <div class="describe">
                <br>
                <h5>Mô tả</h5>
                <hr>
                <div class="content-describe">
                    <p><%=product.getName()%>́</p>

                    <p><%=product.getContent()%>
                    </p>
                </div>
                <hr>
            </div>
            <div class="info-bus">
                <div class="row">
                    <div class="col-6"> Giấy tờ cần kiểm tra khi mua xe cũ:
                        <ul class="list-info">
                            <li><i class="icon-dot-1"></i>Đăng ký, đăng kiểm, biên lai phí sử dụng đường bộ</li>
                            <li><i class="icon-dot-1"></i>Hồ sơ bảo dưỡng xe</li>
                            <li><i class="icon-dot-1"></i>Tra cứu phạt nguội</li>
                        </ul>
                    </div>
                    <div class="col-6"> Thủ tục sang tên xe cũ cho người mua:
                        <ul class="list-info">
                            <li><i class="icon-dot-1"></i>Công chứng hợp đồng mua bán xe ô tô</li>
                            <li><i class="icon-dot-1"></i>Nộp lệ phí trước bạ</li>
                            <li><i class="icon-dot-1"></i>Rút hồ sơ gốc / làm sổ đăng kiểm mới</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="comment">
                <h5>ĐÁNH GIÁ NGƯỜI BÁN
                </h5>
                <h5>5.0 <i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i> <i
                        class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
                    <span style="font-size: 16px; font-weight: normal">(2 lượt đánh giá)</span></h5>
                <br/>
                <div class="box-input">
                    <form action="">
                        <h5 style="text-align: center; font-weight: bold">Nội dung đánh giá</h5>
                        <textarea id="w3review" name="w3review" rows="4" cols="50"></textarea>

                        <div style="display: flex;align-items: center">
                            <p style="margin-top: 8px"><b>Đánh giá:</b></p>
                            <div class="stars">
                                <form action="">
                                    <input class="star star-5" id="star-5" type="radio" name="star"/>
                                    <label class="star star-5" for="star-5"></label>
                                    <input class="star star-4" id="star-4" type="radio" name="star"/>
                                    <label class="star star-4" for="star-4"></label>
                                    <input class="star star-3" id="star-3" type="radio" name="star"/>
                                    <label class="star star-3" for="star-3"></label>
                                    <input class="star star-2" id="star-2" type="radio" name="star"/>
                                    <label class="star star-2" for="star-2"></label>
                                    <input class="star star-1" id="star-1" type="radio" name="star"/>
                                    <label class="star star-1" for="star-1"></label>
                                </form>
                            </div>
                        </div>
                        <div class="upload">
                            <div class="wrapperUpload">
                                <label for="imgUploadBtn">Chọn ảnh hay video: </label> <i id="imgUploadBtn"
                                                                                          class="imgUploadBtn fa-solid fa-image"></i>
                                <input id="myFileInput" type="file" multiple style="display: none; ">
                            </div>
                            <div class="containerImg">
                            </div>
                            <div class="containerVideo">
                            </div>
                        </div>
                        <div class="box-bt">
                            <button style="cursor: pointer" id="${id}" class="bt-comment">Gửi đánh giá</button>
                        </div>
                    </form>
                </div>

                <div class="list-comment">
                    <c:forEach items="${listComment}" var="item">
                        <div class="cmt">
                            <div class="cmt_h">
                                <c:if test="${item.avatar!=null}">
                                    <img src="${item.avatar}" alt="src">
                                </c:if>
                                <c:if test="${avatar==null}">
                                    <img src="https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.30497-1/143086968_2856368904622192_1959732218791162458_n.png?stp=cp0_dst-png_p40x40&_nc_cat=1&ccb=1-7&_nc_sid=dbb9e7&_nc_ohc=4WG6vRsKhfwAX8Bce7J&_nc_ht=scontent.fsgn2-6.fna&oh=00_AfBRz5wLhcd8XP66sUoUweAK2PaU0ABxXKHrhZHXEuUqAg&oe=646409F8"
                                         alt="src">
                                </c:if>
                                <div class="start-cmt">
                                    <c:forEach begin="1" end="${item.star}">
                                        <i class="fa-solid fa-star"></i>
                                    </c:forEach>
                                </div>
                            </div>
                            <p>từ <b>${item.userName}</b>, <span class="cl-lg">${item.createAt}</span></p>
                            <p>${item.content}</p>
                            <c:if test="${item.listImg.size()>=1}">
                                <div class="renderListImg">
                                    <c:forEach items="${item.listImg}" var="tmp">
                                        <img src="${tmp}" alt="">
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${item.listVideo.size()>=1}">
                                <div class="renderListVideo">
                                    <c:forEach items="${item.listVideo}" var="tmp">
                                        <video src="${tmp}" controls="true"></video>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
        <div class="right-detail">
            <jsp:include page="../Component/keyword/keyword.jsp"></jsp:include>
        </div>
    </div>
</div>
<jsp:include page="../Component/footer/footer.jsp"/>
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("dot");
        if (n > slides.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = slides.length
        }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
    }

    const addToCart = (id) => {
        $.ajax({
            url: "/cart?action=addtocart&idpost=" + id,
            type: 'POST',
            success: function (res) {


            }
        });
    }
    const addToCart = (id) => {
        $.ajax({
            url: "/cart?action=addtocart&idpost=" + id,
            type: 'POST',
            success: function (res) {

            }
        });
    }
</script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="application/javascript">
    const containerImg = document.querySelector(".containerImg")
    const containerVideo = document.querySelector(".containerVideo")
    document.querySelector(".imgUploadBtn").addEventListener("click", () => {
        fileInput.click();
    })
    const delImg = (e) => {
        const wrapperImg = e.target.parentNode;
        wrapperImg.remove();
    }
    const createItemImg = (src) => {
        const newDiv = document.createElement("div");
        newDiv.setAttribute("id", "your-div-id");
        newDiv.classList.add("wrapperImg");
        const deleteIcon = document.createElement("i");
        deleteIcon.classList.add("imgDel");
        deleteIcon.classList.add("fa-solid");
        deleteIcon.classList.add("fa-trash");
        deleteIcon.addEventListener("click", (e) => delImg(e))
        newDiv.appendChild(deleteIcon);
        const newImg = document.createElement("img");
        newImg.setAttribute("src", src);
        newImg.classList.add("imgUpload");
        newDiv.appendChild(newImg);
        return newDiv;
    }
    const createItemVideo = (src) => {
        const newDiv = document.createElement("div");
        newDiv.classList.add("wrapperVideo");
        const deleteIcon = document.createElement("i");
        deleteIcon.classList.add("videoDel");
        deleteIcon.classList.add("fa-solid");
        deleteIcon.classList.add("fa-trash");
        deleteIcon.addEventListener("click", (e) => delImg(e))
        newDiv.appendChild(deleteIcon);
        const newVideo = document.createElement("video");
        newVideo.setAttribute("src", src);
        newVideo.classList.add("videoUpload");
        newVideo.setAttribute("controls", true);
        newDiv.appendChild(newVideo);
        const container = document.querySelector(".containerVideo");
        container.appendChild(newDiv);
        return newDiv;
    }
    var formData = new FormData();
    const fileInput = document.getElementById("myFileInput");
    fileInput.addEventListener("change", async () => {
        const files = fileInput.files;
        for (let i = 0; i < files.length; i++) {
            const file = files[i];
            console.log(file.size)
            const reader = new FileReader();
            reader.addEventListener("load", () => {
                const base64String = reader.result;
                if (file.type.includes("image")) {
                    if (formData.getAll("fileImage").length + 1 > 5) {
                        swal({
                            title: "Thất bại",
                            text: "Chỉ được chọn 5 image",
                        })
                    } else {
                        formData.append("fileImage", file)
                        const div = createItemImg(base64String)
                        containerImg.appendChild(div)
                    }
                } else if (file.type.includes("video/mp4")) {
                    if (formData.getAll("fileVideo").length + 1 > 2) {
                        swal({
                            title: "Thất bại",
                            text: "Chỉ được chọn 2 video",
                        })
                    } else {
                        formData.append(`fileVideo`, file)
                        const div = createItemVideo(base64String)
                        containerVideo.appendChild(div)
                    }
                }
            });
            await reader.readAsDataURL(file);
        }
    });

    // Thêm các tệp trong mảng fileArray vào FormData
    var star = 0;
    document.querySelectorAll(".stars input").forEach((item, index) => {
        item.addEventListener("click", () => {
            star = 5 - index;
        })
    })
    document.querySelector(".bt-comment").addEventListener("click", (e) => {
        e.preventDefault()
        let content = $("#w3review").val();
        let idPost = $(".bt-comment").attr('id')
        formData.append("content", content)
        formData.append("star", star)
        formData.append("idPost", idPost)
        if (idPost && star && content) {
            $.ajax({
                url: "/postComment",
                type: "POST",
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    console.log(data)
                    if (data.message == "ok") {
                        swal({
                            title: "Thành công",
                            text: "Thêm comment thành công",
                        })
                        window.location.href = window.location.href
                    } else {
                        if (data.message == "no user") {
                            swal({
                                title: "Thất bại",
                                text: "Vui lòng đăng nhập",
                            })
                        }
                    }
                }
            });
        } else {
            swal({
                title: "Thất bại",
                text: "Vui lòng nhập đầy đủ thông tin",
            })
        }
    })
</script>
</html>
