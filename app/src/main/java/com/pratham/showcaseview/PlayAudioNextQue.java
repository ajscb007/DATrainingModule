package com.pratham.showcaseview;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by AJ on 12-01-2019.
 */

public class PlayAudioNextQue extends Service {

    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    public void onCreate()
    {
        mp = MediaPlayer.create(this, R.raw.next_que);
        mp.setLooping(false);
    }
    public void onDestroy()
    {
        mp.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return Service.START_NOT_STICKY;
    }

    public void onStart(Intent intent, int startid){

        Log.d("log", "On start");
        mp.start();
    }
}
