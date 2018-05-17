package mx.itesm.csf.proyectofinal.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.itesm.csf.proyectofinal.Controller.Services;
import mx.itesm.csf.proyectofinal.LoginActivity;
import mx.itesm.csf.proyectofinal.MainActivity;
import mx.itesm.csf.proyectofinal.R;
import mx.itesm.csf.proyectofinal.RegisterActivity;
import mx.itesm.csf.proyectofinal.SessionWrapper;
import mx.itesm.csf.proyectofinal.VolleyWrapper;
import mx.itesm.csf.proyectofinal.VolleyWrapperInterface;

public class Update_client_info_Fragment extends Fragment implements VolleyWrapperInterface {


    EditText password, address, phone;
    ButtonRectangle btn_guardar;

    String pass, addr, ph;
    private int request_seq = 0;
    private String userId = "";

    /* Instance to be shown on the Botom Navigation Activity*/
    public static Update_client_info_Fragment newInstance() {
        return new Update_client_info_Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_update_client_info__fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            get_data();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        password = getView().findViewById(R.id.et_pass);
        address = getView().findViewById(R.id.et_address);
        phone = getView().findViewById(R.id.et_tel);

        btn_guardar = getView().findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    update_data();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void update_data() throws JSONException {

        // Get the information from the EditText
        pass = password.getText().toString();
        addr = address.getText().toString();
        ph   = phone.getText().toString();


        if(!pass.matches("") || !addr.matches("") || !ph.matches("") )
        {
            JSONObject json = new JSONObject();
            String query = "/"+ this.userId;

            json.put("address", addr);
            json.put("phone", ph);
            json.put("password", pass);

            VolleyWrapper vw = new VolleyWrapper(this, getContext());
            vw.execute(
                    Services.CLIENT + query,
                    Request.Method.PATCH,
                    json.toString(),
                    "",
                    VolleyWrapper.ResourceType.RRawData
            );

            Log.d("JSON", json.toString());
        }
        else{
            Toast.makeText(getContext(), "INGRESA DATOS VALIDOS", Toast.LENGTH_LONG).show();
        }
    }

     public void get_data() throws JSONException {

        SessionWrapper sw = new SessionWrapper(getContext());
        String email = sw.getUserName();
        String query = "?email="+email;

        VolleyWrapper vw = new VolleyWrapper(this, getContext());
        vw.execute(
                Services.CLIENT + query,
                Request.Method.GET,
                "",
                "",
                VolleyWrapper.ResourceType.RJSONArray
        );
    }


    private void obtainId(JSONArray ja) {
         request_seq++;
         Log.d("GETID", ja.toString());
         try {
             this.userId = ja.getJSONObject(0).getString("_id");

            password.setText(ja.getJSONObject(0).getString("password"));

         }
         catch(Exception e ){e.printStackTrace();}
    }

    public void OnResponse(Object data) {
        if (request_seq == 0) {
            obtainId((JSONArray) data);
        }
        else if(request_seq == 1){
            Toast.makeText(getContext(), "SAVED", Toast.LENGTH_LONG).show();
        }
    }

    public void OnError(Exception e) {

    }


}
