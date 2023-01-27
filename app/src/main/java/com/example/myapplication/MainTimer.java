package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainTimer extends AppCompatActivity {

    private String maxTime;
    private Boolean playerOneFlag = true;
    private Boolean playerTwoFlag = true;
    private int minsP1, minsP2, secsP1, secsP2;


    CountDownTimer countDownTimer;

    TextView textViewPlayerOne, textViewPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_timer);
        textViewPlayerOne = (TextView) findViewById(R.id.player_one);
        textViewPlayerTwo = (TextView) findViewById(R.id.player_two);

        Intent intent = getIntent();

        minsP1 = intent.getIntExtra("minutes", 10);
        minsP2 = minsP1;
        secsP1 = intent.getIntExtra("seconds", 0);
        secsP2 = secsP1;
        maxTime = minsP1+":"+formatSecs(secsP1);

        textViewPlayerOne.setText(maxTime);
        textViewPlayerTwo.setText(maxTime);

    }

    private String formatSecs(int value){
        if (value < 10){
            return "0"+value;
        }
        return ""+value;
    }
    public void pauseGame(View view) throws InterruptedException {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        Thread.sleep(2000);
    }

    public void resumeGame(View view) throws InterruptedException {
        if(playerOneFlag == playerTwoFlag){
            playerOneFlag = true;
            playerTwoFlag = false;
        }
        Thread.sleep(500);
        if (playerOneFlag){
            countDownTimer = new CountDownTimer((minsP1*60+secsP1)*1000, 1000){

                @Override
                public void onTick(long l) {

                    textViewPlayerOne.setText(minsP1+":"+formatSecs(secsP1));
                    if (secsP1 == 0){
                        minsP1 -= 1;
                        secsP1 = 59;
                    }else{
                        secsP1 -= 1;
                    }
                }

                @Override
                public void onFinish() {
                    textViewPlayerOne.setText("Loser");
                    textViewPlayerTwo.setText("Winner");
                }
            }.start();
        }else if (playerTwoFlag){
            countDownTimer = new CountDownTimer((minsP2*60+secsP2)*1000, 1000){

                @Override
                public void onTick(long l) {
                    if (secsP2 == 0){
                        minsP2 -= 1;
                        secsP2 = 59;
                    }else{
                        secsP2 -= 1;
                    }
                    textViewPlayerTwo.setText(minsP2+":"+formatSecs(secsP2));
                }

                @Override
                public void onFinish() {

                    textViewPlayerOne.setText("Winner");
                    textViewPlayerTwo.setText("Loser");
                }
            }.start();
        }
    }

    public void restartGame(View view) throws InterruptedException {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        finish();
    }

    public void playerTurnSwitch(View view) throws InterruptedException {
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        if (playerOneFlag){
            playerOneFlag=false;
            playerTwoFlag=true;
        }else {
            playerTwoFlag=false;
            playerOneFlag=true;
        }
        resumeGame(view);
    }

}