<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Product" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="Model.Company" %>
<%@ page import="DAO.ProductDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Danh sách nhân viên | Quản trị Admin</title>
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
<%ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products"); %>
<%ArrayList<Company> list = (ArrayList<Company>)request.getAttribute("list") ;%>
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
                <li class="breadcrumb-item active"><a href="#"><b>Danh sách sản phẩm</b></a></li>
            </ul>
            <div id="clock"></div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <div class="row element-button">
                            <div class="col-sm-2">
              
                              <a class="btn btn-add btn-sm" href="admin?page=post" title="Thêm"><i class="fas fa-plus"></i>
                                Tạo mới sản phẩm</a>
                            </div>

                            <div class="col-sm-2">
                              <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất Excel</a>
                            </div>
                            <div class="col-sm-2">
                              <a class="btn btn-delete btn-sm pdf-file" type="button" title="In" onclick="myFunction(this)"><i
                                  class="fas fa-file-pdf"></i> Xuất PDF</a>
                            </div>

                          </div>
                        <table class="table table-hover table-bordered" id="myTable">
                            <thead>
                                <tr>
                                    <th width="10"><input type="checkbox" id="all"></th>
                                    <th>Mã sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Ảnh</th>
                                    <th>Số lượng</th>
                                    <th>Tình trạng</th>
                                    <th>Giá tiền</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>

                            <%
                                ProductDAO productDAO = new ProductDAO();

                                Locale localeVN = new Locale("vi", "VN");
                                NumberFormat vn = NumberFormat.getInstance(localeVN);
                                double doubleNumber1 = 10.17d;

                                for(int i = 0; i < products.size();i++){
                                Product tmp = products.get(i);
                                int quantityProduct = productDAO.getQuantityProduct(tmp.getId());
                                String status;
                                if (quantityProduct > 0){
                                    status = "Còn hàng";
                                }else {
                                    status = "Hêt hàng";
                                }
                            %>
                            <tr id="row<%=tmp.getId()%>">
                                <td width="10"><input type="checkbox" name="check<%=i + 1%>" value="<%=i + 1%>"></td>
                                <td><%=tmp.getId()%></td>
                                <td><%=tmp.getName()%></td>
                                <td><img src="<%=tmp.getImages().get(0)%>" alt="" width="100px;"></td>
                                <td><%=quantityProduct%></td>
                                <td><span class= " <%=(quantityProduct> 0) ? "badge bg-success": "badge bg-info"%>"><%=(quantityProduct> 0) ? "Còn hàng":"Hết hàng"%></span></td>
                                <td><%=vn.format(tmp.getPrice())%> USD</td>

                                <td><button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                                            onclick="deleteRow(this, <%=tmp.getId()%>)"><i class="fas fa-trash-alt"></i>
                                </button>
                                <form action="admin?action=editproduct&id=<%=tmp.getId()%>" method="POST" >
                                    <input name="id" value="<%=tmp.getId()%>" hidden>
                                    <button class="btn btn-primary btn-sm edit"  type="submit" title="Sửa"   id="show-emp<%=tmp.getId()%>" data-toggle="modal"
                                            data-target="#ModalUP2"><i class="fas fa-edit"></i></button>
                                </form>
                                </td>
                            </tr>
                            <%}%>
                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>

<!--
  MODAL
-->

<div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true"
>
<div class="modal-dialog modal-dialog-centered" role="document">
  <div class="modal-content">

    <div class="modal-body">
      <div class="row">
        <div class="form-group  col-md-12">
          <span class="thong-tin-thanh-toan">
            <h5>Chỉnh sửa thông tin sản phẩm</h5>
          </span>
        </div>
      </div>
      <div class="row">
        <div class="form-group col-md-6">
            <label class="control-label" name="idpost"  >Mã sản phẩm </label>
            <input class="form-control" disabled type="number">
          </div>
        <div class="form-group col-md-6">
            <label class="control-label">Tên sản phẩm</label>
          <input class="form-control"  name="title" type="text" />
        </div>
        <div class="form-group  col-md-6">
            <label class="control-label">Số lượng</label>
          <input class="form-control" name="quantity" type="number" required >
        </div>

          <div class="form-group col-md-6">
            <label class="control-label">Giá bán</label>
            <input class="form-control" name="price" type="text" >
          </div>


      </div>
      <BR>
      <a href="#" style="    float: right;
    font-weight: 600;
    color: #ea0000;">Chỉnh sửa sản phẩm nâng cao</a>
      <BR>
      <BR>
      <button class="btn btn-save" type="button" onclick="updateBasic()">Lưu lại</button>
      <a class="btn btn-cancel" data-dismiss="modal" href="#">Hủy bỏ</a>
      <BR>
    </div>
    <div class="modal-footer">
    </div>
  </div>
</div>
</div>
<!--
MODAL
-->

    <!-- Essential javascripts for application to work-->
    <script src="jsadmin/jquery-3.2.1.min.js"></script>
    <script src="jsadmin/popper.min.js"></script>
    <script src="jsadmin/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="src/jquery.table2excel.js"></script>
    <script src="jsadmin/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="jsadmin/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
    <!-- Data table plugin-->
    <script type="text/javascript" src="jsadmin/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="jsadmin/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">



        $('#sampleTable').DataTable();
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
    <script>
        function deleteRow(r,idPost) {
            var i = r.parentNode.parentNode.rowIndex;
            swal({
                title: "Cảnh báo",
                text: "Bạn có chắc chắn là muốn xóa sản phẩm này?",
                buttons: ["Hủy bỏ", "Đồng ý"],
            })
                .then((willDelete) => {
                    if (willDelete) {
                        $.ajax({
                            url: "/product?action=delete&&id="+idPost,
                            type: "POST",
                            contentType: 'application/x-www-form-urlencoded',
                            success: function (data) {
                                console.log(data)
                                if( JSON.parse(data) === 1)
                                    document.getElementById("myTable").deleteRow(i);

                            }
                        });
                        swal("Đã xóa thành công.!", {

                        });
                    }
                });
        }

        oTable = $('#sampleTable').dataTable();
        $('#all').click(function (e) {
            $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
            e.stopImmediatePropagation();
        });

    </script>
</body>

</html>