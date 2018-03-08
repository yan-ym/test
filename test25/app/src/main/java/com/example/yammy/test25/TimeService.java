package com.example.yammy.test25;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yammy on 2018/3/6.
 */

public  class TimeService extends Service{
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer=new Timer(true);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder=new Notification.Builder(TimeService.this);
                CharSequence contentTitle=getText(R.string.content_title);
                CharSequence contentText=getText(R.string.content_text);
                builder.setSmallIcon(R.drawable.warning);
                builder.setContentTitle(contentTitle);
                builder.setContentText(contentText);
                builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);
                //Intent intent1=new Intent(TimeService.this,MainActivity.class);
                try {
                    manager.notify(0, builder.build());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                TimeService.this.stopSelf();
            }
        },60000);
    }
}
