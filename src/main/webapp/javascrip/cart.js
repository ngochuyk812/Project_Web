var arrCart = []

const USDollar = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
});
const render =()=>{

    let tableBody = ``
    let bill = ``
    let total = 0
    arrCart.map((tmp, index)=>{
        total += tmp.post.price * tmp.amount
        tableBody += ` <tr class="rem0" id="item-cart-${tmp.post.idPost}">
                            <td class="invert">${index + 1}</td>
                            <td class="invert-image"><a href="#" ><img src="https://img1.oto.com.vn/crop/575x430/2022/10/21/20221021114014-e7ea_wm.jpeg" alt=" " class="img-responsive"></a></td>
                            <td class="invert">
                                <div class="quantity">
                                    <div class="quantity-select">
                                            <div class="${tmp.amount == 1 ? "entry value-minus events-none" : "entry value-minus"}" id="decrease"  onclick="decrease(${tmp.post.idPost})">&nbsp;</div>
                                       
                                        <div class="entry value"><span class="amount">${tmp.amount}</span></div>
                                            <div class="entry value-plus active" onclick="increase(${tmp.post.idPost})">&nbsp;</div>

                                    </div>
                                </div>
                            </td>
                            <td class="invert">${tmp.post.title}</td>

                            <td class="invert price">${USDollar.format(tmp.post.price * tmp.amount)}</td>
                            <td class="invert">
                                <div class="rem0">
                                    <div class="closeItem closeItem0" onclick="removeCart(${tmp.post.idPost})"> <i class="fas fa-times"></i> </div>
                                </div>

                            </td>
                        </tr>`
        bill += `                            
           <li >${tmp.post.title} <i>-</i> <span>${USDollar.format(tmp.post.price * tmp.amount)}</span></li>
            `
                           
    })
    bill += `<hr>
          <li>Tổng cộng <i>-</i> <span>${USDollar.format(total)}</span></li>`
    document.getElementById("table-item").innerHTML = tableBody
    document.getElementById("list-bill").innerHTML = bill

}
const getCart = ()=>{
    $.ajax({
        url: "/cart?action=listcart",
        type: 'GET',
        success: function(res) {
            arrCart = JSON.parse(res);
            console.log(JSON.parse(res));
            render()
        }
    });
}
getCart();


increase = (idpost) =>{

    var request = new XMLHttpRequest();
    request.open("POST", "cart?action=increase&idpost="+idpost);
    request.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            for( var i = 0; i < arrCart.length; i++){
                if ( arrCart[i].post.idPost === JSON.parse(this.responseText).post.idPost) {
                    arrCart[i] = JSON.parse(this.responseText)
                    console.log(arrCart[i])
                }
            }

            render()
        }
    };
    // var myForm = document.getElementById("myForm");
    // var formData = new FormData(myForm);
    request.send();
}
decrease = (idpost) =>{

    var request = new XMLHttpRequest();
    request.open("POST", "cart?action=decrease&idpost="+idpost);
    request.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            for( var i = 0; i < arrCart.length; i++){
                if ( arrCart[i].post.idPost === JSON.parse(this.responseText).post.idPost) {
                    arrCart[i] = JSON.parse(this.responseText)
                    console.log(arrCart[i])
                }
            }

            render()
        }
    };
    // var myForm = document.getElementById("myForm");
    // var formData = new FormData(myForm);
    request.send();
}

const removeCart = (idpost)=>{
    let new_arr =[]


    console.log(arrCart)

    $.ajax({
        url: "/cart?action=remove&idpost="+idpost,
        type: 'POST',
        success: function(res) {
            console.log()
            if(JSON.parse(res) === 1){
                for( var i = 0; i < arrCart.length; i++){
                    if (arrCart[i].post.idPost == Number(idpost)) {

                        arrCart.splice(i, 1);
                        break
                    }

                }
                render()
            }
        }
    });
}


