package uqac.dim.bop_it;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyServices extends Service {
    private MediaPlayer mMediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate(){
        Toast.makeText(this,"Service created",Toast.LENGTH_SHORT).show();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ambiantgameormenufx);
        mMediaPlayer.setLooping(false);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mMediaPlayer.start();
    }

    public void onDestroy(){
        Toast.makeText(this,"Service stopped",Toast.LENGTH_SHORT).show();
        mMediaPlayer.stop();
    }
    public void onPause(){
        Toast.makeText(this,"Service paused",Toast.LENGTH_SHORT).show();
        mMediaPlayer.release();
    }
}
