
console.log("Lexical scoping means the place where a f() is defined");

var y = 1;
z = 1;

function foo() {
  console.log("value of y in the begining",y, global['y'], global['z'] );
  if (!y) var y = 7;
  console.log("value of ", y);
  var x=1;
  if(x) {
     (function () {var x=2; }());  
  }
 console.log("Value of y within f() ", y, this == global);
}
foo();
console.log( y, typeof x);


console.log("lets test for global scope");

var testObject = {
  method: function() { console.log("I am in local scope", this==testObject, this==global);  }
}

testObject.method();


function ua() { console.log(this==global);   }

ua.call(null);



