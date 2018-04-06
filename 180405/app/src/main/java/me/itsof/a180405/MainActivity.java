package me.itsof.a180405;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "http://ubiquitous.csf.itesm.mx/~raulms/do/REST/";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
    }

    public void pullString(View v) {
        String endpoint = "string.app";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, BASE_URL + endpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder alertadd = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                        final View view = factory.inflate(R.layout.alert_with_text, null);
                        ((TextView) view.findViewById(R.id.alertText)).setText(response);
                        alertadd.setView(view);
                        alertadd.setNeutralButton("Ok!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {

                            }
                        });

                        alertadd.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void pullJSON(View v) {
        String endpoint = "ObjetoJSON.app";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, BASE_URL + endpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder alertadd = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                        final View view = factory.inflate(R.layout.alert_with_text, null);
                        ((TextView) view.findViewById(R.id.alertText)).setText(response);
                        alertadd.setView(view);
                        alertadd.setNeutralButton("Ok!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {

                            }
                        });

                        alertadd.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void pullJSONArray(View v) {
        String endpoint = "ArregloJSON.app?count=2";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, BASE_URL + endpoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder alertadd = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                        final View view = factory.inflate(R.layout.alert_with_text, null);
                        ((TextView) view.findViewById(R.id.alertText)).setText(response);
                        alertadd.setView(view);
                        alertadd.setNeutralButton("Ok!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {

                            }
                        });

                        alertadd.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void pullImage(View v) {
        // Initialize a new ImageRequest
        ImageRequest imageRequest = new ImageRequest(
                "http://ubiquitous.csf.itesm.mx/~raulms/images/people/Frida.jpg", // Image URL
                new Response.Listener<Bitmap>() { // Bitmap listener
                    @Override
                    public void onResponse(Bitmap response) {
                        Log.d("onr", "done");
                        AlertDialog.Builder alertadd = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                        final View view = factory.inflate(R.layout.alert_with_image, null);
                        ((ImageView) view.findViewById(R.id.alertImage)).setImageBitmap(response);
                        alertadd.setView(view);
                        alertadd.setNeutralButton("Ok!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {

                            }
                        });

                        alertadd.show();
                    }
                },
                0, // Image width
                0, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
                new Response.ErrorListener() { // Error listener
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Add ImageRequest to the RequestQueue
        queue.add(imageRequest);
    }
}
