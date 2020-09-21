package com.shine56.blue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.shine56.blue.R

class ErrorLogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_log)

        var errorString = intent.getStringExtra("errorInfo");
        val textView : TextView = findViewById(R.id.error_log_text);
        textView.text = errorString
    }
}