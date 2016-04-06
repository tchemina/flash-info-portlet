(function($) {
	$(window).bind('load', function() {
		initCaption();
		clampCaption(null);
		$('#myCarousel').on('slide.bs.carousel', function(e) {
			colorizeCaption(e);
			clampCaption(e);
		});
	});

	function clampCaption(e) {
		var maxTextLines = 5;
		if (e !== null) {
			var captionElement = $(e.relatedTarget).children(
					'.carousel-caption').children('p');
			var captionClassName = captionElement.attr('class');
			var captionText = document.getElementsByClassName(captionClassName)[1];
			$clamp(captionText, {
				clamp : maxTextLines,
				useNativeClamp : false,
				animate : false
			});
		} else {
			var captionText = document.getElementsByClassName('carousel-text0')[1];
			$clamp(captionText, {
				clamp : maxTextLines,
				useNativeClamp : false,
				animate : false
			});
		}

	}

	function initCaption() {
		var src_init = $('.item').find('img')[0];
		var colorThief_init = new ColorThief();
		var color_init = colorThief_init.getColor(src_init);
		$('.item').children('.carousel-caption').css('background-color',
				"rgb(" + color_init + ")");
		colorText(color_init, null);
	}
	function colorizeCaption(e) {
		var sourceImage = $(e.relatedTarget).find('img')[0];
		var colorThief = new ColorThief();
		var color = colorThief.getColor(sourceImage);
		$(e.relatedTarget).children(".carousel-caption").css(
				"background-color", "rgb(" + color + ")");
		colorText(color, e);
	}

	function colorText(color, e) {
		var o = Math
				.round(((parseInt(color[0]) * 299) + (parseInt(color[1]) * 587) + (parseInt(color[2]) * 114)) / 1000);
		if (o > 125) {
			if (e !== null) {
				$(e.relatedTarget).children('.carousel-caption').each(
						function() {
							this.style.setProperty('color', 'black',
									'important');
						});
				 $('.knowMoreText').css('color','black');
			} else {
				$('.item').children('.carousel-caption').each(function() {
					this.style.setProperty('color', 'black', 'important');
				});
				 $('.knowMoreText').css('color','black');
			}

		} else {
			if (e !== null) {
				$(e.relatedTarget).children('.carousel-caption').each(
						function() {
							this.style.setProperty('color', 'white',
									'important');
						});
				 $('.knowMoreText').css('color','white');
			} else {
				$('.item').children('.carousel-caption').each(function() {
					this.style.setProperty('color', 'white', 'important');

				});
				 $('.knowMoreText').css('color','white');
			}

		}

	}

})(jQuery);
