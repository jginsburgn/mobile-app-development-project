package me.itsof.a180115;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;

public class Principal extends AppCompatActivity {

    private final String MSG = "ALC";

    private void toast(String message) {
        // Code taken from: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void alert(String title, String message) {
        // Code taken from: https://www.mkyong.com/android/android-alert-dialog-example/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        Log.d(MSG, "onCreate");
        toast("onCreate");
        alert("Message", "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onStart();
        Log.d(MSG, "onRestart");
        toast("onStart");
        alert("Message", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MSG, "onStart");
        toast("onStart");
        alert("Message", "onStart");
    }

    @Override
    protected void onResume() {
        super.onPause();
        Log.d(MSG, "onResume");
        toast("onResume");
        alert("Message", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MSG, "onPause");
        toast("onPause");
        alert("Message", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MSG, "onStop");
        toast("onStop");
        alert("Message", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MSG, "onDestroy");
        toast("onDestroy");
        alert("Message", "onDestroy");
    }
}
