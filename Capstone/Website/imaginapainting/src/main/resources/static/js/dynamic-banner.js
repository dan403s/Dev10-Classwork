// Banner Background JS

function reduce_opacity(i, random_value) {
  // start = 1;
  setTimeout(function() {
      jQuery(banner_columns[random_value]).removeClass("opacity-on");
      jQuery(banner_columns[random_value]).addClass("opacity-half");
  }, 1500 * i);
  // start += 1;
}

function add_opacity(i, random_value) {
  // start = 1;
  setTimeout(function() {
      jQuery(banner_columns[random_value]).addClass("opacity-on");
      jQuery(banner_columns[random_value]).removeClass("opacity-half");
  }, 1500 * (i + 1));
  // start += 1;
}

function random_array_value() {
  return Math.floor(Math.random() * 36) + 0;
}

var banner_columns = [
  '.banner-background-1',
  '.banner-background-2',
  '.banner-background-3',
  '.banner-background-4',
  '.banner-background-5',
  '.banner-background-6',
  '.banner-background-7',
  '.banner-background-8',
  '.banner-background-9',
  '.banner-background-10',
  '.banner-background-11',
  '.banner-background-12',
  '.banner-background-13',
  '.banner-background-14',
  '.banner-background-15',
  '.banner-background-16',
  '.banner-background-17',
  '.banner-background-18',
  '.banner-background-19',
  '.banner-background-20',
  '.banner-background-21',
  '.banner-background-22',
  '.banner-background-23',
  '.banner-background-24',
  '.banner-background-25',
  '.banner-background-26',
  '.banner-background-27',
  '.banner-background-28',
  '.banner-background-29',
  '.banner-background-30',
  '.banner-background-31',
  '.banner-background-32',
  '.banner-background-33',
  '.banner-background-34',
  '.banner-background-35',
  '.banner-background-36'
]

for (var i = 1; i < 1000; i++) {
  
  var random_value = random_array_value();
  reduce_opacity(i, random_value);
  add_opacity(i, random_value);

}

console.log("Banner Background JS Working");