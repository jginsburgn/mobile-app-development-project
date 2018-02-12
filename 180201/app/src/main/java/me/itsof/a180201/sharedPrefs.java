package me.itsof.a180201;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class sharedPrefs extends AppCompatActivity {

    private EditText name;
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
        setContentView(R.layout.layout_shared_prefs);

        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
    }



    public void save(View v) {
        String nameData = name.getText().toString();
        String contactData = contact.getText().toString();
        SharedPreferences prefs = getSharedPreferences("crm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(nameData, contactData);
        editor.commit();
        toast("Contact saved.");
    }

    public void retrieve(View v) {
        String nameData = name.getText().toString();
        SharedPreferences prefs = getSharedPreferences("crm", Context.MODE_PRIVATE);
        String contactData = prefs.getString(nameData, "");
        contact.setText(contactData);
        if (contactData.length() == 0) toast("Contact does not exist.");
    }
}
