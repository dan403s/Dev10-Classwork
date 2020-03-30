$(document).ready(function () {

  defaultPageLoadView();

  $('#sellerAccountPageAccountDetailsEditPasswordModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#sellerAccountPageAccountDetailsPasswordModalErrorMessages');
    clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  })

  $('#sellerAccountPageAccountDetailsDeleteAccountModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#sellerAccountPageAccountDetailsDeleteAccountModalErrorMessages');
    clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  })

  $('#sellerAccountPageListArtworkSectionAddItemModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#sellerAccountPageListArtworkSectionAddItemModalErrorMessages');
    clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  })

  $('#sellerAccountPageListArtworkSectionEditItemModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#sellerAccountPageListArtworkSectionEditItemModalErrorMessages');
    clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  })

  $('#sellerAccountPageAccountDetailsSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#sellerAccountPageAccountDetailsForm').find('input'), $('#sellerAccountPageAccountDetailsForm').find('select'), '#sellerAccountPageInnerRightContainerErrorMessages');


    if (haveValidationErrors) {
      return false;
    }

    changeSellerDetails();
  });

  $('#sellerAccountPageAccountDetailsEditPasswordSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#sellerAccountPageAccountDetailsPasswordModalForm').find('input'), $('#sellerAccountPageAccountDetailsPasswordModalForm').find('select'), '#sellerAccountPageAccountDetailsPasswordModalErrorMessages');


    if (haveValidationErrors) {
      return false;
    }

    changeSellerPassword();
  });

  $('#sellerAccountPageListArtworkSectionAddItemSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#sellerAccountPageListArtworkSectionAddItemModalForm').find('input'), $('#sellerAccountPageListArtworkSectionAddItemModalForm').find('textarea'), '#sellerAccountPageListArtworkSectionAddItemModalErrorMessages');

    var haveValidationErrors2 = checkAndDisplayValidationErrorsImageFile($('#sellerAccountPageListArtworkSectionAddItemPhoto'), '#sellerAccountPageListArtworkSectionAddItemModalErrorMessages');

    if (haveValidationErrors || haveValidationErrors2) {
      return false;
    }

    addSellerArtwork();
  });

  $('#sellerAccountPageListArtworkSectionEditItemSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#sellerAccountPageListArtworkSectionEditItemModalForm').find('input'), $('#sellerAccountPageListArtworkSectionEditItemModalForm').find('textarea'), '#sellerAccountPageListArtworkSectionEditItemModalErrorMessages');

    var haveValidationErrors2 = checkAndDisplayValidationErrorsImageFile($('#sellerAccountPageListArtworkSectionEditItemPhoto'), '#sellerAccountPageListArtworkSectionEditItemModalErrorMessages');

    if (haveValidationErrors || haveValidationErrors2) {
      return false;
    }

    editSellerArtworkSubmit();
  });

  $('#sellerAccountPagePublicProfileDetailsSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#sellerAccountPagePublicProfileDetailsForm').find('input'), $('#sellerAccountPagePublicProfileDetailsForm').find('textarea'), '#sellerAccountPageInnerRightContainerErrorMessages');

    var haveValidationErrors2 = checkAndDisplayValidationErrorsImageFile($('#sellerAccountPagePublicProfileDetailsPublicImageUpload'), '#sellerAccountPageInnerRightContainerErrorMessages');

    if (haveValidationErrors || haveValidationErrors2) {
      return false;
    }

    editPublicProfileDetails();
  });

  $('#sellerAccountPageAccountDetailsDeleteButton').on('click', function () {
    deleteSellerAccount();
  })

});

function defaultPageLoadView() {
  $('#sellerAccountPageListArtworkSection').show();
  $('#sellerAccountPagePublicProfileDetailsSection').hide();
  $('#sellerAccountPageAccountDetailsSection').hide();
  $('#sellerAccountPageViewSoldOrdersSection').hide();
  loadSellerArtwork();
  loadPublicProfileDetails();
  loadSellerOrders();
  loadSellerDetails();
}

function sellerAccountPageShowListArtwork() {
  $('#sellerAccountPageListArtworkSection').show();
  $('#sellerAccountPagePublicProfileDetailsSection').hide();
  $('#sellerAccountPageAccountDetailsSection').hide();
  $('#sellerAccountPageViewSoldOrdersSection').hide();
  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  loadSellerArtwork();
}

function sellerAccountPageShowPublicProfile() {
  $('#sellerAccountPageListArtworkSection').hide();
  $('#sellerAccountPagePublicProfileDetailsSection').show();
  $('#sellerAccountPageAccountDetailsSection').hide();
  $('#sellerAccountPageViewSoldOrdersSection').hide();
  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  loadPublicProfileDetails();
}

