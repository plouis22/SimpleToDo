package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val longClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //remove
                listOfTasks.removeAt(position)
                //notify adapter
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }

        loadItems()

        val addTaskField = findViewById<EditText>(R.id.add_task_field)
        //
       val taskViews = findViewById<RecyclerView>(R.id.list_of_taskView)
        //
        adapter = TaskItemAdapter(listOfTasks, longClickListener)
        //
        taskViews.adapter = adapter

        taskViews.layoutManager = LinearLayoutManager(this)

        // add task
        findViewById<Button>(R.id.add_button).setOnClickListener {

           val newTask = addTaskField.text.toString()
            listOfTasks.add(newTask)
            //update data
            adapter.notifyItemInserted(listOfTasks.size - 1)
            saveItems()
            addTaskField.text.clear()

        }
    }
    //save data for app by writing and reading from file

    //Get

    fun getDateFile() : File {
        return File(filesDir, "tasks.txt")

    }

    //Load
    fun loadItems(){

        try {
            listOfTasks = FileUtils.readLines(getDateFile(), Charset.defaultCharset())
        }catch (ioException: IOException){
            ioException.printStackTrace()
        }

    }


    //Save
    fun saveItems(){
        try {
            FileUtils.writeLines(getDateFile(), listOfTasks)
        }
        catch (ioException: IOException) {
            ioException.printStackTrace()
        }


    }

}