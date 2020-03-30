$(document).ready(function () {

  loadDvds();

  $('#deleteConfirmButton').on('click', function () {
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/dvd/' + $('#deleteDvdId').val(),
      success: function () {
        loadDvds();
        $('#deleteModal').modal('hide');
      }
    })
  });

  $('#createConfirmButton').on('click', function () {
    // check for errors and display any that we have
    // pass the input associated with the add form to the validation function
    var haveValidationErrors = checkAndDisplayValidationErrors($('#createForm').find('input'),'#createErrorMessagesDiv');

    // if we have errors, bail out by returning false
    if (haveValidationErrors) {
      return false;
    }

    // if we made it here, there are no errors so make the ajax call
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/dvd',
      data: JSON.stringify({
        title: $('#createDVDTitle').val(),
        releaseYear: $('#createReleaseYear').val(),
        director: $('#createDirector').val(),
        rating: $('#createRating').val(),
        notes: $('#createNotes').val()
      }),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      success: function (data, status) {
        // clear errorMessages
        clearErrorMessages();
        // Clear the form and hide create window and reload the table
        hideCreateForm();
        loadDvds();
      },
      error: function () {
        $('#createErrorMessagesDiv')
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('Error calling web service.  Please try again later.'));
      }
    });
  });

  $('#editConfirmButton').on('click', function () {
    // check for errors and display any that we have
    // pass the input associated with the add form to the validation function
    var haveValidationErrors = checkAndDisplayValidationErrors($('#editForm').find('input'), '#editErrorMessagesDiv');

    // if we have errors, bail out by returning false
    if (haveValidationErrors) {
      return false;
    }

    // if we get to here, there were no errors, so make the Ajax call
    $.ajax({
      type: 'PUT',
      url: 'http://localhost:8080/dvd/' + $('#editDvdId').val(),
      data: JSON.stringify({
        dvdId: $('#editDvdId').val(),
        title: $('#editDVDTitle').val(),
        releaseYear: $('#editReleaseYear').val(),
        director: $('#editDirector').val(),
        rating: $('#editRating').val(),
        notes: $('#editNotes').val()
      }),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      success: function () {
        // clear errorMessages
        clearErrorMessages();
        // Clear the form and hide create window and reload the table
        hideEditForm();
        loadDvds();
      },
      error: function () {
        $('#editErrorMessagesDiv')
          .append($('<li>')
            .attr({
              class: 'list-group-item list-group-item-danger'
            })
            .text('Error calling web service.  Please try again later.'));
      }
    });
  });

});

function loadDvds() {

  // we need to clear the previous content so we don't append to it
  clearDvdsTable();
  clearErrorMessages();

  var contentRows = $('#contentRows');

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/dvds',
    success: function (data, status) {
      $.each(data, function (index, dvd) {
        var title = dvd.title;
        var releaseYear = dvd.releaseYear;
        var director = dvd.director;
        var rating = dvd.rating;
        var dvdId = dvd.dvdId;

        var row = '<tr>';
        row += '<td><a onclick="viewDVD(' + dvdId + ')">' + title + '</a></td>';
        row += '<td>' + releaseYear + '</td>';
        row += '<td>' + director + '</td>';
        row += '<td>' + rating + '</td>';
        row += '<td><a onclick="showEditForm(' + dvdId + ')">Edit</a> | <a onclick="deleteDVD(' + dvdId + ')">Delete</a></td>';
        row += '</tr>';

        contentRows.append(row);
      });
    },
    error: function () {
      $('#defaultErrorMessagesDiv')
        .append($('<li>'))
        .attr({
          class: 'list-group-item list-group-item-danger'
        })
        .text('Error calling web service. Please try again later.')
    }
  });

}

function clearDvdsTable() {

  $('#contentRows').empty();

}

function clearErrorMessages() {
  $('#defaultErrorMessagesDiv').empty();
  $('#defaultErrorMessagesDiv').removeClass('list-group-item list-group-item-danger');
  $('#editErrorMessagesDiv').empty();
  $('#createErrorMessagesDiv').empty();
  $('#viewErrorMessagesDiv').empty();
}

function viewDVD(dvdId) {
  // clear error messages
  clearErrorMessages();

  // show view window and hide default window
  $('#viewDiv').show();
  $('#defaultDiv').hide();

  // get the dvd details from server and then fill and show the details window
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/dvd/' + dvdId,
    success: function (data, status) {
      var title = data.title;
      var releaseYear = data.releaseYear;
      var director = data.director;
      var rating = data.rating;
      var notes = data.notes;
      var dvdId = data.dvdId;

      $('#viewHeaderDiv').append('<h2>' + title + '</h2>');

      var result = '<table>';
      result += '<tr>';
      result += '<td>Release Year</td>';
      result += '<td>' + releaseYear + '</td>';
      result += '</tr>';
      result += '<tr>';
      result += '<td>Director</td>';
      result += '<td>' + director + '</td>';
      result += '</tr>';
      result += '<tr>';
      result += '<td>Rating</td>';
      result += '<td>' + rating + '</td>';
      result += '</tr>';
      result += '<tr>';
      result += '<td>Notes</td>';
      result += '<td>' + notes + '</td>';
      result += '</tr>';
      result += '</table>';

      $('#viewDivTableRows').append(result);
    },
    error: function () {
      $('#viewErrorMessagesDiv')
        .append($('<li>'))
        .attr({
          class: 'list-group-item list-group-item-danger'
        })
        .text('Error calling web service. Please try again later.')
    }
  });
}