function sellerAccountPageShowViewSoldOrders() {
  $('#sellerAccountPageListArtworkSection').hide();
  $('#sellerAccountPagePublicProfileDetailsSection').hide();
  $('#sellerAccountPageAccountDetailsSection').hide();
  $('#sellerAccountPageViewSoldOrdersSection').show();
  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  loadSellerOrders();
}

function sellerAccountPageShowAccountDetails() {
  $('#sellerAccountPageListArtworkSection').hide();
  $('#sellerAccountPagePublicProfileDetailsSection').hide();
  $('#sellerAccountPageAccountDetailsSection').show();
  $('#sellerAccountPageViewSoldOrdersSection').hide();
  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
  loadSellerDetails();
}

function sellerAccountLogoutButton() {
  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');
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

  // loop through each select and check for validation errors
  input2.each(function () {
    // Use the HTML5 validation API to find the validation errors
    if (!this.validity.valid) {
      var errorField = $('label[for=' + this.id + ']').text();
      errorMessages.push(errorField + ' ' + this.validationMessage);
    }
  });

  // if new passwords do not match, throw error
  var newPassword = $('#sellerAccountPageAccountDetailsNewPassword').val();
  var newPassword2 = $('#sellerAccountPageAccountDetailsNewPassword2').val();

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

function sellerAccountPageEditFormField(input) {
  if ($(input).hasClass('readonly')) {
    $(input).removeClass('readonly');
  } else {
    $(input).addClass('readonly');
  }
}

function checkAndDisplayValidationErrorsImageFile(input, errorMessagesDiv) {

  var ext = $(input).val().split('.').pop().toLowerCase();
  if (ext !== 'jpg' && ext !== 'jpeg' && ext !== 'png' && ext !== '') {
    $(errorMessagesDiv).append($('<li>').attr({
      class: 'list-group-item list-group-item-danger'
    }).text('Please upload a file in the format: .jpg, .jpeg, .png'));
    return true;
  }

  return false;

}

function loadSellerArtwork() {

  clearSellerArtwork();

  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#sellerAccountPageListArtworkSectionDiv');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/seller/load-seller-artwork/',
    success: function (data, status) {

      var sellerId = data.sellerId;
      var userId = data.user.userId;
      var products = data.products;

      if (products == 0) {
        insertDiv
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('You do not have any products.'));
      } else {
        $.each(products, function (index, item) {

          var productId = item.productId;
          var productTitle = item.title;
          var productDescription = item.description;
          var productPrice = item.price;
          var productQuantity = item.quantity;
          var productPhotoUrl = item.photoUrl;
          var productStatus = item.productStatus;

          output = '<div class="seller-account-page-right-inner-container-list-artwork">';
          output += '<div class="seller-account-page-right-inner-list-artwork-details-block">';
          output += '<div class="seller-account-page-list-artwork-image-div">';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<img src="' + productPhotoUrl + '">';
          output += '</a>';
          output += '</div>';
          output += '<div class="seller-account-page-list-artwork-text-div">';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<h4 class="font-h4">' + productTitle + '</h4>';
          output += '</a>';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<p class="font-p">Price: $' + productPrice.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</p>';
          output += '</a>';
          output += '<a href="/individual-product?productIdString=' + productId + '">';
          output += '<p class="font-p">Quantity in Stock: ' + productQuantity + '</p>';
          output += '</a>';
          output += '</div>';
          output += '<div class="seller-account-page-list-artwork-button-div">';
          output += '<button type="button" id="sellerAccountPageListArtworkSectionDeleteItemButton" class="btn btn-outline-dark custom-dark-button" onclick="deleteSellerArtwork(' + userId + ', ' + sellerId + ', ' + productId + ')">Delete Item</button>';
          output += '<button type="button" id="sellerAccountPageListArtworkSectionEditItemButton" class="btn btn-outline-dark custom-dark-button" data-toggle="modal" data-target="#sellerAccountPageListArtworkSectionEditItemModal" onclick="editSellerArtworkPopulateModal(' + productId + ')">Edit Item</button>';
          output += '</div>';
          output += '</div>';
          output += '</div>';

          insertDiv.append(output);
        });
      }

    },
    error: function () {
      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearSellerArtwork() {
  $('#sellerAccountPageListArtworkSectionDiv').empty();
}

function deleteSellerArtwork(userId, sellerId, productId) {

  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/delete-seller-artwork/',
    data: JSON.stringify({
      userId: userId,
      sellerId: sellerId,
      productId: productId
    }),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadSellerArtwork();
    },
    error: function (xhr, status, error) {
      var err = eval("(" + xhr.responseText + ")");

      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function addSellerArtwork() {

  clearErrorMessages('#sellerAccountPageListArtworkSectionAddItemModalErrorMessages');

  var addModalForm = document.getElementById('sellerAccountPageListArtworkSectionAddItemModalForm');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/add-seller-artwork/',
    data: new FormData(addModalForm),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function (data, status) {
      loadSellerArtwork();
      $('#sellerAccountPageListArtworkSectionAddItemModal').modal('hide');
    },
    error: function (xhr, status, error) {
      var err = eval("(" + xhr.responseText + ")");

      $('#sellerAccountPageListArtworkSectionAddItemModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });


}

function editSellerArtworkPopulateModal(productId) {

  clearErrorMessages('#sellerAccountPageListArtworkSectionEditItemModalErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/seller/edit-seller-artwork-populate-modal/' + productId,
    success: function (data, status) {

      var title = data.title;
      var description = data.description;
      var price = data.price;
      var quantity = data.quantity;

      var category = "";
      if (data.category != null) {
        category = data.category.categoryId;
      }

      var style = "";
      if (data.style != null) {
        style = data.style.styleId;
      }

      var subject = "";
      if (data.subject != null) {
        subject = data.subject.subjectId;
      }

      var medium = "";
      if (data.medium != null) {
        medium = data.medium.mediumId;
      }

      var material = "";
      if (data.material != null) {
        material = data.material.materialId;
      }


      $('#sellerAccountPageListArtworkSectionEditItemTitle').val(title);
      $('#sellerAccountPageListArtworkSectionEditItemDescription').val(description);
      $('#sellerAccountPageListArtworkSectionEditItemPrice').val(price);
      $('#sellerAccountPageListArtworkSectionEditItemQuantity').val(quantity);
      $('#sellerAccountPageListArtworkSectionEditCategory').val(category);
      $('#sellerAccountPageListArtworkSectionEditStyle').val(style);
      $('#sellerAccountPageListArtworkSectionEditSubject').val(subject);
      $('#sellerAccountPageListArtworkSectionEditMedium').val(medium);
      $('#sellerAccountPageListArtworkSectionEditMaterial').val(material);
      $('#sellerEditArtworkProductId').val(productId);
    },
    error: function () {
      $('#sellerAccountPageListArtworkSectionEditItemModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function editSellerArtworkSubmit() {

  clearErrorMessages('#sellerAccountPageListArtworkSectionEditItemModalErrorMessages');

  var editModalForm = document.getElementById('sellerAccountPageListArtworkSectionEditItemModalForm');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/edit-seller-artwork-submit/',
    data: new FormData(editModalForm),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function (data, status) {
      loadSellerArtwork();
      $('#sellerAccountPageListArtworkSectionEditItemModal').modal('hide');
    },
    error: function (xhr, status, error) {
      var err = eval("(" + xhr.responseText + ")");

      $('#sellerAccountPageListArtworkSectionEditItemModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function loadPublicProfileDetails() {

  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/seller/load-public-profile-details/',
    success: function (data, status) {

      var photoUrl = data.photoUrl;
      var publicName = data.publicName;
      var publicCompany = data.publicCompany;
      var publicIntroduction = data.publicIntroduction;

      $('#sellerAccountPagePublicProfileDetailsImage').attr('src', photoUrl);
      $('#sellerAccountPagePublicProfileDetailsPublicName').val(publicName);
      $('#sellerAccountPagePublicProfileDetailsPublicCompany').val(publicCompany);
      $('#sellerAccountPagePublicProfileDetailsPublicIntroduction').val(publicIntroduction);
    },
    error: function () {
      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function editPublicProfileDetails() {

  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');

  var addModalForm = document.getElementById('sellerAccountPagePublicProfileDetailsForm');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/edit-public-profile-details/',
    data: new FormData(addModalForm),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function (data, status) {
      loadPublicProfileDetails();
    },
    error: function (xhr, status, error) {
      var err = eval("(" + xhr.responseText + ")");

      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function loadSellerOrders() {

  clearSellerOrders();

  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#sellerAccountPageViewSoldOrdersSectionDiv');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/seller/load-seller-orders/',
    success: function (data, status) {

      if (data == 0) {
        insertDiv
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('You do not have any orders.'));
      } else {
        $.each(data, function (index, item) {

          var orderId = item.orderId;
          var date = item.date;
          var firstName = item.firstName;
          var lastName = item.lastName;
          var streetAddress = item.streetAddress;
          var aptUnit = item.aptUnit;
          var city = item.city;
          var zip = item.zip;
          var state = item.state.name;
          var products = item.products;

          output = '<div class="seller-account-page-right-inner-container-list-artwork">';

          $.each(products, function (index, item) {
            var productId = item.productId;
            var title = item.title;
            var description = item.description;
            var productPrice = item.price;
            var quantity = item.quantity;
            var photoUrl = item.photoUrl;

            output += '<div class="seller-account-page-right-inner-list-artwork-details-block">';
            output += '<div class="seller-account-page-list-artwork-image-div">';
            output += '<a href="/individual-product?productIdString=' + productId + '">';
            output += '<img src="' + photoUrl + '">';
            output += '</a>';
            output += '</div>';
            output += '<div class="seller-account-page-list-artwork-text-div">';
            output += '<a href="/individual-product?productIdString=' + productId + '">';
            output += '<h4 class="font-h4">' + title + '</h4>';
            output += '</a>';
            output += '<p class="font-p w-100">Order ID: ' + orderId + '</p>';
            output += '<p class="font-p w-100">Order Date: ' + date + '</p>';
            output += '<p class="font-p w-100">Price: $' + productPrice.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</p>';
            output += '<p class="font-p w-100">' + firstName + ' ' + lastName + '</p>';
            output += '<p class="font-p w-100">' + streetAddress + '</p>';
            output += '<p class="font-p w-100">' + aptUnit + '</p>';
            output += '<p class="font-p w-100">' + city + ', ' + state + ' ' + zip + '</p>';
            output += '</div>';
            output += '</div>';
          });

          output += '</div>';

          insertDiv.append(output);
        });
      }

    },
    error: function () {
      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearSellerOrders() {
  $('#sellerAccountPageViewSoldOrdersSectionDiv').empty();
}

function loadSellerDetails() {

  clearErrorMessages('#sellerAccountPageInnerRightContainerErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/seller/load-seller-details/',
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

      $('#sellerAccountStatus').text('Account Status: ' + acctStatusDescription);
      $('#sellerAccountPageAccountDetailsFirstName').val(firstName);
      $('#sellerAccountPageAccountDetailsLastName').val(lastName);
      $('#sellerAccountPageAccountDetailsStreetAddress').val(streetAddress);
      $('#sellerAccountPageAccountDetailsAptUnitNumber').val(aptUnit);
      $('#sellerAccountPageAccountDetailsCity').val(city);
      $('#sellerAccountPageAccountDetailsState').val(state);
      $('#sellerAccountPageAccountDetailsZip').val(zip);
      $('#sellerAccountPageAccountDetailsUsername').val(username);
      $('#sellerAccountPageAccountDetailsUserId').val(userId);
    },
    error: function () {
      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function changeSellerDetails() {

  var userId = $('#sellerAccountPageAccountDetailsUserId').val();
  var firstName = $('#sellerAccountPageAccountDetailsFirstName').val();
  var lastName = $('#sellerAccountPageAccountDetailsLastName').val();
  var streetAddress = $('#sellerAccountPageAccountDetailsStreetAddress').val();
  var aptUnit = $('#sellerAccountPageAccountDetailsAptUnitNumber').val();
  var city = $('#sellerAccountPageAccountDetailsCity').val();
  var stateId = $('#sellerAccountPageAccountDetailsState').val();
  var zip = $('#sellerAccountPageAccountDetailsZip').val();
  var username = $('#sellerAccountPageAccountDetailsUsername').val();

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/change-seller-details/',
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
      // loadsellerDetails();
      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-success'
          })
          .text("Successfully changed user details."));

      location.reload();

    },
    error: function (xhr, status, error) {

      var err = eval("(" + xhr.responseText + ")");

      $('#sellerAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function changeSellerPassword() {

  var currentPassword = $('#sellerAccountPageAccountDetailsCurrentPassword').val();
  var newPassword = $('#sellerAccountPageAccountDetailsNewPassword').val();
  var newPassword2 = $('#sellerAccountPageAccountDetailsNewPassword2').val();

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/change-seller-password/',
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
      $('#sellerAccountPageAccountDetailsPasswordModalErrorMessages')
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

      $('#sellerAccountPageAccountDetailsPasswordModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function deleteSellerAccount() {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/seller/delete-seller-account/',
    data: JSON.stringify({}),
    headers: {
      // 'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      $('#sellerAccountPageAccountDetailsDeleteAccountModalErrorMessages')
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

      $('#sellerAccountPageAccountDetailsDeleteAccountModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}