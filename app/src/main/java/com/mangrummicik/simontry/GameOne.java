package com.mangrummicik.simontry;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class GameOne extends Activity implements View.OnClickListener{

    Random r = new Random();
    final int TIMER_ONE = 1000;
    final int TIMER_TWO = 500;
    final int TIMER_THREE = 250;
    int iSub = 0;
    static int turns = 1;
    static int guess = 0;
    int [] reqs = new int[8];
    private SoundPool soundPool;
    private Set<Integer> soundsLoaded;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        soundsLoaded = new HashSet<>();

        final ImageButton playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        final ImageButton playButton = findViewById(R.id.playButton);
        final TextView tv = findViewById(R.id.testTextView);
        final ImageButton greenSquare = findViewById(R.id.greenButton);
        greenSquare.setOnClickListener(this);
        final ImageButton redSquare = findViewById(R.id.redButton);
        redSquare.setOnClickListener(this);
        final ImageButton yellowSquare = findViewById(R.id.yellowButton);
        yellowSquare.setOnClickListener(this);
        final ImageButton blueSquare = findViewById(R.id.blueButton);
        blueSquare.setOnClickListener(this);
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder sp = new SoundPool.Builder();
        sp.setAudioAttributes(builder.build());
        sp.setMaxStreams(6);

        soundPool = sp.build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) {
                    soundsLoaded.add(sampleId);
                } else {
                    Log.i("Error", "Sound not loaded!");
                }
            }
        });

        final int greenId = soundPool.load(this, R.raw.green, 1);
        final int redId = soundPool.load(this, R.raw.red, 1);
        final int yellowId = soundPool.load(this, R.raw.yellow, 1);
        final int blueId = soundPool.load(this, R.raw.blue, 1);
        final int loseId = soundPool.load(this, R.raw.wrong, 1);
        final int winId = soundPool.load(this, R.raw.win, 1);

        if (v.getId() == R.id.greenButton && reqs[guess] == 1) {
            playSound(greenId);
            guess++;
        } else if (v.getId() == R.id.redButton && reqs[guess] == 2) {
            playSound(redId);
            guess++;
        } else if (v.getId() == R.id.yellowButton && reqs[guess] == 3) {
            playSound(yellowId);
            guess++;
        } else if (v.getId() == R.id.blueButton && reqs[guess] == 0) {
            playSound(blueId);
            guess++;
        } else if (v.getId() != R.id.playButton) {
            turns = 1;
            guess = 0;
            playSound(loseId);
        }
        if (v.getId() == R.id.playButton || guess == turns) {
            final int[] all = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

            playButton.setImageResource(R.drawable.wait);
            Handler handler = new Handler();
            final int compSeq = all[r.nextInt(all.length)];
            if (turns == guess) {
                guess = 0;
                turns++;
            }
            if (turns == 9) {
                playSound(winId);
            } else {

                reqs[turns - 1] = (compSeq % 4);
                iSub = 0;
                for (int i = 0; i < turns; i++) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("iSub", String.valueOf(iSub));
                            String textM = String.valueOf(compSeq);
                            tv.setText(textM);
                            if (reqs[iSub] == 1) {
                                greenSquare.setImageResource(R.drawable.green_lit);
                                playSound(greenId);
                                greenSquare.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        greenSquare.setImageResource(R.drawable.green_default);
                                        iSub++;
                                    }
                                }, TIMER_ONE);
                            } else if (reqs[iSub] == 2) {
                                redSquare.setImageResource(R.drawable.red_lit);
                                playSound(redId);
                                redSquare.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        redSquare.setImageResource(R.drawable.red_default);
                                        iSub++;
                                    }
                                }, TIMER_ONE);
                            } else if (reqs[iSub] == 3) {
                                yellowSquare.setImageResource(R.drawable.yellow_lit);
                                playSound(yellowId);
                                yellowSquare.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        yellowSquare.setImageResource(R.drawable.yello_default);
                                        iSub++;
                                    }
                                }, TIMER_ONE);
                            } else if (reqs[iSub] == 0) {
                                blueSquare.setImageResource(R.drawable.blue_lit);
                                playSound(blueId);
                                blueSquare.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        blueSquare.setImageResource(R.drawable.blue_default);
                                        iSub++;
                                    }
                                }, TIMER_ONE);
                            }
                        }
                    }, 1500 * i);
                }
            }
        }
    }
    private void playSound(int id){
        if(soundsLoaded.contains(id)) {
            soundPool.play(id, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
}

