package me.itsof.a180218;

import android.content.*;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class AutocompleteAndSharedPreferences extends Fragment {

    private AutoCompleteTextView name;
    private EditText contact;

    public AutocompleteAndSharedPreferences() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_autocomplete_and_shared_preferences, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        name = (AutoCompleteTextView) view.findViewById(R.id.name);
        contact = (EditText) view.findViewById(R.id.contact);

        ((Button) getView().findViewById(R.id.save)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                save();
            }
        });
        ((Button) getView().findViewById(R.id.retrieve)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                retrieve();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getKeys());

        name.setThreshold(1);
        name.setAdapter(adapter);
    }

    private String[] getKeys() {
        ArrayList<String> keys = new ArrayList<String>();
        android.content.SharedPreferences prefs = getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            keys.add(entry.getKey());
        }
        return keys.toArray(new String[0]);
    }

    public void save() {
        String nameData = name.getText().toString();
        String contactData = contact.getText().toString();
        android.content.SharedPreferences prefs = getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putString(nameData, contactData);
        editor.commit();
        toast("Contact saved.");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getKeys());

        name.setThreshold(1);
        name.setAdapter(adapter);
    }

    public void retrieve() {
        String nameData = name.getText().toString();
        SharedPreferences prefs = getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        String contactData = prefs.getString(nameData, "");
        contact.setText(contactData);
        if (contactData.length() == 0) toast("Contact does not exist.");
    }

    private void toast(String message) {
        // Code taken from: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
        Context context = getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
