package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * All required elements of Json in Firebase
 */

public class Contact implements Serializable {

    public  String name;
    public  String email;
    public  String address;
    public  String business;
    public  String province;
    public  String bid;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String bid, String name, String email, String address, String business, String province){
        this.name = name;
        this.bid = bid;
        this.email = email;
        this.address = address;
        this.business = business;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bid", bid);
        result.put("name", name);
        result.put("email", email);
        result.put("address", address);
        result.put("business", business);
        result.put("province", province);

        return result;
    }
}
