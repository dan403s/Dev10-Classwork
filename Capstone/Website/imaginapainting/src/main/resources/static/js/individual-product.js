$(document).ready(function () {

  $('#individualProductPageAddToCartButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#individualProductPageAddToCartForm').find('input'), '#individualProductPageErrorMessages', '#cartQuantityInStockString', '#cartBuyerLoggedIn');

    if (haveValidationErrors) {
      return false;
    }
  });

  $('#individualProductPageAddToFavoritesButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#individualProductPageAddToFavoritesForm').find('input'), '#individualProductPageErrorMessages', '', '#favoriteBuyerLoggedIn');

    if (haveValidationErrors) {
      return false;
    }
  });

});

// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input, errorMessagesDiv, quantityInStockId, buyerLoggedInId) {
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
      } else if (this.id == 'sellerAccountPageAccountDetailsZip') {
        errorMessages.push('Zip needs to have at least 5 digits');
      } else {
        var errorField = $('label[for=' + this.id + ']').text();
        errorMessages.push(errorField + ': ' + this.validationMessage);
      }
    }
  });

  var quantityInStock = $(quantityInStockId).val();

  quantityInStock = parseInt(quantityInStock);

  if (quantityInStock == 0) {
    errorMessages.push('Sorry, this item is out of stock.');
  }

  var buyerLoggedIn = $(buyerLoggedInId).val();

  buyerLoggedIn = parseInt(buyerLoggedIn);

  if (buyerLoggedIn == 0) {
    errorMessages.push('You must be logged in to do that. Please log in.');
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

function clearErrorMessages(errorDiv) {
  $(errorDiv).empty();
}