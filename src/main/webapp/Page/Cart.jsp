<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Cart" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        <%@include file="cart.css" %>
    </style>
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="../Component/header/Header.jsp"/>
<br>
<div class="ads-grid_shop container" id="cartPage">
    <div class="shop_inner_inf">
        <div class="privacy about">

            <div class="checkout-right">
                <h4 class="title-checkout-right"></h4>
                <table class="timetable_sub">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Hình ảnh</th>
                        <th>Số lượng</th>
                        <th>Tên</th>

                        <th>Giá</th>
                        <th>Xoá</th>
                    </tr>
                    </thead>
                    <tbody id="table-item">

                    </tbody>
                </table>
            </div>
            <div class="checkout-left">
                <div class=" checkout-left-basket" style="width: 100%;">
                    <div class="s1" >
                        <a href="./" style="text-decoration: none;"><h4>Xem thêm xe</h4></a>
                        <ul id="list-bill">


                        </ul>
                    </div>
                </div>
                <div class="s2" style="border: 1px solid lightgray; flex: 2; padding: 10px;">

                    <h2>Ghi chú</h2>
                    <form>

                            <textarea style="width: 100%" class="control-custom" rows="5"></textarea>

                    </form>

                </div>





            </div>
            <div>
                <form action="oder" method="POST">
                    <a ><input type="submit" value="ĐẶT HÀNG" class="nowpay"  style="margin: 30px; text-align: center; width: 150px; height: 50px; float: right; background-color: #fb383b; border: none; color : white"></a>
                </form>
            </div>

            <div class="clearfix"></div>

        </div>
    </div>
</div>
<jsp:include page="../Component/footer/footer.jsp" />
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="../javascrip/cart.js"></script>
</html>
