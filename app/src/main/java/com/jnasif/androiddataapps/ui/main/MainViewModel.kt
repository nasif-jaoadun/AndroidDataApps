package com.jnasif.androiddataapps.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.androiddataapps.LOG_TAG
import com.jnasif.androiddataapps.R
import com.jnasif.androiddataapps.data.Monster
import com.jnasif.androiddataapps.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MainViewModel(app : Application) : AndroidViewModel(app) {
    private val listType = Types.newParameterizedType(List::class.java, Monster::class.java)
    init {
//        val text = FileHelper.getTextFromResources(app, R.raw.monster_data)
        val text = FileHelper.getTextFromAsset(app, "monster_data.json")
        parseText(text)
    }

    fun parseText(text : String){
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
        val monsterData = adapter.fromJson(text)
        for (monster in monsterData ?: emptyList()){
            Log.i(LOG_TAG, "${monster.monsterName} (\$${monster.price})")
        }
    }
}