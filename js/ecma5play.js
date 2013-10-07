/**
 * Resources :- 
 *   1. John Reigh ecma5 strict mode and more
 *
 *
 * **/

// Similar to prototype's bind e.g. Function.prototype.bind( thisArg, arg1, arg2 ... )

var myObj = {
  method : function (name) { console.log(name); }
}

var result = myObj.method.bind(myObj, "Dhval");

console.log(result());

