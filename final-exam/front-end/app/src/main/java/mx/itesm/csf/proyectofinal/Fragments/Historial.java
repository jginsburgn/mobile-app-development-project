package mx.itesm.csf.proyectofinal.Fragments;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.itesm.csf.proyectofinal.Adapters.HistorialAdapter;
import mx.itesm.csf.proyectofinal.Controller.Services;
import mx.itesm.csf.proyectofinal.Model.HistorialModel;
import mx.itesm.csf.proyectofinal.R;
import mx.itesm.csf.proyectofinal.SessionWrapper;
import mx.itesm.csf.proyectofinal.VolleyWrapper;
import mx.itesm.csf.proyectofinal.VolleyWrapperInterface;

public class Historial extends Fragment implements VolleyWrapperInterface {

    RecyclerView recView;
    RecyclerView.Adapter recAdapter;
    RecyclerView.LayoutManager recLayoutManager;
    List<HistorialModel> historialElements;

    ProgressDialog progressBar;
    public static Historial newInstance() {
        return new Historial();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_historial, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the elements from the CardView and the list
        recView =  getView().findViewById(R.id.recyclerview);
        progressBar = new ProgressDialog(getContext());
        historialElements = new ArrayList<>();

        try {
            get_data();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /* CardView components */
        recLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recView.setLayoutManager(recLayoutManager);
        recAdapter = new HistorialAdapter(getContext(), historialElements);
        recView.setAdapter(recAdapter);
    }

    public void get_data() throws JSONException {

        SessionWrapper sw = new SessionWrapper(getContext());
        String email = sw.getUserName();

        VolleyWrapper vw = new VolleyWrapper(this, getContext());
        vw.execute(
                Services.PROMOTION,
                Request.Method.GET,
                "",
                "",
                VolleyWrapper.ResourceType.RJSONArray
        );
    }

    public void OnResponse(Object data) {
        Log.d("HIST", data.toString());

        JSONArray ja = (JSONArray) data;

        try{
            for(int i=0; i<ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                HistorialModel hs = new HistorialModel();
                hs.setDescription(jo.getString("name"));
                hs.setDestination(jo.getString("product"));
                hs.setPrice(jo.getString("type"));
                historialElements.add(hs);
            }
        } catch (Exception ex)
        {

        }
        recAdapter.notifyDataSetChanged();
    }

    public void OnError(Exception e) {

    }
}
