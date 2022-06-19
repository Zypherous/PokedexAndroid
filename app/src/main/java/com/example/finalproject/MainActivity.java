package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RequestQueue queue;
    Button simpleRequestBtn;
    String pokeQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokeQuery = "1";
        textView = (TextView) findViewById(R.id.text);
        simpleRequestBtn = (Button) findViewById(R.id.button_test);
        queue = Volley.newRequestQueue(this);
        simpleRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokeApiUrl = "https://pokeapi.co/api/v2/pokemon/1" + pokeQuery;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pokeApiUrl,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String pokeName = response.getString("name");
                            textView.setText(pokeName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String pokeName = "Not found";
                        textView.setText(pokeName);
                    }
                });
            }
        });
    }



//    public void simpleRequest(View view) {
//        // Instantiate the RequestQueue.
//
//        String url = "https://pokeapi.co/api/v2/pokemon/ditto";
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0, 500));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
//            }
//        });

// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
}

