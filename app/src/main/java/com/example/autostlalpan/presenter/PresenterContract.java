package com.example.autostlalpan.presenter;

import com.example.autostlalpan.view.ViewContract;

public interface PresenterContract {

    void initRetrofit();
    void onBindView(ViewContract view);
}
