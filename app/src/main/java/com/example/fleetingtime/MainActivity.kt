package com.example.fleetingtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val inputText = load()
        if (inputText.isNotEmpty()){
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this,"读取成功",Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val editText = findViewById<EditText>(R.id.edit_text)
        save(editText.text.toString())
    }

    private fun save(text : String){
        try {
            val out = openFileOutput("note", MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(out))
            writer.use {
                it.append(text)
            }
        }catch (e :Exception){
            e.printStackTrace()
        }
    }

    private fun load():String{
        val content = StringBuilder()
        try {
            val input = openFileInput("note")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return content.toString()
    }
}