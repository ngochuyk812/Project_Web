<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    <%@include file="./filter.css" %>
  </style>
</head>


<div class="" style="border: 1px solid #E1E1E1">


  <div class="">

    <div class="">


      <div class="card">
        <article class="filter-group">
          <header class="card-header">
            <a  class="collapsed" >
              <h6 class="title">Hãng xe </h6>
            </a>
          </header>
          <div class="filter-content collapse show" id="collapse_aside1" style="">
            <div class="card-body">
              <select id="company" class="select-filter">

              </select>

            </div>
          </div>
        </article>
        <article class="filter-group">
          <header class="card-header">
            <a  class="collapsed" >
              <h6 class="title">Giá ( triệu ) </h6>
            </a>
          </header>
          <div class="group">
            <div class="progress"></div>
            <div class="range-input">
              <input class="range-min" max="50000" type="range" value="0">

              <input class="range-max" max="50000" type="range" value="50000">
            </div>
            <div class="range-text">
              <div class="text-min">0</div>
              <div class="text-max">50.000</div>
            </div>
          </div>
        </article>
        <article class="filter-group">
          <header class="card-header">
            <a   class="collapsed" >
              <h6 class="title">Năm sản xuất </h6>
            </a>
          </header>
          <div class="filter-content collapse show" id="collapse_aside3" style="">
            <div class="card-body">
              <select id="year" class="select-filter">

              </select>

            </div>
          </div>
        </article>
        <article class="filter-group">
          <header class="card-header">
            <a  class="collapsed" >
              <h6 class="title">Nhiên liệu </h6>
            </a>
          </header>
          <div class="filter-content collapse show" id="collapse_aside4" style="">
            <div class="card-body">
              <label class="custom-control">
                <input type="checkbox" id="xang"  value="Xăng" class="custom-control-input">
                <div class="custom-control-label">Xăng  </div>
              </label>

              <label class="custom-control">
                <input type="checkbox"  id="dau" value="Dầu" class="custom-control-input">
                <div class="custom-control-label">Dầu </div>
              </label>


            </div>
          </div>
        </article>
      </div>

    </div>

  </div>
</div>
<script>
  <%@include file="./filter.js" %>
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

