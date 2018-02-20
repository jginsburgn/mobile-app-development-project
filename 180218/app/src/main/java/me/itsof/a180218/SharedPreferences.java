package me.itsof.a180218;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferences extends Fragment {

    private EditText name;
    private EditText contact;

    public SharedPreferences() {
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
        return inflater.inflate(R.layout.fragment_shared_preferences, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        name = getView().findViewById(R.id.name);
        contact = getView().findViewById(R.id.contact);
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
    }

    public void save() {
        String nameData = name.getText().toString();
        String contactData = contact.getText().toString();
        android.content.SharedPreferences prefs = getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putString(nameData, contactData);
        editor.commit();
        toast("Contact saved.");
    }

    public void retrieve() {
        String nameData = name.getText().toString();
        android.content.SharedPreferences prefs = getContext().getSharedPreferences("crm", Context.MODE_PRIVATE);
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
