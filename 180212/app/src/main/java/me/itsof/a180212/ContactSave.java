package me.itsof.a180212;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ContactSave extends AppCompatActivity {

    private String lastSearchTerm = "";
    private int timesSearched = 0;
    EditText search;
    RadioGroup highestDegree;
    CheckBox mitsubishi;
    CheckBox nissan;
    CheckBox hyundai;
    RadioGroup gender;
    ToggleButton hasOwnedCar;
    EditText fullName;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contact_save);
        Person.context = getBaseContext();

        search = (EditText) findViewById(R.id.search);

        highestDegree = (RadioGroup) findViewById(R.id.degree);
        mitsubishi = (CheckBox) findViewById(R.id.mitsubishi);
        nissan = (CheckBox) findViewById(R.id.nissan);
        hyundai = (CheckBox) findViewById(R.id.hyundai);
        gender = (RadioGroup) findViewById(R.id.gender);
        hasOwnedCar = (ToggleButton) findViewById(R.id.ownedCar);
        fullName = (EditText) findViewById(R.id.name);
        phoneNumber = (EditText) findViewById(R.id.phone);
    }

    public void Save(View v) {
        Person p = GetPerson();
        p.Save();
    }

    public void Load(View v) {
        try {
            String newSearchTerm = search.getText().toString();
            if (!lastSearchTerm.equals(newSearchTerm)) {
                lastSearchTerm = search.getText().toString();
                timesSearched = 0;
            }
            Person p = Person.SearchPerson(lastSearchTerm, timesSearched);
            timesSearched++;
            Populate(p);
        }
        catch (Exception e) {
            if (e.getMessage() == "No person found") {
                String toastMessage = "";
                if (timesSearched > 0) {
                    timesSearched = 0;
                    toastMessage = "No more persons match that info.";
                }
                else {
                    toastMessage = "No person found with that info.";
                }
                Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
            else Log.d("Load, error", e.getMessage());
        }
    }

    private void Populate(Person person) {
        highestDegree.clearCheck();
        switch (person.highestDegree) {
            case "Bachelor":
                highestDegree.check(R.id.bachelor);
                break;
            case "Master":
                highestDegree.check(R.id.master);
                break;
            case "Doctorate":
                highestDegree.check(R.id.doctorate);
                break;
            default:
                highestDegree.clearCheck();
        }
        mitsubishi.setChecked(false);
        nissan.setChecked(false);
        hyundai.setChecked(false);
        for (String b : person.favoriteBrands) {
            switch (b) {
                case "Mitsubishi":
                    mitsubishi.setChecked(true);
                    break;
                case "Nissan":
                    nissan.setChecked(true);
                    break;
                case "Hyundai":
                    hyundai.setChecked(true);
                    break;
            }
        }
        gender.clearCheck();
        switch (person.gender) {
            case "Male":
                gender.check(R.id.male);
                break;
            case "Female":
                gender.check(R.id.female);
                break;
        }
        hasOwnedCar.setChecked(person.hasOwnedCar);
        fullName.setText(person.fullName);
        phoneNumber.setText(person.phoneNumber);
    }

    private Person GetPerson() {
        Person person = new Person();
        switch (highestDegree.getCheckedRadioButtonId()) {
            case R.id.bachelor:
                person.highestDegree = "Bachelor";
                break;
            case R.id.master:
                person.highestDegree = "Master";
                break;
            case R.id.doctorate:
                person.highestDegree = "Doctorate";
                break;
        }
        if (mitsubishi.isChecked()) person.favoriteBrands.add("Mitsubishi");
        if (nissan.isChecked()) person.favoriteBrands.add("Nissan");
        if (hyundai.isChecked()) person.favoriteBrands.add("Hyundai");
        switch (gender.getCheckedRadioButtonId()) {
            case R.id.male:
                person.gender = "Male";
                break;
            case R.id.female:
                person.gender = "Female";
                break;
        }
        person.hasOwnedCar = hasOwnedCar.isChecked();
        person.fullName = fullName.getText().toString();
        person.phoneNumber = phoneNumber.getText().toString();
        return person;
    }
}
