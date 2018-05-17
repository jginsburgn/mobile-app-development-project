package mx.itesm.csf.proyectofinal.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.List;
;
import mx.itesm.csf.proyectofinal.Controller.Services;
import mx.itesm.csf.proyectofinal.Model.SearchModel;
import mx.itesm.csf.proyectofinal.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchContainer>{

    // List to add every JSON element
    private List<SearchModel> searchElements;
    // Context
    private Context context;

    /* Adapter to send Context and Element list */
    public SearchAdapter (Context context, List<SearchModel> elements)
    {
        this.searchElements= elements;
        this.context = context;
    }

    /* Creating the ViewHolder with an element (not personalized yet) */
    @NonNull
    @Override
    public SearchContainer onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        /* Get the Layout */
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View layout = layoutInflater.inflate(R.layout.search_list_view, parent,false);
        /* Create a DataContainer Instance with the layout*/
        return new SearchContainer(layout);
    }


    /* Personalizing the ViewHolder */
    @Override
    public void onBindViewHolder(@NonNull final SearchContainer holder, int position)
    {
        /* Vendedor Model instance based on position */
        SearchModel searchModel= searchElements.get(position);

        /* Get the name from the model and set the name in the holder*/
        holder._id.setText(searchModel.getType());
        //holder.product_name.setText(ventasModel.getProduct_name());
        //holder.client_name.setText(ventasModel.getClient_name());
        //holder.product_specs.setText(ventasModel.getProduct_specs());
        //holder.product_price.setText(ventasModel.getProduct_price());

        holder.searchModel = searchModel;

    }

    /* Get the size of the List */
    @Override
    public int getItemCount() {
        return searchElements.size();
    }

    /* Data Container class */
    class SearchContainer extends RecyclerView.ViewHolder
    {
        EditText searchbar;
        TextView _id;
        /*TextView venta_id;
        TextView product_name;
        TextView client_name;
        TextView product_specs;
        TextView product_price;*/

        ButtonFlat btn;

        SearchModel searchModel;

        // Define the View
        public  SearchContainer (View itemView)
        {
            super(itemView);

            // Find the view Components
            _id = itemView.findViewById(R.id._id);
            /*venta_id       =  itemView.findViewById(R.id.v_id);
            product_name   =  itemView.findViewById(R.id.v_sales);
            client_name    =  itemView.findViewById(R.id.v_name);
            product_specs  =  itemView.findViewById(R.id.v_store);
            product_price  =  itemView.findViewById(R.id.product_price);
            btn_entregado  =  itemView.findViewById(R.id.btn_entregado);*/

        }

    }
}