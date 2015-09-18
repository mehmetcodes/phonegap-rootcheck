var exec = require("cordova/exec");

var RootDetection = function () {
    this.name = "RootDetection";
};

RootDetection.prototype.RootCheck = function (successCallback, failureCallback, resultType) {
    exec(successCallback, failureCallback, "RootCheckPlugin", "Rootcheck", [resultType]);
};

module.exports = new RootDetection();