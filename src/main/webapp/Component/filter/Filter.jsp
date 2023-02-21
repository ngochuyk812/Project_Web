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
            <a href="#" data-toggle="collapse" data-target="#collapse_aside1" data-abc="true" aria-expanded="false" class="collapsed">
              <i class="icon-control fa fa-chevron-down"></i>
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
            <a href="#" data-toggle="collapse" data-target="#collapse_aside2" data-abc="true" aria-expanded="false" class="collapsed">
              <i class="icon-control fa fa-chevron-down"></i>
              <h6 class="title">Giá ( triệu ) </h6>
            </a>
          </header>
          <div class="filter-content collapse show" id="collapse_aside2" style="">
            <div class="card-body">
              <div class="price-input">
                <div class="field">
                  <span>Min</span>
                  <input type="number" class="input-min" value="0">
                </div>
                <div class="separator">-</div>
                <div class="field">
                  <span>Max</span>
                  <input type="number" class="input-max" value="2000">
                </div>
              </div>

            </div>
          </div>
        </article>
        <article class="filter-group">
          <header class="card-header">
            <a href="#" data-toggle="collapse" data-target="#collapse_aside3" data-abc="true" aria-expanded="false" class="collapsed">
              <i class="icon-control fa fa-chevron-down"></i>
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
            <a href="#" data-toggle="collapse" data-target="#collapse_aside4" data-abc="true" class="collapsed" aria-expanded="false">
              <i class="icon-control fa fa-chevron-down"></i>
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

