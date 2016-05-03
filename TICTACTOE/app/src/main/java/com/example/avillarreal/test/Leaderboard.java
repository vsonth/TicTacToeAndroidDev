package com.example.avillarreal.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Leaderboard extends AppCompatActivity {
    public final static String newgame="com.example.avillarreal.test.MainActivity.newgame";

    int score1;
    int score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button NewGame=(Button) findViewById(R.id.newgame);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        TextView textView11 = (TextView) findViewById(R.id.textView11);

        Intent intent= getIntent();
        final String text1=intent.getStringExtra(MainActivity.player1_score);
        final String text2=intent.getStringExtra(MainActivity.player2_score);
        score1=Integer.parseInt(text1);
        score2=Integer.parseInt(text2);
        if (score1>score2) {
            textView2.setText("Player1: " + text1);
            textView3.setText("Player2: " + text2);
        }else{
            textView2.setText("Player2: " + text2);
            textView3.setText("Player1: " + text1);
        }

NewGame.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Leaderboard.this,MainActivity.class);
       // intent.putExtra(newgame,"true");
        startActivity(intent);
    }
});
        }
}
