(function($) {
	$(window).bind('load', function() {
		
		initBackgroundChecker();
		initCaption();
		clampCaption(null);

		// evenement de slid du carousel terminé 
		// BackgroundCheck en a besoin pour definir l'element sur lequel il va s'appliquer
		$('#myCarousel').on('slid.bs.carousel', function(e){
			checkImageBackground(e);
			colorizeCaption(e);
			clampCaption(e)
		});
	});
	function initBackgroundChecker(){
	  	var src_init = $('.item').find('img')[0];
		var bcTarget = $('.item').children('.carousel-caption');
		console.log("Targets : "+bcTarget);
		console.log(src_init);
                BackgroundCheck.init({
                        targets: bcTarget,
                        images: src_init,
                        minOverlap:0,
                        debug:true
                        });
	
	}
	function checkImageBackground(e){
		var sourceImage =   $('#myCarousel .active').find('img')[0];
		var targetCaption = $('#myCarousel .active').children('.carousel-caption');
                BackgroundCheck.set('images', sourceImage);
         	BackgroundCheck.set('targets',targetCaption);    
	}
	function clampCaption(e) {
		var maxTextLines = 5;
		if (e !== null) {
			var captionElement = $('#myCarousel .active').children('.carousel-caption').children('p');
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
	function getOpacityOf(carouselCaption){
	 	var isBackgroundDark = carouselCaption.hasClass('background--dark');
                var isBackgroundLight = carouselCaption.hasClass('background--light');
                var isBackgroundComplex = carouselCaption.hasClass('background--complex');
                var opacityOfCaption = 0;
                if (isBackgroundLight || isBackgroundDark){
                        opacityOfCaption = 0.6;
                }
                if (isBackgroundComplex){
                        opacityOfCaption = 0.9;
                }
		return opacityOfCaption;
	
	}
	function initCaption() {
		var src_init = $('.item').find('img')[0];
		var colorThief_init = new ColorThief();
		var color_init = colorThief_init.getColor(src_init);
		var carouselCaption = $('.item').children('.carousel-caption');
		var opacityOfCaption=getOpacityOf(carouselCaption);
		$('.item').children('.carousel-caption').css('background-color',
				"rgba(" + color_init + ","+opacityOfCaption+")");
		colorText(color_init, null);
	}
	function colorizeCaption(e) {
		var sourceImage = $('#myCarousel .active').find('img')[0];
		var colorThief = new ColorThief();
		var color = colorThief.getColor(sourceImage);
		var carouselCaption = $('#myCarousel .active').children('.carousel-caption'); 
		var opacityOfCaption =getOpacityOf(carouselCaption);
		carouselCaption.css("background-color", "rgba(" + color + ","+opacityOfCaption+")");
		colorText(color, e);
	}

	function colorText(color, e) {
		var o = Math.round(((parseInt(color[0]) * 299) + (parseInt(color[1]) * 587) + (parseInt(color[2]) * 114)) / 1000);
		var carouselCaption;
		if(e!==null){
			var carouselCaption = $('#myCarousel .active').children('.carousel-caption');
		}
		if (o > 125) {
			if (e !== null) {
				carouselCaption.each(
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
				carouselCaption.each(
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