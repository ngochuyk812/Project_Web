var dataFilter = []
var dataCompany = []
var indexNumber = 1;
var quantityProductOnPage = 10
var dataAll = []
const changeNumberPage = (n)=>{
    indexNumber = n
    window.scrollTo(0, 300);
    showData(dataFilter)
}
const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
const showData = (arr) =>{
    console.log(indexNumber)
    let rs =``
    let number = arr.length/quantityProductOnPage
    let numberPage = ` <a >&laquo;</a>`

    for (let i = 1; i <= number; i++) {
        i === indexNumber ? numberPage += `<a class="active" onclick="changeNumberPage(${i})" >${i}</a>` : numberPage += `<a onclick="changeNumberPage(${i})" >${i}</a>`

    }
    numberPage += `<a >&raquo;</a>`
    if(number === 0){
        numberPage =``
    }
    arr.forEach((tmp, index)=>{
        if(index <= indexNumber * quantityProductOnPage && index >= (indexNumber - 1) *  quantityProductOnPage){
            rs += `
        <div class="item-car vipprior dev-item-car" >
           <button class="button-item-car" onclick="addToCart(${tmp.idPost})">Thêm giỏ hàng</button>
  <div class="photo"><a href="details?id=${tmp.idPost}" class="rt pdt-per-74">
    
    <img class="lozad" src="${tmp.images.split("||")[0]}" width="230" height="172" data-loaded="true"> </a> <span
          class="box-icon"> </span></div>
  <div class="info">
    <h3 class="title ">
      <a href="details?id=${tmp.idPost}">
    ${tmp.title}
      </a>
    </h3>
    <p class="price redprice">${VND.format(tmp.price)} triệu<br>
      <span class="old-price">140 triệu</span>
      <span class="b-promotion">Tiền mặt</span></p>
    <ul class="info-car">
      <li>${tmp.yearOfManuFacture}</li>
      <li>${tmp.status === 0 ? "Chưa sử dụng" : "Đã qua sử dụng"}</li>
      <li>${tmp.coverNumber === 0 ? "Số tay" : "Số tự động"}</li>
      
    </ul>
    <ul class="info-car">
      <li>Nhiên liệu: <b>${decodeURI(tmp.fuel)}</b></li>
      
    </ul>   
      
      
  </div>
</div>
        `
        }
    })
    document.querySelector("#box-list-car").innerHTML = rs
    document.querySelector(".pagination").innerHTML =numberPage
}
const handleFilter = ()=>{
    indexNumber = 1
    let companySel =  document.querySelector("#company").value
    let year =  document.querySelector("#year").value
    dataFilter = dataAll

    if(companySel === "all"){
        dataFilter = dataAll
    }else{
        dataFilter = dataAll.filter((tmp)=>{
            return Number(companySel) === tmp.idCompany
        })
    }
    let priceMin = document.querySelector(".input-min").value
    let priceMax = document.querySelector(".input-max").value
    dataFilter = dataFilter.filter((tmp)=>{
        return Number(priceMin)*1000000<= tmp.price && Number(priceMax)*1000000 >= tmp.price
    })
    console.log(parseInt(year))

    if(year === "all"){
        dataFilter = dataFilter
    }else{
        dataFilter = dataFilter.filter((tmp)=>{
            return parseInt(year) === tmp.yearOfManuFacture

        })
    }

    let xang = document.querySelector('#xang')
    let dau = document.querySelector('#dau')

    if(xang.checked){

        console.log(xang.checked, xang.value)
        dataFilter = dataFilter.filter((tmp)=>{
            console.log(
                decodeURI(tmp.fuel)
            )
            return decodeURI(tmp.fuel) === xang.value
        })
    }
    if(dau.checked){

        xang.checked = false

        dataFilter = dataFilter.filter((tmp)=>{
            return decodeURIComponent(tmp.fuel) === dau.value
        })
    }
    console.log(year, companySel,)

    showData(dataFilter)
}
document.querySelector('#xang').addEventListener("input", ()=>{
    {document.querySelector('#xang').checked ? document.querySelector('#dau').checked = false : ""}
})
document.querySelector('#dau').addEventListener("input", ()=>{
    {document.querySelector('#dau').checked ? document.querySelector('#xang').checked = false : ""}
})
const getCompany = ()=>{
    $.ajax({
        url: "/company",
        type: 'GET',
        success: function(res) {
            dataCompany = JSON.parse(res)
            console.log(dataCompany)
            let compantElm = document.querySelector("#company")
            let rs = `<option value="all">Tất cả</option>`
            dataCompany.map((tmp)=>{
                rs += `<option value="${tmp.id}">${tmp.name}</option>`
            })
            compantElm.innerHTML = rs
            compantElm.addEventListener(("input"), handleFilter)

        }
    });
}
const init = ()=>{
    $.ajax({
        url: "/product?action=getlistproduct",
        type: 'GET',
        success: function(res) {
            dataFilter = JSON.parse(res)
            dataAll =JSON.parse(res)
            showData(JSON.parse(res))
            getCompany()
           document.querySelector(".input-min").addEventListener("blur", handleFilter)
            document.querySelector(".input-max").addEventListener("blur", handleFilter)
            let year = `<option value="all">Tất cả</option>`
            for (let i = 1990; i < 2024; i++) {
                year += `<option value="${i}">${i}</option>`
            }
            document.querySelector("#year").innerHTML = year
            document.querySelector("#year").addEventListener("input", handleFilter)
            document.querySelectorAll('#collapse_aside4 input').forEach((tmp)=>{
                tmp.addEventListener("input", handleFilter)
            })
        }
    });
}
const addToCart = (id)=>{
    $.ajax({
        url: "/cart?action=addtocart&idpost="+id,
        type: 'POST',
        success: function(res) {
            console.log(res)
            swal("Đã thêm sản phẩm vào giỏ hàng", {
                buttons: false,
                timer: 500,
            });
            if(JSON.parse(res) === 0){
                window.location = "/Page/Login_Register.jsp"
            }


        }
    });
    console.log(id)


}
init()

