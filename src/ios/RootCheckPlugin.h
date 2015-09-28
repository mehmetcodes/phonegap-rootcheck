#import <Cordova/CDV.h>
#import <Cordova/CDVPluginResult.h>

@interface RootCheckPlugin : CDVPlugin{
  
}
inline void weHaveA_JBProblem() __attribute__((always_inline));
inline void weHaveA_DBGProblem() __attribute__((always_inline));

-(void)Rootcheck:(CDVInvokedUrlCommand*)command;
+(void) jbCheck;
+(void) dbgCheck;
@end
