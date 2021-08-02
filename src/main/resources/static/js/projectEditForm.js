$(document).ready(function () {
  $("#form-project").submit(function (event) {
    event.preventDefault();
    var form = document.getElementById("form-project");
    var data = new FormData(form);
    fire_ajax_submit(data, id);

  });

  var token = $("meta[name='_csrf']").attr("content");
  var header = "X-CSRF-TOKEN";
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });
});

function fire_ajax_submit(formData, id) {
  $.ajax({
    type: "POST",
    url: "/edit/" + id,
    data: formData,
    contentType: false,
    processData: false,
    cache: false,
    timeout: 600000,
    complete: function (e, xhr) {
      if (e.status == '200') {
            window.location = '/projects/' + id;
      } else {
            window.location = '/projects';
      }
    }
  });

}