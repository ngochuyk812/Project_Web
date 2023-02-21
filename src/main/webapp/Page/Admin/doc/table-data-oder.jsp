<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="Model.Oder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Danh sách đơn hàng | Quản trị Admin</title>
    <!-- Main CSS-->
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

</head>

<body onload="time()" class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header">
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <%@ include file="./header.jsp" %>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<jsp:include page="SideBar.jsp">
    <jsp:param name="page" value="5"/>
</jsp:include>
<main class="app-content">
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item active"><a href="#"><b>Danh sách đơn hàng</b></a></li>
        </ul>
        <div id="clock"></div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <div class="row element-button">


                        <div class="col-sm-2">
                            <a class="btn btn-excel btn-sm" title="In"><i class="fas fa-file-excel"
                                                                          onclick="exportExel('OderAdmin')"></i> Xuất
                                Excel</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm pdf-file" type="button" title="In"
                               onclick="myFunction(this)"><i
                                    class="fas fa-file-pdf"></i> Xuất PDF</a>
                        </div>

                    </div>
                    <table class="table table-hover table-bordered" id="myTable">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" id="all"></th>
                            <th>ID đơn hàng</th>
                            <th>Khách hàng</th>
                            <th>Đơn hàng</th>
                            <th>Số lượng</th>
                            <th>Tổng tiền</th>
                            <th>Tình trạng</th>
                            <th>Tính năng</th>
                        </tr>
                        </thead>
                        <%ArrayList<Oder> oders = (ArrayList<Oder>) request.getAttribute("oders");%>
                        <tbody>
                        <%
                            System.out.println(oders.size());
                            Locale localeVN = new Locale("vi", "VN");
                            NumberFormat vn = NumberFormat.getInstance(localeVN);
                            for (int i = 0; i < oders.size(); i++) {
                                Oder tmp = oders.get(i);
                        %>
                        <tr>
                            <td width="10"><input type="checkbox" name="check<%=tmp.getId()%>" value="<%=i%>"></td>
                            <td><%=tmp.getId()%>
                            </td>
                            <td><%=tmp.getFullName()%>
                            </td>
                            <td><%=tmp.getProductName()%>
                            </td>
                            <td><%=tmp.getQuantity()%>
                            </td>
                            <td><%=vn.format(tmp.getTotal())%> đ</td>
                            <%
                                String status = "";
                                String badge = "";
                                if (tmp.getStatus() == 0) {
                                    status = "Đang xử lý";
                                    badge = "badge bg-info";
                                }
                                if (tmp.getStatus() == 1) {
                                    status = "Đã hoàn thành";
                                    badge = "badge bg-success";
                                }
                                if (tmp.getStatus() == 2) {
                                    status = "Đã hủy";
                                    badge = "badge badge-danger";
                                }
                            %>
                            <td><span class="<%=badge%>"><%=status%></span></td>
                            <td>
                                <button class="btn btn-primary btn-sm trash" type="button"
                                        onclick="deleteRow(this, <%=tmp.getId()%>)" title="Xóa"><i
                                        class="fas fa-trash-alt"><input hidden value="<%=tmp.getId()%>"/> </i></button>
                                <button class="btn btn-primary btn-sm edit" type="button" data-toggle="modal"
                                        data-target="#ModalUP<%=tmp.getId()%>" title="Sửa"><i class="fa fa-edit"></i>
                                </button>
                                <button class="btn btn-primary btn-sm edit"
                                        style="background-color: #1e7e34; color:white;" type="button"
                                        data-toggle="modal"
                                        data-target="#ModalView<%=tmp.getId()%>" title="Sửa"><i class="fa fa-info"></i>
                                </button>
                            </td>

                        </tr>
                        <div class="modal fade" id="ModalUP<%=tmp.getId()%>" tabindex="-1" role="dialog"
                             aria-hidden="true"
                        >
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">

                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="form-group  col-md-12">
          <span class="thong-tin-thanh-toan">
            <h5>Chỉnh sửa thông tin đơn hàng</h5>
          </span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label class="control-label" name="idpost">Mã đơn hàng </label>
                                                <input class="form-control" disabled value="<%=tmp.getId()%>"
                                                       type="number">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Tên khách hàng</label>
                                                <input class="form-control" value="<%=tmp.getFullName()%>"
                                                       name="fullname" type="text"/>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Tên sản phẩm</label>
                                                <input class="form-control" disabled value="<%=tmp.getProductName()%>"
                                                       name="title" type="text"/>
                                            </div>
                                            <div class="form-group  col-md-6">
                                                <label class="control-label">Số lượng</label>
                                                <input class="form-control" name="quantity"
                                                       value="<%=tmp.getQuantity()%>" type="number" required>
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label class="control-label">Địa chỉ</label>
                                                <input class="form-control" name="address" value="<%=tmp.getAddress()%>"
                                                       type="text">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Phone</label>
                                                <input class="form-control" name="phone" value="<%=tmp.getPhone()%>"
                                                       type="text">
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label class="control-label">Trạng thái</label>
                                                <select class="form-control" name="status">
                                                    <option value="0" <%=tmp.getStatus() == 0 ? "selected" : ""%>>Đang
                                                        xử lý
                                                    </option>
                                                    <option value="1" <%=tmp.getStatus() == 1 ? "selected" : ""%>>Đã
                                                        hoàn thành
                                                    </option>
                                                    <option value="2" <%=tmp.getStatus() == 2 ? "selected" : ""%>>Đã
                                                        hủy
                                                    </option>

                                                </select>
                                            </div>


                                        </div>
                                        <BR>
                                        <BR>
                                        <button class="btn btn-save" type="button" onclick="updateOder(this)">Lưu lại
                                        </button>
                                        <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                                        <BR>
                                    </div>
                                    <div class="modal-footer">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="ModalView<%=tmp.getId()%>" tabindex="-1" role="dialog"
                             aria-hidden="true"
                        >
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">

                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="form-group  col-md-12">
          <span class="thong-tin-thanh-toan">
            <h5>Thông tin chi tiết đơn hàng</h5>
          </span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label class="control-label" name="idpost">Mã đơn hàng </label>
                                                <input class="form-control" disabled value="<%=tmp.getId()%>"
                                                       type="number">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Tên khách hàng</label>
                                                <input class="form-control" value="<%=tmp.getFullName()%>" disabled
                                                       name="fullname" type="text"/>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Địa chỉ</label>
                                                <input class="form-control" name="address" disabled
                                                       value="<%=tmp.getAddress()%>" type="text">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Phone</label>
                                                <input class="form-control" name="phone" disabled
                                                       value="<%=tmp.getPhone()%>" type="text">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Username</label>
                                                <input class="form-control" name="Username" disabled
                                                       value="<%=tmp.getUsername()%>" type="text">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Tên sản phẩm</label>
                                                <input class="form-control" disabled value="<%=tmp.getProductName()%>"
                                                       name="title" type="text"/>
                                            </div>

                                            <div class="form-group  col-md-6">
                                                <label class="control-label">Số lượng</label>
                                                <input class="form-control" name="quantity" disabled
                                                       value="<%=tmp.getQuantity()%>" type="number" required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label">Tổng tiền</label>
                                                <input class="form-control" disabled value="<%=tmp.getTotal()%>"
                                                       name="total" type="text"/>
                                            </div>

                                            <div class="form-group col-md-12">
                                                <label class="control-label">Trạng thái</label>
                                                <input class="form-control" name="status" disabled value="<%=status%>"
                                                       type="text">

                                            </div>


                                        </div>
                                        <BR>
                                        <BR>
                                        <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
                                        <BR>
                                    </div>
                                    <div class="modal-footer">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%}%>

                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
