$(document).ready(function () {

  $('#cartPagePlaceOrderButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#cartPageCartForm').find('input'), $('#cartPageCartForm').find('select'), '#cartPageCartSubmissionErrorMessages');

    if (haveValidationErrors) {
      return false;
    }
  });

});


// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input, input2, errorMessagesDiv) {
  // clear displayed error message if there are any
  clearErrorMessages(errorMessagesDiv);
  // check for HTML5 validation errors and process/display appropriately
  // a place to hold error messages
  var errorMessages = [];

  // loop through each input and check for validation errors
  input.each(function () {
    // Use the HTML5 validation API to find the validation errors
    if (!this.validity.valid) {
      if (this.id == 'sellerAccountPageAccountDetailsNewPassword') {
        errorMessages.push('New password needs to have at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and have at least 8 characters total');
      } else if (this.id == 'sellerAccountPageAccountDetailsNewPassword2') {
        errorMessages.push('New password entered again needs to have at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and have at least 8 characters total');
      } else if (this.id == 'cartPageZip') {
        errorMessages.push('Zip needs to have at least 5 digits');
      } else {
        var errorField = $('label[for=' + this.id + ']').text();
        errorMessages.push(errorField + ': ' + this.validationMessage);
      }
    }
  });

  // loop through each select and check for validation errors
  input2.each(function () {
    // Use the HTML5 validation API to find the validation errors
    if (!this.validity.valid) {
      var errorField = $('label[for=' + this.id + ']').text();
      errorMessages.push(errorField + ' ' + this.validationMessage);
    }
  });

  // cartPageCartItemQuantityError = [];

  // input.each(function () {
  //   if (this.id == 'cartPageCartItemQuantity') {
  //     if (this.value == 0) {
  //       cartPageCartItemQuantityError.push("BAD");
  //     }
  //   }
  // });

  // if (cartPageCartItemQuantityError.length > 0) {
  //   errorMessages.push('One or more of the items in your cart have 0 quantity.');
  // }

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

function clearErrorMessages(errorDiv) {
  $(errorDiv).empty();
}

function removeItemFromCart(productId) {

  clearErrorMessages('#cartPageCartSubmissionErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/buyer-cart/remove-from-cart/',
    data: JSON.stringify({
      productId: productId
    }),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      // loadsellerDetails();
      $('#cartPageCartSubmissionErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-success'
          })
          .text("Successfully removed item from cart."));

      location.reload();

    },
    error: function (xhr, status, error) {

      var err = eval("(" + xhr.responseText + ")");

      $('#cartPageCartSubmissionErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}