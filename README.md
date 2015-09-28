# phonegap-rootcheck

Android and iPhone Release V0.1.0 now available for Cordova/Phonegap 3.0 and greater

Beware, there are fundamental differences between iOS and Android versions, play close attention to the differences.

In Android:

Call RootDetection.RootCheck(success_fn,error_fn) in your code.  If you get back true, your phone is rooted.  If you get back false, RootDetection could not find evidence of rooting.

In iOS:

Call RootDetection.RootCheck(success_fn,error_fn) in your code.  If you get back false, you are probably fine.

If you #define DEBUG, no real check should happen, otherwise:

If you are in an emulator, your app will be murdered by jailbreak check.

If you are connected with debug, your app will be murdered by debug check 

If you are jailbroken, we hope your app will be murdered.

Thank you for reviewing and your contributions.
~M


