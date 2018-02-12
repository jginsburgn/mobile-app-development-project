package me.itsof.a180118;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RateGrade extends AppCompatActivity {

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
        setContentView(R.layout.layout_rate_grade);
    }

    private boolean inInterval(int value, int lowerInclusive, int upperInclusive) {
        return lowerInclusive <= value && value <= upperInclusive;
    }

    public void rate(View v) {
        EditText g = (EditText) findViewById(R.id.grade);
        int val = Integer.parseInt(g.getText().toString());
        String message = "";
        if (inInterval(val, 10, 70)) message = "Malo";
        if (inInterval(val, 71, 80)) message = "Regular";
        if (inInterval(val, 81, 90)) message = "Bueno";
        if (inInterval(val, 91, 95)) message = "Mejor";
        if (inInterval(val, 96, 100)) message = "Excelente";
        toast(message);
    }
}
