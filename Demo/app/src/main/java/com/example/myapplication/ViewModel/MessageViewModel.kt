package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.Model
import com.example.myapplication.Database.WordRoomDatabase
import com.example.myapplication.Model.Messages
import com.example.myapplication.Responsity.MessageRespository
import com.example.myapplication.Responsity.WordRepository
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.IOException
import kotlin.system.measureTimeMillis

class MessageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MessageRespository

    init {
        repository = MessageRespository()
    }

    suspend fun insert(s: String) : ArrayList<Messages>   {
        val a : String  = repository.fetchDocs(s)
        val arrayList = ArrayList<Messages>()
        try {
            val json = JSONObject(a)
            val jsonArray = json.getJSONArray("messages")
            for (i in 0..jsonArray.length() - 1) {
                val JSONObject = jsonArray.getJSONObject(i)
                var id: String = JSONObject.getString("id")
                var from: String = JSONObject.getString("from")
                var email: String = JSONObject.getString("email")
                var subject: String = JSONObject.getString("subject")
                var message: String = JSONObject.getString("message")
                var date: String = JSONObject.getString("date")
                arrayList.add(Messages(id, from, email, subject, message, date))
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return arrayList
    }

    fun main(s : String)  = runBlocking {
        val time = measureTimeMillis {
            val arrayList: Deferred<ArrayList<Messages>> = async { insert(s)  }
            return@runBlocking arrayList.await()
        }
    }

}