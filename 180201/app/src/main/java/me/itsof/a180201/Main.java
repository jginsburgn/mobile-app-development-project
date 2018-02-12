package me.itsof.a180201;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends AppCompatActivity {
    private String[] brands = {
            "Mazda",
            "Toyota",
            "Hyundai"
    };

    private void toast(String message) {
        // Code taken from: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        ((CheckBox) findViewById(R.id.starcb)).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        EditText yourEditText = (EditText) findViewById(R.id.editText);

        yourEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
                toast(s.toString());

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        View.OnClickListener lista = new View.OnClickListener() {
            public void onClick(View v) {
                String option = "";
                switch (v.getId()) {
                    case R.id.rb1:
                        option = "Option 1 ";
                        break;
                    case R.id.rb2:
                        option = "Option 2 ";
                        break;
                }
                toast(option + "has been selected.");
            }
        };

        ((RadioButton) findViewById(R.id.rb1)).setOnClickListener(lista);
        ((RadioButton) findViewById(R.id.rb2)).setOnClickListener(lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, brands);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoComplete);

        actv.setThreshold(3);
        actv.setAdapter(adapter);
    }

    public void btnGuardar_clickeado(View view) {
        toast("Guardar");
    }

    public void btnAbrir_clickeado(View view) {
        toast("Abrir");
    }

    public void btnAndroid_clickeado(View view) {
        toast("Android");
    }

    public void checkbox_Click(View view) {
        if (((CheckBox) view).isChecked()) {
            toast("Checkbox is checked.");
        }
        else {
            toast("Checkbox is not checked.");
        }
    }

    public void starCheckbox_Click(View view) {

    }

    public void toggleButtonClick(View v) {
        if (((ToggleButton) v).isChecked()) {
            toast(((ToggleButton) v).getTextOn().toString());
        }
        else {
            toast(((ToggleButton) v).getTextOff().toString());
        }
    }

    public void goToCRM(View v) {
        Intent intent = new Intent(Main.this, Menu.class);
        startActivity(intent);
    }
}
