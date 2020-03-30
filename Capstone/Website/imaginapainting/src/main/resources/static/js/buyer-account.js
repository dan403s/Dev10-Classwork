$(document).ready(function () {

  defaultPageLoadView();

  $('#buyerAccountPageAccountDetailsEditPasswordModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#buyerAccountPageAccountDetailsPasswordModalErrorMessages');
    clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');
  })

  $('#buyerAccountPageAccountDetailsDeleteAccountModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#buyerAccountPageAccountDetailsDeleteAccountModalErrorMessages');
    clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');
  })

  $('#buyerAccountPageAccountDetailsSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#buyerAccountPageAccountDetailsForm').find('input'), $('#buyerAccountPageAccountDetailsForm').find('select'), '#buyerAccountPageInnerRightContainerErrorMessages');


    if (haveValidationErrors) {
      return false;
    }

    changeBuyerDetails();
  });

  $('#buyerAccountPageAccountDetailsEditPasswordSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#buyerAccountPageAccountDetailsPasswordModalForm').find('input'), $('#buyerAccountPageAccountDetailsPasswordModalForm').find('select'), '#buyerAccountPageAccountDetailsPasswordModalErrorMessages');


    if (haveValidationErrors) {
      return false;
    }

    changeBuyerPassword();
  });

  $('#buyerAccountPageAccountDetailsDeleteButton').on('click', function () {
    deleteBuyerAccount();
  })

});

function defaultPageLoadView() {
  $('#buyerAccountPageAllOrdersSection').show();
  $('#buyerAccountPageFavoritesSection').hide();
  $('#buyerAccountPageAccountDetailsSection').hide();
  loadOrders();
  loadFavorites();
  loadBuyerDetails();
}

function buyerAccountPageShowAllOrders() {
  $('#buyerAccountPageAllOrdersSection').show();
  $('#buyerAccountPageFavoritesSection').hide();
  $('#buyerAccountPageAccountDetailsSection').hide();
  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');
  loadOrders();
}

function buyerAccountPageShowFavorites() {
  $('#buyerAccountPageAllOrdersSection').hide();
  $('#buyerAccountPageFavoritesSection').show();
  $('#buyerAccountPageAccountDetailsSection').hide();
  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');
  loadFavorites();
}

function buyerAccountPageShowAccountDetails() {
  $('#buyerAccountPageAllOrdersSection').hide();
  $('#buyerAccountPageFavoritesSection').hide();
  $('#buyerAccountPageAccountDetailsSection').show();
  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');
  loadBuyerDetails();
}

