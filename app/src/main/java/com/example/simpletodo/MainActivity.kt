package com.example.simpletodo

//Phillip Aizaga

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    var taskList = mutableListOf<String>()
    lateinit var adapter : TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //desired functionality - remove item from the list, and then notify the adapter that the data set has changed.
                taskList.removeAt(position)
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }
        //detect when the user clicks on the add button
        findViewById<Button>(R.id.button).setOnClickListener{
            Log.i("Phillip:", "User clicked on button")//log actions




        }
        loadItems()
        //taskList.add("this is a test")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(taskList,onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //set up button and input field for user to be able to enter a task

        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        findViewById<Button>(R.id.button).setOnClickListener {
            val userInputtedTask = inputTextField.text.toString()

            taskList.add(userInputtedTask)

            //notify the adapter that data has been updated

            adapter.notifyItemInserted(taskList.size - 1)
            inputTextField.setText("")
            saveItems()

        }

    }
    //below - code to save data the user inputted in previous sessions
    //saving data by writing and reading from a file
    //items to be loaded by reading every line in the data file
    //item saving would be written into the data file

    fun getDataFile() : File {

        return File(filesDir, "data.txt")



    }

    fun loadItems(){
        try{
        taskList = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch(ioException : IOException){
            ioException.printStackTrace()
        }

    }

    fun saveItems(){
        try {
            org.apache.commons.io.FileUtils.writeLines(getDataFile(),taskList)
        } catch(ioException: IOException){
            ioException.printStackTrace()
        }
    }





}