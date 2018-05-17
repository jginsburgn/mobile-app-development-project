package mx.itesm.csf.proyectofinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONObject;

import mx.itesm.csf.proyectofinal.Controller.Services;
import mx.itesm.csf.proyectofinal.Fragments.Historial;
import mx.itesm.csf.proyectofinal.Fragments.Search;
import mx.itesm.csf.proyectofinal.Fragments.Update_client_info_Fragment;

public class MainActivity extends AppCompatActivity {

    Fragment selectedFragment = null;
    ButtonRectangle logout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                    /* Gerente quiere visualizar el Reporte de Ventas */
                case R.id.navigation_home:
                    selectedFragment = Update_client_info_Fragment.newInstance();
                    break;
                /* Gerente quiere visualizar el Reporte de Vendedores */
                case R.id.navigation_dashboard:
                    selectedFragment = Search.newInstance();
                    break;
                /* Gerente quiere agregar un nuevo Vendedor */
                case R.id.navigation_notifications:
                    selectedFragment = Historial.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout_user();
            }
        });

       /* Obtener el Bottom navigation View y agregar listener */
        BottomNavigationView navigation_owner =  findViewById(R.id.navigation);
        navigation_owner.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       /* Mostrar el primer fragment al ingresar a la actividad */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, Update_client_info_Fragment.newInstance());
        transaction.commit();
    }

    public void logout_user(){
        SessionWrapper sw = new SessionWrapper(this);
        sw.logout();
        finish();
    }

}
