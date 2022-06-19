package com.example.finalproject;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class PokemonRetriever {
    private static PokemonRetriever instance;
    private RequestQueue requestQueue;
    private Pokemon pokemon;
    private static Context ctx;

    private PokemonRetriever(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized PokemonRetriever getInstance(Context context) {
        if (instance == null) {
            instance = new PokemonRetriever(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
