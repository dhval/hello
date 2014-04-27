/** A little demo for hashchange and history api's  **/

// ! -- helps crawlers
// window.location += "!#url"; -- reloads page w/o refresh & triggers hashchange event.

/** API **/ go(x), back(), forward(), length ;

// window.history.pushState(data, "Title", "/new-url");

!function() {
    
    var currentHash = location.hash; 
    console.log("d" +location.hash);
    
    
} ();
