package com.sotkou.lotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    lateinit var resultView: TextView
    lateinit var shareButtonView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultView = findViewById(R.id.resultTextView)
        shareButtonView = findViewById(R.id.shareButton)

        var luckyNumbers = generateRandomNumbers(6)
        resultView.text = luckyNumbers

        // Λαμβάνουμε το userName απο την MainActivity
        var user = receiveUserName()

        // Στέλνουμε τους αριθμούς με mail
        shareButtonView.setOnClickListener {
            shareResult(user, luckyNumbers)
        }
    }

    fun generateRandomNumbers(count: Int): String {
        // Παράγουμε την λίστα με τους τυχαίους αριθμους
        var randomNumbers = List(count) { // lambda expression
            (1..42).random() // random() παράγει τυχαίους αριθμούς απο 0 εως και 42
        }
        return randomNumbers.joinToString(" ")
    }

    fun receiveUserName(): String {
        // Λαμβάνουμε τα extras που προστέθηκαν στο Intent
        // ? --> Μας λέει οτι η μεταβλητή μπορεί να ειναι Null
        var bundle: Bundle? = intent.extras
        // Key --> Value Το κλειδί ειναι το userName
        var username = bundle?.getString("userName").toString()
        return username
    }

    fun shareResult(username:String, luckyNumbers: String) {
        // Implicit Intent. Αναφέρεται σε μια ενέργεια που πρέπει να γίνει
        // και το σύστημα αποφασίζει ποιο πρόγραμμα ειναι κατάλληλο για
        // την πραγματοποίηση της ενέργειας.

        // Στέλνουμε δεδομένα σε άλλη εφαρμογή
        var intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain") // ρυθμίζουμε τον τύπο των δεδομένων
        // Το θέμα του mail
        intent.putExtra(Intent.EXTRA_SUBJECT, "$username sends you the lucky numbers")
        // Το κείμενο
        intent.putExtra(Intent.EXTRA_TEXT, "The Lucky numbers are: $luckyNumbers")
        startActivity(intent)
    }
}