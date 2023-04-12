<%@ page import="Model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Product</title>
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="Page/Admin/doc/PostStatus/make/index.css">

    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <!-- or -->
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Scripts/swiper-6.3.3/swiper-bundle.min.css">
    <link rel="stylesheet" href="https://oto.com.vn/Styles/v2.0/common.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Styles/v2.0/theme.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/post_news.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/node_modules/@angular/material/prebuilt-themes/indigo-pink.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <style>
        .fileupload {
            display: none;
        }

        .inp:focus {
            border: 1px solid #56A1EE;
        }
        .group-bt.two-bt .btn-send {
            float: right;
            background: #4DB848;
        }
        .control-last{
            display: flex;
            align-items: center;
        }
        .imgContainer {
            width: 180px;
            height: 134px;
            margin-right: 10px;
        }
        .imgContainer > img {
            width: 100%;
            height: 100%;
            border-radius: 4px;
            border: none;
            overflow: hidden;
        }
        .list-thumb-gallery{
            display: flex !important;
            align-items: center;
            flex-wrap: wrap;
        }



    </style>

</head>

<body onload="time()" class="app sidebar-mini rtl">
<%Product product = (Product) request.getAttribute("product");%>
<!-- Navbar-->
<input id="idpost" hidden value="<%=product.getIdPost()%>"/>
<header class="app-header">
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>

