/**
 * Practice some array API's in JS
 **/

var myArray = [3, 7, 9, 0, 5];

/**
* Pattern - create an instance of self if called without new.
**/
var Class = function(name) {
    var self = (this instanceof Class) ? this : new Class();
    self.name = name;
    return self;
};
/**
 * A closure
 * 
 **/ 
var myClosure = (function(data) {
    return function(index) {
        return data[i];
    }
})(['1','2','3','4','5'])


function myFunction() {
    console.log(Array.prototype.slice.call(arguments, 2));
}

function curry(a) {
    return function(b) {
        a += b;
        return a;
    }
}

! function() {
    var fn = curry(100);
    console.log(fn(5));
    console.log(fn(25));
}();