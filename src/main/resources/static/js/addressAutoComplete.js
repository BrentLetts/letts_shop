// This sample uses the Autocomplete widget to help the user select a
// place, then it retrieves the address components associated with that
// place, and then it populates the form fields with those details.

var placeSearch, autocomplete;

function initAutocomplete() {
  // Create the autocomplete object, restricting the search predictions to
  // geographical location types.
  autocomplete = new google.maps.places.Autocomplete(
      document.getElementById('autocomplete'), {types: ['geocode']});

  // Avoid paying for data that you don't need by restricting the set of
  // place fields that are returned to just the address components.
  autocomplete.setFields(['address_component']);

  // When the user selects an address from the drop-down, populate the
  // address fields in the form.
  autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
  // Get the place details from the autocomplete object.
  var place = autocomplete.getPlace();

    // Get each component of addressAutoCompleteDto and set it with the value we
    // want to get from the place details
    for(var component in addressAutoCompleteDto){
        addressAutoCompleteDto[component] = "long_name";
    }
  // Get each component of the address from the place details,
  // and then fill-in the corresponding field on the form.
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    if (addressAutoCompleteDto[addressType]) {
      var val = place.address_components[i][addressAutoCompleteDto[addressType]];
      addressAutoCompleteDto[addressType] = val;
      if(document.getElementById(addressType) != null){
          document.getElementById(addressType).disabled = false;
          document.getElementById(addressType).value = addressAutoCompleteDto[addressType];
        }
    }
  }

  // joining the street number and the route from the place details
  document.getElementById('autocomplete').disabled = false;
  document.getElementById('autocomplete').value =
                addressAutoCompleteDto['street_number'] + ' ' +
                addressAutoCompleteDto['route'];

  document.getElementById('aptNumber').disabled = false;

}

// TODO: Fill in the Geo-info
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var geolocation = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
      var circle = new google.maps.Circle(
          {center: geolocation, radius: position.coords.accuracy});
      autocomplete.setBounds(circle.getBounds());
    });
  }
}