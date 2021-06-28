document.addEventListener("DOMContentLoaded", function(){
    new Splide('.splide', {
        width: '100%',
        // height: ,
        autoHeight: true,
        padding: {
            top: '10rem',
            bottom: '10rem',
        },
        type: 'loop',
        focus: 'center',
        direction: 'ttb',
        perPage: 1,
        // perMove: 1,
        drag: true,
        updateOnMove: true
    }).mount()
})