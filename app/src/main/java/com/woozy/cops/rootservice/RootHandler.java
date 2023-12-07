package com.woozy.cops.rootservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class RootHandler implements Handler.Callback {
    public static RootHandler instance = new RootHandler();

    //Root connection
    private MSGConnection messageConnection;
    private Messenger remoteMessenger;
    private final Messenger replyMessenger = new Messenger(new Handler(Looper.getMainLooper(), this));

    public void Inject(Activity activity) {
        MSGConnection mSGConnection = messageConnection;
        if (mSGConnection == null) {
            RootService.bind(new Intent(activity, RootService.class), new MSGConnection());
        } else {
            RootService.unbind(mSGConnection);
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        int result = message.getData().getInt("result");

        Log.e("mystik", "RootHandler.handleMessage : result -> " + result);

        return false;
    }

    public class MSGConnection implements ServiceConnection {
        MSGConnection() {}

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("mystik", "inside MSGConnection.onServiceConnected");
            remoteMessenger = new Messenger(iBinder);
            messageConnection = this;

            Message message = Message.obtain((Handler) null, 1);
            message.replyTo = replyMessenger;

            try {
                remoteMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            remoteMessenger = null;
            messageConnection = null;
        }
    }
}
