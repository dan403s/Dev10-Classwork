$(document).ready(function () {
  // Center all H1 elements.
  $("h1").addClass("text-center");

  // Center all H2 elements.
  $("h2").addClass("text-center");

  // Replace the class that is on the element containing the text "Team Up!" with the class page-header.
  $(".myBannerHeading").addClass("page-header");
  $(".myBannerHeading").removeClass("page-header");

  // Change the text of "The Squad" to "Yellow Team".
  $("#yellowHeading").text("Yellow Team");

  // Change the background color for each team list to match the name of the team.
  $("#orangeHeading").css("color", "orange");
  $("#blueHeading").css("color", "blue");
  $("#redHeading").css("color", "red");
  $("#yellowHeading").css("color", "yellow");

  // Add Joseph Banks and Simon Jones to the Yellow Team list.
  $("#yellowTeamList").prepend("<li>Joseph Banks</li>");
  $("#yellowTeamList").append("<li>Simon Jones</li>");

  // Hide the element containing the text "Hide Me!!!"
  $("#oops h1").hide();

  // Remove the element containing the text "Bogus Contact Info" from the footer.
  $("#footerPlaceholder").remove();

  // Add a paragraph element containing your name and email to the footer. The text must be in Courier font and be 24 pixels in height.
  $("#footer").prepend("<h2 class='footer-h2'>Daniel Bart</h2>");
  $("#footer").append("<h2 class='footer-h2'>dbart@danielvbart.com</h2>");
  $(".footer-h2").css("font-size", "24px");
  $(".footer-h2").css("font-family", "courier");
});