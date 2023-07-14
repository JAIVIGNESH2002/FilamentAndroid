package com.reviling.filamentandroid

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import java.io.IOException

class pickActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick)
        listView = findViewById(R.id.fileNames)
        val fileNames = fetchFileNames()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,fileNames)
        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val tappedItem = fileNames[position]
                navigateToMainActivity(tappedItem)
            }
    }

    fun fetchFileNames():Array<String>{
        try {
            val assetManager = applicationContext.assets
            val modelFiles = assetManager.list("models")

            if (modelFiles != null && modelFiles.isNotEmpty()) {
                return modelFiles;
            } else {
                Log.d(ContentValues.TAG, "No files found in models directory.")
            }
        } catch (e: IOException) {
            Log.e(ContentValues.TAG, "Error accessing assets directory: ${e.message}")
        }
        return emptyArray()
    }

    private fun navigateToMainActivity(tappedItem: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("fileName", tappedItem)
        startActivity(intent)
    }
}