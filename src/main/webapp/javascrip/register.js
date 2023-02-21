
document.querySelector("#bt-register").addEventListener("click", ()=>{
    let checkData = true;
    let notifyNote= '';
    var getInputs = document.querySelectorAll("#formRegister input")
    console.log(getInputs)
    var obj = {}
    getInputs.forEach(tmp=>{
        let key =  tmp.getAttribute("id")
        if(tmp.value === ""){
            document.querySelector(`#${key}`).style.outline = "1px solid red"
            checkData = false;
            if(notifyNote === ''){
                notifyNote+= "- Input not null \n"
            }
        }else{
            document.querySelector(`#${key}`).style.outline = "none"
        }
        obj[key] = encodeURI( tmp.value)
    })
    obj["captcha"] = encodeURI(document.querySelector("#g-recaptcha-response").value    )
    var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if(!mailformat.test(obj.email)){
        document.querySelector(`#email`).style.outline = "1px solid red"
        checkData= false;
        notifyNote += "- Invalid email \n"

    }
    if(obj.phone.length < 10){
        document.querySelector(`#phone`).style.outline = "1px solid red"
        checkData= false;
        notifyNote += "- Invalid phone \n"

    }
    if(obj.password !== obj.repeatPassword ){
        document.querySelector(`#password`).style.outline = "1px solid red"
        document.querySelector(`#repeatPassword`).style.outline = "1px solid red"
        checkData= false;
        notifyNote += "- Re-enter incorrect password \n"

    }

    if(obj.password.length < 8){
        document.querySelector(`#password`).style.outline = "1px solid red"
        document.querySelector(`#repeatPassword`).style.outline = "1px solid red"
        checkData= false;
        notifyNote += "- Password must not be less than 8 characters \n"
    }

    if(obj.captcha === ''){
        checkData= false;
        notifyNote += "- Verify Captcha \n"
    }


    if(!checkData) {

        notify("Error", notifyNote)
        return;
    }
     document.querySelector("#loading").style.display = "block";


    $.ajax({
        url: "/register",
        method: "POST",
        data: obj,
        success: res =>{
            console.log(res.responseText)
            console.log(typeof res.responseText)

            notify("Success", "Đăng ký thành công vui lòng vào mail để xác nhận trước khi đăng nhập")

        },
        error: err=>{

            notify("Error", err.responseText)

        }
    })

})

const notify = (type,mess) =>{
    document.querySelector("#loading").style.display = "none";

    swal({
        title: type ,
        text: mess,
        icon: type.toLowerCase(),

    }).then(rs=>{

        if(type.trim().toLowerCase() === "success"){
            location.href = "/login"
        }
    });

}