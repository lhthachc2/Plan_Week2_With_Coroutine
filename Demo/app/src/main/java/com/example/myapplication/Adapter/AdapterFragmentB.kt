package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Database.Model
import com.example.myapplication.R

class AdapterFragmentB internal constructor() : RecyclerView.Adapter<AdapterFragmentB.WordViewHolder>() {

    private var models = emptyList<Model>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Username = itemView!!.findViewById<TextView>(R.id.username)
        val Password = itemView!!.findViewById<TextView>(R.id.password)
        val Emai = itemView!!.findViewById<TextView>(R.id.email)
        val Address = itemView!!.findViewById<TextView>(R.id.address)
        val Gioitinh = itemView!!.findViewById<TextView>(R.id.gioitinh)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview_b, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = models[position]
        holder.Username.text = current.Username
        holder.Password.text = current.Password
        holder.Emai.text = current.Email
        holder.Address.text = current.Address
        holder.Gioitinh.text = current.Gender

    }

    internal fun setWords(models: List<Model>) {
        this.models=models
        notifyDataSetChanged()
    }

    override fun getItemCount() = models.size
}