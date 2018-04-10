package me.itsof.a180506;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText id;
    EditText marca;
    EditText auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.id);
        marca = (EditText) findViewById(R.id.marca);
        auto = (EditText) findViewById(R.id.auto);
    }

    public void search(View v) {
        Intent i = new Intent(this, PostsActivity.class);
        i.putExtra("id", id.getText().toString());
        i.putExtra("marca", marca.getText().toString());
        i.putExtra("auto", auto.getText().toString());
        startActivity(i);
    }
}
