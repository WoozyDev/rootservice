package com.woozy.cops.rootservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class RootService extends com.topjohnwu.superuser.ipc.RootService implements Handler.Callback {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("mystik", "inside RootService.onCreate");
        Log.e("mystik", "MainActivity.thisInstance -> " + MainActivity.thisInstance);
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        Log.e("mystik", "inside RootService.handleMessage");
        if(message.what != 1) {
            return false;
        }

        Message msg = Message.obtain();
        Bundle bundle = new Bundle();

        bundle.putInt("result", 1);

        msg.setData(bundle);

        try {
            message.replyTo.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        Log.e("mystik", "inside RootService.onBind");
        Handler handler = new Handler(Looper.getMainLooper(), this);
        return new Messenger(handler).getBinder();
    }
}
