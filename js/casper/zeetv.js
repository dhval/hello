// Casper initilization, injecting client side js & LOG levels.

var casper = require('casper').create({
	pageSettings: {
		loadImages: false, // The WebPage instance used by Casper will
		loadPlugins: false // use these settings
	},
	clientScripts: ['jquery.js'],
	verbose: true,
	logLevel: "debug"
});

// Listen to events, including HTTP status.

casper.on('page.error', function(msg, trace) {
	this.echo('Error: ' + msg, 'ERROR');
});
casper.on('remote.message', function(msg) {
	this.echo('remote message caught: ' + msg);
});

/**
 *	Extract all embed video's from the given page and return as array.
 **/
function findLinksOnPage() {
	var array = [];
	jQuery("a[href^='http://www.zeetv.com/shows/fear-files/video/fear-files']").each(function(i, v) {
		array.push(v.href);
	});
	return array;
}

//paginated url containing links  
var url = "http://zeetv.com/shows/fear-files/video/?page=",
	pgMin = 1,
	pgMax = 11,
	urls = [],
	ids = [];

casper.start();

for (var i = pgMin; i <= pgMax; i++) {
	casper.thenOpen(url + i, function() {
		this.echo("Current Page : - " + this.getTitle());
		casper.waitForSelector("div#show_videos_list1", function capture() {
			var myArr = this.evaluate(findLinksOnPage);
			casper.each(myArr, function(i, data) {
				casper.thenOpen(data, function() {
					var emId = this.evaluate(function() {
						if ($("iframe[src^='http://www.youtube.com/embed/']")[0])
							return $("iframe[src^='http://www.youtube.com/embed/']")[0].src;
						else
							return $("embed[src^='http://www.youtube.com/v/']")[0].src;
					});
					ids.push({ "id": emId, "link": data });
					this.echo("YouTube : - " + emId + " #" + this.getTitle());
				});
			});
			urls = urls.concat(myArr);
		}, function timeout() {
			this.captureSelector('screen/capture.png', 'html');
		}, 10000);
	});
}

/**
* Dump all results on console.
*/

casper.then(function() {
	require('utils').dump(urls);
});

casper.then(function() {
	require('utils').dump(ids);
});


casper.run();