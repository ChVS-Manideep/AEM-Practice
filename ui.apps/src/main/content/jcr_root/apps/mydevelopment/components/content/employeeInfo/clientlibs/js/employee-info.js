/* global jQuery, Coral */
(function ($, Coral) {
    "use strict";

    console.log(" --------CLIENTLIBS LOADED------- ");

    var registry = $(window).adaptTo("foundation-registry");

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=regex.name.validation]",
        validate: function (element) {
            let el = $(element);
            let pattern = /[0-9]/;
            let value = el.val();
            if (pattern.test(value)) {
                return "Please add only Letters in name";
            }
        }
    });



    registry.register("foundation.validation.validator", {
        selector: "[data-validation=regex.number.validation]",
        validate: function (element) {
            let el = $(element);
            let pattern = /^[0-9]{10}$/;
            let value = el.val();
            if (!pattern.test(value)) {
                return "Please enter only valid phone number format(10 digits)";
            }
        }
    });
})(jQuery, Coral);
