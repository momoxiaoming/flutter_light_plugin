import 'dart:async';

import 'package:flutter/services.dart';

class FlutterLightPlugin {

  static const MethodChannel _channel =
      const MethodChannel('flutter_light_plugin');


  static Future<num> get getCurrentLight async {
    final double num = await _channel.invokeMethod('getCurrentLight');
    return num;
  }

  static Future<bool> setLight(double light) async {
    final bool num =
    await _channel.invokeMethod('setLight', {"light": light});
    return num;
  }

  static Future<num> get getMaxLight async {
    final double num =
    await _channel.invokeMethod('getMaxLight');
    return num;
  }
}
