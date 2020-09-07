package com.juancarlos.examentecnico

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.juancarlos.examentecnico.view.LoginActivity

class MainActivity : AppCompatActivity() {
    val context:Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 300)
    }
}