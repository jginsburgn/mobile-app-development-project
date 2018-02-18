package mx.itesm.csf.actionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Principal extends AppCompatActivity {

    Toolbar toolbar;
    public boolean shouldGatherUserContactInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
            finish();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private String getFourthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView4)).getText().toString();
        RadioGroup group = (RadioGroup) findViewById(R.id.four);
        RadioButton a;
        switch (group.getCheckedRadioButtonId()) {
            case R.id.fourA:
                a = (RadioButton) findViewById(R.id.fourA);
                retVal += "\n" + a.getText();
                break;
            case R.id.fourB:
                a = (RadioButton) findViewById(R.id.fourB);
                retVal += "\n" + a.getText();
                break;
            case R.id.fourC:
                a = (RadioButton) findViewById(R.id.fourC);
                retVal += "\n" + a.getText();
                break;
            case R.id.fourD:
                a = (RadioButton) findViewById(R.id.fourD);
                retVal += "\n" + a.getText();
                shouldGatherUserContactInfo = true;
                break;
        }
        return retVal;
    }

    private String getFifthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView5)).getText().toString();
        RadioGroup group = (RadioGroup) findViewById(R.id.five);
        RadioButton a;
        switch (group.getCheckedRadioButtonId()) {
            case R.id.fiveA:
                a = (RadioButton) findViewById(R.id.fiveA);
                retVal += "\n" + a.getText();
                break;
            case R.id.fiveB:
                a = (RadioButton) findViewById(R.id.fiveB);
                retVal += "\n" + a.getText();
                break;
            case R.id.fiveC:
                a = (RadioButton) findViewById(R.id.fiveC);
                retVal += "\n" + a.getText();
                break;
            case R.id.fiveD:
                a = (RadioButton) findViewById(R.id.fiveD);
                retVal += "\n" + a.getText();
                shouldGatherUserContactInfo = true;
                break;
        }
        return retVal;
    }

    private String getSixthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView6)).getText().toString();
        EditText et = (EditText) findViewById(R.id.six);
        retVal += "\n" + et.getText().toString();
        return retVal;
    }

    private String getSeventhAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView7)).getText().toString();
        CheckBox a = (CheckBox) findViewById(R.id.sevenA);
        CheckBox b = (CheckBox) findViewById(R.id.sevenB);
        CheckBox c = (CheckBox) findViewById(R.id.sevenC);
        CheckBox d = (CheckBox) findViewById(R.id.sevenD);
        CheckBox e = (CheckBox) findViewById(R.id.sevenE);
        if (a.isChecked()) retVal += "\n" + a.getText().toString();
        if (b.isChecked()) retVal += "\n" + b.getText().toString();
        if (c.isChecked()) retVal += "\n" + c.getText().toString();
        if (d.isChecked()) retVal += "\n" + d.getText().toString();
        if (e.isChecked()) {
            retVal += "\n" + e.getText().toString();
            shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getEighthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView8)).getText().toString();
        ToggleButton tb = (ToggleButton) findViewById(R.id.eight);
        if (tb.isChecked()) {
            retVal += "\n" + tb.getTextOn().toString();
        }
        else {
            retVal += "\n" + tb.getTextOff().toString();
            shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getNinthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView9)).getText().toString();
        CheckBox a = (CheckBox) findViewById(R.id.nineA);
        CheckBox b = (CheckBox) findViewById(R.id.nineB);
        CheckBox c = (CheckBox) findViewById(R.id.nineC);
        CheckBox d = (CheckBox) findViewById(R.id.nineD);
        CheckBox e = (CheckBox) findViewById(R.id.nineE);
        if (a.isChecked()) retVal += "\n" + a.getText().toString();
        if (b.isChecked()) retVal += "\n" + b.getText().toString();
        if (c.isChecked()) retVal += "\n" + c.getText().toString();
        if (d.isChecked()) retVal += "\n" + d.getText().toString();
        if (e.isChecked()) {
            retVal += "\n" + e.getText().toString();
            shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getTenthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView10)).getText().toString();
        CheckBox a = (CheckBox) findViewById(R.id.tenA);
        CheckBox b = (CheckBox) findViewById(R.id.tenB);
        CheckBox c = (CheckBox) findViewById(R.id.tenC);
        CheckBox d = (CheckBox) findViewById(R.id.tenD);
        CheckBox e = (CheckBox) findViewById(R.id.tenE);
        if (a.isChecked()) retVal += "\n" + a.getText().toString();
        if (b.isChecked()) retVal += "\n" + b.getText().toString();
        if (c.isChecked()) retVal += "\n" + c.getText().toString();
        if (d.isChecked()) retVal += "\n" + d.getText().toString();
        if (e.isChecked()) {
            retVal += "\n" + e.getText().toString();
            shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getEleventhAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView11)).getText().toString();
        ToggleButton tb = (ToggleButton) findViewById(R.id.eleven);
        if (tb.isChecked()) {
            retVal += "\n" + tb.getTextOn().toString();
        }
        else {
            retVal += "\n" + tb.getTextOff().toString();
            shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getTwelfthAnswer() {
        String retVal = ((TextView) findViewById(R.id.textView12)).getText().toString();
        EditText et = (EditText) findViewById(R.id.twelve);
        retVal += "\n" + et.getText().toString();
        return retVal;
    }
    public void finish(View v) {
        Intent intent = new Intent(Principal.this, Thanks.class);
        intent.putExtra("4", getFourthAnswer());
        intent.putExtra("5", getFifthAnswer());
        intent.putExtra("6", getSixthAnswer());
        intent.putExtra("7", getSeventhAnswer());
        intent.putExtra("8", getEighthAnswer());
        intent.putExtra("9", getNinthAnswer());
        intent.putExtra("10", getTenthAnswer());
        intent.putExtra("11", getEleventhAnswer());
        intent.putExtra("12", getTwelfthAnswer());
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent;
        switch (requestCode) {
            case 0:
                if (shouldGatherUserContactInfo) {
                    intent = new Intent(Principal.this, Phone.class);
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
