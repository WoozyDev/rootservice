@echo off
C:\Users\woozy\Desktop\mtkclient-1.52\adb push C:\Users\woozy\AndroidStudioProjects\rootservice\app\build\outputs\apk\debug\app-debug.apk /data/local/tmp
C:\Users\woozy\Desktop\mtkclient-1.52\adb shell am force-stop com.woozy.cops.rootservice
C:\Users\woozy\Desktop\mtkclient-1.52\adb shell su -c "pm install /data/local/tmp/app-debug.apk;am start com.woozy.cops.rootservice/com.woozy.cops.rootservice.MainActivity"