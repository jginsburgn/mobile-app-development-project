package me.itsof.a180118;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.gc.materialdesign.views.ButtonRectangle;
import android.widget.EditText;

public class Omnibar extends AppCompatActivity {

    private EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_omnibar);
        setTitle("Omnibar");
        texto = (EditText) findViewById(R.id.urlField);
        ButtonRectangle button1 = (ButtonRectangle) findViewById(R.id.go);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(Omnibar.this, Web.class);
                actividad.putExtra("url", texto.getText().toString());
                startActivity(actividad);
            }
        });
    }
}
