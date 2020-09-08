package com.juancarlos.examentecnico.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.juancarlos.examentecnico.R
import com.juancarlos.examentecnico.downloadFile
import com.juancarlos.examentecnico.readFile
import com.juancarlos.examentecnico.viewmodel.MenuViewModel
import kotlinx.coroutines.coroutineScope
import java.io.File


class MenuActivity : AppCompatActivity(), OnMapReadyCallback {
    //Firebase Auth
    private lateinit var mAuth: FirebaseAuth
    val viewModel: MenuViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        mAuth = FirebaseAuth.getInstance()

        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as
                SupportMapFragment
        mapFragment.getMapAsync(this)
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

    override fun onMapReady(googleMap: GoogleMap?) {
        val UPV = LatLng(39.481106, -0.340987) //Nos ubicamos en la UPV
        googleMap?.addMarker(MarkerOptions().position(UPV).title("Marker UPV"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(UPV))
    }
}