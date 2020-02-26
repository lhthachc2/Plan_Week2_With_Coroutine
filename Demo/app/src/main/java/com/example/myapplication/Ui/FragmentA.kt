package com.example.myapplication.Ui

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.AdapterFragmentA
import com.example.myapplication.Database.Model
import com.example.myapplication.Model.Messages
import com.example.myapplication.R
import com.example.myapplication.ViewModel.MessageViewModel
import com.example.myapplication.ViewModel.WordViewModel
import kotlinx.coroutines.Job
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL

class FragmentA: Fragment() {

    lateinit var wordViewModel: MessageViewModel
    lateinit var recycleView: RecyclerView
    var arraylist = ArrayList<Messages>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        wordViewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        arraylist = wordViewModel.main("https://api.androidhive.info/mail/inbox.json") as ArrayList<Messages>

        recycleView = view.findViewById(R.id.recyclerview)
        val adapter = AdapterFragmentA(arraylist)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))


       // ReadJson().execute("https://api.androidhive.info/mail/inbox.json")

        return view
    }

//    private inner class ReadJson : AsyncTask<String, Void, String>() {
//        override fun doInBackground(vararg strings: String): String {
//            val content = StringBuilder()
//            try {
//                val url = URL(strings[0])
//                val inputStreamReader = InputStreamReader(url.openConnection().getInputStream())
//                val bufferedReader = BufferedReader(inputStreamReader)
//                var line: String? = ""
//                while ({ line = bufferedReader.readLine(); line }() != null) {
//                    content.append(line)
//                }
//            } catch (e: MalformedURLException) {
//                e.printStackTrace()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//
//            return content.toString()
//        }
//
//        override fun onPostExecute(s: String) {
//            super.onPostExecute(s)
//            try {
//                val json = JSONObject(s)
//                val jsonArray = json.getJSONArray("messages")
//                for (i in 0..jsonArray.length()-1) {
//                    val JSONObject = jsonArray.getJSONObject(i)
//                    var id: String = JSONObject.getString("id")
//                    var from: String = JSONObject.getString("from")
//                    var email: String = JSONObject.getString("email")
//                    var subject: String = JSONObject.getString("subject")
//                    var message: String = JSONObject.getString("message")
//                    var date: String = JSONObject.getString("date")
//                    arraylist.add(Messages(id,from,email,subject,message,date))
//                }
//                val adapter = AdapterFragmentA(arraylist)
//                recycleView.adapter = adapter
//                recycleView.layoutManager = LinearLayoutManager(context)
//                recycleView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
    fun loadDataItn()
    {

    }
}