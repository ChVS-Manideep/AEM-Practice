document.addEventListener("DOMContentLoaded", function() {
  var myForm = document.getElementById("myForm");
  myForm.addEventListener("submit", function(event) {
    var response = grecaptcha.getResponse();

    if (response.length === 0) {
      event.preventDefault();
      alert("Please complete the CAPTCHA.");
    }
  });
});




