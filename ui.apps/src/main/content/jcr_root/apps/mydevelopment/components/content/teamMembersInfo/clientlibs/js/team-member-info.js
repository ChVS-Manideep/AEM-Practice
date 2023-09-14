/* global jQuery, Coral */
(function ($, Coral) {
  "use strict";

  console.log(" --------CLIENTLIBS LOADED------- ");

  var registry = $(window).adaptTo("foundation-registry");

  registry.register("foundation.validation.validator", {
    selector: "[data-validation=multifield.validation]",
    validate: function (element) {
      let el = $(element);
      let max = el.data("max-items");
      let min = el.data("min-items");
      let items = el.children("coral-multifield-item").length;
      let domitems = el.children("coral-multifield-item");
      if (items > max) {
        domitems.last().remove();
        return "You can add only " + max + " team members."
      }
      if (items < min) {
        return "You should add a minimum of " + min + " team members"
      }
    }
  });

})(jQuery, Coral);
