package me.itsof.a180315;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BridgeDelegate {

    public void Result(final JSONArray data) {
        try {
            // Populate list view with category titles
            ListView lv = (ListView) findViewById(R.id.lv);
            ArrayList<String> al = new ArrayList<String>();
            for (int i = 0; i < data.length(); ++i) {
                JSONObject category = data.getJSONObject(i);
                String name = category.getString("name");
                al.add(name);
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.posts_list_item, R.id.pitv, al);
            lv.setAdapter(aa);
            // Add click listener to show category posts
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowID) {
                    try {
                        JSONObject category = data.getJSONObject(position);
                        int id = category.getInt("id");
                        Intent i = new Intent(MainActivity.this, Categories.class);
                        i.putExtra("category", Integer.toString(id));
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

    public void Result(JSONObject data) {

    }

    public void Result(Object data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bridge b = new Bridge(this);
        b.execute("http://ubiquitous.csf.itesm.mx/~pddm-1021617/CMS/wp-json/wp/v2/categories", Bridge.ResourceType.RJSONArray);
    }
}