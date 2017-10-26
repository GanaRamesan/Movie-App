/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {

    public static final String PREFS_NAME = "MUSIC_APP";
    public static final String FAVORITES = "Track_Favorite";
   // HashSet<TrackInfo> fv = new HashSet<>();
    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<MovieInfo> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.apply();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();


    }

    public void addFavorite(Context context, MovieInfo trackInfo) {
        ArrayList<MovieInfo> favorites = getFavorites(context);

        if (favorites == null) {
            favorites = new ArrayList<MovieInfo>();
        }
       // fv.add(trackInfo);
       // if (fv.contains(trackInfo))
        favorites.add(trackInfo);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, MovieInfo product) {
        ArrayList<MovieInfo> favorites = getFavorites(context);
        if (favorites != null) {
            if(favorites.contains(product)) {
                favorites.remove(product);
                saveFavorites(context, favorites);
            }
        }
    }

    public ArrayList<MovieInfo> getFavorites(Context context) {
        SharedPreferences settings;
        List<MovieInfo> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            MovieInfo[] favoriteItems = gson.fromJson(jsonFavorites,
                    MovieInfo[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<MovieInfo>(favorites);
        } else
            return null;

        return (ArrayList<MovieInfo>) favorites;
    }
}