package com.hermdev.detectordecubetasdehuevos

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class DetectorActivity : AppCompatActivity() {
    //Variables para definir valores por defecto
    private var currentSize: String = "S"
    private var currentPrice: Double = 0.06

    //Crear variable que esta en la vista con su tipo respectivo
    private lateinit var tvSize: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvTotal: TextView
    private lateinit var btnScan: Button
    private lateinit var ivCamare: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detector)
        initComponents()
        initListeners()
        initUI()



    }

    //Vincular variables con la interfaz para manipularlas
    private fun initComponents() {
        tvSize = findViewById(R.id.tvSize)
        tvPrice = findViewById(R.id.tvPrice)
        tvTotal = findViewById(R.id.tvTotal)
        btnScan = findViewById(R.id.btnScan)
        ivCamare = findViewById(R.id.ivCamare)

    }

    // Crear acciones de click de cada botón
    private fun initListeners() {

        btnScan.setOnClickListener {
          openCamare()


        }


    }

    private fun initUI() {
        setPrice()
        setSize()

    }

    private fun setPrice() {

    }

    private fun setSize() {

    }

    private fun getTotal() {

    }
    @SuppressLint("SuspiciousIndentation")
    private  fun openCamare(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                 if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent,1)
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val extras: Bundle? = data?.extras
            val imgBitmap: Bitmap? = extras?.get("data") as Bitmap?
            sendPhoto(imgBitmap)
            ivCamare.setImageBitmap(imgBitmap)
        }
    }

fun sendPhoto( imgBits:Bitmap?){

    // Creamos un cliente
    val okHttpClient = OkHttpClient()
    //Creamos objeto json
    val jsonObject = JSONObject()
    //Definir parametros
    jsonObject.put("datos", imgBits)
    //Especificar el tipo de contenido en el encabezado
    val jsonMediaType = "application/json; charset=utf-8".toMediaType()
    //Definir el body de la peticion
    val requestBody = jsonObject.toString().toRequestBody(jsonMediaType)
    // Construimos la peticion con un request
    val solicitud = Request.Builder()
        .url("http://192.168.100.5:5000/valor")
        .post(requestBody)
        .build()

    // Hacemos una llamada de forma asincrona
    okHttpClient.newCall(solicitud).enqueue(object : Callback {
        //En caso de que falle
        override fun onFailure(call: Call, e: IOException) {
            runOnUiThread {
                // Mostrar un mensaje breve en la pantalla
                Toast.makeText(this@DetectorActivity, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
                Log.i("Flask","$e")
            }
        }
        //En caso de que se comunique
        override fun onResponse(call: Call, response: Response) {
            GlobalScope.launch(Dispatchers.Main) {
                val respuesta = response.body?.string()
                val jsonResponse = JSONObject(respuesta)
                val textoRespuesta = jsonResponse.optString("respuesta")
                tvTotal.text = textoRespuesta
                Log.i("Flask", "$textoRespuesta")
            }
        }
    })

}














}