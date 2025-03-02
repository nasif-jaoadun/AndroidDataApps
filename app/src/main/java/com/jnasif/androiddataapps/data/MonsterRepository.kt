package com.jnasif.androiddataapps.data

import android.content.Context
import com.jnasif.androiddataapps.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MonsterRepository {
    private val listType = Types.newParameterizedType(List::class.java, Monster::class.java)

    fun getMonsterData(context : Context) : List<Monster>{
//        val text = FileHelper.getTextFromResources(app, R.raw.monster_data)
        val text = FileHelper.getTextFromAsset(context, "monster_data.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
        return adapter.fromJson(text) ?: emptyList()
    }
}