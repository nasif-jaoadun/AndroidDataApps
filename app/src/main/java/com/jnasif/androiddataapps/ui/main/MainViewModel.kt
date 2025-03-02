package com.jnasif.androiddataapps.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.androiddataapps.LOG_TAG
import com.jnasif.androiddataapps.data.MonsterRepository

class MainViewModel(app : Application) : AndroidViewModel(app) {
    private val dataRepo = MonsterRepository()
    init {
        val monsterData = dataRepo.getMonsterData(app)
        for (monster in monsterData){
            Log.i(LOG_TAG, "${monster.name} (\$${monster.price})")
        }
    }


}