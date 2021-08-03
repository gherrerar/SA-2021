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
  $(".form-group button").attr("disabled", true)
  $(".form-group button ~ a").addClass("disabled")
  $(".loading-icon").css("opacity", 1)
  $.ajax({
    type: "POST",
    url: "/edit/" + id,
    data: formData,
    contentType: false,
    processData: false,
    cache: false,
    timeout: 600000,
    complete: function (e, xhr) {
      $(".form-group button").attr("disabled", false)
      $(".form-group button ~ a").removeClass("disabled")
      $(".loading-icon").css("opacity", 0)

      if (e.status == '200') {
            window.location = '/projects/' + id;
      } else {
            window.location = '/projects';
      }
    }
  });

}