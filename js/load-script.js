
/** 
 * 
 **/

var js = document.createElement("script"); 
js.type="text/javascript";
js.src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js";

//for nonIE browsers
script.onload=function(){
        addVideo();
    }

 //for IE Browsers
 ieLoadBugFix(script, function(){
     addVideo();}
 );

function ieLoadBugFix(scriptElement, callback){
        if (scriptElement.readyState=='loaded' || scriptElement.readyState=='completed') {
             callback();
         }else {
             setTimeout(function() {ieLoadBugFix(scriptElement, callback); }, 100);
         }


 }

document.body.appendChild(js);

