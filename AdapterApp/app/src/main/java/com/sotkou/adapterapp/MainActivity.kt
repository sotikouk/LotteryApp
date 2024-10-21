package com.sotkou.adapterapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1 - Αρχικοποίηση του ListView
        // ListView: Εμφανίζει μια scrollable λίστα απο στοιχεία
        val myListView: ListView = findViewById(R.id.listView)
        // 2 - Πηγή δεδομένων
        val operatingSystems = arrayOf("Windows", "Linux", "Android", "iOS")

        // 3 - Adapter
        // Η ListView δεν έχει τα δεδομένα απευθείας, απλά τα εμφανίζει
        // βασίζεται στον Adapter για την παροχή δεδομένων
        var myAdapter = ArrayAdapter(
            this, // Context, η παρούσα κατάσταση της εφαρμογής
            android.R.layout.simple_list_item_1, // layout για το κάθε στοιχείο
            operatingSystems // η πηγή δεδομένων
        )
        // 4 - Σύνδεση Adapter με ListView
        myListView.adapter = myAdapter
    }
}