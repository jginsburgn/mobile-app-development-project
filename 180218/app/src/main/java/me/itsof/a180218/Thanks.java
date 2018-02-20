package me.itsof.a180218;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Thanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        String answers = "";
        Bundle extras = getIntent().getExtras();
        if (extras == null) return;
        for (String key : extras.keySet()) {
            String answer = (String) extras.get(key);
            answers += answer + "\n";
        }

        ((TextView) findViewById(R.id.answers)).setText(answers);
    }
}
