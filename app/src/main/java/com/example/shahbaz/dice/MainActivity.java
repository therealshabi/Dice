package com.example.shahbaz.dice;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mRoll;
    Button mReset;
    Button mHold;
    ImageView mDice;
    TextView mPlayerScore, mComputerScore, mWinnerText;
    int playerScore = 0;
    int computerScore = 0;
    int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoll = (Button) findViewById(R.id.roll);
        mReset = (Button) findViewById(R.id.reset);
        // mHold = (Button) findViewById(R.id.hold);
        mDice = (ImageView) findViewById(R.id.dice_image);
        mPlayerScore = (TextView) findViewById(R.id.score_text);
        mComputerScore = (TextView) findViewById(R.id.computer_score);
        mWinnerText = (TextView) findViewById(R.id.winner_text_view);

        playerTurn();


        //Reset Button Action

        mReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPlayerScore.setText("Player Score : 0");
                mComputerScore.setText("Computer Score : 0");
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                mWinnerText.setText("\t\tPlayer's TURN");
                playerScore = 0;
                computerScore = 0;
                mRoll.setEnabled(Boolean.TRUE);
            }
        });
    }

    private void computerTurn() {
        mWinnerText.setText("\t\tComputer's TURN");
        mRoll.setEnabled(Boolean.FALSE);
        computerScore += diceRoll();
        mComputerScore.setText("Computer Score : " + computerScore);
        if (computerScore >= 100) {
            mWinnerText.setText("\t\tComputer WINS!");
        } else {
            playerTurn();
        }
    }

    private void playerTurn() {
        mWinnerText.setText("\t\tPlayer's TURN");
        //mHold.setEnabled(Boolean.FALSE);
        mRoll.setEnabled(Boolean.TRUE);
        mRoll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int x = diceRoll();
                playerScore += x;
                mPlayerScore.setText("Your Score : " + playerScore);

                if (playerScore >= 100) {
                    mWinnerText.setText("\t\tPlayer WINS!");
                    mRoll.setEnabled(Boolean.FALSE);
                } else {
                    if (x == 2 || x == 6) {
                        playerTurn();
                        //holdButton();


                        //mHold.setEnabled(Boolean.FALSE);
                    } else {
                        mWinnerText.setText("\t\tComputer's TURN");
                        mRoll.setEnabled(Boolean.FALSE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                computerTurn();
                            }
                        }, 1000);
                    }


                }
            }
        });
    }


    public int diceRoll() {
        int min = 1;
        int max = 6;
        int randomDice = min + (int) (Math.random() * (max - min + 1));
        switch (randomDice) {
            case 1:
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                return 0;

            case 2:
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                return 2;

            case 3:
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                return 3;

            case 4:
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                return 4;

            case 5:
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                return 5;

            case 6:
                mDice.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                return 6;

        }
        return randomDice;
    }

    /*public void holdButton() {
        mHold.setEnabled(Boolean.TRUE);
        mHold.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playerTurn();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                computerTurn();
            }
        }, 1000);
    }*/
}
