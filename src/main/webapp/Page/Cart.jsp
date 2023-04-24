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
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<%@include file="../Component/loading/Loading.jsp" %>

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
                        <a href="./product" style="text-decoration: none;"><h4>Xem thêm xe</h4></a>
                        <ul id="list-bill">


                        </ul>
                    </div>
                </div>
                <div class="s2" style="border: 1px solid lightgray; flex: 2; padding: 10px;">

                    <h6 style="font-weight: 600; text-align: center;">THÔNG TIN THANH TOÁN</h6>
                    <br/>
                    <form>
                        <div class="info_order" style="padding: 0 30px;">
                            <div class="div_form_name" style="display: flex; justify-content: space-between;">
                                <label class="form-label" for="address"><strong>Họ tên: </strong></label>
                                <span class="form_name"> <span ></span><a style="color: #3399cc; font-weight: 600; cursor: pointer;"> Thay đổi</a></span>
                            </div>
                            <div class="div_edit_name hide" style="display: flex; justify-content: space-between;">
                                <label class="form-label" for="addressEdit"><strong>Họ tên: </strong></label>
                                <p class="edit_name"><input value=""/><a style="color: #3399cc; font-weight: 600; cursor: pointer;"> Lưu</a></p>
                            </div>


                            <div class="div_form_phone" style="display: flex; justify-content: space-between;">
                                <label class="form-label" for="phone"><strong>Số điện thoại: </strong></label>
                                <span  class="form_phone"><span></span><a style="color: #3399cc; font-weight: 600; cursor: pointer;"> Thay đổi</a></span>
                            </div>
                            <div class="div_edit_phone hide" style="display: flex; justify-content: space-between;">
                                <label class="form-label" for="phoneEdit"><strong>Số điện thoại: </strong></label>
                                <p  class="edit_phone"><input value=""/><a style="color: #3399cc; font-weight: 600; cursor: pointer;"> Lưu</a></p>
                            </div>



                            <div class="show_address" style="display: flex; justify-content: space-between;">
                                <label class="form-label" for="address"><strong>Địa chỉ: </strong></label>
                                <span class="form_address"><span></span><a class="edit_address" style="color: #3399cc; font-weight: 600; cursor: pointer;"> Thay đổi</a></span>
                            </div>
                            <div class="form_edit_address hide">
                                <label class="form-label" for="address"><strong>Địa chỉ: </strong></label>
                                <div id="address" style="display: flex; flex-direction: column; ">
                                    <select class="address province">
                                        <option value="0">Tỉnh</option>
                                    </select>
                                    <select class="address district events_none" >
                                        <option value="0">Huyện</option>
                                    </select>
                                    <select class="address ward events_none" style="    margin-bottom: 0;">
                                        <option value="0">Xã</option>
                                    </select>
                                    <a class="save_address" style="color: #3399cc; font-weight: 600; cursor: pointer;">Lưu</a>
                                </div>
                            </div>
                            <div>
                                <label><strong>Ghi chú</strong></label>
                                <textarea style="width: 100%" class="control-custom note_text" rows="3"></textarea>
                            </div>
                            <div>
                                <label><strong>Phương thức thanh toán </strong></label>
                                <br/>
                                <div style="display: flex; justify-content: space-between">
                                    <div><input type="radio" id="default" name="fav_language" value="0" checked>
                                        <label for="default">Thanh toán khi nhận hàng</label><br></div>
                                    <div><input type="radio" id="atm" name="fav_language" value="1">
                                        <label for="atm">Thanh toán online</label><br></div>
                                </div>
                            </div>

                        </div>



                    </form>

                </div>






            </div>
            <div>
<%--                <form action="oder" id="nowpay" method="POST">--%>
<%--                    <a ><input type="submit" value="ĐẶT HÀNG" class="nowpay"  style="margin: 30px; text-align: center; width: 150px; height: 50px; float: right; background-color: #fb383b; border: none; color : white"></a>--%>
<%--                </form>--%>
                <a ><input type="submit" value="ĐẶT HÀNG" class="nowpay" id="myBtn"  style="margin: 30px; text-align: center; width: 150px; height: 50px; float: right; background-color: #135EAC; border: none; color : white"></a>

            </div>

            <div class="clearfix"></div>

        </div>
    </div>
</div>
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content" style="width: 700px">
        <span class="close">&times;</span>
        <div>
            <div class="title_modal">
                <h5> &nbsp XÁC NHÂN ĐƠN HÀNG  &nbsp</h5>
            </div>
            <div class="info_modal">
                <h2>Thông tin nhận hàng</h2>
                <div class="item_info_modal"><strong>Họ và tên: </strong><span class="name_modal"></span></div>
                <div class="item_info_modal"><strong>Số điện thoại: </strong><span class="phone_modal"></span></div>
                <div class="item_info_modal"><strong>Địa chỉ: </strong><span class="address_modal" ></span></div>
            </div>
            <hr/>
            <div class="sp_modal">
                <h2>Sản phẩm</h2>
                <table class="table" style="text-align: center">
                    <thead class="thead-light">
                    <tr style="background-color: lightgray">
                        <th scope="col">#</th>
                        <th scope="col">Sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Giá</th>
                    </tr>
                    </thead>
                    <tbody class="body_table_confirm">


                    </tbody>
                </table>

            </div>
            <div class="total_modal">
                <div style="display: flex; justify-content: space-between"><p><strong>Phương thức thanh toán: </strong></p> <p class="ptvc">Thẻ ngân hàng</p></div>

                <div style="display: flex; justify-content: space-between"><p><strong>Phí vận chuyển: </strong></p> <p class="vc_price"></p></div>
                <div style="display: flex; justify-content: space-between"><p><strong>Tổng cộng: </strong></p> <p class="total_price"></p></div>
                <div>


                <form action="order" method="post">
                    <input class="pttt" name="pttt" value="0" hidden=""/>
                    <input class="note" name="note" value="" hidden=""/>
                    <a><input type="submit" value="THANH TOÁN" class="nowpay" id="payment" style="margin: 30px; text-align: center; width: 150px; height: 50px; float: right; background-color: rgb(19, 94, 172); border: none; color: white; opacity: 1; pointer-events: auto;"></a>

                </form>

                </div>
            </div>
        </div>
    </div>
</div>

</div>
<jsp:include page="../Component/footer/footer.jsp" />
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<script src="../javascrip/cart.js"></script>
<script src="../javascrip/cartInfoUser.js"></script>
<script src="../javascrip/confirmOrder.js"></script>


</html>
