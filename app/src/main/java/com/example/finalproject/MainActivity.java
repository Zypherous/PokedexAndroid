package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText etDataInput;
    Button simpleRequestBtn, getPokemon;
    String pokeQuery;
    List<Pokemon> Pokemons;
    public static  final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PokemonRetriever pokemonRetriever = new PokemonRetriever(MainActivity.this);
        etDataInput = findViewById(R.id.et_dataInput);
        pokeQuery = "10000";
        textView = (TextView) findViewById(R.id.text);
        simpleRequestBtn = (Button) findViewById(R.id.button_test);
        getPokemon = (Button) findViewById(R.id.button_getPokemon);
        simpleRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pokemonRetriever.getPokeName(etDataInput.getText().toString(), new PokemonRetriever.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Pokemon poke = new Pokemon();

                            pokemonRetriever.getPokeDesc(
                                    response.getJSONObject("species").getString("url").toString(),
                                    new PokemonRetriever.VolleyResponseListener() {
                                        @Override
                                        public void onError(String message) {

                                        }

                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                JSONArray flavor = response.getJSONArray("flavor_text_entries");
                                                String desc = flavor.getJSONObject(0).getString("flavor_text").toString();
                                                textView.setText(desc);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onResponse(String response) {

                                        }
                                    });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String pokeName) {
                        Toast.makeText(MainActivity.this, "Returned Pokemon Named: " + pokeName, Toast.LENGTH_SHORT).show();
                        textView.setText(pokeName );
                        Log.d(TAG, "Pokemon name from onClick(): " + pokeName);


                    }
                });

            }
        });
        getPokemon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                pokemonRetriever.getPokemonById(etDataInput.getText().toString());
//
//                , new PokemonRetriever.VolleyResponseListener() {
//
//                    @Override
//                    public void onError(String message) {
//                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }
}

