package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Phillip Aizaga

class TaskItemAdapter(val listOfItems: List<String>, val longClickListener: OnLongClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    //define an interface that should help with removing and editing tasks in the to-do list

    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context

        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1,parent, false)
        return ViewHolder(contactView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfItems.get(position)

        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView
        init{
            textView = itemView.findViewById(android.R.id.text1)
            itemView.setOnLongClickListener{
                Log.i("phillip", "User long clicked on item:" + adapterPosition)
                longClickListener.onItemLongClicked(adapterPosition)
                true

            }
        }
    }

}