package com.example.numbersgameapp2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter (private val context: Context, private val messages: ArrayList<String>):
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var tvcolor = itemView.findViewById<TextView>(R.id.tvcolor)

        fun bind(message: String){
            tvcolor.text = message
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        return MessageViewHolder(

            LayoutInflater.from(context).inflate(

                R.layout.item_row,

                parent,

                false

            )

        )

    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        val message = messages[position]

        holder.bind(message)

    }

    override fun getItemCount() = messages.size

}