package me.itsof.a180315;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Categories extends AppCompatActivity implements BridgeDelegate {

    public void Result(final JSONArray data) {
        try {
            ListView lv = (ListView) findViewById(R.id.clv);
            ArrayList<String> al = new ArrayList<String>();
            for (int i = 0; i < data.length(); ++i) {
                JSONObject post = data.getJSONObject(i);
                String title = post.getJSONObject("title").getString("rendered");
                al.add(title);
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.posts_list_item, R.id.pitv, al);
            lv.setAdapter(aa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowID) {
                    try {
                        JSONObject post = data.getJSONObject(position);
                        int id = post.getInt("id");
                        Intent i = new Intent(Categories.this, PostActivity.class);
                        i.putExtra("id", Integer.toString(id));
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
        setContentView(R.layout.activity_categories);

        Intent i = getIntent();
        String category = i.getExtras().getString("category");
        Bridge b = new Bridge(this);
        b.execute("http://ubiquitous.csf.itesm.mx/~pddm-1021617/CMS/wp-json/wp/v2/posts?categories=" + category, Bridge.ResourceType.RJSONArray);
    }
}
