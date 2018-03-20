package me.itsof.a180315;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jonathan on 3/15/18.
 */

public class Bridge extends AsyncTask<String, Void, Object> {

    private BridgeDelegate delegate;

    Bridge(BridgeDelegate delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    protected Object doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            urlConnection.disconnect();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        try {
            JSONObject parsedResult = new JSONObject((String) result);
            delegate.Result(parsedResult);
        }
        catch (JSONException e) {
            try {
                JSONArray parsedResult = new JSONArray((String) result);
                delegate.Result(parsedResult);
            }
            catch (JSONException ee) {
                delegate.Result(result);
            }
        }
        delegate.Result(result);
    }
}
