let navbarToggle = document.querySelector(".navbar-toggle")
let mainNav = document.querySelector(".main-nav")
    navbarToggle.addEventListener("click", function(){
        mainNav.classList.toggle("active")
        // if (mainNav.classList.contains("active")) {
        
        //     for (let i = 0; i < navs.length; i++){

        //         navs[i].addEventListener("mouseenter", function(){
        //             this.classList.remove("back")
        //         })
        //         navs[i].addEventListener("mouseleave", function(){

        //             this.classList.add("back")
        //         })
        //     }
        // }
    })


const colorThief = new ColorThief()
let color
let prjtImg = document.querySelectorAll(".prjt-img")
let prjtBtn = document.querySelectorAll(".btn-prjt")
let imgs = document.querySelectorAll(".prjt-img > img")


for (let i = 0; i < prjtImg.length; i++){

    document.addEventListener("DOMContentLoaded", function(){
        // let imgs = prjtImg[i].getElementsByTagName("img")
        console.log("Img " + i + ": " + colorThief.getColor(imgs[i]))
        
        if (imgs[i].complete) {
            color = colorThief.getColor(imgs[i]);
        } 
        else {
            imgs[i].addEventListener('load', function() {
                color = colorThief.getColor(imgs[i]);
            });
        }
        prjtBtn[i].style.backgroundColor = "rgba(" + color + ", 0.6)"
    })
}


let saveBtn = document.querySelector(".form-group button[type='submit']")
let titleInput = document.querySelector(".form-group > input[type='text']")
let fileInput = document.querySelector(".form-group > input[type='file']")
let fileSpan = ''

titleInput.addEventListener("change", function(){
    document.querySelector(".prjt-card.no-action h2").innerHTML = this.value || "Projeto"
})
fileInput.addEventListener("change", function(){
    var reader = new FileReader()
    reader.onload = function (e){
        document.querySelector(".prjt-card.no-action .prjt-img img").src = e.target.result
    }
    reader.readAsDataURL(fileInput.files[0])

    if (fileInput.files.length > 1) {
        fileSpan = fileInput.files.length + " imagens selecionadas"
    }
    else {
        fileSpan = fileInput.files[0].name
    }
    document.querySelector("#project_form input[type='file'] ~ label ~ span").style.color = "var(--color-green)"
    document.querySelector("#project_form input[type='file'] ~ label ~ span").innerHTML = fileSpan || ''
})
saveBtn.addEventListener("click", function(){

    if (fileInput.files.length == 0) {
        document.querySelector("#project_form input[type='file'] ~ label ~ span").style.color = "rgba(255, 189, 69, 1)"
        document.querySelector("#project_form input[type='file'] ~ label ~ span").innerHTML = "<i class='fas fa-exclamation-circle'></i>" + " Insira, no mínimo, uma imagem"
    }
})