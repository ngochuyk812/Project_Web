<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Scripts/swiper-6.3.3/swiper-bundle.min.css">
    <link rel="stylesheet" href="https://oto.com.vn/Styles/v2.0/common.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/Styles/v2.0/theme.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/post_news.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/member/Styles/web/postnew-quick.css?v=638035266443576953">
    <link rel="stylesheet" href="https://oto.com.vn/node_modules/@angular/material/prebuilt-themes/indigo-pink.css">
    <style>
        <%@include file="index.css"%>
    </style>
</head>
<body>
<form class="editForm ng-untouched ng-pristine ng-invalid" novalidate="">
    <div class="container postnews">
        <div class="gr-heading-post"><h2 class="heading "> Tiêu đề - nội dung </h2><span class="status-per per3"></span>
        </div>
        <div class="control mt-20"><label class="lbl-form" for="">Tiêu đề</label>
            <div class="prefix-title" style="margin: -10px 0 10px 195px"> Toyota Vios 1.5E MT 2017 -</div>
            <input id="tilte123" class="inp ng-untouched ng-pristine ng-invalid" formcontrolname="Title"
                   placeholder="Ngắn gọn, đầy đủ, từ khóa quan trọng gây chú ý " style="margin-left: 190px;"
                   type="text"><!----><!----><!----><!----></div>
        <div class="control box-des"><label class="lbl-form" for="">Nội dung</label><textarea
                id="content"
                class="multi-row ng-untouched ng-pristine ng-invalid" formcontrolname="Description"
                placeholder="Nhập nội dung. Tối thiểu 50 ký tự. Tối đa 3000 ký tự"></textarea><!---->
            <div class="txt-right"> 0/3000</div><!----><!----><!----></div><!---->

    </div>
</form>
</body>
<script src="js/jquery-3.2.1.min.js"></script>

</html>
