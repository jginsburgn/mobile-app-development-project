package mx.itesm.csf.almacenamientosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//
public class AdaptadorDB {

    static final String ID = "_id";
    static final String NOMBRE = "nombre";
    static final String APELLIDO_PATERNO = "Appaterno";
    static final String APELLIDO_MATERNO = "Apmaterno";
    static final String EMAIL = "email";
    static final String TELEFONO = "telefono";

    static final String ETIQUETA = "AdaptadorDB";

    static final String NOMBRE_BD = "CRM";
    static final String BD_TABLA = "Clientes";
    static final int VERSION_DB = 1;

    static final String CREAR_BD =
            "create table " + BD_TABLA + " (" +
                    ID + " integer primary key autoincrement, " +
                    NOMBRE + " text not null, " +
                    APELLIDO_PATERNO + " text, " +
                    APELLIDO_MATERNO + " text, " +
                    EMAIL + " text not null, " +
                    TELEFONO + " text);";

    static final String BORRAR_DB =
            "drop table if exists " + BD_TABLA;

    private final Context contexto;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public AdaptadorDB(Context ctx) {
        this.contexto = ctx;
        DBHelper = new DatabaseHelper(contexto);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, NOMBRE_BD, null, VERSION_DB);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREAR_BD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(ETIQUETA, "Actualizando la version de la Base de Datos de " + oldVersion + " a "
                    + newVersion + ", este proceso eliminará los regustros de la versión anterior");
            db.execSQL("DROP TABLE IF EXISTS " + BD_TABLA);
            onCreate(db);
        }
    }

    //--- Abrimos la BD ---
    public AdaptadorDB open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- Cerramos la BD ---
    public void close() {
        DBHelper.close();
    }

    //--- Insertamos registros a la tabla clientes ---
    public long insertaCliente(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono) {
        ContentValues values = new ContentValues();
        values.put(NOMBRE, nombre);
        values.put(APELLIDO_PATERNO, apellidoPaterno);
        values.put(APELLIDO_MATERNO, apellidoMaterno);
        values.put(EMAIL, email);
        values.put(TELEFONO, telefono);
        return db.insert(BD_TABLA, null, values);
    }

    //--- Borra un cliente en particular ---
    public boolean borraCliente(long idFila) {
        return db.delete(BD_TABLA, ID + "= ?", new String[] {Long.toString(idFila)}) > 0;
    }

    //--- Recuperamos todos los registros de la tabla ---
    public Cursor obtenTodosLosClientes() {
        return db.query(BD_TABLA, new String[] {ID, NOMBRE,
                EMAIL}, null, null, null, null, null);
    }

    //--- Recuperamos un registro de cliente en particular ---
    public Cursor obtieneUnCliente(long idFila) throws SQLException
    {
        Cursor mCursor =
                db.query(BD_TABLA,
                        null,
                        ID + " = ?",
                        new String[] {Long.toString(idFila)},
                        null,
                        null,
                        null
                );
        Log.d("Resultados", Integer.toString(mCursor.getCount()));
        return mCursor;
    }

    //--- Actualizamos un registro  ---
    public int actualizaCliente(long idFila, String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono)
    {
        ContentValues values = new ContentValues();
        values.put(NOMBRE, nombre);
        values.put(APELLIDO_PATERNO, apellidoPaterno);
        values.put(APELLIDO_MATERNO, apellidoMaterno);
        values.put(EMAIL, email);
        values.put(TELEFONO, telefono);
        return db.update(BD_TABLA,
                values,
                ID + "= ?",
                new String[]{Long.toString(idFila)});
    }

}
