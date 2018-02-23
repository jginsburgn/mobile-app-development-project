package me.itsof.a180212;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main extends AppCompatActivity {

    private EditText text;
    private final String FILE_NAME = "archivo_texto.txt";
    private final int BUFFER_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        setTitle("Almacenamiento de archivos.");

        text = (EditText) findViewById(R.id.text);

    }

    public void save(View v) {
        String str = text.getText().toString();
        try {
            FileOutputStream fout = openFileOutput(FILE_NAME, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fout);
            osw.write(str);
            osw.flush();
            osw.close();
            Toast.makeText(getBaseContext(), "File has been saved.", Toast.LENGTH_SHORT).show();
            text.setText("");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void load(View v) {
        try {
            FileInputStream fin = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fin);
            char[] inputBuffer = new char[BUFFER_SIZE];
            String contents = "";
            int charsRead;
            while ((charsRead = isr.read(inputBuffer)) != -1) {
                String readString = String.copyValueOf(inputBuffer, 0, charsRead);
                contents += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            text.setText(contents);
            Toast.makeText(getBaseContext(), "File loaded successfully.", Toast.LENGTH_SHORT).show();
            fin.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void goToCRM(View v) {
        Intent i = new Intent(Main.this, ContactSave.class);
        startActivity(i);
    }
}
