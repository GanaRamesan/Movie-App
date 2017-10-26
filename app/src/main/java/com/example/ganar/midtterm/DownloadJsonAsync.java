/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class DownloadJsonAsync extends AsyncTask<RequestParam, Integer, String> {
    private Context context;
    private Activity activity;
    ArrayList<MovieInfo> trackInfoArrayList = new ArrayList<>();

    @Override
    protected String doInBackground(RequestParam... strings) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            HttpURLConnection con = strings[0].setUpCOnnectio();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            trackInfoArrayList = MovieInfoUtil.parseTrackInfos(s.toString());
            if(trackInfoArrayList.size()==0)
                Toast.makeText(context, "No movie Found", Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) activity.findViewById(R.id.tracklistResults);
        CustomAdapter customAdapter = new CustomAdapter(context, trackInfoArrayList);
        MainActivity.InfoArrayList = trackInfoArrayList;
        listView.setAdapter(customAdapter);
        customAdapter.setNotifyOnChange(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("demo", "onItemClick: ");
                Intent i1 = new Intent(context, MovieDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("json", trackInfoArrayList.get(i));
                i1.putExtras(bundle);
                context.startActivity(i1);


            }
        });
    }

    public DownloadJsonAsync(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

}
