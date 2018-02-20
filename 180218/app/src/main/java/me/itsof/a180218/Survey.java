package me.itsof.a180218;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.security.Principal;

public class Survey extends Fragment {

    public Survey() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((Main) getActivity()).shouldGatherUserContactInfo = false;
        ((Button) getView().findViewById(R.id.finish)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getFourthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView4)).getText().toString();
        RadioGroup group = (RadioGroup) getView().findViewById(R.id.four);
        RadioButton a;
        switch (group.getCheckedRadioButtonId()) {
            case R.id.fourA:
                a = (RadioButton) getView().findViewById(R.id.fourA);
                retVal += "\n" + a.getText();
                break;
            case R.id.fourB:
                a = (RadioButton) getView().findViewById(R.id.fourB);
                retVal += "\n" + a.getText();
                break;
            case R.id.fourC:
                a = (RadioButton) getView().findViewById(R.id.fourC);
                retVal += "\n" + a.getText();
                break;
            case R.id.fourD:
                a = (RadioButton) getView().findViewById(R.id.fourD);
                retVal += "\n" + a.getText();
                ((Main) getActivity()).shouldGatherUserContactInfo = true;
                break;
        }
        return retVal;
    }

    private String getFifthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView5)).getText().toString();
        RadioGroup group = (RadioGroup) getView().findViewById(R.id.five);
        RadioButton a;
        switch (group.getCheckedRadioButtonId()) {
            case R.id.fiveA:
                a = (RadioButton) getView().findViewById(R.id.fiveA);
                retVal += "\n" + a.getText();
                break;
            case R.id.fiveB:
                a = (RadioButton) getView().findViewById(R.id.fiveB);
                retVal += "\n" + a.getText();
                break;
            case R.id.fiveC:
                a = (RadioButton) getView().findViewById(R.id.fiveC);
                retVal += "\n" + a.getText();
                break;
            case R.id.fiveD:
                a = (RadioButton) getView().findViewById(R.id.fiveD);
                retVal += "\n" + a.getText();
                ((Main) getActivity()).shouldGatherUserContactInfo = true;
                break;
        }
        return retVal;
    }

    private String getSixthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView6)).getText().toString();
        EditText et = (EditText) getView().findViewById(R.id.six);
        retVal += "\n" + et.getText().toString();
        return retVal;
    }

    private String getSeventhAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView7)).getText().toString();
        CheckBox a = (CheckBox) getView().findViewById(R.id.sevenA);
        CheckBox b = (CheckBox) getView().findViewById(R.id.sevenB);
        CheckBox c = (CheckBox) getView().findViewById(R.id.sevenC);
        CheckBox d = (CheckBox) getView().findViewById(R.id.sevenD);
        CheckBox e = (CheckBox) getView().findViewById(R.id.sevenE);
        if (a.isChecked()) retVal += "\n" + a.getText().toString();
        if (b.isChecked()) retVal += "\n" + b.getText().toString();
        if (c.isChecked()) retVal += "\n" + c.getText().toString();
        if (d.isChecked()) retVal += "\n" + d.getText().toString();
        if (e.isChecked()) {
            retVal += "\n" + e.getText().toString();
            ((Main) getActivity()).shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getEighthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView8)).getText().toString();
        ToggleButton tb = (ToggleButton) getView().findViewById(R.id.eight);
        if (tb.isChecked()) {
            retVal += "\n" + tb.getTextOn().toString();
        }
        else {
            retVal += "\n" + tb.getTextOff().toString();
            ((Main) getActivity()).shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getNinthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView9)).getText().toString();
        CheckBox a = (CheckBox) getView().findViewById(R.id.nineA);
        CheckBox b = (CheckBox) getView().findViewById(R.id.nineB);
        CheckBox c = (CheckBox) getView().findViewById(R.id.nineC);
        CheckBox d = (CheckBox) getView().findViewById(R.id.nineD);
        CheckBox e = (CheckBox) getView().findViewById(R.id.nineE);
        if (a.isChecked()) retVal += "\n" + a.getText().toString();
        if (b.isChecked()) retVal += "\n" + b.getText().toString();
        if (c.isChecked()) retVal += "\n" + c.getText().toString();
        if (d.isChecked()) retVal += "\n" + d.getText().toString();
        if (e.isChecked()) {
            retVal += "\n" + e.getText().toString();
            ((Main) getActivity()).shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getTenthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView10)).getText().toString();
        CheckBox a = (CheckBox) getView().findViewById(R.id.tenA);
        CheckBox b = (CheckBox) getView().findViewById(R.id.tenB);
        CheckBox c = (CheckBox) getView().findViewById(R.id.tenC);
        CheckBox d = (CheckBox) getView().findViewById(R.id.tenD);
        CheckBox e = (CheckBox) getView().findViewById(R.id.tenE);
        if (a.isChecked()) retVal += "\n" + a.getText().toString();
        if (b.isChecked()) retVal += "\n" + b.getText().toString();
        if (c.isChecked()) retVal += "\n" + c.getText().toString();
        if (d.isChecked()) retVal += "\n" + d.getText().toString();
        if (e.isChecked()) {
            retVal += "\n" + e.getText().toString();
            ((Main) getActivity()).shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getEleventhAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView11)).getText().toString();
        ToggleButton tb = (ToggleButton) getView().findViewById(R.id.eleven);
        if (tb.isChecked()) {
            retVal += "\n" + tb.getTextOn().toString();
        }
        else {
            retVal += "\n" + tb.getTextOff().toString();
            ((Main) getActivity()).shouldGatherUserContactInfo = true;
        }
        return retVal;
    }

    private String getTwelfthAnswer() {
        String retVal = ((TextView) getView().findViewById(R.id.textView12)).getText().toString();
        EditText et = (EditText) getView().findViewById(R.id.twelve);
        retVal += "\n" + et.getText().toString();
        return retVal;
    }

    public void finish() {
        Intent intent = new Intent(getContext(), Thanks.class);
        intent.putExtra("4", getFourthAnswer());
        intent.putExtra("5", getFifthAnswer());
        intent.putExtra("6", getSixthAnswer());
        intent.putExtra("7", getSeventhAnswer());
        intent.putExtra("8", getEighthAnswer());
        intent.putExtra("9", getNinthAnswer());
        intent.putExtra("10", getTenthAnswer());
        intent.putExtra("11", getEleventhAnswer());
        intent.putExtra("12", getTwelfthAnswer());
        getActivity().startActivityForResult(intent, 0);
    }
}
