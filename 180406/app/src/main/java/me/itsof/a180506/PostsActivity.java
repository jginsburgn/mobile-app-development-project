package me.itsof.a180506;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {
    final private String SERVICE_URL = "http://ubiquitous.csf.itesm.mx/~pddm-1021617/servicio.php?";

    private String id = "";
    private String marca = "";
    private String auto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        id = b.getString("id");
        marca = b.getString("marca");
        auto = b.getString("id");
        String finalURL = SERVICE_URL;
        Log.d("ID", "ID IS" + id);
        if (id.length() != 0) finalURL += "&id=" + id;
        if (marca.length() != 0) finalURL += "&marca=" + marca;
        if (auto.length() != 0) finalURL += "&auto=" + auto;

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, finalURL, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(final JSONArray data) {
                    try {
                        ListView lv = (ListView) findViewById(R.id.clv);
                        ArrayList<String> al = new ArrayList<String>();
                        for (int i = 0; i < data.length(); ++i) {
                            JSONObject post = data.getJSONObject(i);
                            String auto = post.getString("auto");
                            al.add(auto);
                        }
                        ArrayAdapter<String> aa = new ArrayAdapter<String>(PostsActivity.this, R.layout.posts_list_item, R.id.pitv, al);
                        lv.setAdapter(aa);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowID) {
                                try {
                                    JSONObject post = data.getJSONObject(position);
                                    String url = post.getString("url");
                                    Intent i = new Intent(PostsActivity.this, PostActivity.class);
                                    i.putExtra("url", url);
                                    startActivity(i);
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Posts Activity", error.getMessage());
            }
        });

        queue.add(stringRequest);
    }
}
