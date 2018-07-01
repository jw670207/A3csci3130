package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Map;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, addressField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    Spinner spinnerBus, spinnerPro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        appState = ((MyApplicationData) getApplicationContext());
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        addressField = (EditText) findViewById(R.id.address);
        spinnerBus = (Spinner) findViewById(R.id.business);
        spinnerPro = (Spinner) findViewById(R.id.province);

        //ArrayAdapter for Spinner
        ArrayAdapter <CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Businesses, android.R.layout.simple_spinner_item);
        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBus.setAdapter(adapter1);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPro.setAdapter(adapter2);

        //If province value is null, then display "Unknown" in spinner as intial value
        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            addressField.setText(receivedPersonInfo.address);
            if(receivedPersonInfo.province.equals("")){
                spinnerPro.setSelection(13,true);
            }
            spinnerBus.setSelection(adapter1.getPosition(receivedPersonInfo.business));
            spinnerPro.setSelection(adapter2.getPosition(receivedPersonInfo.province));
        }
    }

    public void updateContact(View v){

        //If user chose "Unknown", then put "" into Firebase
        String prov = spinnerPro.getSelectedItem().toString();
        if (prov.equals("Unknown")){
            prov = "";
        }
        receivedPersonInfo.name = nameField.getText().toString();
        receivedPersonInfo.email = emailField.getText().toString();
        receivedPersonInfo.address = addressField.getText().toString();
        receivedPersonInfo.business = spinnerBus.getSelectedItem().toString();
        receivedPersonInfo.province = prov;

        Map<String, Object> updates = receivedPersonInfo.toMap();
        appState.firebaseReference.child(receivedPersonInfo.bid).updateChildren(updates);
        finish();

    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.bid).removeValue();
        finish();
    }
}