function deleteDVD(dvdId) {
  // set hidden input value
  $('#deleteDvdId').val(dvdId);

  // show delete modal ---- data-toggle="modal" data-target="#deleteModal"
  $('#deleteModal').modal('show');
}

function hideCreateForm() {
  // show default div and hide create div
  $('#defaultDiv').show();
  $('#createDiv').hide();
  // clear create form
  $('#createDVDTitle').val('');
  $('#createReleaseYear').val('');
  $('#createDirector').val('');
  $('#createRating').val('');
  $('#createNotes').val('');
  clearErrorMessages();
}

function showCreateForm() {
  // hide default div and show create div
  $('#defaultDiv').hide();
  $('#createDiv').show();
  clearErrorMessages();
}

function hideViewWindow() {
  // hide view div and show default div
  $('#viewDiv').hide();
  $('#defaultDiv').show();

  // reload dvds
  loadDvds();

  // clear view divs
  $('#viewHeaderDiv').empty();
  $('#viewDivTableRows').empty();
  clearErrorMessages();
}

function hideDeleteModal() {
  loadDvds();
  $('#deleteModal').modal('hide');
}

function showEditForm(dvdId) {
  // clear errorMessages
  clearErrorMessages();
  // get the contact details from the server and then fill and show the
  // form on success
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/dvd/' + dvdId,
    success: function (data, status) {
      $('#editHeaderDiv').append('<h2>Edit DVD: ' + data.title + '</h2>')

      $('#editDVDTitle').val(data.title);
      $('#editReleaseYear').val(data.releaseYear);
      $('#editDirector').val(data.director);
      $('#editRating').val(data.rating);
      $('#editNotes').val(data.notes);
      $('#editDvdId').val(data.dvdId);
    },
    error: function () {
      $('editErrorMessagesDiv')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });

  // hide default div and show edit div
  $('#defaultDiv').hide();
  $('#editDiv').show();
}

function hideEditForm() {
  // clear edit form
  $('#editDVDTitle').val('');
  $('#editReleaseYear').val('');
  $('#editDirector').val('');
  $('#editRating').val('');
  $('#editNotes').val('');
  $('#editDvdId').val('');
  // show default div and hide edit div
  $('#defaultDiv').show();
  $('#editDiv').hide();
  // clear view divs
  $('#editHeaderDiv').empty();
  clearErrorMessages();
}

function showSearchResults() {
  // check for errors and display any that we have
  // pass the input associated with the add form to the validation function
  var haveValidationErrors = checkAndDisplayValidationErrors($('#searchForm').find('input'), '#defaultErrorMessagesDiv');

  var searchCategory = $('#searchCategory').val();
  var searchTerm = $('#searchBar').val();
  console.log(typeof searchTerm);

  if (searchCategory != 'title' && searchCategory != 'year' && searchCategory != 'director' && searchCategory != 'rating') {
    haveValidationErrors = true;
    $('#defaultErrorMessagesDiv')
      .append($('<li>'))
      .attr({
        class: 'list-group-item list-group-item-danger'
      })
      .text('Search category needs to be selected.');
  }

  // if we have errors, bail out by returning false
  if (haveValidationErrors) {
    return false;
  }

  clearErrorMessages();

  // clear existing default table
  clearDvdsTable();

  var contentRows = $('#contentRows');

  // load results
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/dvds/' + searchCategory + '/' + searchTerm,
    success: function (data, status) {
      $.each(data, function (index, dvd) {
        var title = dvd.title;
        var releaseYear = dvd.releaseYear;
        var director = dvd.director;
        var rating = dvd.rating;
        var dvdId = dvd.dvdId;

        var row = '<tr>';
        row += '<td><a onclick="viewDVD(' + dvdId + ')">' + title + '</a></td>';
        row += '<td>' + releaseYear + '</td>';
        row += '<td>' + director + '</td>';
        row += '<td>' + rating + '</td>';
        row += '<td><a onclick="showEditForm(' + dvdId + ')">Edit</a> | <a onclick="deleteDVD(' + dvdId + ')">Delete</a></td>';
        row += '</tr>';

        contentRows.append(row);
      });
    },
    error: function () {
      $('#defaultErrorMessagesDiv')
        .append($('<li>'))
        .attr({
          class: 'list-group-item list-group-item-danger'
        })
        .text('Error calling web service. Please try again later.')
    }
  });

  $('#searchCategory').val('');
  $('#searchBar').val('');
}

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
      var errorField = $('label[for=' + this.id + ']').text();
      errorMessages.push(errorField + ' ' + this.validationMessage);
    }
  });

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