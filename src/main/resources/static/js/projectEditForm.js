$(document).ready(function () {

  $("#form-project").submit(function (event) {
    event.preventDefault();
    var form = document.getElementById("form-project");
    var data = new FormData(form);
    fire_ajax_submit(data, id);

  });
});

function fire_ajax_submit(formData, id) {
    $(".form-group button").attr("disabled", true)
    $(".form-group button ~ a").addClass("disabled")
    $(".loading-icon").css("opacity", 1)
    var token = $("meta[name='_csrf']").attr("content");
    var header = "X-CSRF-TOKEN";
    var FILE_INPUT = document.getElementById("files")
    var FILE_LIST = FILE_INPUT.files
    var linkList = []
    var promises = []
    var apiUrl = 'https://api.imgur.com/3/image';

    function uploadImageCallBack(file) {
      return new Promise(
        (resolve, reject) => {
          const xhr = new XMLHttpRequest();
          xhr.open('POST', 'https://api.imgur.com/3/image');
          xhr.setRequestHeader('Authorization', 'Client-ID 195a0e9c2a0ecf0');
          const data = new FormData();
          data.append('image', file);
          xhr.send(data);
          xhr.addEventListener('load', () => {
            const response = JSON.parse(xhr.responseText);
            resolve(response);
          });
          xhr.addEventListener('error', () => {
            const error = JSON.parse(xhr.responseText);
            reject(error);
          });
        }
      );
    }

    for (file of FILE_LIST) {
        promises.push(uploadImageCallBack(file))
    }

    Promise.all(promises).then((values) => {
        linkList = values.map((value) => value.data.link)
                  const xhr = new XMLHttpRequest();
                  xhr.open('POST', `/edit/${id}`);
                  xhr.setRequestHeader(header, token);
                  formData.append("linkList", linkList)
                  console.log(formData)
                  xhr.send(formData);
                  xhr.addEventListener('load', () => {
                         $('input[name="title"]').val('')
                         $('input[name="files"]').val('')
                         $('textarea[name="text"]').val('')
                         window.location = '/projects/' + id;
                  });
                  xhr.addEventListener('error', () => {
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
                                              title: "Erro durante a operação de editar projeto"
                                            })
                  });
    })

}