package com.jnasif.androiddataapps.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.androiddataapps.LOG_TAG
import com.jnasif.androiddataapps.R
import com.jnasif.androiddataapps.utilities.FileHelper

class MainViewModel(app : Application) : AndroidViewModel(app) {
    init {
//        val text = FileHelper.getTextFromResources(app, R.raw.monster_data)
        val text = FileHelper.getTextFromAsset(app, "monster_data.json")
        Log.i(LOG_TAG, text)
    }
}