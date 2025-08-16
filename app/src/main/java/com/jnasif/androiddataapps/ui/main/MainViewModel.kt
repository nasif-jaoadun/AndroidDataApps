package com.jnasif.androiddataapps.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jnasif.androiddataapps.data.MonsterRepository

class MainViewModel(app : Application) : AndroidViewModel(app) {

    private val dataRepo = MonsterRepository(app)
    val monsterData = dataRepo.monsterData
    fun refreshData() {
        dataRepo.refreshData()
    }
}