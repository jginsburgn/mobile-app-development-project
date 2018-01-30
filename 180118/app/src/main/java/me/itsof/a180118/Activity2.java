package me.itsof.a180118;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    public int counter = 0;

    private void toast(String message) {
        // Code taken from: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Contador");
        setContentView(R.layout.layout_activity2);
        Button counter = (Button) findViewById(R.id.counter);
        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(Integer.toString(++Activity2.this.counter));
            }
        });
    }
}
