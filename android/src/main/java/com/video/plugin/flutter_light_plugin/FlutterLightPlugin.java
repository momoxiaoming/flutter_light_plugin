package com.video.plugin.flutter_light_plugin;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.PluginRegistry;

/**
 * FlutterLightPlugin
 */
public class FlutterLightPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware
{
    private static String CHANNEL = "flutter_light_plugin";


    private LightChangeObserver lightChangeObserver;


    private PluginRegistry.Registrar registrar;

    public static void registerWith(PluginRegistry.Registrar registrar)
    {
        Log.d(LightChangeObserver.TAG, "registerWith");
        FlutterLightPlugin flutterLightPlugin = new FlutterLightPlugin();
        flutterLightPlugin.onAttachedToEngine(registrar.context(), registrar.messenger());

    }


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding)
    {
        Log.d(LightChangeObserver.TAG, "onAttachedToEngine");
        onAttachedToEngine(flutterPluginBinding.getApplicationContext(), flutterPluginBinding.getBinaryMessenger());
    }

    private void onAttachedToEngine(Context applicationContext, BinaryMessenger messenger)
    {
        lightChangeObserver = new LightChangeObserver(applicationContext);
        //method chanel
        final MethodChannel channel = new MethodChannel(messenger, CHANNEL);
        channel.setMethodCallHandler(this);

    }


    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result)
    {

        String method = call.method;
        if (method.equals("getCurrentLight"))
        {
            result.success(lightChangeObserver.getCurrentWindowLight());
        } else if (method.equals("setLight"))
        {
            double volumeValue = Double.parseDouble(call.argument("light").toString());
            lightChangeObserver.setWindowLight(volumeValue);
        } else if (method.equals("getMaxLight"))
        {
            result.success(lightChangeObserver.getMaxWindowLight());

        } else
        {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding)
    {
        Log.d(LightChangeObserver.TAG, "onDetachedFromEngine");
        lightChangeObserver = null;
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding binding)
    {
        Log.d(LightChangeObserver.TAG, "onAttachedToActivity");

        lightChangeObserver.setActivity(binding.getActivity());
    }

    @Override
    public void onDetachedFromActivityForConfigChanges()
    {
        Log.d(LightChangeObserver.TAG, "onDetachedFromActivityForConfigChanges");

    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding)
    {
        Log.d(LightChangeObserver.TAG, "onReattachedToActivityForConfigChanges");

    }

    @Override
    public void onDetachedFromActivity()
    {

    }
}
