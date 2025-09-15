package com.jnasif.androiddataapps.utilities

import android.app.Application
import android.content.Context
import java.io.File

class FileHelper {
    companion object{
        fun getTextFromResources(context : Context, resourceId : Int) : String{
            return context.resources.openRawResource(resourceId).use { it.bufferedReader().use { it.readText() } }
        }

        fun getTextFromAsset(context : Context, fileName : String) : String{
            return context.assets.open(fileName).use { it.bufferedReader().use { it.readText() } }
        }

        fun saveTextToFile(app: Application, json: String?) {
            val file = File(app.filesDir, "monsters.json")
            file.writeText(json ?: "", Charsets.UTF_8)
        }

        fun saveTextToCache(app: Application, json: String?) {
            val file = File(app.cacheDir, "monsters.json")
            file.writeText(json ?: "", Charsets.UTF_8)
        }

        fun readTextFile(app: Application) : String? {
            val file = File(app.cacheDir, "monsters.json")
            return if(file.exists()){
                file.readText()
            }else null
        }

        fun saveTextToExternalStorageFile(app: Application, json: String?) {
            val file = File(app.getExternalFilesDir("monsters"), "monsters.json")
            file.writeText(json ?: "", Charsets.UTF_8)
        }

        fun readTextFromExternalStorageFile(app: Application) : String? {
            val file = File(app.getExternalFilesDir("monsters"), "monsters.json")
            return if(file.exists()){
                file.readText()
            }else null
        }
    }
}