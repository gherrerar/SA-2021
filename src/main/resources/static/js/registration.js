$(document).ready(function () {
  console.log("alo")
  $("#form-registration").submit(function (event) {
    event.preventDefault();
    var form = document.getElementById("form-registration");
    var data = new FormData(form);
    fire_ajax_submit(data);

  });

  var token = $("meta[name='_csrf']").attr("content");
  var header = "X-CSRF-TOKEN";
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

});

function fire_ajax_submit(formData) {

  $.ajax({
    type: "POST",
    url: "/registration",
    data: formData,
    contentType: false,
    processData: false,
    cache: false,
    timeout: 600000,
    complete: function (e, xhr) {
      if (e.status == '200') {
      //        TODO limpar os campos.
        var Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 4000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })

        Toast.fire({
          icon: 'success',
          title: e.responseText
        })
      } else {
        var Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 4000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })

        Toast.fire({
          icon: 'error',
          title: e.responseText
        })
      }
    },
    fail: function () {
      var Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 4000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })

      Toast.fire({
        icon: 'error',
        title: 'Erro desconhecido, meu amigo!'
      })
    }
  });

}