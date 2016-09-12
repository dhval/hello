/** In the main window: **/

var popupWin = null;

function openPopup() {
  var url = "popup.htm";
	popupWin = open( "", "popupWin", "width=500,height=400" );
	if( !popupWin || popupWin.closed || !popupWin.doSomething ) {
		popupWin = window.open( url, "popupWin", "width=500,height=400" );
	} else popupWin.focus();
}

function doSomething() {
	 openPopup();
	 popupWin.doSomething();
}

/** In the popup: **/

self.focus();

function doSomething() {
  alert("I'm doing something");
}