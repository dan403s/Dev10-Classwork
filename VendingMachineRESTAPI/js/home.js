// declare variables
// total money counter
var totalMoney = 0;
// if user doesn't make purchase, manually calculate change, if user does make purchase, use returned JSON from AJAX
var didUserMakePurchase = false;
// id and price of item selected before purchase button
var itemSelectedBeforePurchaseId = '';
var itemSelectedBeforePurchasePrice = 0;
// change due
var changeQuarters = 0;
var changeDimes = 0;
var changeNickels = 0;
var changePennies = 0;

$(document).ready(function () {

  // initial load items
  loadItems();

  // make purchase
  $('#makePurchaseButton').on('click', function () {
    clearErrorMessages();
    $('#change').val('');

    $.ajax({
      type: 'POST',
      url: 'http://tsg-vending.herokuapp.com/money/' + (totalMoney / 100).toFixed(2) + '/item/' + itemSelectedBeforePurchaseId,
      // headers: {
      //   'Accept': 'application/json',
      //   'Content-Type': 'application/json'
      // },
      // 'dataType': 'json',
      success: function (data, status) {
        // reload items
        loadItems();
        // deduct from totalMoney variable and send out to the form
        totalMoney -= itemSelectedBeforePurchasePrice;
        $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
        // set change
        changeQuarters = data.quarters;
        changeDimes = data.dimes;
        changeNickels = data.nickels;
        changePennies = data.pennies;
        didUserMakePurchase = true;
        $('#messages').val('Thank you!!!');
        returnChange();
        // console.log(changeQuarters);
        // console.log(changeDimes);
        // console.log(changeNickels);
        // console.log(changePennies);

        // console.log("total money: " + totalMoney);
        // console.log("total money: " + itemSelectedBeforePurchasePrice);
      },
      error: function (xhr, status, error) {
        var err = eval("(" + xhr.responseText + ")");
        $('#messages').val(err.message);
        loadItems();
      }
    });
  });

});

// initial load items
function loadItems() {
  // we need to clear the previous content so we don't append to it
  clearErrorMessages();
  clearItems();

  // grab the div that will hold the data returned
  var contentButtons = $('#vendingMachineSelectionContainerDiv');
  var count = 0;

  $.ajax({
    type: 'GET',
    url: 'http://tsg-vending.herokuapp.com/items',
    success: function (data, status) {
      $.each(data, function (index, item) {
        var itemId = item.id;
        var itemName = item.name;
        var itemPrice = item.price;
        var itemQuantity = item.quantity;
        count++;

        if (item.quantity == 0) {
          var outputButton = '<button onclick="selectItem(' + itemId + ', ' + count + ',' + itemPrice + ')" class="vending-machine-item-button empty-item"><div class="vending-machine-item-text">';
        } else {
          var outputButton = '<button onclick="selectItem(' + itemId + ', ' + count + ',' + itemPrice + ')" class="vending-machine-item-button"><div class="vending-machine-item-text">';
        }


        outputButton += '<p>' + count + '</p>';
        outputButton += '<p>' + itemName + '</p>';
        outputButton += '<p>$' + itemPrice.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</p>';
        outputButton += '<p>Quantity Left: ' + itemQuantity + '</p>';
        outputButton += '</div>';
        outputButton += '<input type="hidden" id="' + itemId + '">';
        outputButton += '<input type="hidden" id="' + itemName + '">';
        outputButton += '<input type="hidden" id="' + itemPrice + '">';
        outputButton += '<input type="hidden" id="' + itemQuantity + '">';

        contentButtons.append(outputButton);
      });
    },
    error: function () {
      $('#errorMessages')
        .append($('<li>')
          .attr({
            class: 'list-group-item list-group-item-danger'
          })
          .text('Error calling web service.  Please try again later.'));
    }
  });
}

// clear items from page
function clearItems() {
  $('#vendingMachineSelectionContainerDiv').empty();
}

function clearErrorMessages() {
  $('#errorMessages').empty();
}

// add money functions
function addDollar() {
  totalMoney += 100;
  $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
  $('#change').val('');
  $('#messages').val('');
}

function addQuarter() {
  totalMoney += 25;
  $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
  $('#change').val('');
  $('#messages').val('');
}

function addDime() {
  totalMoney += 10;
  $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
  $('#change').val('');
  $('#messages').val('');
}

function addNickel() {
  totalMoney += 5;
  $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
  $('#change').val('');
  $('#messages').val('');
}

// select item by clicking it
function selectItem(itemId, itemNumber, itemPrice) {
  $('#messages').val('');
  $('#change').val('');
  $('#itemNumber').val('');
  $('#itemNumber').val(itemNumber);
  itemSelectedBeforePurchaseId = itemId;
  itemSelectedBeforePurchasePrice = itemPrice * 100;
}

// return change method
function returnChange() {
  if (didUserMakePurchase) {
    // use change variables above set when purchase was made
    displayChange();
    resetToDefault();
    $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
    $('#itemNumber').val('');
  } else {
    // calculate change because no purchase was made
    while (totalMoney > 0) {
      if (totalMoney >= 25) {
        changeQuarters++;
        totalMoney -= 25;
      } else if (totalMoney >= 10) {
        changeDimes++;
        totalMoney -= 10;
      } else if (totalMoney >= 5) {
        changeNickels++;
        totalMoney -= 5;
      } else if (totalMoney >= 1) {
        changePennies++;
        totalMoney -= 1;
      }
    }

    displayChange();
    resetToDefault();
    $('#totalMoneyIn').val((totalMoney / 100).toFixed(2));
    $('#messages').val('');
    $('#itemNumber').val('');
    loadItems();
  }
}

// reset game after change has been returned
function resetToDefault() {
  totalMoney = 0;
  didUserMakePurchase = false;
  changeQuarters = 0;
  changeDimes = 0;
  changeNickels = 0;
  changePennies = 0;
  itemSelectedBeforePurchaseId = '';
  itemSelectedBeforePurchasePrice = 0;
}

function displayChange() {
  var changeOutputArray = [];
  var changeOutputString = '';

  var quarterSingularOrPlural = changeQuarters == 1 ? ' quarter' : ' quarters';
  var dimeSingularOrPlural = changeDimes == 1 ? ' dime' : ' dimes';
  var nickelSingularOrPlural = changeNickels == 1 ? ' nickel' : ' nickels';
  var pennySingularOrPlural = changePennies == 1 ? ' penny' : ' pennies';

  if (changeQuarters > 0) {
    changeOutputArray.push(changeQuarters + quarterSingularOrPlural);
  }

  if (changeDimes > 0) {
    changeOutputArray.push(changeDimes + dimeSingularOrPlural);
  }

  if (changeNickels > 0) {
    changeOutputArray.push(changeNickels + nickelSingularOrPlural);
  }

  if (changePennies > 0) {
    changeOutputArray.push(changePennies + pennySingularOrPlural);
  }

  if (changeQuarters == 0 && changeDimes == 0 && changeNickels == 0 && changePennies == 0) {
    changeOutputArray.push('No change due.');
  }

  changeOutputString = changeOutputArray.join(', ');

  $('#change').val(changeOutputString);
}

// TO DO OR CHECK STILL:
// circled items in requirements:
// 3. Ask Sean about change input being cleared if total $ in is 0
// 6. Pressing the change return button will clear the inputs, update the item list, and prepare for the next purchase