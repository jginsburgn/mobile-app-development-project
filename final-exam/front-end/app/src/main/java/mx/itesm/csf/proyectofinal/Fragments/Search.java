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
import android.widget.EditText;

import com.android.volley.Request;
import com.gc.materialdesign.views.ButtonFlat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.itesm.csf.proyectofinal.Adapters.SearchAdapter;
import mx.itesm.csf.proyectofinal.Controller.Services;
import mx.itesm.csf.proyectofinal.Model.HistorialModel;
import mx.itesm.csf.proyectofinal.Model.SearchModel;
import mx.itesm.csf.proyectofinal.R;
import mx.itesm.csf.proyectofinal.SessionWrapper;
import mx.itesm.csf.proyectofinal.VolleyWrapper;
import mx.itesm.csf.proyectofinal.VolleyWrapperInterface;

public class Search extends Fragment implements VolleyWrapperInterface {

    RecyclerView recView;
    RecyclerView.Adapter recAdapter;
    RecyclerView.LayoutManager recLayoutManager;
    List<SearchModel> search_list_Elements;

    ButtonFlat btn;
    EditText searchbar;

    ProgressDialog progressBar;
    public static Search newInstance() {
        return new Search();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the elements from the CardView and the list
        recView =  getView().findViewById(R.id.recyclerview);
        progressBar = new ProgressDialog(getContext());
        search_list_Elements= new ArrayList<>();

        try {
            get_data();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /* CardView components */
        recLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); /* Change to Grid Layout*/
        recView.setLayoutManager(recLayoutManager);
        recAdapter = new SearchAdapter(getContext(), search_list_Elements);
        recView.setAdapter(recAdapter);
    }

    public void get_data() throws JSONException {

        SessionWrapper sw = new SessionWrapper(getContext());

        VolleyWrapper vw = new VolleyWrapper(this, getContext());
        vw.execute(
                Services.STORE,
                Request.Method.GET,
                "",
                "",
                VolleyWrapper.ResourceType.RJSONArray
        );
    }

    public void OnResponse(Object data) {
        Log.d("SEARCH", data.toString());

        JSONArray ja = (JSONArray) data;

        try{
            for(int i=0; i<ja.length(); i++)
            {
                JSONObject jo = ja.getJSONObject(i);

                SearchModel sch = new SearchModel();

                sch.setType(jo.getString("name"));
                sch.setDescription(jo.getString("location"));
                sch.setDestination(jo.getString("description"));
                sch.setPrice(jo.getString("owner"));
                search_list_Elements.add(sch);
            }
        }
        catch (Exception ex)
        {

        }

        recAdapter.notifyDataSetChanged();

    }

    public void OnError(Exception e) {

    }
}
