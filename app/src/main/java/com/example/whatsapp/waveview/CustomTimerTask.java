package com.example.whatsapp.waveview;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.widget.Toast;

import java.util.TimerTask;

/**
 * Created by WhatsApp on 8/29/2017.
 */

public class CustomTimerTask extends TimerTask {

    private AudioManager audioManager;
    private Context context;
    private Handler mHandler = new Handler();

    // Write Custom Constructor to pass Context
    public CustomTimerTask(Context con) {
        this.context = con;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        // your code starts here.
        // I have used Thread and Handler as we can not show Toast without starting new thread when we are inside a thread.
        // As TimePicker has run() thread running., So We must show Toast through Handler.post in a new Thread. Thats how it works in Android..
        new Thread(new Runnable() {
            @Override
            public void run() {
                audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                            Toast.makeText(context, "Ringer Mode set to Normal", Toast.LENGTH_SHORT).show();
                        } else {
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                            Toast.makeText(context, "Ringer Mode set to Silent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();

    }

}