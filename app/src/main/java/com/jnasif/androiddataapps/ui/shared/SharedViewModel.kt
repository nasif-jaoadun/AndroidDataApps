package com.jnasif.androiddataapps.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnasif.androiddataapps.data.Monster
import com.jnasif.androiddataapps.data.MonsterRepository

class SharedViewModel(app : Application) : AndroidViewModel(app) {

    private val dataRepo = MonsterRepository(app)
    val monsterData = dataRepo.monsterData
    val selectedMonster = MutableLiveData<Monster>()
    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }
}