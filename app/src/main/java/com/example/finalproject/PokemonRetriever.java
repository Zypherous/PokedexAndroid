package com.example.finalproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

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
//    public List <Pokemon> getPokemonById(String pokeID){
//
//    }
}
