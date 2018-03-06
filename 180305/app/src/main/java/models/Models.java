package models;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import mx.itesm.csf.almacenamientosqlite.R;

/**
 * Created by jonathan on 3/5/18.
 */

public class Models {
    // TODO: implement instance methods to CRUD entities.

    public static Context context = null;
    private static Models singleton = null;

    private SQLiteDatabase db;
    private DatabaseHelper dbh;

    protected Models() {
        Resources res = context.getResources();
        String dbName = res.getString(R.string.database_name);
        int dbVersion = res.getInteger(R.integer.database_version);
        dbh = new DatabaseHelper(context, dbName, dbVersion);
        db = dbh.getWritableDatabase();
    }

    public static Models GetModels() throws Exception {
        if (Models.context == null) throw new Exception("First seed the models class with a context.");
        if (Models.singleton == null) {
            Models.singleton = new Models();
        }
        return Models.singleton;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String dbName, int dbVersion) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Resources res = Models.context.getResources();
                InputStream is = res.openRawResource(R.raw.create);
                String createStatements = IOUtils.toString(is);
                String[] statements = createStatements.split(";");
                for (String statement : statements) {
                    Log.d("Models", statement);
                    db.execSQL(statement);
                }
                IOUtils.closeQuietly(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
