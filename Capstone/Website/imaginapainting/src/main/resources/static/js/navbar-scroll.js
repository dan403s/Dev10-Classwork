// navbar is transparent until scrolling 2 times the height

function checkScroll() {
  var navPosition = $('#navbar-background').height();

  if ($(window).scrollTop() > navPosition) {
    $('#navbar-background').addClass('nav-bar-scrolled');
  } else {
    $('#navbar-background').removeClass('nav-bar-scrolled');
  }
}



if ($('#navbar-background').length > 0) {
  $(window).on('scroll load resize', function () {
    checkScroll();
  });
}

console.log('Navbar JS Loaded');