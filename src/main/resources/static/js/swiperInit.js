var swiper = new Swiper('.swiper-container', {
    direction: getDirection(),
    effect: 'fade',
    slidesPerView: 1,
    spaceBetween: 60,
    loop: true,
    autoplay: {
        disableOnInteraction: true,
    },
    // centeredSlides: true,
    keyboard: true,
    mousewheel: false,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },
    on: {
        resize: function(){
            swiper.changeDirection(getDirection())
        }
    }
})

function getDirection() {
    var direction = window.innerWidth <= 996 ? 'horizontal' : 'vertical'
    return direction;
  }