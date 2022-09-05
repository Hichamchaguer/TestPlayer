package com.example.testplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    Button btn_play,btn_pause,btn_next;
    MediaPlayer player;
    SeekBar seekBar;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        seekBar = findViewById(R.id.seekBar);
        player = MediaPlayer.create(this,R.raw.loseyouself);
        seekBar.setMax(player.getDuration());
        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    public class updateSeekbar implements Runnable{

        @Override
        public void run() {

            seekBar.setProgress(player.getCurrentPosition());
            handler.postDelayed(this,100);
        }
    }

    public void play (View v)
    {
        player.start();
        updateSeekbar updateSeekbar = new updateSeekbar();
        handler.post(updateSeekbar);
    }

    public void pause (View v)
    {
        player.pause();
    }
}