$(document).ready(function () {
  $('#emptyDiv').append('<p>A new paragraph of text...</p>');

  $('p').remove();

  $('H1').css('color', 'blue');

  // $('#first').css({
  //   'width': '400',
  //   'background-color': 'CornflowerBlue'
  // });

  $('H1').removeClass('text-center');

  $('#add-button').addClass('btn btn-default');

  $("#newButton").on("click", function () {
    $("#third").toggle("slow");
  })

  $("h1").hover(
    // in callback
    function () {
      $("h1").css("background-color", "red");
      $(this).css("background-color", "CornflowerBlue");
    },
    // out callback
    function () {
      $("h1").css("background-color", "");
      $(this).css("background-color", "");
    }
  );
});