package com.mangrummicik.simontry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton gameOne = findViewById(R.id.gameOneButton);
        gameOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setContentView(R.layout.game_one);

                ImageButton imageButton = findViewById(R.id.skillOneButton);
                imageButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), GameOne.class);
                        startActivity(intent);
                    }
                });
            }
        });

        ImageButton gameTwo = findViewById(R.id.gameTwoButton);

        ImageButton gameThree = findViewById(R.id.gameThreeButton);

        final ImageButton howButton = findViewById(R.id.howButton);
        howButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(v.getId() == R.id.howButton){
                    Intent intent = new Intent(getApplicationContext(), HowToPlay.class);
                    startActivity(intent);
                }
            }
        });

        ImageButton aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.aboutButton) {
                    Intent intent = new Intent(getApplicationContext(), About.class);
                    startActivity(intent);
                }
            }
        });
    }
}
