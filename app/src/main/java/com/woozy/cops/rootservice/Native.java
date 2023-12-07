package com.woozy.cops.rootservice;

public class Native {
    // Used to load the 'rootservice' library on application startup.
    static {
        System.loadLibrary("rootservice");
    }
}
