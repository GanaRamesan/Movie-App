/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ganar on 10/16/2017.
 */

public class MovieInfoUtil {
    //method to parse the json for track information
    static ArrayList<MovieInfo> parseTrackInfos(String in) throws JSONException {
        ArrayList<MovieInfo> MovieInfoArrayList = new ArrayList<>();
        JSONObject root = new JSONObject(in);
        JSONArray tracks = root.getJSONArray("results");

        Log.d("root", root + "");
        Log.d("tracks",tracks+"");

        for(int i = 0;i<tracks.length();i++){
            JSONObject tracksJSONObject = tracks.getJSONObject(i);
            MovieInfo trackInfo = new MovieInfo();

            trackInfo.setRating(tracksJSONObject.getString("vote_average"));

            trackInfo.setPopularity(tracksJSONObject.getString("popularity"));
            trackInfo.setPosterpath(tracksJSONObject.getString("poster_path"));
            trackInfo.setMovie_name(tracksJSONObject.getString("original_title"));
            trackInfo.setOverview(tracksJSONObject.getString("overview"));
            trackInfo.setReleasedate(tracksJSONObject.getString("release_date"));

            MovieInfoArrayList.add(trackInfo);
        }
        return MovieInfoArrayList;
    }
}
