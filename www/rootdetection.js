var exec = require("cordova/exec");

var RootDetection = function () {
    this.name = "RootDetection";
};

RootDetection.prototype.RootCheck = function (successCallback, failureCallback, resultType) {
    exec(successCallback, failureCallback, "RootCheckPlugin", "Rootcheck", [resultType]);
};


if(!window.plugins){
	window.plugins = {};
}

if(!window.plugins.RootDetection){
	window.plugins.RootDetection = new RootDetection();
}  

var RootDetection = new RootDetection();
module.exports = RootDetection;
console.log("RD Plugin available");