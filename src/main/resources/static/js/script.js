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

