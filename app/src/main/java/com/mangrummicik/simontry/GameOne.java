package com.mangrummicik.simontry;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameOne extends Activity implements View.OnClickListener{

    Random r = new Random();
    final int TIMER_ONE = 1000;
    final int TIMER_TWO = 500;
    final int TIMER_THREE = 250;
    int iSub = 0;
    static int turns = 1;
    static int guess = 0;
    int [] reqs = new int[1000];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);


        final ImageButton playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
        }
    @Override
    public void onClick(View v){
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
        if(v.getId() == R.id.greenButton && reqs[guess] == 1){
            greenSquare.setImageResource(R.drawable.green_lit);
            greenSquare.postDelayed(new Runnable() {
                @Override
                public void run() {
                    greenSquare.setImageResource(R.drawable.green_default);
                }
            }, TIMER_ONE);
            guess++;
        }
        else if(v.getId() == R.id.redButton && reqs[guess] == 2){
            guess++;
        }
        else if(v.getId() == R.id.yellowButton && reqs[guess] == 3){
            guess++;
        }
        else if(v.getId() == R.id.blueButton && reqs[guess] == 0){
            guess++;
        }
        else if(v.getId() != R.id.playButton){
            Toast.makeText(getApplicationContext(), String.valueOf(reqs[guess]) , Toast.LENGTH_LONG).show();
            turns = 1;
            guess = 0;
        }
        if(v.getId() == R.id.playButton || guess == turns){
            final int[] all = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

            playButton.setImageResource(R.drawable.wait);
            //playButton.setClickable(false);
            Handler handler = new Handler();
            if(turns == guess)
                turns++;
            guess = 0;
            final int compSeq = all[r.nextInt(all.length)];
            reqs[turns-1] = (compSeq % 4);
            for (int i = 0; i < turns; i++) {
                iSub = i;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String textM = String.valueOf(compSeq);
                        tv.setText(textM);

                        if (reqs[iSub] == 1) {
                            greenSquare.setImageResource(R.drawable.green_lit);
                            greenSquare.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    greenSquare.setImageResource(R.drawable.green_default);
                                }
                            }, TIMER_ONE);
                        }
                        if (reqs[iSub] == 2) {
                            redSquare.setImageResource(R.drawable.red_lit);
                            redSquare.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    redSquare.setImageResource(R.drawable.red_default);
                                }
                            }, TIMER_ONE);
                        }
                        if (reqs[iSub] == 3) {
                            yellowSquare.setImageResource(R.drawable.yellow_lit);
                            yellowSquare.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    yellowSquare.setImageResource(R.drawable.yello_default);
                                }
                            }, TIMER_ONE);
                        }
                        if (reqs[iSub] == 0) {
                            blueSquare.setImageResource(R.drawable.blue_lit);
                            blueSquare.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    blueSquare.setImageResource(R.drawable.blue_default);
                                }
                            }, TIMER_ONE);
                        }
                        iSub++;
                    }
                }, 1500 * i);
            }
        }
    }
}

