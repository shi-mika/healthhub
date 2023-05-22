package com.example.healthhub.ui.medicines;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicinesViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public MedicinesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Buy Medicine");
    }

    public LiveData<String> getText() {
        return mText;
    }
}