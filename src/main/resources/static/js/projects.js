$(document).ready(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const confirmDelete = urlParams.get('confirmDelete');
  if (confirmDelete == "true") {
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
                                       title: 'Sucesso ao deletar seu projeto!'
                                     })
  }
});