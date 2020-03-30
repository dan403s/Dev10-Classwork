$(document).ready(function () {

  defaultPageLoadView();

  $('#adminAccountPageAccountDetailsEditPasswordModal').on('hidden.bs.modal', function (e) {
    $(this)
      .find("input,textarea,select")
      .val('')
      .end()
      .find("input[type=checkbox], input[type=radio]")
      .prop("checked", "")
      .end();
    clearErrorMessages('#adminAccountPageAccountDetailsPasswordModalErrorMessages');
    clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');
  })

  $('#adminAccountPageAccountDetailsSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#adminAccountPageAccountDetailsForm').find('input'), $('#adminAccountPageAccountDetailsForm').find('select'), '#adminAccountPageInnerRightContainerErrorMessages');


    if (haveValidationErrors) {
      return false;
    }

    changeAdminDetails();
  });

  $('#adminAccountPageAccountDetailsEditPasswordSaveButton').on('click', function () {
    var haveValidationErrors = checkAndDisplayValidationErrors($('#adminAccountPageAccountDetailsPasswordModalForm').find('input'), $('#adminAccountPageAccountDetailsPasswordModalForm').find('select'), '#adminAccountPageAccountDetailsPasswordModalErrorMessages');


    if (haveValidationErrors) {
      return false;
    }

    changeAdminPassword();
  });

});

function defaultPageLoadView() {
  $('#adminPendingApprovalSection').show();
  $('#adminAccountPageManageUsersSection').hide();
  $('#adminAccountPageAccountDetailsSection').hide();
  loadPendingApprovals();

  loadManageSellers();

  loadManageBuyers();

  loadAdminDetails();
}

function adminAccountPageShowPendingApproval() {
  $('#adminPendingApprovalSection').show();
  $('#adminAccountPageManageUsersSection').hide();
  $('#adminAccountPageAccountDetailsSection').hide();
  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');
  loadPendingApprovals();
}

function adminAccountPageShowManageUsers() {
  $('#adminPendingApprovalSection').hide();
  $('#adminAccountPageManageUsersSection').show();
  $('#adminAccountPageAccountDetailsSection').hide();
  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');
  loadManageSellers();
  loadManageBuyers();
}

function adminAccountPageShowAccountDetails() {
  $('#adminPendingApprovalSection').hide();
  $('#adminAccountPageManageUsersSection').hide();
  $('#adminAccountPageAccountDetailsSection').show();
  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');
  loadAdminDetails();
}

