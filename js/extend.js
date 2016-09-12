//  JavaScript inheritance - http://davidshariff.com/blog/javascript-inheritance-patterns/

/**
 * 1. Pseudoclassical pattern, similar to classical programming languages.
 **/
 
var extendObj = function(childObj, parentObj) {
    var tmpObj = function () {}
    tmpObj.prototype = parentObj.prototype;
    // fix pass by reference, any changes to child.prototype won't effect parent.prototype ( and other siblings)  
    childObj.prototype = new tmpObj();
    // reset the constructor
    childObj.prototype.constructor = childObj;
};

/**
 * 2.  Douglas Crockford, The good parts - Functional pattern
 * Create a new constructor function, whose prototype is the parent object's prototype.
 **/

var vehicle = function(attrs) {
    var _privateObj = {
        hasEngine: true
    },
    that = {};
    that.name = attrs.name || null;
    that.hasEngine = function () {
        alert('This ' + that.name + ' has an engine: ' + _privateObj.hasEngine);
    };
    return that;
}

var motorbike = function () {
    // private
    var _privateObj = {
        numWheels: 2
    },
    // inherit
    that = vehicle({
        name: 'Motorbike',
        engineSize: 'Small'
    });
    // public
    that.totalNumWheels = function () {
        alert('This Motobike has ' + _privateObj.numWheels + ' wheels');
    };
    return that;
};

/**
 * 3.  Object.create as of ECMAScript 5
 **/

 var child = Object.create(parent, {
        gender : {value: 'Male'}
    });
