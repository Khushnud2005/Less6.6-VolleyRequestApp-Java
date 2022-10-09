package uz.example.less66_volleyrequestapp_java.utils;

import android.util.Log;

import uz.example.less66_volleyrequestapp_java.network.VolleyHttp;

public class Logger {
    public static void d(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.v(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.e(tag, msg);
    }
}