<main class="app-content">
    <div class="row">
        <div class="col-md-12">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="#"><b>Cập nhập thông tin sản phẩm ID: <%=product.getIdPost()%></b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
        </div>
    </div>
    <div class="container">
        <form class="editForm ng-valid ng-dirty ng-touched" novalidate="">
            <div class="new-form-post form-upload container postnews">
                <div class="gr-heading-post"><h2 class="heading step1"> HÌNH ẢNH <span class="note-heading"><i
                        class="icon-dot"></i>0/25 - Bạn có thể đăng tối đa 25 ảnh</span></h2><span
                        class="status-per per1"></span></div>
                <ul class="list-note">
                    <li><i class="icon-ok"></i><span>Đăng ít nhất 03 hình để tin rao hiệu quả hơn.</span></li>
                    <li><i class="icon-ok"></i><span>Ảnh đầu tiên sẽ là ảnh đại diện cho tin của bạn, kéo thả để sắp xếp vị trí ảnh.</span>
                    </li>
                </ul>
                <div class="upload">
                    <div style="text-align: center" class="swiper-container">
                        <ul class="list-thumb-gallery  default-theme" id="uploadimage"><input style="display: none"
                                                                                              multiple=""
                                                                                              class="fileupload input-hidden"
                                                                                              type="file">
                            <li class="upload-item working-upload-item add"><a class="add-img"><i
                                    class="icon-plus"></i><span class="txt-add">Nhấn vào đây để chọn ảnh</span></a>
                            </li>
                        </ul>

                        <div class="swiper-scrollbar"></div>
                    </div>
                </div>

                <%--                <div class="control"><label class="lbl-form" for="inputVideo">Video</label><input id="inputVideo"--%>
                <%--                                                                                                  class="inp ng-untouched ng-pristine ng-valid"--%>
                <%--                                                                                                  formcontrolname="VideoEmbed"--%>
                <%--                                                                                                  name="VideoEmbed"--%>
                <%--                                                                                                  placeholder="Bạn hãy nhập link video Youtube hoặc link video Facebook vào đây"--%>
                <%--                                                                                                  type="text"><!---->--%>
                <%--                    <div class="txt-right"><!----></div>--%>
                <%--                </div>--%>
            </div>
        </form>

        <%--      make--%>
        <%ArrayList< Company> cp = (ArrayList< Company>) request.getAttribute("listCompany");%>
        <form class="editForm ng-pristine ng-invalid ng-touched" novalidate="">
            <div class="container postnews make-form">
                <div class="gr-heading-post"><h2 class="heading "> Thông tin xe </h2><span class="status-per per2"></span>
                </div>
                <div class="control mt-20" style="margin-top: 20px">
                    <label class="lbl-form">Hãng xe</label>
                    <select class="form-select form-select-company" aria-label="Default select example">

                                            <c:forEach items="${listCompany}" var="item">
                            <option ${(item.getId() == product.getIdCompany()) ? 'selected' : ''} value="${item.getId()}">${item.getName()}</option>
                        </c:forEach>


                    </select>


                    <mat-autocomplete class="mat-autocomplete"><!----></mat-autocomplete><!----><!----></div><!----><!---->
                <div class="control year"><label class="lbl-form">Năm sản xuất</label>
                    <select class="form-select form-select-year" aria-label="Default select example">
                        <c:forEach var="i" begin="1900" end="2023">
                            <option ${(i == product.yearOfManuFacture) ? 'selected' : ''} value="${i}">${i}</option>
                        </c:forEach>



                    </select>
                </div>
                <div class="control"><label class="lbl-form sm-lh">Sản xuất</label>
                    <ul class="status-group">

                        <li><input class="hide" id="nn" ${product.made == "Trong nước" ? "checked" : ""} name="made" value="Trong nước" type="radio"><label class="free-label" for="nn">Trong nước</label></li>
                        <li><input class="hide" id="tn" ${product.made == "Ngoài nước" ? "checked" : ""}  name="made" value="Ngoài nước" type="radio"><label class="free-label"
                                                                                                         for="tn">Ngoài nước</label></li>
                    </ul><!----></div><!---->
                <div class="control"><label class="lbl-form sm-lh">Hộp số</label>
                    <ul class="status-group status-group-small">
                        <li><input class="hide" ${product.gear == 0 ? "checked" : ""}   id="transmission1" name="transmission" value="0" type="radio"><label
                                class="free-label" for="transmission1">Số tay</label></li>
                        <li><input class="hide" ${product.gear == 1 ? "checked" : ""} id="transmission2" name="transmission" value="1" type="radio"><label
                                class="free-label" for="transmission2">Số tự động</label></li>

                    </ul><!----></div>
                <div class="control"><label class="lbl-form sm-lh">Nhiên liệu</label>
                    <ul class="status-group group-three">
                        <li><input class="hide" id="fuelType1" ${product.fuel == "Xăng" ? "checked" : ""} name="fuelType" value="Xăng" type="radio"><label class="free-label"
                                                                                                                for="fuelType1">Xăng</label>
                        </li>


                        <li><input class="hide" id="fuelType4" ${product.fuel == "Dầu" ? "checked" : ""} name="fuelType" value="Dầu" type="radio"><label class="free-label"
                                                                                                               for="fuelType4">
                            Dầu </label></li>

                    </ul><!----></div><!---->
                <div class="control"><label class="lbl-form sm-lh">Tình trạng</label>
                    <ul class="status-group">
                        <li><input class="hide" id="old" ${product.status == 1 ? "checked" : ""} name="radio2" value="1" type="radio"><label class="free-label" for="old">Đã
                            qua sử dụng</label></li>
                        <li><input class="hide" id="new" ${product.status == 0 ? "checked" : ""} name="radio2" value="0" type="radio"><label class="free-label"
                                                                                                     for="new">Mới</label></li>
                    </ul><!----></div><!---->
                <div class="control"><label class="lbl-form" for="">Giá bán</label>
                    <div class="my-input-container"><input class="inp ng-pristine ng-invalid error ng-touched" value="<%=product.getPrice()%>"
                                                           formcontrolname="Price" id="Price" maxlength="12"
                                                           placeholder="Nhập giá bán của xe (Đơn vị: triệu VNĐ)"
                                                           style="width: 100%;" type="text"><!----><span class="tmpPrice"
                                                                                                         style="visibility: hidden;"></span>
                    </div><!---->
                    <div class="control"><label class="lbl-form" for="">Số lượng</label>
                        <div class="my-input-container"><input class="inp ng-pristine ng-invalid error ng-touched" value="${product.quantity}"
                                                               formcontrolname="Price" id="quantity" maxlength="12"
                                                               placeholder="Số lượng"
                                                               style="width: 100%;" type="text"><!----><span
                                class="tmpPrice"
                                style="visibility: hidden;"></span>
                        </div>
                        <div class="control"><label class="lbl-form" for="">Kiểu dáng</label>
                            <div class="my-input-container"><input class="inp ng-pristine ng-invalid error ng-touched" value="${product.body}"
                                                                   formcontrolname="Price" id="body" maxlength="12"
                                                                   placeholder="Nhập kiểu dáng xe"
                                                                   style="width: 100%;" type="text"><!----><span
                                    class="tmpPrice"
                                    style="visibility: hidden;"></span>
                            </div><!---->
                            <div class="txt-right"><!---->
                                <div class="msg-error"><span style="display: none"
                                                             class="txt">Vui lòng nhập giá xe đúng định dạng</span></div>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
        <%--      tiltle--%>
        <form class="editForm ng-untouched ng-pristine ng-invalid" novalidate="">
            <div class="container postnews">
                <div class="gr-heading-post"><h2 class="heading "> Tiêu đề - nội dung </h2><span class="status-per per3"></span>
                </div>
                <div class="control mt-20"><label class="lbl-form" for="">Tiêu đề</label>
                    <div class="prefix-title" style="margin: -10px 0 10px 195px"> Toyota Vios 1.5E MT 2017 -</div>
                    <input id="tilte123" class="inp ng-untouched ng-pristine ng-invalid" formcontrolname="Title" value="${product.title}"
                           placeholder="Ngắn gọn, đầy đủ, từ khóa quan trọng gây chú ý " style="margin-left: 190px;"
                           type="text"><!----><!----><!----><!----></div>
                <div class="control box-des"><label class="lbl-form" for="">Nội dung</label><textarea
                        value = ""
                        id="content"
                        class="multi-row ng-untouched ng-pristine ng-invalid" formcontrolname="Description"
                        placeholder="Nhập nội dung. Tối thiểu 50 ký tự. Tối đa 3000 ký tự">${product.content}</textarea><!---->
                    <div class="txt-right"> 0/3000</div><!----><!----><!----></div><!---->

            </div>
        </form>
        <div class="group-bt two-bt" id="btn-postListing">
            <a href="" class="btn-send" id="btn-send">Hoàn tất</a><!----></div>
    </div>


    </div>

