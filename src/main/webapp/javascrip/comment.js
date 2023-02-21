const comment = ()=>{
    var star=0;
    document.querySelectorAll(".stars input").forEach((item,index)=>{
        item.addEventListener("click",()=>{
            star=5-index;
            console.log(star)
        })
    })
    document.querySelector( ".bt-comment").addEventListener("click",(e)=>{
        e.preventDefault()
        let content= encodeURI($("#w3review").val())
        let dateObj = new Date();
        let month = dateObj.getUTCMonth() + 1; //months from 1-12
        let day = dateObj.getUTCDate();
        let year = dateObj.getUTCFullYear();
        let newdate = year + "-" + month + "-" + day
        let idPost = $(".bt-comment input").val()
        console.log(idPost,content,newdate)
        if(idPost&&star&&content&&newdate){

            const dataBody={
                content,
                newdate,
                idPost,
                star,
            }
            $.ajax({
                url: "/postComment",
                type: "POST",
                data: dataBody,
                contentType: 'application/x-www-form-urlencoded',
                success: function (data) {
                    getCmt()
                }
            });
        }

    })
}
comment()
const getCmt = ()=>{
    let idPost = $(".bt-comment input").val()
    $.ajax({
        url: "/postComment?id="+idPost,
        type: "GET",

        contentType: 'application/x-www-form-urlencoded',
        success: function (data) {
            render(JSON.parse(data))

        }
    });
}
const render = (arr)=>{
    let rs = ``
    console.log(arr)
    document.querySelector("#countCmt").textContent = arr.length
    arr.map((tmp)=>{
        let start = ``
        rs+=`<div class="cmt">
                            <div class="start-cmt">`
        for (let i = 0; i < tmp.star; i++) {
            rs +=`<i class="fa-solid fa-star"></i>`
        }
        rs+=`
                                
                            </div>
                            <p>từ <b>${tmp.username}</b>, <span class="cl-lg">${tmp.time}</span></p>
                            <p>${decodeURI(tmp.comment)}</p>
                        </div>
`
    })
    console.log(rs)
    document.querySelector(".list-comment").innerHTML = rs

}
getCmt()