package com.example.billysaputra.googlemapsapi;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import java.util.TimerTask;

/**
 * Created by BillySaputra on 21-Mar-17.
 */

public class CustomTimerHandler extends TimerTask{
    private Context context;
    private Handler handler = new Handler();
    public CustomTimerHandler(Context context) {
        this.context = context;
    }
    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        final Handler handler = new Handler();
                        final long start = SystemClock.uptimeMillis();
                        final long duration = 1500;
                        final Interpolator interpolator = new BounceInterpolator();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                long elapsed = SystemClock.uptimeMillis() - start;
                                float time = Math.max(1 - interpolator.getInterpolation((float) elapsed/duration), 0);
                                MapsActivity.marker.setAnchor(0.5f, (float) (1.0 + 2 * time));
                                if(time > 0.0)
                                    handler.postDelayed(this, 10);
                            }
                        });
                    }
                });
            }
        }).start();
    }
}
