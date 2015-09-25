#import <Foundation/Foundation.h>
#import "SecurityCheck.h"

@implementation RootCheckPlugin

-(void)Rootcheck:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* myarg = [command.arguments objectAtIndex:0];
    pluginResult = [CDVPluginResult    resultWithStatus:@"result"];
}