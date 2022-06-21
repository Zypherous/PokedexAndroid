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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText etDataInput;
    Button simpleRequestBtn;
    String pokeQuery;
    public static  final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etDataInput = findViewById(R.id.et_dataInput);
        pokeQuery = "10000";
        textView = (TextView) findViewById(R.id.text);
        simpleRequestBtn = (Button) findViewById(R.id.button_test);
        simpleRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokemonRetriever pokemonRetriever = new PokemonRetriever(MainActivity.this);
                pokemonRetriever.getPokeName(etDataInput.getText().toString(), new PokemonRetriever.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
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
    }
}

