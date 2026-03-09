package com.jnasif.androiddataapps.data

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.jnasif.androiddataapps.LOG_TAG
import com.jnasif.androiddataapps.WEB_SERVICE_URL
import com.jnasif.androiddataapps.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MonsterRepository(val app: Application) {
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    val monsterData = MutableLiveData<List<Monster>>()
    private val monsterDao = MonsterDatabase.getDatabase(app).monsterDao()
    init {
        CoroutineScope(Dispatchers.IO ).launch {
            val data = monsterDao.getAll()
            if (data.isEmpty()){
                callWebService()
            }else{
                monsterData.postValue(data)
                withContext(Dispatchers.Main){
                    Toast.makeText(app, "Using Local data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    @WorkerThread
    suspend fun callWebService(){
        if (networkAvailable()){
            withContext(Dispatchers.Main){
                Toast.makeText(app, "Using Remote data", Toast.LENGTH_LONG).show()
            }
            Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder().baseUrl(WEB_SERVICE_URL).addConverterFactory(MoshiConverterFactory.create()).build()
            val service = retrofit.create(MonsterService::class.java)
            val serviceData = service.getMonsterData().body() ?: emptyList()
            monsterData.postValue(serviceData)
            monsterDao.deleteAll()
            monsterDao.insertMovies(serviceData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    private fun saveDataTOCache(monsterData : List<Monster>){
        if (ContextCompat.checkSelfPermission(app,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            val moshi = Moshi.Builder().build()
            val listType = Types.newParameterizedType(List::class.java, Monster::class.java)
            val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
            val json = adapter.toJson(monsterData)
            FileHelper.saveTextToExternalStorageFile(app, json)
        }
    }

    private fun readDataFromCache() : List<Monster>{
        val json= FileHelper.readTextFromExternalStorageFile(app)
        if (json == null){
            return emptyList()
        }
        val moshi= Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Monster::class.java)
        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
        return adapter.fromJson(json)?: emptyList()
    }
}