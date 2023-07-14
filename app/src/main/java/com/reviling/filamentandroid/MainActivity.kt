package com.reviling.filamentandroid

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.Console
import java.io.File
import java.io.IOException



class MainActivity : AppCompatActivity() {

    var surfaceView: SurfaceView? = null
    var customViewer: CustomViewer = CustomViewer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        surfaceView = findViewById<View>(R.id.surface_view) as SurfaceView
        val fileName = intent.getStringExtra("fileName").toString()
        customViewer.run {
            loadEntity()
            setSurfaceView(requireNotNull(surfaceView))

            //directory and model each as param
            loadGlb(this@MainActivity, fileName, fileName)
            //loadGltf(this@MainActivity, "warcraft", "scene");

            //directory and model as one
            //loadGlb(this@MainActivity, "grogu/grogu");

            //Enviroments and Lightning (OPTIONAL)
            loadIndirectLight(this@MainActivity, "venetian_crossroads_2k")
            //loadEnviroment(this@MainActivity, "venetian_crossroads_2k");
        }
    }

    override fun onResume() {
        super.onResume()
        customViewer.onResume()
    }

    override fun onPause() {
        super.onPause()
        customViewer.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        customViewer.onDestroy()
    }

}