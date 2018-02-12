package me.itsof.a180201;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class SharedArray extends AppCompatActivity {

    private AutoCompleteTextView name;
    private EditText contact;

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
        setContentView(R.layout.activity_shared_array);

        name = (AutoCompleteTextView) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getKeys());

        name.setThreshold(1);
        name.setAdapter(adapter);
    }

    private String[] getKeys() {
        ArrayList<String> keys = new ArrayList<String>();
        SharedPreferences prefs = getSharedPreferences("crm", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            keys.add(entry.getKey());
        }
        return keys.toArray(new String[0]);
    }

    public void save(View v) {
        String nameData = name.getText().toString();
        String contactData = contact.getText().toString();
        SharedPreferences prefs = getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(nameData, contactData);
        editor.commit();
        toast("Contact saved.");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getKeys());

        name.setThreshold(1);
        name.setAdapter(adapter);
    }

    public void retrieve(View v) {
        String nameData = name.getText().toString();
        SharedPreferences prefs = getSharedPreferences("crm", Context.MODE_PRIVATE);
        String contactData = prefs.getString(nameData, "");
        contact.setText(contactData);
        if (contactData.length() == 0) toast("Contact does not exist.");
    }
}