function buyerAccountLogoutButton() {
  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');
}

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
      if (this.id == 'buyerAccountPageAccountDetailsNewPassword') {
        errorMessages.push('New password needs to have at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and have at least 8 characters total');
      } else if (this.id == 'buyerAccountPageAccountDetailsNewPassword2') {
        errorMessages.push('New password entered again needs to have at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and have at least 8 characters total');
      } else if (this.id == 'buyerAccountPageAccountDetailsZip') {
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

  // if new passwords do not match, throw error
  var newPassword = $('#buyerAccountPageAccountDetailsNewPassword').val();
  var newPassword2 = $('#buyerAccountPageAccountDetailsNewPassword2').val();

  if (newPassword != newPassword2) {
    errorMessages.push('Passwords do not match');
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

function buyerAccountPageEditFormField(input) {
  if ($(input).hasClass('readonly')) {
    $(input).removeClass('readonly');
  } else {
    $(input).addClass('readonly');
  }
}

function loadOrders() {

  clearOrders();

  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#buyerAccountPageAllOrdersSectionDiv');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/buyer/load-orders/',
    success: function (data, status) {

      if (data.length == 0) {
        insertDiv
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('You do not have any orders placed.'));
      } else {
        $.each(data, function (index, item) {

          var orderId = item.orderId;
          var date = item.date;
          var total = item.total;
          var firstName = item.firstName;
          var lastName = item.lastName;
          var streetAddress = item.streetAddress;
          var aptUnit = item.aptUnit;
          var city = item.city;
          var zip = item.zip;
          var state = item.state.name;
          var products = item.products;

          output = '<div class="buyer-account-page-right-inner-container-order">';
          output += '<div class="buyer-account-page-right-inner-order-summary">';
          output += '<div class="buyer-account-page-right-inner-order-summary-text-block">';
          output += '<h6 class="font-h6">';
          output += 'Order ID';
          output += '</h6>';
          output += '<h6 class="font-h6">';
          output += orderId;
          output += '</h6>';
          output += '</div>';
          output += '<div class="buyer-account-page-right-inner-order-summary-text-block">';
          output += '<h6 class="font-h6">';
          output += 'Order Date';
          output += '</h6>';
          output += '<h6 class="font-h6">';
          output += date;
          output += '</h6>';
          output += '</div>';
          output += '<div class="buyer-account-page-right-inner-order-summary-text-block">';
          output += '<h6 class="font-h6">';
          output += 'Order Total';
          output += '</h6>';
          output += '<h6 class="font-h6">';
          output += '$' + total.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
          output += '</h6>';
          output += '</div>';
          output += '</div>';
          output += '<hr />';

          $.each(products, function (index, item) {

            var productId = item.productId;
            var productTitle = item.title;
            var productDescription = item.description;
            var productPrice = item.price;
            var productQuantity = item.quantity;
            var productPhotoUrl = item.photoUrl;
            var productStatus = item.productStatus;

            output += '<div class="buyer-account-page-right-inner-order-details-block">';
            output += '<div class="buyer-account-page-order-image-div">';
            output += '<a href="/individual-product?productIdString=' + productId + '">';
            output += '<img src="' + productPhotoUrl + '">';
            output += '</a>';
            output += '</div>';
            output += '<div class="buyer-account-page-order-text-div">';
            output += '<a href="/individual-product?productIdString=' + productId + '">';
            output += '<h4 class="font-h4">' + productTitle + '</h4>';
            output += '</a>';
            output += '<a href="/individual-product?productIdString=' + productId + '">';
            output += '<p class="font-p">Price: $' + productPrice.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</p>';
            output += '</a>';
            output += '</div>';
            output += '</div>';

          })

          output += '</div>';

          insertDiv.append(output);
        });
      }

    },
    error: function () {
      $('#buyerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearOrders() {
  $('#buyerAccountPageAllOrdersSectionDiv').empty();
}

function loadFavorites() {

  clearFavorites();

  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#buyerAccountPageFavoritesSectionDiv');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/buyer/load-favorites/',
    success: function (data, status) {

      var buyerId = data.buyerId;
      var userId = data.user.userId;
      var favoriteListId = data.favoriteList.favoriteListId;
      var products = data.favoriteList.products;

      if (data.favoriteList.products == 0) {
        insertDiv
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('You do not have any products favorited.'));
      } else {
        $.each(products, function (index, item) {

          var productId = item.productId;
          var productTitle = item.title;
          var productDescription = item.description;
          var productPrice = item.price;
          var productQuantity = item.quantity;
          var productPhotoUrl = item.photoUrl;
          var productStatus = item.productStatus;

          output = '<div class="buyer-account-page-favorites-right-inner-container-item">';
          output += '<div class="buyer-account-page-favorites-item-image-div">';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<img src="' + productPhotoUrl + '">';
          output += '</a>';
          output += '</div>';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<h3 class="font-h3">' + productTitle + '</h3>';
          output += '</a>';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<p class="font-p">Price: $' + productPrice.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</p>';
          output += '</a>';
          output += '<button type="button" id="buyerAccountPageAccountDetailsButton" class="btn btn-outline-dark custom-dark-button" onclick="removeFromFavorites(' + userId + ', ' + buyerId + ', ' + favoriteListId + ', ' + productId + ')">Remove From My Favorites</button>';
          output += '</div>';

          insertDiv.append(output);
        });
      }

    },
    error: function () {
      $('#buyerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearFavorites() {
  $('#buyerAccountPageFavoritesSectionDiv').empty();
}

function removeFromFavorites(userId, buyerId, favoriteListId, productId) {

  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/buyer/remove-from-favorites/',
    data: JSON.stringify({
      userId: userId,
      buyerId: buyerId,
      favoriteListId: favoriteListId,
      productId: productId
    }),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadFavorites();
    },
    error: function (xhr, status, error) {
      var err = eval("(" + xhr.responseText + ")");

      $('#buyerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function loadBuyerDetails() {

  clearErrorMessages('#buyerAccountPageInnerRightContainerErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/buyer/load-buyer-details/',
    success: function (data, status) {
      var userId = data.userId;
      var firstName = data.firstName;
      var lastName = data.lastName;
      var streetAddress = data.streetAddress;
      var aptUnit = data.aptUnit;
      var city = data.city;
      var zip = data.zip;
      var username = data.username;
      var password = data.password;
      var acctStatus = data.acctStatus;
      var userRole = data.userRole;
      var state = data.state.stateId;
      var acctStatusDescription = '';

      if (acctStatus == 1) {
        acctStatusDescription = "Active";
      } else if (acctStatus == 2) {
        acctStatusDescription = "Suspended";
      } else if (acctStatus == 3) {
        acctStatusDescription = "Deleted";
      } else if (acctStatus == 4) {
        acctStatusDescription = "Pending Approval";
      } else if (acctStatus == 5) {
        acctStatusDescription = "Rejected";
      }

      $('#buyerAccountStatus').text('Account Status: ' + acctStatusDescription);
      $('#buyerAccountPageAccountDetailsFirstName').val(firstName);
      $('#buyerAccountPageAccountDetailsLastName').val(lastName);
      $('#buyerAccountPageAccountDetailsStreetAddress').val(streetAddress);
      $('#buyerAccountPageAccountDetailsAptUnitNumber').val(aptUnit);
      $('#buyerAccountPageAccountDetailsCity').val(city);
      $('#buyerAccountPageAccountDetailsState').val(state);
      $('#buyerAccountPageAccountDetailsZip').val(zip);
      $('#buyerAccountPageAccountDetailsUsername').val(username);
      $('#buyerAccountPageAccountDetailsUserId').val(userId);
    },
    error: function () {
      $('#buyerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function changeBuyerDetails() {

  var userId = $('#buyerAccountPageAccountDetailsUserId').val();
  var firstName = $('#buyerAccountPageAccountDetailsFirstName').val();
  var lastName = $('#buyerAccountPageAccountDetailsLastName').val();
  var streetAddress = $('#buyerAccountPageAccountDetailsStreetAddress').val();
  var aptUnit = $('#buyerAccountPageAccountDetailsAptUnitNumber').val();
  var city = $('#buyerAccountPageAccountDetailsCity').val();
  var stateId = $('#buyerAccountPageAccountDetailsState').val();
  var zip = $('#buyerAccountPageAccountDetailsZip').val();
  var username = $('#buyerAccountPageAccountDetailsUsername').val();

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/buyer/change-buyer-details/',
    data: JSON.stringify({
      userId: userId,
      firstName: firstName,
      lastName: lastName,
      streetAddress: streetAddress,
      aptUnit: aptUnit,
      city: city,
      state: stateId,
      zip: zip,
      username: username
    }),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      // loadBuyerDetails();
      $('#buyerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-success'
          })
          .text("Successfully changed user details."));

      location.reload();

    },
    error: function (xhr, status, error) {

      var err = eval("(" + xhr.responseText + ")");

      $('#buyerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function changeBuyerPassword() {

  var currentPassword = $('#buyerAccountPageAccountDetailsCurrentPassword').val();
  var newPassword = $('#buyerAccountPageAccountDetailsNewPassword').val();
  var newPassword2 = $('#buyerAccountPageAccountDetailsNewPassword2').val();

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/buyer/change-buyer-password/',
    data: JSON.stringify({
      currentPassword: currentPassword,
      newPassword: newPassword,
      newPassword2: newPassword2,
    }),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      $('#buyerAccountPageAccountDetailsPasswordModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-success'
          })
          .text("Successfully changed user password."));

      // location.reload();
      window.location.assign('/index');

    },
    error: function (xhr, status, error) {

      var err = eval("(" + xhr.responseText + ")");

      $('#buyerAccountPageAccountDetailsPasswordModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function deleteBuyerAccount() {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/buyer/delete-buyer-account/',
    data: JSON.stringify({
    }),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      $('#buyerAccountPageAccountDetailsDeleteAccountModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-success'
          })
          .text("Successfully deleted your account."));

      // location.reload();
      window.location.assign('/index');

    },
    error: function (xhr, status, error) {

      var err = eval("(" + xhr.responseText + ")");

      $('#buyerAccountPageAccountDetailsDeleteAccountModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}