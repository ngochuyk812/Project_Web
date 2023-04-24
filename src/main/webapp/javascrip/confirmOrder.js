var modal = document.getElementById("myModal");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    document.querySelector("#loading").style.display = "block";
    fee()
}
const fee = ()=>{

    $.ajax({
        url: "/api/address?type=fee",
        type: 'GET',
        success: function(res) {
            console.log(res)
            if(res.includes("loginName")){
                window.location = '/login'

            }
            if(typeof JSON.parse(res) === "number"){
                document.querySelector(".vc_price").textContent = USDollar.format(JSON.parse(res))
               let priceProduct =  document.querySelector(".total_price").innerHTML
                priceProduct = priceProduct.substring(1, priceProduct.length).replace(",","")
                document.querySelector(".total_price").textContent = USDollar.format(JSON.parse(res) + Number(priceProduct))
                modal.style.display = "block";
                document.querySelector(".pttt").value = $("input[name=fav_language]:checked")[0].value
                document.querySelector(".ptvc").textContent = document.querySelector("label[for="+$("input[name=fav_language]:checked")[0].id+"]") .textContent
                document.querySelector("#loading").style.display = "none";

            }

        }
    });
}
span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}