package com.example.avillarreal.test;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishal on 5/2/2016.
 */
public class ArrayTTC extends StringRequest {

    private static final String Array_Request_URL ="http://vsonthalia.comli.com/TICTACTOE_ORIGINAL.php";
    private Map<String,String> params;


    public ArrayTTC(String A1, String A2, String A3, String B1, String B2, String B3, String C1, String C2, String C3, Response.Listener<String> listener){
        super(Method.POST,Array_Request_URL,listener,null);
        params = new HashMap<>();
        params.put("A1",A1);
        params.put("A2",A2);
        params.put("A3",A3);
        params.put("B1",B1);
        params.put("B2",B2);
        params.put("B3",B3);
        params.put("C1",C1);
        params.put("C2",C2);
        params.put("C3",C3);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
