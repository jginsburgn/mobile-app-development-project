package mx.itesm.csf.proyectofinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mx.itesm.csf.proyectofinal.Controller.Services;


public class LoginActivity extends AppCompatActivity implements VolleyWrapperInterface {

     public static final String USUARIO = "usuario";
     public static final String PASSWORD = "password";

    private final String TAG = "LOGIN";
    private EditText editTextUsuario;
    private EditText editTextPassword;

    private String usuario;
    private String password;

    ButtonRectangle btn_login;
    ButtonFlat btn_register;
    

    private Usuario userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Check if user is logged
        check_login();

        editTextUsuario  = (EditText) findViewById(R.id.lg_usuario);
        editTextPassword = (EditText) findViewById(R.id.lg_pass);

        // Login
        btn_login = findViewById(R.id.lg_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    usuarioLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // Register
        btn_register = findViewById(R.id.btn_registrar);
        btn_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                usuarioRegister();
            }
        });

    }

    //Register Data
    private void usuarioRegister()
    {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    // Handle Login Data
    private void usuarioLogin() throws JSONException {
        /* Think */
        final ProgressDialog progressBar = new ProgressDialog(LoginActivity.this);
        progressBar.setMessage("Iniciando sesion...");
        progressBar.show();

        /* Get User and Password*/
        usuario = editTextUsuario.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();


        /* Put the data to a JSON object */
        if(!usuario.matches("")|| !password.matches("")) {

            String route = "client";
            String query = "?email=" + usuario + "&password" + password;

            VolleyWrapper vw = new VolleyWrapper(this, this);
            vw.execute(
                    Services.CLIENT + query,
                    Request.Method.GET,
                    "",
                    "",
                    VolleyWrapper.ResourceType.RJSONArray
            );

        }
        else
        {
            Toast.makeText(LoginActivity.this, "INGRESA DATOS PARA INICIAR SESIÓN", Toast.LENGTH_LONG).show();
        }

        progressBar.hide();
    }

    private void check_login()
    {
        SessionWrapper sw = new SessionWrapper(this);

        if(sw.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    public void OnResponse(Object data) {
        JSONArray ja = (JSONArray) data;
        Log.d("LOGIN", ja.toString());
        if (ja.length() != 0) {
            SessionWrapper sw = new SessionWrapper(this);
            JSONObject json = new JSONObject();
            try {
                json.put("username", ja.getJSONObject(0).getString("email"));
                json.put("password", ja.getJSONObject(0).getString("password"));
            } catch (Exception e) {}
            sw.login(json);

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        else{
            Toast.makeText(this, "Error en el log in", Toast.LENGTH_LONG).show();
        }
    }

    public void OnError(Exception e) {

    }

}