</main>
<script src="jsadmin/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="jsadmin/popper.min.js"></script>
<script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
<!--===============================================================================================-->
<script src="jsadmin/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="jsadmin/main.js"></script>
<!--===============================================================================================-->
<script src="jsadmin/plugins/pace.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="jsadmin/plugins/chart.js"></script>
<!--===============================================================================================-->
<script type="text/javascript">
    var data = {
        labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6"],
        datasets: [{
            label: "Dữ liệu đầu tiên",
            fillColor: "rgba(255, 213, 59, 0.767), 212, 59)",
            strokeColor: "rgb(255, 212, 59)",
            pointColor: "rgb(255, 212, 59)",
            pointStrokeColor: "rgb(255, 212, 59)",
            pointHighlightFill: "rgb(255, 212, 59)",
            pointHighlightStroke: "rgb(255, 212, 59)",
            data: [20, 59, 90, 51, 56, 100]
        },
            {
                label: "Dữ liệu kế tiếp",
                fillColor: "rgba(9, 109, 239, 0.651)  ",
                pointColor: "rgb(9, 109, 239)",
                strokeColor: "rgb(9, 109, 239)",
                pointStrokeColor: "rgb(9, 109, 239)",
                pointHighlightFill: "rgb(9, 109, 239)",
                pointHighlightStroke: "rgb(9, 109, 239)",
                data: [48, 48, 49, 39, 86, 10]
            }
        ]
    };
</script>
<script type="text/javascript">
    //Thời Gian
    function time() {
        var today = new Date();
        var weekday = new Array(7);
        weekday[0] = "Chủ Nhật";
        weekday[1] = "Thứ Hai";
        weekday[2] = "Thứ Ba";
        weekday[3] = "Thứ Tư";
        weekday[4] = "Thứ Năm";
        weekday[5] = "Thứ Sáu";
        weekday[6] = "Thứ Bảy";
        var day = weekday[today.getDay()];
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        nowTime = h + " giờ " + m + " phút " + s + " giây";
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = day + ', ' + dd + '/' + mm + '/' + yyyy;
        tmp = '<span class="date"> ' + today + ' - ' + nowTime +
            '</span>';
        document.getElementById("clock").innerHTML = tmp;
        clocktime = setTimeout("time()", "1000", "Javascript");

        function checkTime(i) {
            if (i < 10) {
                i = "0" + i;
            }
            return i;
        }
    }
</script>
</body>
<%--uploadfile--%>
<script>
    document.querySelector(".btn-send").addEventListener("click", (e) => {
        e.preventDefault();
    })
    document.querySelector(".upload-item").addEventListener("click", (e) => {
        document.querySelector(".fileupload").click()
    })
