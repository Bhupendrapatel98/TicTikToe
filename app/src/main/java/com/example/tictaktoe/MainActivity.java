package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    TextView status;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);
    }

    public void playerTap(View view) {

        ImageView img = (ImageView) view; //gindImageview

        int tappedImage = Integer.parseInt(img.getTag().toString()); //getImagePosition
        Log.i("jshdjddd", "playerTap: "+tappedImage);

        if(!gameActive){
            gameReset(view);
        }

        if(gameState[tappedImage] == 2 && gameActive){

            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                status.setText("O turn");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                status.setText("X turn");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Check if any player has won

        for (int[] winPosition : winPositions){

            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){

                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has won";
                }
                else{
                    winnerStr = "O has won";
                }

                // Update the status bar for winner announcement
                status.setText(winnerStr);


            }
        }

    }

    private void gameReset(View view) {

        gameActive = true;
        activePlayer = 0;

        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        status.setText("X' Turn - Tap to play");

    }

}
