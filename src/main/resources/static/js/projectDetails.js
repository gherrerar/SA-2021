$(document).ready(function () {
  var token = $("meta[name='_csrf']").attr("content");
  var header = "X-CSRF-TOKEN";
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });
});


function deleteRequest(id) {
  Swal.fire({
    title: 'Você quer mesmo deletar este projeto?',
    showDenyButton: true,
    confirmButtonText: 'Deletar',
    denyButtonText: 'Cancelar',
  }).then((result) => {

    if (result.isConfirmed) {
      $.ajax({
        type: "DELETE",
        url: "/delete/" + id,
        contentType: "application/json",
        complete: function (e, xhr) {
          if (e.status == '200') {
            var Toast = Swal.mixin({
              toast: true,
              position: 'top-end',
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
              },
              didClose: redirectToProjectsPage
            })

            Toast.fire({
              icon: 'success',
              title: e.responseText
            })

            function redirectToProjectsPage () {
                window.location = '/projects';
            }
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
            title: e.responseText
          })
        }
      });
    }
  })
}