package com.example.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonRetriever {

    public static final String API_V_2_POKEMON = "https://pokeapi.co/api/v2/pokemon/";

    Context context;
    String pokeName;
    public PokemonRetriever(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String response);
    }

    public void getPokeName(String pokeId, VolleyResponseListener volleyResponseListener){
        Log.d("BUTTON"  , "Inside button onClick");
        String pokeApiUrl = API_V_2_POKEMON + pokeId;
        Log.d("BUTTON", pokeApiUrl);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pokeApiUrl,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pokeName = "";
                try {
                    pokeName = response.getString("name");

                    Log.d("BUTTON", pokeName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(context, "Retriever: Pokemon Named --> " +
//                        pokeName, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(pokeName);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String pokeName = "Not found";
//                Toast.makeText(context, "Retriever: Pokemon Named --> " + pokeName, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong");
            }
        });
        RequestSingleton.getInstance(context ).addToRequestQueue(request);
    }




//    public List<Pokemon> getPokemonByName(String pokeName){
//
//    }
    public void  getPokemonById(String pokeID, View view){
//        List<Pokemon> pokemonInfo = new ArrayList<>();

        String url = API_V_2_POKEMON + pokeID;
        Log.d("PokemonRetriever", "Insiside get Pokemon by ID");
        Log.d("PokemonRetriever", "url: " + url);
        //Get json Object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url
                ,null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    getPokeDesc(response.getJSONObject("species").getString("url").toString(), view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestSingleton.getInstance(context ).addToRequestQueue(request);
        // get properties of pokemon including second json object with description

        // create the pokemon
    }

    public void getPokeDesc(String url, View view) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url
                ,null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray flavor = response.getJSONArray("flavor_text_entries");
                    String desc = flavor.getJSONObject(0).getString("flavor_text").toString();
                    Toast.makeText(context, desc, Toast.LENGTH_SHORT).show();
                    TextView tv  = (View) MainActivity.findViewById(R.id.text);
                    tv.setText(desc);


                } catch (JSONException e) {
                    Toast.makeText(context, "Description unavailable", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestSingleton.getInstance(context ).addToRequestQueue(request);
    }
}
