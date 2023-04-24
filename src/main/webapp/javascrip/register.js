
document.querySelector("#bt-register").addEventListener("click", ()=>{
    let checkData = true;
    let notifyNote= '';
    var getInputs = document.querySelectorAll("#formRegister input")
    getInputs.forEach(tmp=>{
        tmp.style.outline = "none"
    })
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
    let province = document.querySelector(".province")
    let district = document.querySelector(".district")
    let ward = document.querySelector(".ward")

    if(province.value === '0' || district.value === '0' || ward.value === '0' ){
        console.log(province.value,district.value,ward.value)
        if(province.value === '0')
            province.style.outline = "1px solid red"
        else
            province.style.outline = "none"

        if(district.value === '0')
            district.style.outline = "1px solid red"
        else
            district.style.outline = "none"
        if(ward.value === '0')
            ward.style.outline = "1px solid red"
        else
            ward.style.outline = "none"
        checkData= false;
        notifyNote += "- Invalid address \n"


    }
    obj['address'] =  $( ".ward option:selected" ).text() + " - " + $( ".district option:selected" ).text() + " - " + $( ".province option:selected" ).text();
    console.log(obj.address)
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
        grecaptcha.reset();

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
            grecaptcha.reset();

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
var provinceData =''
const initProvince =()=>{
    provinceData = JSON.parse(provinceData)
    let elmProvince = document.querySelector(".province")
    provinceData = JSON.parse(provinceData)
    console.log(provinceData)
    provinceData.original.data.map(tmp=>{

        elmProvince.innerHTML += `<option value=${tmp.ProvinceID}>${tmp.ProvinceName}</option>`
    })

}
const elmProvince = document.querySelector(".province")
elmProvince.addEventListener("input",(e)=>{
    if(e.target.value != '0'){
        let idProvince = e.target.value
        console.log(idProvince)
        let district = document.querySelector(".district")
        district.classList.remove('events_none')
        let ward = document.querySelector(".ward")
        ward.innerHTML = `<option value="0">Xã</option>`

        ward.classList.add('events_none')

        initDistrict(idProvince)
    }else{
        console.log(e.target.value)
        let district = document.querySelector(".district")
        district.innerHTML = `<option value="0">Huyện</option>`
        district.classList.add('events_none')
        let ward = document.querySelector(".ward")
        ward.innerHTML = `<option value="0">Xã</option>`

        ward.classList.add('events_none')


    }
})
document.querySelector(".district").addEventListener("input",(e)=>{
    console.log(e)
    if(e.target.value != '0'){
        let idDistrict = e.target.value
        console.log(idDistrict)
        let ward = document.querySelector(".ward")
        ward.classList.remove('events_none')
        ward.setAttribute('value', '0')
        initWard(idDistrict)
    }else{
        let ward = document.querySelector(".ward")
        ward.innerHTML = `<option value="0">Xã</option>`
        ward.classList.add('events_none')

    }
})
const initDistrict = (idProvince) =>{
    $.ajax({
        url: "/api/address?type=district&idProvince="+idProvince,
        method: "GET",
        success: res =>{
            districtData= JSON.parse(JSON.parse(res))
            console.log(districtData)
            let district = document.querySelector(".district")
            let elms = `<option value="0">Huyện</option>`

            districtData.original.data.map(tmp=>{
                console.log(tmp)
                elms += `<option value=${tmp.DistrictID}>${tmp.DistrictName}</option>`
            })
            district.innerHTML =elms
        },
        error: err=>{



        }
    })
}
const initWard = (idDistrict)=>{
    $.ajax({
        url: "/api/address?type=ward&idDistrict="+idDistrict,
        method: "GET",
        success: res =>{
            dataWard= JSON.parse(JSON.parse(res))
            console.log(dataWard)
            let ward = document.querySelector(".ward")
            let elms = `<option value="0">Xã</option>`

            dataWard.original.data.map(tmp=>{
                console.log(tmp)
                elms += `<option value=${tmp.WardCode}>${tmp.WardName}</option>`
            })
            ward.innerHTML = elms
        },
        error: err=>{



        }
    })
}
const province = ()=>{
    $.ajax({
        url: "/api/address?type=province",
        method: "GET",
        success: res =>{
            provinceData = res
            initProvince()
        },
        error: err=>{



        }
    })
}
province()
