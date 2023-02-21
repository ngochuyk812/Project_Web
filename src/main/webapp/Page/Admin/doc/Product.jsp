<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://staticfile.oto.com.vn/dist/web/styles/theme.min.css.gz?v=638031797131449271">
    <link rel="stylesheet"
          href="https://staticfile.oto.com.vn/dist/web/styles/listtinrao.min.css.gz?v=638031797131449271">
    <link rel="stylesheet" href="https://staticfile.oto.com.vn/dist/web/styles/fillte.min.css.gz?v=638031797131449271">
    <link rel="stylesheet" href="https://staticfile.oto.com.vn/dist/web/styles/footer.min.css.gz?v=638031797131449271">
    <link rel="stylesheet"
          href="https://staticfile.oto.com.vn/dist/web/styles/css-boxright.min.css.gz?v=638031797131449271">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <style>
        <%@include file="product.css" %>
    </style>
</head>
<body>
<jsp:include page="../Component/header/Header.jsp"/>
<div class="container">
    <jsp:include page="/Component/header/FilterHeader.jsp"/>
    <div class="container-content">
        <div class="Container-right">
            <h2 class="heading-c">Phiên bản ô tô nổi bật</h2>
            <div class="box-content-c">
                <ul class="list-link-c">
                    <li><a href="/Page/Product.jsp" title="Toyota Vios MT "><h3>Toyota Vios MT </h3></a></li>
                    <li><a href="/Page/Product.jsp" title="Mua xe Mazda 3"><h3>Mua xe Mazda 3</h3></a></li>
                    <li><a href="/Page/Product.jsp" title="Bán xe Mazda CX5"><h3>Bán xe Mazda CX5</h3></a></li>
                    <li><a href="/Page/Product.jsp" title="Bán xe Kia Cerato"><h3>Bán xe Kia Cerato</h3></a></li>
                    <li><a href="/Page/Product.jsp" title="Mua xe Kia Morning"><h3>Mua xe Kia Morning</h3></a>
                    </li>
                    <li><a href="/Page/Product.jsp" title="Bán xe Honda City "><h3>Bán xe Honda City </h3></a></li>
                    <li><a href="/Page/Product.jsp" title="Mua xe Honda Civic"><h3>Mua xe Honda Civic</h3></a>
                    </li>
                    <li><a href="/Page/Product.jsp" title="Mua xe Ford Ranger "><h3>Mua xe Ford Ranger </h3></a>
                    </li>
                    <li><a href="/Page/Product.jsp" title="Bán xe Toyota Vios"><h3>Bán xe Toyota Vios</h3></a>
                    </li>
                    <li><a href="/Page/Product.jsp" title="Giá xe Hyundai i10 "><h3>Mua xe Hyundai i10</h3>
                    </a>
                    </li>
                    <li><a href="/Page/Product.jsp" title="Mua xe Hyundai Accent"><h3>Mua xe Hyundai
                        Accent</h3>
                    </a></li>
                </ul>
            </div>

        </div>
        <div class="Container-left">
            <h1 class="heading mb-10">Mua bán xe ô tô mới</h1>

            <%--            ListItem--%>
            <div class="box-list-car" id="box-list-car">
                <c:forEach begin="0" end="99">
                    <%@include file="../Component/Item/Filter/FilterProduct.jsp" %>
                </c:forEach>

            </div>
            <div class="loading">
                <div class="hide spinner-grow" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <div class="wrap-load-more set-relative"><span class="btn-loadmore" onclick="ListAuto.getData()">Hiển thị thêm</span>
                <div class="lds-ellipsis sa-loadmore visibility-hidden">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
            <div class="description-explain"><span class="first-font explain-test">*Ghi chú:</span> <span
                    class="explain-test"> <strong> “Salon chính hãng”</strong> là danh hiệu dành cho tin rao được đăng bán bởi Salon chính hãng hoặc Salon tư nhân nhập khẩu có ủy quyền, đã được xác thực bởi Oto.com.vn.
            </span> <span class="explain-test"> <strong>“Salon nổi bật”</strong> là danh hiệu dành cho tin rao được đăng bán bởi Salon xe cũ, được Oto.com.vn đánh giá là hoạt động tích cực,&nbsp;dựa trên số lượng xe đang rao bán và xe đã giao dịch thành công trong 2 tháng gần nhất.
            </span></div>
        </div>

    </div>
</div>

</div>

</div>
<jsp:include page="../Component/footer/footer.jsp"/>
</body>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myList li").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
<script src="../EventJs/ReloadListitem.js">
</script>
<script src="../EventJs/FilterBrand.js">
</script>
</html>