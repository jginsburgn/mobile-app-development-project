package mx.itesm.csf.almacenamientosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import models.Models;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Models.context = getApplicationContext();
        try {
            Models.GetModels();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
