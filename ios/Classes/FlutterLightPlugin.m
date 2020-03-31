#import "FlutterLightPlugin.h"
#import "LightChangObserver.h"

@implementation FlutterLightPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"flutter_light_plugin"
            binaryMessenger:[registrar messenger]];
  FlutterLightPlugin* instance = [[FlutterLightPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"setLight" isEqualToString:call.method]) {
      double lightNm=[[[call arguments] objectForKey:@"light"] doubleValue];
      [LightChangObserver setLight:lightNm];
  } if ([@"getMaxLight" isEqualToString:call.method]) {

      result(@([LightChangObserver getMaxLight]));

  }else if ([@"getCurrentLight" isEqualToString:call.method]) {
    result(@([LightChangObserver getCurrentLight]));
  }else{
    result(FlutterMethodNotImplemented);
  }
}

@end
