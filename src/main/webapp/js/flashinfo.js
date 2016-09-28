var flashInfoPortlet = flashInfoPortlet || {};


flashInfoPortlet.init = function($, namespace, portletId) {


	(function initContainer($, namespace, portletId) {
		$(window).bind('load', function () {

			//console.log("namespace : " + namespace);
			if ($(namespace + ' #myCarousel_' + portletId + " .item").length > 0) {
				initBackgroundChecker($, namespace, portletId);
				initCaption($, namespace, portletId);

				// evenement de slid du carousel terminé
				// BackgroundCheck en a besoin pour definir l'element sur lequel il va s'appliquer
				$(namespace + ' #myCarousel_' + portletId).on('slid.bs.carousel', function (e) {
					checkImageBackground($, namespace, portletId, e);
					colorizeCaption($, namespace, portletId, e);
				});
			} else {
				console.log("FlashInfo doesn't have element to show !")
			}
		});

	})($, namespace, portletId);

	function initBackgroundChecker($, namespace, portletId) {
		var src_init = $(namespace + ' .item').find('img')[0];
		var bcTarget = $(namespace + ' .item').children('.carousel-caption');
		if(src_init && bcTarget) {
			BackgroundCheck.init({
				targets: bcTarget,
				images: src_init,
				minOverlap: 0,
				debug: true
			});
		}
	}

	function checkImageBackground($, namespace, portletId, e) {
		var sourceImage = $(namespace + ' #myCarousel_' + portletId + ' .active').find('img')[0];
		var targetCaption = $(namespace + ' #myCarousel_' + portletId + ' .active').children('.carousel-caption');
		BackgroundCheck.set('images', sourceImage);
		BackgroundCheck.set('targets', targetCaption);
	}

	function getOpacityOf(carouselCaption) {
		var isBackgroundDark = carouselCaption.hasClass('background--dark');
		var isBackgroundLight = carouselCaption.hasClass('background--light');
		var isBackgroundComplex = carouselCaption.hasClass('background--complex');
		var opacityOfCaption = 0;
		if (isBackgroundLight || isBackgroundDark) {
			opacityOfCaption = 0.6;
		}
		if (isBackgroundComplex) {
			opacityOfCaption = 0.9;
		}
		return opacityOfCaption;

	}

	function initCaption($, namespace, portletId) {
		var src_init = $(namespace + ' .item').find('img')[0];
		if (src_init) {
			var colorThief_init = new ColorThief();
			var color_init = colorThief_init.getColor(src_init);
			var carouselCaption = $(namespace + ' .item').children('.carousel-caption');
			var opacityOfCaption = getOpacityOf(carouselCaption);
			$(namespace + ' .item').children('.carousel-caption').css('background-color',
				"rgba(" + color_init + "," + opacityOfCaption + ")");
			colorText($, namespace, portletId, color_init, null);
		}
	}

	function colorizeCaption($, namespace, portletId, e) {
		var sourceImage = $(namespace + ' #myCarousel_' + portletId + ' .active').find('img')[0];
		var colorThief = new ColorThief();
		var color = colorThief.getColor(sourceImage);
		var carouselCaption = $(namespace + ' #myCarousel_' + portletId + ' .active').children('.carousel-caption');
		var opacityOfCaption = getOpacityOf(carouselCaption);
		carouselCaption.css("background-color", "rgba(" + color + "," + opacityOfCaption + ")");
		colorText($, namespace, portletId, color, e);
	}

	function colorText($, namespace, portletId, color, e) {
		var o = Math.round(((parseInt(color[0]) * 299) + (parseInt(color[1]) * 587) + (parseInt(color[2]) * 114)) / 1000);
		//console.log("Colortext params :", color, o, e);
		var carouselCaption;
		if (e !== null) {
			var carouselCaption = $(namespace + ' #myCarousel_' + portletId + ' .active').children('.carousel-caption');
		}
		if (o > 125) {
			if (e !== null) {
				carouselCaption.each(
					function () {
						this.style.setProperty('color', 'black',
							'important');
					});
				$(namespace + ' .knowMoreText').css('color', 'black');
			} else {
				$(namespace + ' .item').children('.carousel-caption').each(function () {
					this.style.setProperty('color', 'black', 'important');
				});
				$(namespace + ' .knowMoreText').css('color', 'black');
			}

		} else {
			if (e !== null) {
				carouselCaption.each(
					function () {
						this.style.setProperty('color', 'white',
							'important');
					});
				$(namespace + ' .knowMoreText').css('color', 'white');
			} else {
				$(namespace + ' .item').children('.carousel-caption').each(function () {
					this.style.setProperty('color', 'white', 'important');

				});
				$(namespace + ' .knowMoreText').css('color', 'white');
			}

		}
	}
}