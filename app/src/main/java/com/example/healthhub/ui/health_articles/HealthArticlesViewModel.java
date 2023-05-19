package com.example.healthhub.ui.health_articles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HealthArticlesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HealthArticlesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Today's Health Articles");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
