$(document).ready(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const error = urlParams.get('error');
  if (error == "true") {
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
                                       title: 'O endereço de email ou a senha que você inseriu não é válido!'
                                     })
  }
});