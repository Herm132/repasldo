package com.hermdev.detectordecubetasdehuevos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class ConfigurationActivity : AppCompatActivity() {

    //Variables para definir valores por defecto
    private var currentPriceS: Double = 0.03
    private var currentPriceM: Double = 0.05
    private var currentPriceL: Double = 0.07
    private var currentPriceXL: Double = 0.10


    //Crear variable que esta en la vista con su tipo respectivo
    private lateinit var btnSave: AppCompatButton
    private lateinit var btnReset: AppCompatButton
    private lateinit var etSmall: AppCompatEditText
    private lateinit var etMedium: AppCompatEditText
    private lateinit var etBig: AppCompatEditText
    private lateinit var etSuperBig: AppCompatEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        initComponents()
        initListeners()
        initUI()
    }

    //Vincular variables con la interfaz para manipularlas
    private fun initComponents() {
        btnReset = findViewById(R.id.btnReset)
        btnSave = findViewById(R.id.btnSave)
        etSuperBig = findViewById(R.id.etSuperBig)
        etSmall = findViewById(R.id.etSmall)
        etMedium = findViewById(R.id.etMedium)
        etBig = findViewById(R.id.etBig)


    }

    // Crear acciones de click de cada botón
    private fun initListeners() {


        btnReset.setOnClickListener {
            getTotal()

        }

        btnSave.setOnClickListener {
            getTotal()

        }


    }

    private fun initUI() {
        setPriceS()
        etSmall.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        etSmall.imeOptions = EditorInfo.IME_ACTION_DONE


    }

    private fun setPriceS() {
        etSmall.text = null

    }

    private fun setSize() {

    }

    private fun getTotal() {

    }

    private fun formatNumber() {
        val text =  etSmall.text.toString().trim()

        if (text.isNotEmpty()) {
            val number = text.toDouble()
            val formattedNumber = String.format("%.2f", number)
            etSmall.setText(formattedNumber)
            etSmall.setSelection(formattedNumber.length)
        }
    }
private fun formateNumber(){
    etSmall.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            formatNumber()
        }
    })


}


}