function adminAccountLogoutButton() {
  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');
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
      if (this.id == 'adminAccountPageAccountDetailsNewPassword') {
        errorMessages.push('New password needs to have at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and have at least 8 characters total');
      } else if (this.id == 'adminAccountPageAccountDetailsNewPassword2') {
        errorMessages.push('New password entered again needs to have at least 1 uppercase, 1 lowercase, 1 digit, 1 special character, and have at least 8 characters total');
      } else if (this.id == 'adminAccountPageAccountDetailsZip') {
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
  var newPassword = $('#adminAccountPageAccountDetailsNewPassword').val();
  var newPassword2 = $('#adminAccountPageAccountDetailsNewPassword2').val();

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

function adminAccountPageEditFormField(input) {
  if ($(input).hasClass('readonly')) {
    $(input).removeClass('readonly');
  } else {
    $(input).addClass('readonly');
  }
}

function loadPendingApprovals() {

  clearPendingApprovals();

  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#adminPendingApprovalSectionDiv');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/admin/load-pending-approvals/',
    success: function (data, status) {
      $.each(data, function (index, item) {

        var sellerId = item.sellerId;
        var publicName = item.publicName;
        var publicCompany = item.publicCompany;
        var publicIntroduction = item.publicIntroduction;
        var photoUrl = item.photoUrl;
        var userId = item.user.userId;
        var firstName = item.user.firstName;
        var lastName = item.user.lastName;
        var streetAddress = item.user.streetAddress;
        var aptUnit = item.user.aptUnit;
        var city = item.user.city;
        var zip = item.user.zip;
        var username = item.user.username;
        var acctStatus = item.user.acctStatus;
        var acctStatusDescription = '';
        var userRole = item.user.userRole;
        var state = item.user.state.name;

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

        var output = '<div class="admin-account-page-pending-approval-right-inner-container-user">';
        output += '<h3 class="font-h3"><span>' + firstName + ' ' + lastName + '</span></h3>';
        output += '<p class="font-p"><span>' + streetAddress + '</span></p>';
        output += '<p class="font-p"><span>' + aptUnit + '</span></p>';
        output += '<p class="font-p"><span>' + city + ', ' + state + ' ' + zip + '</span></p>';
        output += '<p class="font-p"><span>' + username + '</span></p>';
        output += '<p class="font-p"><b><span>Account Type: Seller</span></b></p>';
        output += '<p class="font-p"><b><span>Account Status: ' + acctStatusDescription + '</span></b></p>';
        output += '<a type="button" id="adminAccountPagePendingApprovalViewButton" class="btn btn-outline-dark custom-dark-button" href="/seller-public-page-admin-view?sellerIdString=' + sellerId + '">View Profile</a>';
        output += '<a type="button" id="adminAccountPagePendingApprovalApproveButton" class="btn btn-outline-dark custom-dark-button" onclick="approveSeller(' + sellerId + ')">Approve</a>';
        output += '<a type="button" id="adminAccountPagePendingApprovalRejectButton" class="btn btn-outline-dark custom-dark-button" onclick="rejectSeller(' + sellerId + ')">Reject</a>';
        output += '</div>';

        insertDiv.append(output);
      });
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearPendingApprovals() {
  $('#adminPendingApprovalSectionDiv').empty();
}

function approveSeller(sellerId) {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/approve-seller/',
    data: JSON.stringify({
      sellerIdString: sellerId
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadPendingApprovals();
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function rejectSeller(sellerId) {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/reject-seller/',
    data: JSON.stringify({
      sellerIdString: sellerId
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadPendingApprovals();
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function loadManageSellers() {

  clearManageSellers();

  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#adminAccountPageManageSellersSection');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/admin/load-manage-sellers/',
    success: function (data, status) {
      $.each(data, function (index, item) {

        var sellerId = item.sellerId;
        var publicName = item.publicName;
        var publicCompany = item.publicCompany;
        var publicIntroduction = item.publicIntroduction;
        var photoUrl = item.photoUrl;
        var userId = item.user.userId;
        var firstName = item.user.firstName;
        var lastName = item.user.lastName;
        var streetAddress = item.user.streetAddress;
        var aptUnit = item.user.aptUnit;
        var city = item.user.city;
        var zip = item.user.zip;
        var username = item.user.username;
        var acctStatus = item.user.acctStatus;
        var acctStatusDescription = '';
        var userRole = item.user.userRole;
        var state = item.user.state.name;
        var suspendButtonText = "Suspend";
        var deleteButtonText = "Delete User";
        var suspendedOrUnsuspended = 2;
        var deletedOrUndeleted = 3;

        if (acctStatus == 1) {
          acctStatusDescription = "Active";
        } else if (acctStatus == 2) {
          acctStatusDescription = "Suspended";
          suspendButtonText = "Unsuspend";
          suspendedOrUnsuspended = 1;
        } else if (acctStatus == 3) {
          acctStatusDescription = "Deleted";
          deleteButtonText = "Undelete User";
          deletedOrUndeleted = 1;
        } else if (acctStatus == 4) {
          acctStatusDescription = "Pending Approval";
        } else if (acctStatus == 5) {
          acctStatusDescription = "Rejected";
        }

        var output = '<div class="admin-account-page-manage-users-right-inner-container-user">';
        output += '<h3 class="font-h3"><span>' + firstName + ' ' + lastName + '</span></h3>';
        output += '<p class="font-p"><span>' + streetAddress + '</span></p>';
        output += '<p class="font-p"><span>' + aptUnit + '</span></p>';
        output += '<p class="font-p"><span>' + city + ', ' + state + ' ' + zip + '</span></p>';
        output += '<p class="font-p"><span>' + username + '</span></p>';
        output += '<p class="font-p"><b><span>Account Type: Seller</span></b></p>';
        output += '<p class="font-p"><b><span>Account Status: ' + acctStatusDescription + '</span></b></p>';
        output += '<a type="button" id="adminAccountPageManageUsersViewButton" class="btn btn-outline-dark custom-dark-button" href="/seller-public-page-admin-view?sellerIdString=' + sellerId + '">View Profile</a>';
        output += '<a type="button" id="adminAccountPageManageUsersSuspendUnsuspendButton" class="btn btn-outline-dark custom-dark-button" onclick="suspendSeller(' + sellerId + ', ' + suspendedOrUnsuspended + ')">' + suspendButtonText + '</a>';
        output += '<a type="button" id="adminAccountPageManageUsersDeleteAccountButton" class="btn btn-outline-dark custom-dark-button" onclick="deleteSeller(' + sellerId + ', ' + deletedOrUndeleted + ')">' + deleteButtonText + '</a>';
        output += '</div>';


        insertDiv.append(output);
      });
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearManageSellers() {
  $('#adminAccountPageManageSellersSection').empty();
}

function suspendSeller(sellerId, suspendedOrUnsuspended) {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/suspend-seller/',
    data: JSON.stringify({
      sellerIdString: sellerId,
      suspendedOrUnsuspended: suspendedOrUnsuspended
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadManageSellers();
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function deleteSeller(sellerId, deletedOrUndeleted) {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/delete-seller/',
    data: JSON.stringify({
      sellerIdString: sellerId,
      deletedOrUndeleted: deletedOrUndeleted
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadManageSellers();
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function loadManageBuyers() {

  clearManageBuyers();

  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');

  // grab the div that will hold the data returned
  var insertDiv = $('#adminaAccountPageManageBuyersSection');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/admin/load-manage-buyers/',
    success: function (data, status) {
      $.each(data, function (index, item) {

        var buyerId = item.buyerId;
        var userId = item.user.userId;
        var firstName = item.user.firstName;
        var lastName = item.user.lastName;
        var streetAddress = item.user.streetAddress;
        var aptUnit = item.user.aptUnit;
        var city = item.user.city;
        var zip = item.user.zip;
        var username = item.user.username;
        var acctStatus = item.user.acctStatus;
        var acctStatusDescription = '';
        var userRole = item.user.userRole;
        var state = item.user.state.name;
        var suspendButtonText = "Suspend";
        var deleteButtonText = "Delete User";
        var suspendedOrUnsuspended = 2;
        var deletedOrUndeleted = 3;

        if (acctStatus == 1) {
          acctStatusDescription = "Active";
        } else if (acctStatus == 2) {
          acctStatusDescription = "Suspended";
          suspendButtonText = "Unsuspend";
          suspendedOrUnsuspended = 1;
        } else if (acctStatus == 3) {
          acctStatusDescription = "Deleted";
          deleteButtonText = "Undelete User";
          deletedOrUndeleted = 1;
        } else if (acctStatus == 4) {
          acctStatusDescription = "Pending Approval";
        } else if (acctStatus == 5) {
          acctStatusDescription = "Rejected";
        }

        var output = '<div class="admin-account-page-manage-users-right-inner-container-user">';
        output += '<h3 class="font-h3"><span>' + firstName + ' ' + lastName + '</span></h3>';
        output += '<p class="font-p"><span>' + streetAddress + '</span></p>';
        output += '<p class="font-p"><span>' + aptUnit + '</span></p>';
        output += '<p class="font-p"><span>' + city + ', ' + state + ' ' + zip + '</span></p>';
        output += '<p class="font-p"><span>' + username + '</span></p>';
        output += '<p class="font-p"><b><span>Account Type: Buyer</span></b></p>';
        output += '<p class="font-p"><b><span>Account Status: ' + acctStatusDescription + '</span></b></p>';
        output += '<a type="button" id="adminAccountPageManageUsersSuspendUnsuspendButton" class="btn btn-outline-dark custom-dark-button" onclick="suspendBuyer(' + buyerId + ', ' + suspendedOrUnsuspended + ')">' + suspendButtonText + '</a>';
        output += '<a type="button" id="adminAccountPageManageUsersDeleteAccountButton" class="btn btn-outline-dark custom-dark-button" onclick="deleteBuyer(' + buyerId + ', ' + deletedOrUndeleted + ')">' + deleteButtonText + '</a>';
        output += '</div>';


        insertDiv.append(output);
      });
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function clearManageBuyers() {
  $('#adminaAccountPageManageBuyersSection').empty();
}

function suspendBuyer(buyerId, suspendedOrUnsuspended) {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/suspend-buyer/',
    data: JSON.stringify({
      buyerIdString: buyerId,
      suspendedOrUnsuspended: suspendedOrUnsuspended
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadManageBuyers();
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function deleteBuyer(buyerId, deletedOrUndeleted) {

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/delete-buyer/',
    data: JSON.stringify({
      buyerIdString: buyerId,
      deletedOrUndeleted: deletedOrUndeleted
    }),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    // 'dataType': 'json',
    success: function (data, status) {
      loadManageBuyers();
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function loadAdminDetails() {

  clearErrorMessages('#adminAccountPageInnerRightContainerErrorMessages');

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'GET',
    url: '/admin/load-admin-details/',
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

      $('#adminAccountPageAccountDetailsFirstName').val(firstName);
      $('#adminAccountPageAccountDetailsLastName').val(lastName);
      $('#adminAccountPageAccountDetailsStreetAddress').val(streetAddress);
      $('#adminAccountPageAccountDetailsAptUnitNumber').val(aptUnit);
      $('#adminAccountPageAccountDetailsCity').val(city);
      $('#adminAccountPageAccountDetailsState').val(state);
      $('#adminAccountPageAccountDetailsZip').val(zip);
      $('#adminAccountPageAccountDetailsUsername').val(username);
      $('#adminAccountPageAccountDetailsUserId').val(userId);
    },
    error: function () {
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

}

function changeAdminDetails() {

  var userId = $('#adminAccountPageAccountDetailsUserId').val();
  var firstName = $('#adminAccountPageAccountDetailsFirstName').val();
  var lastName = $('#adminAccountPageAccountDetailsLastName').val();
  var streetAddress = $('#adminAccountPageAccountDetailsStreetAddress').val();
  var aptUnit = $('#adminAccountPageAccountDetailsAptUnitNumber').val();
  var city = $('#adminAccountPageAccountDetailsCity').val();
  var stateId = $('#adminAccountPageAccountDetailsState').val();
  var zip = $('#adminAccountPageAccountDetailsZip').val();
  var username = $('#adminAccountPageAccountDetailsUsername').val();

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/change-admin-details/',
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
      // loadAdminDetails();
      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-success'
          })
          .text("Successfully changed user details."));

      // location.reload();
      window.location.assign('/index');

    },
    error: function (xhr, status, error) {

      var err = eval("(" + xhr.responseText + ")");

      $('#adminAccountPageInnerRightContainerErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}

function changeAdminPassword() {

  var currentPassword = $('#adminAccountPageAccountDetailsCurrentPassword').val();
  var newPassword = $('#adminAccountPageAccountDetailsNewPassword').val();
  var newPassword2 = $('#adminAccountPageAccountDetailsNewPassword2').val();

  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });

  $.ajax({
    type: 'POST',
    url: '/admin/change-admin-password/',
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
      $('#adminAccountPageAccountDetailsPasswordModalErrorMessages')
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

      $('#adminAccountPageAccountDetailsPasswordModalErrorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text(err.message));
    }
  });

}