package com.example.eva4final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var textData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textData = findViewById(R.id.textData)

        // Referencia a la base de datos de Firebase
        database = FirebaseDatabase.getInstance("https://eva4-272a9-default-rtdb.firebaseio.com/")
            .getReference("datos/lec1/gases")

        // Escuchar cambios en tiempo real
        database.child("dato").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val valor = snapshot.getValue(String::class.java)
                textData.text = valor ?: "No hay datos disponibles"
                val textData: TextView = findViewById(R.id.textData)

            }

            override fun onCancelled(error: DatabaseError) {
                textData.text = "Error: ${error.message}"
            }
        })
    }
}
