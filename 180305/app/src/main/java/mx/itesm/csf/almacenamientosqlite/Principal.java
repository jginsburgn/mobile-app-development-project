package mx.itesm.csf.almacenamientosqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import models.Models;

public class Principal extends AppCompatActivity {

    private AdaptadorDB adaptador;

    private EditText identifier;

    private EditText nombre;
    private EditText apellidoPaterno;
    private EditText apellidoMaterno;
    private EditText email;
    private EditText telefono;

    private ToggleButton shouldUpdate;

    private long lastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        Models.context = getApplicationContext();
        try {
            Models.GetModels();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        adaptador = new AdaptadorDB(this);

        identifier = (EditText) findViewById(R.id.identifier);

        nombre = (EditText) findViewById(R.id.nombre);
        apellidoPaterno = (EditText) findViewById(R.id.firstLastName);
        apellidoMaterno = (EditText) findViewById(R.id.secondLastName);
        email = (EditText) findViewById(R.id.email);
        telefono = (EditText) findViewById(R.id.phone);

        shouldUpdate = (ToggleButton) findViewById(R.id.shouldUpdate);

        /*
        //--- Angregamos clientes ---
        adaptador.open();
        long id = adaptador.insertaClientes("Raúl Morales Salcedo", "raulms@itesm.mx");
        id = adaptador.insertaClientes("Felipe Calderón Hinojosa", "calderon@presidencia.com");
        adaptador.close();
        */

        /*
        //-- Obtenemos todos los clientes ---
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst())
        {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }
        adaptador.close();
        */

        /*
        //--- Obtenemos un cliente ---
        adaptador.open();
        Cursor c = adaptador.obtieneUnCliente(2);
        if (c.moveToFirst())
            DespliegaCliente(c);
        else
            Toast.makeText(this, "No se ha encontrado al cliente", Toast.LENGTH_LONG).show();
        adaptador.close();
        */

        /*
        //--- Actualiza un cliente ---
        adaptador.open();
        if (adaptador.actualizaCliente(2, "Andrés Manuelovich", "elpeje@presidencia.com"))
            Toast.makeText(this, "Actualización exitosa!.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Falló la actualización del registro.", Toast.LENGTH_LONG).show();
        adaptador.close();
        */


        //--- Borra un cliente ---
        /*adaptador.open();
        if (adaptador.borraCliente(1))
            Toast.makeText(this, "Registro eliminado exitosamente.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Falló al eliminar registro.", Toast.LENGTH_LONG).show();
        adaptador.close();


        try {
            String destPath = "/data/data/" + getPackageName() +
                    "/basesdedatos";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();

                //---copy the db from the assets folder into
                // the databases folder---
                CopyDB(getBaseContext().getAssets().open("CRM"),
                        new FileOutputStream(destPath + "/CRM"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //--- Obtenemos todos los clientes ---
        adaptador.open();
        Cursor c = adaptador.obtenTodosLosClientes();
        if (c.moveToFirst())
        {
            do {
                DespliegaCliente(c);
            } while (c.moveToNext());
        }
        adaptador.close();*/
    }

    @Override
    public void onResume() {
        super.onResume();
        adaptador.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        adaptador.close();
    }

    /*public void CopyDB(InputStream inputStream,
                       OutputStream outputStream) throws IOException {
        //--- Copiamos 1K bytes poco a poco ---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }*/

    /*public void DespliegaCliente(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Nombre: " + c.getString(1) + "\n" +
                        "Email:  " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }*/

    public void Search(View v) {
        Long idToQuery;
        try {
            idToQuery = Long.parseLong(identifier.getText().toString());
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese un ID válido.", Toast.LENGTH_LONG).show();
            return;
        }
        Cursor result = adaptador.obtieneUnCliente(idToQuery);
        if (result.moveToNext()) {
            lastId = result.getInt(result.getColumnIndex(AdaptadorDB.ID));
            nombre.setText(result.getString(result.getColumnIndex(AdaptadorDB.NOMBRE)));
            apellidoPaterno.setText(result.getString(result.getColumnIndex(AdaptadorDB.APELLIDO_PATERNO)));
            apellidoMaterno.setText(result.getString(result.getColumnIndex(AdaptadorDB.APELLIDO_MATERNO)));
            email.setText(result.getString(result.getColumnIndex(AdaptadorDB.EMAIL)));
            telefono.setText(result.getString(result.getColumnIndex(AdaptadorDB.TELEFONO)));
        }
        else {
            Toast.makeText(this, "No hay resultados para ese ID.", Toast.LENGTH_SHORT).show();
            lastId = 0;
            nombre.setText("");
            apellidoPaterno.setText("");
            apellidoMaterno.setText("");
            email.setText("");
            telefono.setText("");
        }
    }

    public void Save(View v) {
        if (shouldUpdate.isChecked()) {
            int rowsAltered = adaptador.actualizaCliente(lastId, nombre.getText().toString(), apellidoPaterno.getText().toString(), apellidoMaterno.getText().toString(), email.getText().toString(), telefono.getText().toString());
            if (rowsAltered == 0) {
                Toast.makeText(this, "No se actualizó ningun registro. Intente crearlo.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            lastId = adaptador.insertaCliente(nombre.getText().toString(), apellidoPaterno.getText().toString(), apellidoMaterno.getText().toString(), email.getText().toString(), telefono.getText().toString());
            Toast.makeText(this, "Registro creado con ID: " + lastId, Toast.LENGTH_LONG).show();
        }
    }
}