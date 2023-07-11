package com.hermdev.detectordecubetasdehuevos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton


class MainActivity : AppCompatActivity() {
    //Crear variable que esta en la vista con su tipo respectivo
    private lateinit var btnStart: AppCompatButton
    private lateinit var btnHistory: AppCompatButton
    private lateinit var btnConfiguration: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
    }

    //Vincular variables con la interfaz para manipularlas
    private fun initComponents() {
        btnStart = findViewById(R.id.btnStart)
        btnHistory = findViewById(R.id.btnHistory)
        btnConfiguration = findViewById(R.id.btnConfiguration)
    }

    // Crear acciones de click de cada botón
    private fun initListeners() {

        btnStart.setOnClickListener {
            val intent = Intent(this, DetectorActivity::class.java)
            startActivity(intent)

        }
        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)

        }
        btnConfiguration.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)

        }
    }


}