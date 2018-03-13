package com.mangrummicik.simontry;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class GameOne extends Activity{

    Random r = new Random();
    final int TIMER_ONE = 1000;
    final int TIMER_TWO = 500;
    final int TIMER_THREE = 250;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);


        final ImageButton playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TextView tv = findViewById(R.id.testTextView);
                final ImageButton greenSquare = findViewById(R.id.greenButton);
                final ImageButton redSquare = findViewById(R.id.redButton);
                final ImageButton yellowSquare = findViewById(R.id.yellowButton);
                final ImageButton blueSquare = findViewById(R.id.blueButton);
                final int[] all = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

                playButton.setImageResource(R.drawable.wait);
                //playButton.setClickable(false);
                Handler handler = new Handler();
                int turns = 1;

                for (int i = 0; i < 8; i++) {

                    final int compSeq = all[r.nextInt(all.length)];

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    String textM = String.valueOf(compSeq);
                                    tv.setText(textM);

                                    if (compSeq == 1 || compSeq == 5 || compSeq == 9 || compSeq == 13) {
                                        greenSquare.setImageResource(R.drawable.green_lit);
                                        greenSquare.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                greenSquare.setImageResource(R.drawable.green_default);
                                            }
                                        }, TIMER_ONE);
                                    }
                                    if (compSeq == 2 || compSeq == 6 || compSeq == 10 || compSeq == 14) {
                                        redSquare.setImageResource(R.drawable.red_lit);
                                        redSquare.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                redSquare.setImageResource(R.drawable.red_default);
                                            }
                                        }, TIMER_ONE);
                                    }
                                    if (compSeq == 3 || compSeq == 7 || compSeq == 11 || compSeq == 15) {
                                        yellowSquare.setImageResource(R.drawable.yellow_lit);
                                        yellowSquare.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                yellowSquare.setImageResource(R.drawable.yello_default);
                                            }
                                        }, TIMER_ONE);
                                    }
                                    if (compSeq == 4 || compSeq == 8 || compSeq == 12 || compSeq == 16) {
                                        blueSquare.setImageResource(R.drawable.blue_lit);
                                        blueSquare.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                blueSquare.setImageResource(R.drawable.blue_default);
                                            }
                                        }, TIMER_ONE);
                                    }

                                }
                            }, 1500 * i);
                }
            }
        });
    }
}

