package com.uma.android.cmpi.task;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Umamaheshwar HS on 7/7/2016.
 */
public class RestClientAsyncTask extends AsyncTask<Void,Void,String> {

     private String TAG=this.getClass().getName();

     private URL url;
     private OnTaskCompleted callBackListener;

    public RestClientAsyncTask(String url,OnTaskCompleted callBackListener) throws MalformedURLException {
        this.url=new URL(url);
        this.callBackListener=callBackListener;
    }




    public interface OnTaskCompleted{
        void onTaskComplete(String response);
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder sb=new StringBuilder();
        try {

            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            InputStream is=urlConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String line;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String response) {
        callBackListener.onTaskComplete(response);
    }
}