<%--  <div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true"--%>
<%--  >--%>
<%--    <div class="modal-dialog modal-dialog-centered" role="document">--%>
<%--      <div class="modal-content">--%>

<%--        <div class="modal-body">--%>
<%--          <div class="row">--%>
<%--            <div class="form-group  col-md-12">--%>
<%--          <span class="thong-tin-thanh-toan">--%>
<%--            <h5>Chỉnh sửa thông tin đơn hàng</h5>--%>
<%--          </span>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--          <div class="row">--%>
<%--            <div class="form-group col-md-6">--%>
<%--              <label class="control-label" name="idpost"  >Mã đơn hàng </label>--%>
<%--              <input class="form-control" disabled type="number">--%>
<%--            </div>--%>
<%--            <div class="form-group col-md-6">--%>
<%--              <label class="control-label">Tên khách hàng</label>--%>
<%--              <input class="form-control"   name="fullname" type="text" />--%>
<%--            </div>--%>
<%--            <div class="form-group col-md-6">--%>
<%--              <label class="control-label">Tên sản phẩm</label>--%>
<%--              <input class="form-control" disabled  name="title" type="text" />--%>
<%--            </div>--%>
<%--            <div class="form-group  col-md-6">--%>
<%--              <label class="control-label">Số lượng</label>--%>
<%--              <input class="form-control" name="quantity" type="number" required >--%>
<%--            </div>--%>