</script
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="Page/Admin/doc/PostStatus/make/index.js"></script>


<script>
    document.querySelector(".btn-send").addEventListener("click", (e) => {
        e.preventDefault();
    })
    var handleItemImg = (e) => {
        var item = e.target.value;
        console.log(item)
        var id = item.getAttribute("id")
        $("#contain" + id).remove();
    }

    var imgRequest = '';
    var listImg = ""
    var count = 0;
    $(".fileupload").bind("change", (e) => {
        var file = document.querySelector(".fileupload").files[0];
        var reader = new FileReader();
        reader.onloadend = function () {
            imgRequest = reader.result
            listImg += reader.result + "||"
            let html = `<div id="contain\${count}" class="imgContainer" style="position: relative">
                  <img id="img\${count}" class="uploadImg" src="\${imgRequest}" alt="Vui lòng chọn ảnh">
                  <i id="\${count}" onClick="()=>{console.log(123)}" style="width: 20px;height: 20px; position: absolute; top: 10px;right: 20px;cursor: pointer; color:red " class="fa-solid fa-trash"></i>
                  <i id="\${count}" onClick="(e)=>handleItemImg(e)" style="width: 20px;height: 20px;position: absolute ;top: 10px;right: 50px; cursor: pointer; color:red" class="fa-solid fa-pen"></i>
                </div>`
            console.log(html)
            $('#uploadimage').append(html)
            count++;
        }
        reader.readAsDataURL(file);
    })
    const getYear = () => {
        let year;
        $(".list-year span").each(function () {
            $(this).each(function (index) {
                if ($(this)[0].querySelector("input").checked) {
                    year = $(this)[0].querySelector("label").textContent

                }
            })

        });
        return year;

    }
    const getStatus = () => {
        let arr = []
        $(".status-group li").each(function () {
            $(this).each(function (index) {
                if ($(this)[0].querySelector("input").checked) {
                    arr.push($(this)[0].querySelector("input").getAttribute("value"))
                }
            })

        });
        return arr
    }

    $("#btn-send").click(function (e){
        e.preventDefault()
        const idpost = $("#idpost").val()
        const arr = getStatus()
        const nameCompany = $('.form-select-company option:selected').val();
        const title = encodeURI($("#tilte123").val())
        const content = encodeURI($("#content").val())
        const images = listImg
        const xmas = new Date("December 25, 2000 23:15:00");

        const yearofmanufacture = $('.form-select-year option:selected').text()
        const made = encodeURI(arr[0])
        const gear = arr[1]
        const fuel = encodeURI(arr[2])
        const status = arr[3]
        const price = $("#Price").val()
        const body = $("#body").val()
        const quantity = $("#quantity").val()
        console.log(status)

        if(nameCompany&&title&&content&&yearofmanufacture&&made&&gear&&fuel&&status&&price&&body&&quantity){
            // if(typeof price==="number"){
            var dataBody={
                nameCompany,
                images,
                title,
                content,
                yearofmanufacture,
                gear,
                fuel,
                price,
                status,
                body,
                made,
                quantity
            }
            console.log(dataBody)
            $.ajax({
                url: "/product?editProduct="+ idpost,
                type: "POST",
                data: dataBody,
                contentType: 'application/x-www-form-urlencoded',
                success: function (data) {
                    console.log(JSON.parse(data) === 1)
                    if(JSON.parse(data) === 1){
                        swal({
                            title: 'Thành công',
                            text: 'Cập nhập thành công sản phẩm ID: ' + idpost,
                            content: "form",
                            buttons: {
                                cancel: "Ok",
                            }
                        }).then((value) => {
                            window.location = "admin?page=productManagement"
                        });
                    }else{
                        swal({
                            title: 'Lỗi',
                            text: 'Có lỗi trong quá trình cập nhập sản phẩm ID: ' + idpost,
                            content: "form",
                            buttons: {
                                cancel: "Ok",
                            }
                        }).then((value) => {
                            window.location = "admin?page=productManagement"

                        });
                    }

                }
            });
            console.log(dataBody )
        }else {
            swal({
                title: 'Lỗi ',
                text: 'Thông tin không chính xác',
                content: "form",
                buttons: {
                    cancel: "Cancel",
                }
            }).then((value) => {
                console.log(value);
            });
        }


    })



</script>
<%--post product--%>

</html>