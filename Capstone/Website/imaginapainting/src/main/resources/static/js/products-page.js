$(document).ready(function () {

  loadProducts();

  // $('#selectCategoryFormAdditionalOptionsButton').on('click', function () {
  //   $('#selectCategoryFormAdditionalOptions').toggle();

  //   if ($('#selectCategoryFormAdditionalOptionsButton').text() == 'Show More') {
  //     $('#selectCategoryFormAdditionalOptionsButton').text('Show Less');
  //   } else {
  //     $('#selectCategoryFormAdditionalOptionsButton').text('Show More');
  //   }
  // });

  // $('#selectStyleFormAdditionalOptionsButton').on('click', function () {
  //   $('#selectStyleFormAdditionalOptions').toggle();

  //   if ($('#selectStyleFormAdditionalOptionsButton').text() == 'Show More') {
  //     $('#selectStyleFormAdditionalOptionsButton').text('Show Less');
  //   } else {
  //     $('#selectStyleFormAdditionalOptionsButton').text('Show More');
  //   }
  // });

  // $('#selectSubjectFormAdditionalOptionsButton').on('click', function () {
  //   $('#selectSubjectFormAdditionalOptions').toggle();

  //   if ($('#selectSubjectFormAdditionalOptionsButton').text() == 'Show More') {
  //     $('#selectSubjectFormAdditionalOptionsButton').text('Show Less');
  //   } else {
  //     $('#selectSubjectFormAdditionalOptionsButton').text('Show More');
  //   }
  // });

  // $('#selectMediumFormAdditionalOptionsButton').on('click', function () {
  //   $('#selectMediumFormAdditionalOptions').toggle();

  //   if ($('#selectMediumFormAdditionalOptionsButton').text() == 'Show More') {
  //     $('#selectMediumFormAdditionalOptionsButton').text('Show Less');
  //   } else {
  //     $('#selectMediumFormAdditionalOptionsButton').text('Show More');
  //   }
  // });

  // $('#selectMaterialFormAdditionalOptionsButton').on('click', function () {
  //   $('#selectMaterialFormAdditionalOptions').toggle();

  //   if ($('#selectMaterialFormAdditionalOptionsButton').text() == 'Show More') {
  //     $('#selectMaterialFormAdditionalOptionsButton').text('Show Less');
  //   } else {
  //     $('#selectMaterialFormAdditionalOptionsButton').text('Show More');
  //   }
  // });

  $('#productsPageLeftSearchFormButton').on('click', function () {

    var haveValidationErrors = checkAndDisplayValidationErrors($('#productsPageLeftSearchForm').find('input'), '#selectPriceFormErrorMessages');

    if (haveValidationErrors) {
      return false;
    }

    clearProducts();

    // grab the div that will hold the data returned
    var insertDiv = $('#productsPageRightInnerContainerItemContainer');

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });

    $.ajax({
      type: 'POST',
      url: '/products/filter-products',
      data: JSON.stringify({
        category: $('#category').val(),
        style: $('#style').val(),
        subject: $('#subject').val(),
        medium: $('#medium').val(),
        material: $('#material').val(),
        selectPriceMin: $('#selectPriceMin').val(),
        selectPriceMax: $('#selectPriceMax').val()
      }),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      success: function (data, status) {

        $.each(data, function (index, item) {

          var id = item.productId;
          var href = '/individual-product?productIdString=' + id;
          var img = item.photoUrl;
          var title = item.title;
          var desc = item.description;
          var price = item.price;
          var priceFormatted = currencyFormat(price);

          var output = '<div class="products-page-right-inner-container-item">';
          output += '<div class="products-page-item-image-div">';
          output += '<a href="' + href + '">';
          output += '<img src="' + img + '">';
          output += '</a>';
          output += '</div>';
          output += '<a href="' + href + '">';
          output += '<h3 class="font-h3">' + title + '</h3>';
          output += '</a>';
          output += '<a href="' + href + '">';
          output += '<p class="font-p products-page-item-description">' + desc + '</p>';
          output += '</a>';
          output += '<a href="' + href + '">';
          output += '<p class="font-p">Price: ' + priceFormatted + '</p>';
          output += '</a>';
          output += '</div>';

          insertDiv.append(output);
        });

      },
      error: function () {
        $('#displayProductsErrorMessages')
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('Error calling web service.  Please try again later.'));
      }
    });

  });

});

// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input, errorMessagesDiv) {
  // clear displayed error message if there are any
  clearErrorMessages();
  // check for HTML5 validation errors and process/display appropriately
  // a place to hold error messages
  var errorMessages = [];

  // loop through each input and check for validation errors
  input.each(function () {
    // Use the HTML5 validation API to find the validation errors
    if (!this.validity.valid) {
      // if (this.id == 'selectPriceMin') {
      //   errorMessages.push('Please enter a positive number for min price.');
      // } else if (this.id == 'selectPriceMax') {
      //   errorMessages.push('Please enter a positive number for max price.');
      // } else {
      var errorField = $('label[for=' + this.id + ']').text();
      errorMessages.push(errorField + ': ' + this.validationMessage);
      // }
    }
  });

  var minPrice = $('#selectPriceMin').val();
  var maxPrice = $('#selectPriceMax').val();

  minPrice = parseInt(minPrice);
  maxPrice = parseInt(maxPrice);

  if (minPrice > maxPrice) {
    errorMessages.push('Min price must be less than max price');
  }

  // put any error messages in the errorMessages div
  if (errorMessages.length > 0) {
    $.each(errorMessages, function (index, message) {
      $(errorMessagesDiv).append($('<li>').attr({
        class: 'list-group-item list-group-item-danger'
      }).text(message));
    });
    // return true, indicating that there were errors
    return true;
  } else {
    // return false, indicating that there were no errors
    return false;
  }
}

function clearErrorMessages() {
  $('#selectPriceFormErrorMessages').empty();
  $('#displayProductsErrorMessages').empty();
}

function clearProducts() {
  $('#productsPageRightInnerContainerItemContainer').empty();
}

function loadProducts() {

  clearErrorMessages();
  clearProducts();

  // grab the div that will hold the data returned
  var insertDiv = $('#productsPageRightInnerContainerItemContainer');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/products/load-products/',
    success: function (data, status) {
      $.each(data, function (index, item) {

        var id = item.productId;
        var href = '/individual-product?productIdString=' + id;
        var img = item.photoUrl;
        var title = item.title;
        var desc = item.description;
        var price = item.price;
        var priceFormatted = currencyFormat(price);

        var output = '<div class="products-page-right-inner-container-item">';
        output += '<div class="products-page-item-image-div">';
        output += '<a href="' + href + '">';
        output += '<img src="' + img + '">';
        output += '</a>';
        output += '</div>';
        output += '<a href="' + href + '">';
        output += '<h3 class="font-h3">' + title + '</h3>';
        output += '</a>';
        output += '<a href="' + href + '">';
        output += '<p class="font-p products-page-item-description">' + desc + '</p>';
        output += '</a>';
        output += '<a href="' + href + '">';
        output += '<p class="font-p">Price: ' + priceFormatted + '</p>';
        output += '</a>';
        output += '</div>';

        insertDiv.append(output);
      });
    },
    error: function () {
      $('#displayProductsErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function currencyFormat(num) {
  return '$' + num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
}