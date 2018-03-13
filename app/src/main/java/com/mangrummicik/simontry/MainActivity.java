package com.mangrummicik.simontry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

        ImageButton howButton = findViewById(R.id.howButton);
        howButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToWebSite("https://www.hasbro.com/common/documents/3f4e2ca0206011ddbd0b0800200c9a66/620962835056900B10D1688756D7BA4A.pdf");
            }
        });

        ImageButton aboutButton = findViewById(R.id.aboutButton);
    }
    public void goToWebSite(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
