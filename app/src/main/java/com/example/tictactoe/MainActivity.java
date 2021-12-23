package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;


public class MainActivity extends AppCompatActivity {
    public int move = 1;   //false - 1 player, 2 - 2 player
    public int step = 0;
    public int[] gameState = {0,0,0,0,0,0,0,0,0};
    public boolean modePlay = true;
    public int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };
    // 0 -empty, 2 - red, 1 -yellow
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        if (modePlay==true) {
            ImageView chip = (ImageView) view;
            TextView text = findViewById(R.id.winTextView);
            Button repeatBut = findViewById(R.id.button);
            if (chip.getDrawable() != null)
                Toast.makeText(this, "This place is exist", Toast.LENGTH_LONG).show();
            else {
                step++;
                chip.setTranslationY(-1500);
                int tag = Integer.parseInt(chip.getTag().toString());
                gameState[tag] = move;
                if (move == 1) {
                    chip.setImageResource(R.drawable.yellow);
                    move = 2;
                } else {
                    chip.setImageResource(R.drawable.red);
                    move = 1;
                }
                chip.animate().translationYBy(1500).setDuration(300);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 0) {
                        if (gameState[winningPosition[0]] == 1){
                            text.setText("Yellow player has won");
                            modePlay = false;
                            step = 0;
                        }
                        else {
                            text.setText("Red player has won");
                            modePlay = false;
                            step = 0;
                        }
                        text.setVisibility(View.VISIBLE);
                        repeatBut.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (step == 9) {
                repeatBut.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                text.setText("it's a draw");
                modePlay = false;
                step = 0;
            }
        }
        else Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
    }


    public void restart(View view){
        TextView text = findViewById(R.id.winTextView);
        view.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        move = 1;
        modePlay = true;
       GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i= 0;i<gridLayout.getChildCount();i++){
            gameState[i] = 0;
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }
}