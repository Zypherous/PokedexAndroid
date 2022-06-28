package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityTest extends AppCompatActivity {
    private static final int DONE = 1;
    TextView textView;
    EditText etDataInput;
    Button simpleRequestBtn, getPokemon;
    String pokeQuery;
    List<Pokemon> pokemons;
    public static  final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        PokemonRetriever pokemonRetriever = new PokemonRetriever(MainActivityTest.this);
        etDataInput = findViewById(R.id.et_dataInput);
        pokeQuery = "10000";
        textView = (TextView) findViewById(R.id.text);
        simpleRequestBtn = (Button) findViewById(R.id.button_test);
        getPokemon = (Button) findViewById(R.id.button_nextIntent);
        pokemons = new ArrayList<>();
        simpleRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonRetriever.getPokeName(etDataInput.getText().toString().toLowerCase(), new PokemonRetriever.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivityTest.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Pokemon poke = new Pokemon();
                            pokemonRetriever.makePokemon(poke, response, pokemons, textView);
                            textView.setText(poke.getDescription());

                            Log.d("MainActivity", "Pokemon added: " + poke.toString());
//                            poke.setInfo(response);
//                            Log.d(TAG, "POKEMON AFTER SET INFO: " + poke.toString());
//
//                            pokemonRetriever.getPokeDesc(
//                                    response.getJSONObject("species").getString("url").toString(),
//                                    new PokemonRetriever.VolleyResponseListener() {
//                                        @Override
//                                        public void onError(String message) {
//
//                                        }
//
//                                        @Override
//                                        public void onResponse(JSONObject response) {
//                                            try {
//                                                JSONArray flavor = response.getJSONArray("flavor_text_entries");
//                                                String desc = flavor.getJSONObject(0).getString("flavor_text").toString();
//                                                textView.setText(desc);
//                                                poke.setDescription(response);
//                                                pokemons.add(poke);
//                                                Log.d(TAG, "POKEMON: " + poke.toString());
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onResponse(String response) {
//
//                                        }
//                                    });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String pokeName) {
                        Toast.makeText(MainActivityTest.this, "Returned Pokemon Named: " + pokeName, Toast.LENGTH_SHORT).show();
                        textView.setText(pokeName );
                        Log.d(TAG, "Pokemon name from onClick(): " + pokeName);


                    }
                });

            }
        });
        getPokemon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!pokemons.isEmpty()) {
                    Intent intent = new Intent(MainActivityTest.this, PokemonDetailsActivity.class);
                    for (Pokemon poke : pokemons) {
                        if (poke.getName().toLowerCase().equals(etDataInput.getText().toString().toLowerCase())) {
                            intent.putExtra("POKEMON", pokemons.get(pokemons.indexOf(poke)));
                            startActivityForResult(intent, DONE);
                        }
                    }
                }
            }
        });
    }

//    public void calculateXPosition(ProgressBar seekBar, TextView progressTextView){
//        double xPosition=  (((seekBar.ge - seekBar.left) / seekBar.max) * seekBar.progress ) + seekBar.left
//
//        progressTextView.tray= xPosition.toFloat() - (progressTextView.width/2)
//    }
}

