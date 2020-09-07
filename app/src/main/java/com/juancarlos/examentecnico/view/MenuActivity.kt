package com.juancarlos.examentecnico.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.juancarlos.examentecnico.R
import com.juancarlos.examentecnico.downloadFile
import com.juancarlos.examentecnico.readFile
import com.juancarlos.examentecnico.viewmodel.MenuViewModel
import java.io.File


class MenuActivity : AppCompatActivity() {
    //Firebase Auth
    private lateinit var mAuth: FirebaseAuth
    val viewModel: MenuViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        mAuth = FirebaseAuth.getInstance()
        //val viewModel: MenuViewModel  = ViewModelProvider(this).get(MenuViewModel::class.java)

    }


    public override fun onStart() {
        super.onStart()
        viewModel.callEmployees()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        currentUser?.let {
            it.displayName
            it.email
            it.photoUrl
        }
        updateUI()
    }

    fun updateUI(){
        viewModel.getUrlEmployees().observe(this, { urlString ->
            urlString?.let {
                downloadFile(it, File(this.filesDir.absolutePath, "employees_data.json.zip"))
                val fileJson = File(this.filesDir.absolutePath, "employees_data.json.zip")
                readFile(fileJson)
            }
        })
    }
}