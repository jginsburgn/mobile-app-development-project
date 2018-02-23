package me.itsof.a180212;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jonathan on 2/22/18.
 */

public class Person {
    private static final String FILE_NAME = "/data.json";
    private static String FULL_PATH = "";
    public static Context context;

    public String highestDegree = "";
    public List<String> favoriteBrands = new LinkedList<>();
    public String gender = "";
    public boolean hasOwnedCar = false;
    public String fullName = "";
    public String phoneNumber = "";

    public Person() {}

    public static Person SearchPerson(String phoneOrNameSubstring, int offset) throws Exception {
        LinkedList<Person> persons = Load();
        int counter = 0;
        Log.d("SearchPerson, searching", phoneOrNameSubstring + " and offset " + offset);
        for (Person p : persons) {
            if (p.fullName.toLowerCase().contains(phoneOrNameSubstring) || p.phoneNumber.toLowerCase().contains(phoneOrNameSubstring)) {
                if (counter == offset) {
                    return p;
                }
                counter++;
            }
        }
        throw new Exception("No person found");
    }

    public Person(String highestDegree, List<String> favoriteBrands, String gender, boolean hasOwnedCar, String fullName, String phoneNumber) {
        this.highestDegree = highestDegree;
        this.favoriteBrands = favoriteBrands;
        this.gender = gender;
        this.hasOwnedCar = hasOwnedCar;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    private JSONObject Encode() throws Exception {
        JSONObject encoded = new JSONObject();
        encoded.put("highestDegree", highestDegree);
        JSONArray fb = new JSONArray();
        for (String b : favoriteBrands) {
            fb.put(b);
        }
        encoded.put("favoriteBrands", fb);
        encoded.put("gender", gender);
        encoded.put("hasOwnedCar", hasOwnedCar);
        encoded.put("fullName", fullName);
        encoded.put("phoneNumber", phoneNumber);
        return encoded;
    }

    private JSONArray GetNewWritable() throws Exception {
        LinkedList<Person> persons = Load();
        JSONArray retVal = new JSONArray();
        for (Person p : persons) {
            retVal.put(p.Encode());
        }
        retVal.put(Encode());
        return retVal;
    }

    public void Save() {
        try {
            JSONArray newWritable = GetNewWritable();
            File file = new File(GetPath());
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newWritable.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            Log.d("Save", file.getAbsolutePath());
        }
        catch (Exception e) {
            Log.d("Save", e.getMessage());
        }
    }

    static private LinkedList<Person> Parse(String json) {
        try {
            LinkedList<Person> retVal = new LinkedList<>();
            JSONArray raw = new JSONArray(json);
            Log.d("Parse, raw object", raw.toString());
            for (int i = 0; i < raw.length(); i++) {
                JSONObject rawPerson = (JSONObject) raw.get(i);
                String hd = rawPerson.getString("highestDegree");
                String gen = rawPerson.getString("gender");
                LinkedList<String> fb = new LinkedList<>();
                JSONArray rawFB = rawPerson.getJSONArray("favoriteBrands");
                for (int j = 0; j < rawFB.length(); j++) {
                    fb.add(rawFB.getString(j));
                }
                boolean hoc = rawPerson.getBoolean("hasOwnedCar");
                String fn = rawPerson.getString("fullName");
                String pn = rawPerson.getString("phoneNumber");
                Person newPerson = new Person(hd, fb, gen, hoc, fn, pn);
                retVal.add(newPerson);
            }
            return retVal;
        }
        catch (JSONException e) {
            Log.d("Parse, error", e.getMessage());
        }
        return new LinkedList<>();
    }

    static public LinkedList<Person> Load() {
        File file = new File(GetPath());
        if (!file.exists()) {
            Log.d("Load", "file does not exist.");
            return new LinkedList<>();
        }
        else {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String contents = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    Log.d("line", line);
                    contents += line + "\n";
                }
                bufferedReader.close();
                Log.d("Load, file contents:", contents);
                return Parse(contents);
            }
            catch (Exception e) {
                Log.d("Load, error", e.getMessage());
                return new LinkedList<>();
            }
        }
    }

    static private String GetPath() {
        if (FULL_PATH.length() == 0) {
            if (ExternalStorageAvailable()) {
                FULL_PATH = Environment.getExternalStorageDirectory() + FILE_NAME;
            }
            else {
                FULL_PATH = context.getFilesDir().getAbsolutePath() + FILE_NAME;
            }
        }
        Log.d("GetPath", FULL_PATH);
        return FULL_PATH;
    }

    static private boolean ExternalStorageAvailable() {
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
            return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
            return false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
            return false;
        }
    }
}
