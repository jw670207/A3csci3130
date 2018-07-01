package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.joda.time.*;
import android.widget.Toast;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, addressField;
    private MyApplicationData appState;

    private Spinner spinnerBus, spinnerPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        addressField = (EditText) findViewById(R.id.address);
        spinnerBus = (Spinner) findViewById(R.id.business);
        spinnerPro = (Spinner) findViewById(R.id.province);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Businesses, android.R.layout.simple_spinner_item);
        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBus.setAdapter(adapter1);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPro.setAdapter(adapter2);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        Contact person;

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String address = addressField.getText().toString();
        String business = spinnerBus.getSelectedItem().toString();
        String province = spinnerPro.getSelectedItem().toString();

        //calculate estimated time in second from 2018-06-30 20:00:00 to current time for generating unique key(business ID)
        DateTime standard = new DateTime(2018, 6, 30, 20, 0);
        DateTime current = new DateTime();

        Seconds seconds = Seconds.secondsBetween(standard, current);
        String bid = Integer.toString(seconds.getSeconds());

        int length = 9-bid.length();

        //if digits<9, add 0 in front of calculated value
        for (int i =0; i<length; i++ ) {
            bid = "0"+bid;
        }
        Toast.makeText(getApplicationContext(), bid,Toast.LENGTH_SHORT).show();

        //If province == "Unknown", put "" into Firebase
        if (province.equals("Unknown")){
            person = new Contact(bid, name,email, address, business, "");
        }
        else {
            person = new Contact(bid, name, email, address, business, province);
        }

        appState.firebaseReference.child(bid).setValue(person);

        finish();

    }
}
