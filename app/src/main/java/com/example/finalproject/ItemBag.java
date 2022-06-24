package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Locale;

public class ItemBag extends AppCompatActivity {
    Button drawCards, searchButtonId;
    TextView playerName;
    EditText searchBar;
    ImageView playerAvatar, pokeLogo, pokeViews;

    JsonObjectRequest[] jsonImageQueries = new JsonObjectRequest[6];

//    SurfaceView surfaceView;
//    SurfaceHolder surfaceHolder;
//    TextView textView;

//    View pokeView;
//    Bitmap background;
//    Canvas backgroundCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_bag);

//        surfaceView = findViewById(R.id.surfaceView);
//        surfaceHolder = surfaceView.getHolder();
//        textView = findViewById(R.id.textView);
//        button.findViewById(R.id.button);

//        pokeView = findViewById(R.id.PokeViewStandardId);
        String pokemonName = "pikachu";
        String url = String.format("https://pokeapi.co/api/v2/pokemon/%s", pokemonName.toLowerCase(Locale.ROOT));
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                (JSONObject) null, response -> textView.setText(response.toString()),
                error -> textView.setText(error.toString()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(jsonObjectRequest);
            }
        });
    }
}