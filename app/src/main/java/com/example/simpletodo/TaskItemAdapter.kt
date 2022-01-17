package com.example.simpletodo

import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(val listOfTasks : List<String>, val longClickListener: OnLongClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>()  {


    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val task = listOfTasks.get(position)

        holder.task_view.text = task
    }

    override fun getItemCount(): Int {
        return listOfTasks.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task_view: TextView

        init {
            task_view = itemView.findViewById(android.R.id.text1)

            task_view.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }

        }

    }

}