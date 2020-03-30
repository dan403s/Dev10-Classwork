$(document).ready(function () {
  // Page Load
  // Only the content in the Main section should display when the page is loaded.
  $("#akronInfoDiv").hide();
  $("#minneapolisInfoDiv").hide();
  $("#louisvilleInfoDiv").hide();

  // Navigation Button Behavior
  // When the Akron button is clicked, only the content in the Akron section should display; the weather information for Akron should be hidden initially.
  // When the Minneapolis button is clicked, only the content in the Minneapolis section should display; the weather information for Minneapolis should be hidden initially.
  // When the Louisville button is clicked, only the content in the Louisville section should display; the weather information for Louisville should be hidden initially.
  $("#akronButton").on("click", function () {
    $("#akronInfoDiv").show();
    $("#mainInfoDiv").hide();
    $("#minneapolisInfoDiv").hide();
    $("#louisvilleInfoDiv").hide();
    $("#akronWeather").hide();
    $("#minneapolisWeather").hide();
    $("#louisvilleWeather").hide();
  })

  $("#minneapolisButton").on("click", function () {
    $("#akronInfoDiv").hide();
    $("#mainInfoDiv").hide();
    $("#minneapolisInfoDiv").show();
    $("#louisvilleInfoDiv").hide();
    $("#akronWeather").hide();
    $("#minneapolisWeather").hide();
    $("#louisvilleWeather").hide();
  })

  $("#louisvilleButton").on("click", function () {
    $("#akronInfoDiv").hide();
    $("#mainInfoDiv").hide();
    $("#minneapolisInfoDiv").hide();
    $("#louisvilleInfoDiv").show();
    $("#akronWeather").hide();
    $("#minneapolisWeather").hide();
    $("#louisvilleWeather").hide();
  })

  $("#mainButton").on("click", function () {
    $("#akronInfoDiv").hide();
    $("#mainInfoDiv").show();
    $("#minneapolisInfoDiv").hide();
    $("#louisvilleInfoDiv").hide();
    $("#akronWeather").hide();
    $("#minneapolisWeather").hide();
    $("#louisvilleWeather").hide();
  })

  // Show/Hide Weather Behavior
  // When the Show/Hide Weather button is clicked, the page should display the associated weather information if it was hidden or hide the associated weather information if it was showing. It should default to hidden.
  $("#akronWeatherButton").on("click", function () {
    $("#akronWeather").toggle("slow");
  });

  $("#minneapolisWeatherButton").on("click", function () {
    $("#minneapolisWeather").toggle("slow");
  });

  $("#louisvilleWeatherButton").on("click", function () {
    $("#louisvilleWeather").toggle("slow");
  });

  // Table Row Behavior
  // The background color of any table row should change to “WhiteSmoke” when the mouse pointer is hovering over the row.
  // The background color of the row should return to white when the mouse pointer is no longer hovering over the row.
  // This applies to all rows in all tables except the first (header) row in a given table. The first (header) row in any table should not change appearance when the mouse pointer hovers over it.
  $(".table tr").hover(
    // in callback
    function () {
      $(this).not(":nth-child(1)").css("background-color", "WhiteSmoke");
    },
    // out callback
    function () {
      $(this).not(":nth-child(1)").css("background-color", "");
    }
  );
});