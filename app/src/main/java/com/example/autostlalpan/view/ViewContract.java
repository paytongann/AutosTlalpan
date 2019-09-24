package com.example.autostlalpan.view;

import com.example.autostlalpan.model.CarsPojo;

import java.util.ArrayList;

public interface ViewContract {

    void onDataPopulated(ArrayList<CarsPojo> data);

}
