package com.example.avillarreal.test;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    public final static String player1_score="com.example.avillarreal.test.MainActivity.player1score";
    public final static String player2_score="com.example.avillarreal.test.MainActivity.player2score";

    private static final String SavedP1score = "playerscore1";
    private static final String SavedP2score = "playerscore2";

    Button a1,a2,a3,b1,b2,b3,c1,c2,c3;
    Button[] button_array;

    //This is AWESOMEMEEEEEEEE
    // This is Eric Reyna's Disturbing Laptop

    boolean turn=true;//X=true
    int turnC=0;
    int mPlayer1_score=0;
    int mPlayer2_score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: Create the retrieve php file. And work on the another class to retrieve the file - Vishal
        //TODO: Look up other ways to coordinate the TicTacToe game through php and sqlOnline - Vishal



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a1 = (Button) findViewById(R.id.A1);
        a2 = (Button) findViewById(R.id.A2);
        a3 = (Button) findViewById(R.id.A3);
        b1 = (Button) findViewById(R.id.B1);
        b2 = (Button) findViewById(R.id.B2);
        b3 = (Button) findViewById(R.id.B3);
        c1 = (Button) findViewById(R.id.C1);
        c2 = (Button) findViewById(R.id.C2);
        c3 = (Button) findViewById(R.id.C3);

        button_array = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        for (Button b : button_array) {
            b.setOnClickListener(this);
        }

        if (savedInstanceState != null){
            mPlayer1_score=savedInstanceState.getInt(SavedP1score);
            mPlayer2_score=savedInstanceState.getInt(SavedP2score);
        }
    }

        public void onClick(View v){
            Button b= (Button) v;
            button_clicked(b);
        }

    public void button_clicked(Button b){
        if (turn){
        b.setText("X");
        }
        else{
            b.setText("O");
        }
        turnC++;
        b.setClickable(false);
        turn=!turn;

        checkifWon();
    }

    public void checkifWon(){

        boolean winner=false;
        //check row
        if(a1.getText()==a2.getText()&&a2.getText()==a3.getText()&&!a1.isClickable()){
            winner = true;
        }
        else if(b1.getText()==b2.getText()&&b2.getText()==b3.getText()&&!b1.isClickable()){
            winner=true;
        }
        else if(c1.getText()==c2.getText()&&c2.getText()==c3.getText()&&!c1.isClickable()){
            winner=true;
        }

        //check column
        else if(a1.getText()==b1.getText()&&b1.getText()==c1.getText()&&!a1.isClickable()){
            winner = true;
        }
        else if(a2.getText()==b2.getText()&&b2.getText()==c2.getText()&&!a2.isClickable()){
            winner=true;
        }
        else if(a3.getText()==b3.getText()&&b3.getText()==c3.getText()&&!a3.isClickable()){
            winner=true;
        }
        //check diagonal
        else if(a1.getText()==b2.getText()&&b2.getText()==c3.getText()&&!a1.isClickable()){
            winner = true;
        }
        else if(a3.getText()==b2.getText()&&b2.getText()==c1.getText()&&!a3.isClickable()){
            winner = true;
        }


        if(winner){
            if(!turn) {
                toast("Player 1 Wins");
                mPlayer1_score=mPlayer1_score+1;
            }else {
                toast("Player 2 Wins");
                mPlayer2_score=mPlayer2_score+1;
            }ButtonEnable(false);
        }
        else if(turnC==9){
            toast("DRAW!");
        }


       if(winner==true || turnC==9){
       Intent intent = new Intent(MainActivity.this,Leaderboard.class);
        intent.putExtra(player1_score,String.valueOf(mPlayer1_score));
        intent.putExtra(player2_score, String.valueOf(mPlayer2_score));

        startActivity(intent);
        }

    }

    private void ButtonEnable(boolean enable){
        for(Button b:button_array){
            b.setClickable(enable);
            if(enable)
                b.setText("");
        }

    }
    private void toast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SavedP1score,mPlayer1_score);
        savedInstanceState.putInt(SavedP2score,mPlayer2_score);
        super.onSaveInstanceState(savedInstanceState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        mPlayer1_score = savedInstanceState.getInt(SavedP1score);
        mPlayer2_score = savedInstanceState.getInt(SavedP2score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
