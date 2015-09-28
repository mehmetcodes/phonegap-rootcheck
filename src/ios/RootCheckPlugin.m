#import <Foundation/Foundation.h>
#import "SecurityCheck.h"
#import "RootcheckPlugin.h"

@implementation RootCheckPlugin

-(void)Rootcheck:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* myarg = [command.arguments objectAtIndex:0];
#ifndef DEBUG    
    [RootCheckPlugin jbCheck];
    [RootCheckPlugin dbgCheck];
#endif
    pluginResult = [CDVPluginResult    resultWithStatus:@"false"];
}

+(void) jbCheck {
    
    //**
    //** JB test
        
    //-----------------------------------
    // call JB weHaveAProblem
    //-----------------------------------
    cbBlock chkCallback  = ^{
            
        __weak id weakSelf = self;
        
        if (weakSelf) weHaveA_JBProblem();
    };
        
    //-----------------------------------
    // jailbreak detection
    //-----------------------------------
    checkFork(chkCallback);
    checkFiles(chkCallback);
    checkLinks(chkCallback);

}

+(void) dbgCheck {
    //-------------------------------------------
    // do not allow debuggers
    //-------------------------------------------
    //dbgStop;
    
    //-------------------------------------------
    // check for the presence of a debugger
    // call weHaveAProblem if there is one
    //-------------------------------------------
    cbBlock dbChkCallback  = ^{
        
        __weak id weakSelf = self;
        
        if (weakSelf) weHaveA_DBGProblem();
        
    };
    
    dbgCheck(dbChkCallback);

}

@end

//--------------------------------------------------------------------
// if the device is jailbroken then this method will be called
//--------------------------------------------------------------------
void weHaveA_JBProblem() {
    NSLog(@"RC: True");
    exit(0);
}


//--------------------------------------------------------------------
// if a debugger is attched to the app then this method will be called
//--------------------------------------------------------------------
void weHaveA_DBGProblem() {
    NSLog(@"RC: debugger detected");
    exit(0);
}
