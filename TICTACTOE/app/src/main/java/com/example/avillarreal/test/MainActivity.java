package com.example.avillarreal.test;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    Button a1,a2,a3,b1,b2,b3,c1,c2,c3;
    Button[] button_array;
    //This is AWESOMEMEEEEEEEE

    boolean turn=true;//X=true
    int turnC=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    }

        public void onClick(View v){
            Button b= (Button) v;
            button_clicked(b);
            final String A1 = a1.getText().toString();
            final String A2 = a2.getText().toString();
            final String A3 = a3.getText().toString();
            final String B1 = b1.getText().toString();
            final String B2 = b2.getText().toString();
            final String B3 = b3.getText().toString();
            final String C1 = c1.getText().toString();
            final String C2 = c2.getText().toString();
            final String C3 = c3.getText().toString();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if(success){
                            String A1 = jsonResponse.getString("A1");
                            String A2 = jsonResponse.getString("A2");
                            String A3 = jsonResponse.getString("A3");
                            String B1 = jsonResponse.getString("B1");
                            String B2 = jsonResponse.getString("B2");
                            String B3 = jsonResponse.getString("B3");
                            String C1 = jsonResponse.getString("C1");
                            String C2 = jsonResponse.getString("C2");
                            String C3 = jsonResponse.getString("C3");
                            button_array[0].setText(A1);
                            button_array[1].setText(A2);
                            button_array[2].setText(A3);
                            button_array[3].setText(B1);
                            button_array[4].setText(B2);
                            button_array[5].setText(B3);
                            button_array[6].setText(C1);
                            button_array[7].setText(C2);
                            button_array[8].setText(C3);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Please Select another tile");
                            builder.setNegativeButton("Retry",null);
                            builder.create();
                            builder.show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
            ArrayTTC original = new ArrayTTC(A1,A2,A3,B1,B2,B3,C1,C2,C3, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(original);
        }

    public void button_clicked(Button b){
        if (turn){
        b.setText("X");
        }
        else{
            b.setText("O");
        }
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
            if(!turn)
                toast("Player 1 Wins");
            else
                toast("Player 2 Wins");
            ButtonEnable(false);
        }

    }

    private void ButtonEnable(boolean enable){
        for(Button b:button_array){
            b.setClickable(enable);
        }
    }
    private void toast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
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
}
