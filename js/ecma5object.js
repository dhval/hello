/** John Resig's blog ECMAScript 5 Objects and Properties **/

// Object.preventExtensions(obj)
// Object.isExtensible(obj)
// Object.getOwnPropertyDescriptor(obj, 'key')

var s = {
   
 

  };


var obj = { name : "dhval"  };

console.log("Is the object extensible ", 
  Object.isExtensible(obj));

console.log("get details about 'name' property", 
  JSON.stringify( Object.getOwnPropertyDescriptor(obj, "name") ));

var desc = { value: "test", writable: true, enumerable: true, configurable: true }



