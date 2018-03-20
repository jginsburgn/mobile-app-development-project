package me.itsof.a180315;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostActivity extends AppCompatActivity implements BridgeDelegate {

    public void Result(JSONArray data) {
        Log.d("RESULT", data.toString());
    }

    public void Result(JSONObject data) {
        try {
            WebView webview = (WebView) findViewById(R.id.wb);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadDataWithBaseURL("", data.getJSONObject("content").getString("rendered"), "text/html", "UTF-8", "");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Result(Object data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent i = getIntent();
        String id = i.getExtras().getString("id");
        Bridge b = new Bridge(this);
        b.execute("http://ubiquitous.csf.itesm.mx/~pddm-1021617/CMS/wp-json/wp/v2/posts/" + id, Bridge.ResourceType.RJSONObject);
    }
}
