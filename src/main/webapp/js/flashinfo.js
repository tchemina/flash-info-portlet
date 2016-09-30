var flashInfoPortlet = flashInfoPortlet || {};


flashInfoPortlet.init = function($, namespace, portletId) {


    (function initContainer($, namespace, portletId) {
        $(window).bind('load', function () {

            //console.log("namespace : " + namespace);
            if ($(namespace + ' #myCarousel_' + portletId + " .item").length > 0) {
                var defaults      = {
                    selector:          namespace + ' #myCarousel_' + portletId + " .item img"   ,
                    parent:               namespace + ' #myCarousel_' + portletId + " .item",
                    exclude:              [ 'rgb(0,0,0)', 'rgb(255,255,255)' ],
                    normalizeTextColor:   false,
                    normalizedTextColors:  {
                        light:      "#fff",
                        dark:       "#000"
                    },
                    lumaClasses:  {
                        light:      "ab-light",
                        dark:       "ab-dark"
                    }
                };
                $.adaptiveBackground.run(defaults);

                // évènement de fin de recherche de couleur dominante d'une image
                $(namespace + ' #myCarousel_' + portletId + " .item img").on('ab-color-found', function(ev,payload) {
                    //console.log(payload.color);   // The dominant color in the image.
                    //console.log(payload.palette); // The color palette found in the image.
                    //console.log(ev);   // The jQuery.Event object
                    colorCarouselElements($, namespace, portletId);
                });

                 // évènement de slide du carousel terminé
                $(namespace + ' #myCarousel_' + portletId).on('slid.bs.carousel', function (e) {
                    colorCarouselElements($, namespace, portletId);
                });
            } else {
                console.log("FlashInfo doesn't have element to show !")
            }
        });

    })($, namespace, portletId);

    function colorCarouselElements($, namespace, portletId) {
        var selector = $(namespace + ' #myCarousel_' + portletId + ' .item.active');
        if (selector && selector.length > 0) {
            if(selector.hasClass("ab-dark")) {
                $(namespace + ' #myCarousel_' + portletId).removeClass("ab-light").addClass("ab-dark");
            } else {
                $(namespace + ' #myCarousel_' + portletId).removeClass("ab-dark").addClass("ab-light");
            }
        }
    }
};
