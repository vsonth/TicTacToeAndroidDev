package com.example.avillarreal.test;


import android.app.AlertDialog;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    public final static String player1_score="com.example.avillarreal.test.MainActivity.player1score";
    public final static String player2_score="com.example.avillarreal.test.MainActivity.player2score";
    public final static String TAG="MainActivity";

    private static final String SavedP1score = "playerscore1";
    private static final String SavedP2score = "playerscore2";
    String A1;
    String A2;
    String A3;
    String B1;
    String B2;
    String B3;
    String C1;
    String C2;
    String C3;


    Button a1,a2,a3,b1,b2,b3,c1,c2,c3;
    Button[] button_array;
//<<<<<<< HEAD
//=======
    //This is AWESOMEMEEEEEEEE
    // This is Eric Reyna's Disturbing Laptop
//>>>>>>> origin/master

    boolean turn=true;   //X=true
    int turnC=0;        //Tracks how many turns have passed (0-9) before game ends
    int mPlayer1_score=0;//Score for player 1 that can be added to database
    int mPlayer2_score=0;//Score for player 2 that can be added to database


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: Create the retrieve php file. And work on the another class to retrieve the file - Vishal
        //TODO: Look up other ways to coordinate the TicTacToe game through php and sqlOnline - Vishal




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Button a1 = (Button) findViewById(R.id.A1);
        Button a2 = (Button) findViewById(R.id.A2);
        Button a3 = (Button) findViewById(R.id.A3);
        Button b1 = (Button) findViewById(R.id.B1);
        Button b2 = (Button) findViewById(R.id.B2);
        Button b3 = (Button) findViewById(R.id.B3);
        Button c1 = (Button) findViewById(R.id.C1);
        Button c2 = (Button) findViewById(R.id.C2);
        Button c3 = (Button) findViewById(R.id.C3);*/

        //button_array = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        /*for (Button b : button_array) {
            b.setOnClickListener(this);

        }*/

        if (savedInstanceState != null){  //Keeps the score for when you flip screen
            mPlayer1_score=savedInstanceState.getInt(SavedP1score);
            mPlayer2_score=savedInstanceState.getInt(SavedP2score);
        }
    }

        public void onClick(View v){
            Button a1 = (Button) findViewById(R.id.A1);
            Button a2 = (Button) findViewById(R.id.A2);
            Button a3 = (Button) findViewById(R.id.A3);
            Button b1 = (Button) findViewById(R.id.B1);
            Button b2 = (Button) findViewById(R.id.B2);
            Button b3 = (Button) findViewById(R.id.B3);
            Button c1 = (Button) findViewById(R.id.C1);
            Button c2 = (Button) findViewById(R.id.C2);
            Button c3 = (Button) findViewById(R.id.C3);
            if (v.getId() == R.id.A1) {
                Button b = (Button) v;
                if (turn){
                    b.setText("X");
                    Log.d(TAG, "Pressed X");
                    A1 = a1.getText().toString();
                    //A2 = a2.getText().toString();
                    //A3 = a3.getText().toString();
                    //B1 = b1.getText().toString();
                    //B2 = b2.getText().toString();
                    //B3 = b3.getText().toString();
                    //C1 = c1.getText().toString();
                    //C2 = c2.getText().toString();
                    //C3 = c3.getText().toString();
                    Log.d(TAG, "Created String");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Went inside response listener");
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                Log.d(TAG, "ResponseListener");
                                if (success) {
                                    Log.d(TAG, "Success!");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Insert Successful");
                                    builder.setNegativeButton("Continue", null);
                                    builder.create();
                                    builder.show();
                                } else {
                                    Log.d(TAG, "Failed!");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Insert Failed");
                                    builder.setNegativeButton("Retry", null);
                                    builder.create();
                                    builder.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Log.d(TAG, "After OnResponse");
                    ArrayTTC ArraySend = new ArrayTTC(A1, A2, A3, B1, B2, B3, C1, C2, C3, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(ArraySend);

                    //SendData(b);
                    Log.d(TAG,"After SendData");
                }
                else{
                    b.setText("O");
                    Log.d(TAG, "Pressed O");
                    //SendData(b);
                    Log.d(TAG, "After SendData");
                }

                turnC++;
                b.setClickable(false);
                turn=!turn;

                //checkifWon(); //not yet
            }
        }
        public void SendData(Button b){
        /////// Insert PHP code
            Log.d(TAG,"Inside SendData");
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String A1 = a1.getText().toString();
                    final String A2 = a2.getText().toString();
                    final String A3 = a3.getText().toString();
                    final String B1 = b1.getText().toString();
                    final String B2 = b2.getText().toString();
                    final String B3 = b3.getText().toString();
                    final String C1 = c1.getText().toString();
                    final String C2 = c2.getText().toString();
                    final String C3 = c3.getText().toString();
                    Log.d(TAG, "Created String");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Went inside response listener");
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                Log.d(TAG, "ResponseListener");
                                if (success) {
                                    Log.d(TAG, "Success!");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Insert Successful");
                                    builder.setNegativeButton("Continue", null);
                                    builder.create();
                                    builder.show();
                                } else {
                                    Log.d(TAG, "Failed!");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Insert Failed");
                                    builder.setNegativeButton("Retry", null);
                                    builder.create();
                                    builder.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Log.d(TAG, "After OnResponse");
                    ArrayTTC ArraySend = new ArrayTTC(A1, A2, A3, B1, B2, B3, C1, C2, C3, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(ArraySend);

                }
            });

        ///////End PHP Code

    }

    public void button_clicked(Button b){ //This is to place the x and o's on the tiles
        if (turn){
            b.setText("X");
            Log.d(TAG, "Pressed X");
            SendData(b);
            Log.d(TAG,"After SendData");
        }
        else{
            b.setText("O");
            Log.d(TAG, "Pressed O");
            SendData(b);
            Log.d(TAG, "After SendData");
        }

        turnC++;
        b.setClickable(false);
        turn=!turn;

        checkifWon();
    }

    public void checkifWon(){ //Checks every combination to see if someone wins

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


        if(winner){ //If there is a winner, score adds +1 to last player and toasts win message
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


       if(winner==true || turnC==9){ //Sends to leaderboard page
       Intent intent = new Intent(MainActivity.this,Leaderboard.class);
        intent.putExtra(player1_score,String.valueOf(mPlayer1_score));
        intent.putExtra(player2_score, String.valueOf(mPlayer2_score));

        startActivity(intent);
        }

    }

    private void ButtonEnable(boolean enable){ //After a win, it disables the buttons
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
