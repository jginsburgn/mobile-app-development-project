package me.itsof.a180218;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import java.security.Principal;

public class Main extends AppCompatActivity {

    private ScrollView container;
    public boolean shouldGatherUserContactInfo = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            android.support.v4.app.Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_autocomplete:
                    fragment = new Autocomplete();
                    break;
                case R.id.navigation_shared_preferences:
                    fragment = new SharedPreferences();
                    break;
                case R.id.navigation_autocomplete_and_shared_preferences:
                    fragment = new AutocompleteAndSharedPreferences();
                    break;
                case R.id.navigation_survey:
                    fragment = new Survey();
                    break;
                default:
                    return false;
            }
            if (container.getChildCount() == 1) container.removeViewAt(0);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, fragment);
            fragmentTransaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
            finish();
        }

        container = (ScrollView) findViewById(R.id.container);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_autocomplete);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent;
        Log.d("Jonathan", Integer.toString(requestCode));
        switch (requestCode) {
            case 0:
                if (shouldGatherUserContactInfo) {
                    intent = new Intent(Main.this, Phone.class);
                    startActivity(intent);
                }
                else {
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
