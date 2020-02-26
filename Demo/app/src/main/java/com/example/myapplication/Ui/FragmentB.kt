package com.example.myapplication.Ui

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
import com.example.myapplication.Adapter.AdapterFragmentB
import com.example.myapplication.Database.Model
import com.example.myapplication.R
import com.example.myapplication.ViewModel.WordViewModel

class FragmentB: Fragment() {

    lateinit var wordViewModel: WordViewModel
    lateinit var recycleView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)
        recycleView = view.findViewById(R.id.recyclerview_b)
        val adapter = AdapterFragmentB()
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(viewLifecycleOwner, Observer<List<Model>> {
            adapter.setWords(it)
        })
        return  view
    }
}