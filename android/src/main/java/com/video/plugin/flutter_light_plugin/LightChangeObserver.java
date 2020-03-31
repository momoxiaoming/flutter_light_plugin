package com.video.plugin.flutter_light_plugin;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.view.WindowManager;

import io.flutter.Log;

/**
 * <pre>
 *     author:
 *     time  : 2020-03-27
 *     desc  : new class
 * </pre>
 */
public class LightChangeObserver
{
    static String TAG = "flutter_light";
    private ContentResolver contentResolver;
    private Context mContext;
    private int defVal = 255; //默认亮度

    private Activity activity;


    public LightChangeObserver(Context context)
    {
        this.mContext = context;
        this.contentResolver = context.getContentResolver();

    }

    public LightChangeObserver(Activity activity)
    {
        this.activity = activity;
        this.contentResolver = activity.getContentResolver();

    }


    /**
     * 获取当前窗口最大亮度
     *
     * @return
     */
    public double getMaxWindowLight()
    {
        return defVal;
    }


    /**
     * 获取当前窗口亮度
     *
     * @return
     */
    public double getCurrentWindowLight()
    {

        return (double)Settings.System.getInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS,defVal);
    }

    /**
     * 设置窗口亮度
     *
     * @return
     */
    public void setWindowLight(double light)
    {

        if (activity != null)
        {
            float li = (float) light;
            if(li>defVal){
                Log.d(TAG, "当前已是最大亮度!");
                return;
            }
            Log.d(TAG, "窗口值亮度值:" + li+" 最大亮度值:"+defVal);
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.screenBrightness = (li * (1f / defVal)) > 1 ? 1 : li * (1f / defVal);
            activity.getWindow().setAttributes(lp);

        } else
        {
            Log.d(TAG, "activity is null");

        }
        //
    }


    public Activity getActivity()
    {
        return activity;
    }

    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }
}
