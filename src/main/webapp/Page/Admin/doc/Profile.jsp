<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.User" %><%--
  Created by IntelliJ IDEA.
  User: 84348
  Date: 1/4/2023
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <meta charset="utf-8">

  <title>Profile</title>
  <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

</head>
<body style="">
<jsp:include page="../Component/header/Header.jsp"/>

<div class="container">
  <br>
  <% User user = (User) request.getAttribute("userInfo") ;%>
  <div class="row flex-lg-nowrap">
    <div class="col-12 col-lg-auto mb-3" style="width: 200px;">

    </div>

    <div class="col">
      <div class="row">
        <div class="col mb-3">
          <div class="card">
            <div class="card-body">
              <div class="e-profile">
                <div class="row">
                  <div class="col-12 col-sm-auto mb-3">
                    <div class="mx-auto" style="width: 140px;">
                      <div class="d-flex justify-content-center align-items-center rounded" style="height: 140px; background-color: rgb(233, 236, 239);">
                        <span style="color: rgb(166, 168, 170); font: bold 8pt Arial;"><img src="<%=user.getAvatar()%>" id="imgAvatar" alt="" width="140px" height="140px" srcset=""></span>
                      </div>
                    </div>
                  </div>
                  <div class="col d-flex flex-column flex-sm-row justify-content-center mb-3">
                    <div class="text-center text-sm-left mb-2 mb-sm-0">
                      <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap"><%=user.getFullName()%></h4>
                      <p class="mb-0">@<%=user.getUserName()%></p>
                      <div class="mt-2">
                        <button class="btn btn-primary" type="button" style="width: 100%; position: relative;">
                          <i class="fa fa-fw fa-camera"></i>
                          <div >
                            <input type="file" id="uploadFile" oninput="handleAvatar(this)" style="position: absolute; top: 0; bottom: 0; left: 0; right: 0; opacity: 0;">
                            <span>Change Photo</span>
                          </div>
                        </button>
                      </div>
                    </div>
                    <div class="text-center text-sm-right">
                      <span class="badge badge-secondary"></span>
                      <div class="text-muted"><small></small></div>
                    </div>
                  </div>
                </div>
                <ul class="nav nav-tabs">
                  <li class="nav-item"><a href="#oder" class="active nav-link" data-toggle="tab">Order</a></li>
                  <li class="nav-item"><a href="#edit" class=" nav-link" data-toggle="tab">Settings</a></li>


                </ul>
                <div class="tab-content pt-3">
                  <div  class="tab-pane " id="edit">
                    <form class="form" novalidate="" style="width: 100%" id="editProfile">
                      <div class="row">
                        <div class="col">
                          <div class="row">
                            <div class="col">
                              <div class="form-group">
                                <label>Full Name</label>
                                <input class="form-control" type="text" name="fullName" placeholder="John Smith" value="<%=user.getFullName()%>">
                              </div>
                            </div>
                            <div class="col">
                              <div class="form-group">
                                <label>Username</label>
                                <input class="form-control" type="text" name="username" disabled placeholder="johnny.s" value="<%=user.getUserName()%>">
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="col">
                              <div class="form-group">
                                <label>Email</label>
                                <input class="form-control" type="text" name="email" placeholder="abc@gmail.com" value="<%=user.getEmail()%>">
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="col">
                              <div class="form-group">
                                <label>Phone</label>
                                <input class="form-control" type="text" name="phone" value="<%=user.getPhone()%>">
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="col">
                              <div class="form-group">
                                <label>Address</label>
                                <input class="form-control" type="text" name="address" placeholder="Phù Cát - Bình Định" value="<%=user.getAddress()%>">
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row " id="changlePass">


                      </div>
                      <div class="row">

                        <div class="col">
                          <div class="form-group">
                            <button class="btn btn-primary" type="button" onclick="openFormChanglePass(this)">Thay đổi mật khẩu</button>
                          </div>
                        </div>

                      </div>
                      <hr>
                      <div class="row">
                        <div class="col d-flex justify-content-end">
                          <button class="btn btn-primary" onclick="formEdit()" type="button">Save Changes</button>
                        </div>
                      </div>
                    </form>

                  </div>
                  <div class="tab-pane active" id="oder">
                    Đơn hàng xử lý
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-12 col-md-3 mb-3">
          <div class="card mb-3">
            <div class="card-body">
              <div class="px-xl-3">
                <button class="btn btn-block btn-secondary">
                  <i class="fa fa-sign-out"></i>
                  <span>Logout</span>
                </button>
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-body">
              <h6 class="card-title font-weight-bold">Support</h6>
              <p class="card-text">Get fast, free help from our friendly assistants.</p>
              <button type="button" class="btn btn-primary">Contact Us</button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>

<style type="text/css">

</style>

<script type="text/javascript">

</script>
<script src="../javascrip/profileEdit.js"></script>
</body></html>