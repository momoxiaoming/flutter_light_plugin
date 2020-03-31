import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_light_plugin/flutter_light_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  double light = 0;
  double maxLight = 0;
  String _lightNm;

  @override
  void initState() {
    super.initState();
    FlutterLightPlugin.getMaxLight.then((onValue) {
      print("当前屏幕最大亮度:$onValue");
      maxLight = onValue;
    });
    FlutterLightPlugin.getCurrentLight.then((onValue) {
      print("当前屏幕亮度:$onValue");
      light = onValue;
      setState(() {
        _lightNm="$light";
      });
    });
  }

  void addLight() {
    if(light>maxLight){
      return;
    }
    light=light+0.1*maxLight;
    FlutterLightPlugin.setLight(light);
    setState(() {
      _lightNm="$light";
    });
  }
  void _lessLight() {
    if(light<0){
      return;
    }
    light=light-0.1*maxLight;
    FlutterLightPlugin.setLight(light);
    setState(() {
      _lightNm="$light";
    });
  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              Text("当前亮度$_lightNm"),
              FlatButton(
                child: Text("增加亮度"),
                onPressed: () {
                  addLight();
                },
              ),
              FlatButton(
                child: Text("减少亮度"),
                onPressed: () {
                  _lessLight();
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
