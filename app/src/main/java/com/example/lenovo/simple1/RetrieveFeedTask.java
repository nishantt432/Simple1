package com.example.lenovo.simple1;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


 class RetrieveFeedTask extends AsyncTask<Void, Void, String>  {

    private Exception exception;
    public String finalString1;
     public String finalString2;
     public String number;
     private TextView Showtext;
     private TextView Showtext2;
     public String type;
     static final String API_URL = "http://numbersapi.com/";
     public StringBuilder stringBuilder2;

     public RetrieveFeedTask(TextView Showtext,TextView Showtext2,String number){
         this.Showtext = Showtext;
         this.number = number;
         this.Showtext2 = Showtext2;

     }

protected void onPreExecute() {

    }

    protected String doInBackground(Void... urls) {

        try {
            URL url1 = new URL(API_URL+number+"/"+"trivia"+ "?json" );
            URL url2 = new URL(API_URL+number+"/"+"math"+ "?json" );

            HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
            HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();

            try {
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(urlConnection1.getInputStream()));
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(urlConnection2.getInputStream()));

                StringBuilder stringBuilder1 = new StringBuilder();
                 stringBuilder2 = new StringBuilder();


                while ((finalString1 = bufferedReader1.readLine()) != null) {
                    stringBuilder1.append(finalString1).append("\n");

                }
                while ((finalString2 = bufferedReader2.readLine()) != null) {
                    stringBuilder2.append(finalString2).append("\n");

                }
                bufferedReader1.close();
                bufferedReader2.close();
                return  stringBuilder1.toString();
            }
            finally{
                urlConnection1.disconnect();
                urlConnection2.disconnect();


            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
             finalString1 = object.getString("text");
             Showtext.setText(finalString1);
           JSONObject object2 = (JSONObject) new JSONTokener(stringBuilder2.toString()).nextValue();
            finalString2 = object2.getString("text");
           Showtext2.setText(finalString2);


           Log.i("Here",finalString2);

        } catch (JSONException e) {

        }

    }
}