<%--            <div class="form-group col-md-6">--%>
<%--              <label class="control-label">Địa chỉ</label>--%>
<%--              <input class="form-control" name="address" type="text" >--%>
<%--            </div>--%>
<%--            <div class="form-group col-md-6">--%>
<%--              <label class="control-label">Phone</label>--%>
<%--              <input class="form-control" name="phone" type="text" >--%>
<%--            </div>--%>


<%--          </div>--%>
<%--          <BR>--%>
<%--          <BR>--%>
<%--          <button class="btn btn-save" type="button" onclick="updateBasic()">Lưu lại</button>--%>
<%--          <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>--%>
<%--          <BR>--%>
<%--        </div>--%>
<%--        <div class="modal-footer">--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<!-- Essential javascripts for application to work-->
<script src="jsadmin/popper.min.js"></script>
<script src="jsadmin/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="jsadmin/jquery.table2excel.js"></script>
<script src="jsadmin/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="jsadmin/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<!-- Data table plugin-->
<script type="text/javascript" src="jsadmin/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="jsadmin/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">$('#sampleTable').DataTable();</script>
<script>
    const exportExel = (name) => {
        console.log(name)
        $("#myTable").table2excel({
            name: name,
            filename: "FileExcel",
            fileext: ".xls"
        })

    }
    const updateOder = (e) => {
        let inputs = e.parentElement.querySelectorAll("input")
        let id = inputs[0].value
        let fullName = encodeURI(inputs[1].value)
        let quantity = inputs[3].value
        let address = encodeURI(inputs[4].value)
        let phone = inputs[5].value
        let status = e.parentElement.querySelector("select").value
        let obj = {
            fullName, quantity, address, phone, status
        }
        $.ajax({
            url: "/oder?update=" + id,
            type: "POST",
            contentType: 'application/x-www-form-urlencoded',
            data: obj,
            success: function (data) {
                console.log(data)
                if (JSON.parse(data) === 1)
                    swal("Cập nhập thành công.!", {
                        timer: 1000
                    }).then(() => {
                        window.location = "admin?page=oderManagement"
                    });


            }
        });

        console.log(tr)
        // let arrtd = tr.querySelectorAll("td");
        // console.log(arrtd)
        // let inputs = document.querySelectorAll("#ModalUP input")
        // inputs[0].setAttribute("value", arrtd[1].textContent)
        // inputs[1].setAttribute("value", arrtd[2].textContent)
        // inputs[2].setAttribute("value", arrtd[3].textContent)
        // inputs[3].setAttribute("value", arrtd[4].textContent)
        let timerInterval


    }

    function deleteRow(r, id) {
        var i = r.parentNode.parentNode.rowIndex;
        swal({
            title: "Cảnh báo",
            text: "Bạn có chắc chắn là muốn xóa đơn hàng này?",
            buttons: ["Hủy bỏ", "Đồng ý"],
        }).then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "/oder?delete=" + id,
                    type: "POST",
                    contentType: 'application/x-www-form-urlencoded',
                    success: function (data) {
                        console.log(data)
                        if (JSON.parse(data) === 1)
                            document.getElementById("myTable").deleteRow(i);
                        swal("Đã xóa thành công.!", {});
                    }
                });

            }
        });
    }

    oTable = $('#sampleTable').dataTable();
    $('#all').click(function (e) {
        $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
        e.stopImmediatePropagation();
    });

    //EXCEL
    // $(document).ready(function () {
    //   $('#').DataTable({

    //     dom: 'Bfrtip',
    //     "buttons": [
    //       'excel'
    //     ]
    //   });
    // });


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

    //In dữ liệu
    var myApp = new function () {
        this.printTable = function () {
            var tab = document.getElementById('sampleTable');
            var win = window.open('', '', 'height=700,width=700');
            win.document.write(tab.outerHTML);
            win.document.close();
            win.print();
        }
    }
    //     //Sao chép dữ liệu
    //     var copyTextareaBtn = document.querySelector('.js-textareacopybtn');

    // copyTextareaBtn.addEventListener('click', function(event) {
    //   var copyTextarea = document.querySelector('.js-copytextarea');
    //   copyTextarea.focus();
    //   copyTextarea.select();

    //   try {
    //     var successful = document.execCommand('copy');
    //     var msg = successful ? 'successful' : 'unsuccessful';
    //     console.log('Copying text command was ' + msg);
    //   } catch (err) {
    //     console.log('Oops, unable to copy');
    //   }
    // });


    //Modal
    $("#show-emp").on("click", function () {
        $("#ModalUP").modal({backdrop: false, keyboard: false})
    });
</script>
</body>

</html>