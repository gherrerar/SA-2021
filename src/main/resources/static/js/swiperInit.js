const swiper = new Swiper('.swiper-container', {
    direction: 'vertical',
    loop: true,
    centeredSlides: true,
    keyboard: true,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    pagination: {
        el: '.swiper-slider__pagination',
        clickable: true
    },
})