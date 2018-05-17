package mx.itesm.csf.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.itesm.csf.proyectofinal.Controller.Services;
import mx.itesm.csf.proyectofinal.Model.SearchModel;

public class RegisterActivity extends AppCompatActivity implements VolleyWrapperInterface {

    EditText name, ap, am, dir, tel, mail, pass;
    String name_str, ap_str, am_str, dir_str, tel_str, mail_str, pass_str;
    ButtonRectangle btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.et_name);
        ap = findViewById(R.id.et_ap);
        am = findViewById(R.id.et_am);
        dir = findViewById(R.id.et_direccion);
        tel = findViewById(R.id.et_tel);
        mail = findViewById(R.id.et_correo);
        pass = findViewById(R.id.et_pass);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    register();
                } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        });
    }

    public void register() throws JSONException {

        // Get the information from the EditText
        name_str = name.getText().toString();
        ap_str = ap.getText().toString();
        am_str = am.getText().toString();
        dir_str =dir.getText().toString();
        tel_str = tel.getText().toString();
        mail_str = mail.getText().toString();
        pass_str = pass.getText().toString();

        // Check nothing is missing

            JSONObject json = new JSONObject();

            json.put("name", name_str);
            json.put("paternalName", ap_str);
            json.put("maternalName", am_str);
            json.put("address", dir_str);
            json.put("phone", tel_str);
            json.put("email", mail_str);
            json.put("password", pass_str);

            Log.d("A", json.toString());
            VolleyWrapper vw = new VolleyWrapper(this, this);
            vw.execute(
                    Services.CLIENT,
                    Request.Method.POST,
                    json.toString(),
                    "",
                    VolleyWrapper.ResourceType.RRawData
            );

    }

    public void OnResponse(Object data) {
        Log.d("SEARCH", data.toString());
        Toast.makeText(this, "REGISTO CORRECTO, regrese e inicie sesión para continuar", Toast.LENGTH_LONG).show();


    }

    public void OnError(Exception e) {

    }



}
