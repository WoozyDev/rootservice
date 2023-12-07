package com.woozy.cops.rootservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.topjohnwu.superuser.Shell;
import com.woozy.cops.rootservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static {
        Shell.enableVerboseLogging = true;
        Shell.setDefaultBuilder(Shell.Builder.create()
                .setFlags(Shell.FLAG_REDIRECT_STDERR)
                .setTimeout(10)
        );
    }

    public static MainActivity thisInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisInstance = this;

        Log.e("mystik", "inside MainActivity.onCreate");
        Log.e("mystik", "thisInstance: " + thisInstance);

        setContentView(R.layout.activity_main);

        Shell.getShell(shell -> {

            Log.e("mystik", "Root Access");

            RootHandler.instance.Inject(this);

        });

    }
}