package me.itsof.a180118;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;

public class Calculator extends AppCompatActivity {

    private EditText a;
    private EditText b;
    private ButtonRectangle addition;
    private ButtonRectangle subtraction;
    private ButtonRectangle multiplication;
    private ButtonRectangle division;
    private TextView result;

    private double getA() {
        return Double.parseDouble(a.getText().toString());
    }

    private double getB() {
        return Double.parseDouble(b.getText().toString());
    }

    private void showResult(String resultText) {
        result.setText(resultText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calculator);
        setTitle("Calculadora");
        a = (EditText) findViewById(R.id.a);
        b = (EditText) findViewById(R.id.b);
        addition = (ButtonRectangle) findViewById(R.id.addition);
        subtraction = (ButtonRectangle) findViewById(R.id.subtraction);
        multiplication = (ButtonRectangle) findViewById(R.id.multiplication);
        division = (ButtonRectangle) findViewById(R.id.division);
        result = (TextView) findViewById(R.id.result);

        addition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showResult((getA() + getB()) + "");
            }
        });
        subtraction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showResult((getA() - getB()) + "");
            }
        });
        multiplication.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showResult((getA() * getB()) + "");
            }
        });
        division.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showResult((getA() / getB()) + "");
            }
        });
    }
}
