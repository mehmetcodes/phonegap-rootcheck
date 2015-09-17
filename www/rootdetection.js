var exec = require("cordova/exec");

var RootDetection = function () {
    this.name = "RootDetection";
};

RootDetection.prototype.RootCheck = function (successCallback, failureCallback) {
    exec(successCallback, failureCallback, "Rootcheck", "Rootcheck", []);
};

module.exports = new RootDetection();