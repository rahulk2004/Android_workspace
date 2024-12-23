package com.example.taskgeocoding

import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Declare views and Geocoder using lateinit
    private lateinit var geocoder: Geocoder
    private lateinit var addressInput: EditText
    private lateinit var btnGeocode: Button
    private lateinit var coordinatesOutput: TextView
    private lateinit var latitudeInput: EditText
    private lateinit var longitudeInput: EditText
    private lateinit var btnReverseGeocode: Button
    private lateinit var addressOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views and Geocoder
        geocoder = Geocoder(this, Locale.getDefault())
        addressInput = findViewById(R.id.addressInput)
        btnGeocode = findViewById(R.id.btnGeocode)
        coordinatesOutput = findViewById(R.id.coordinatesOutput)
        latitudeInput = findViewById(R.id.latitudeInput)
        longitudeInput = findViewById(R.id.longitudeInput)
        btnReverseGeocode = findViewById(R.id.btnReverseGeocode)
        addressOutput = findViewById(R.id.addressOutput)

        // Geocoding: Address to Coordinates
        btnGeocode.setOnClickListener {
            val address = addressInput.text.toString()
            if (address.isNotEmpty()) {
                try {
                    val locations = geocoder.getFromLocationName(address, 1)
                    if (locations != null && locations.isNotEmpty()) {
                        val location = locations[0]
                        coordinatesOutput.text = "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                    } else {
                        coordinatesOutput.text = "No results found."
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter an address.", Toast.LENGTH_SHORT).show()
            }
        }

        // Reverse Geocoding: Coordinates to Address
        btnReverseGeocode.setOnClickListener {
            val latitudeStr = latitudeInput.text.toString()
            val longitudeStr = longitudeInput.text.toString()

            if (latitudeStr.isNotEmpty() && longitudeStr.isNotEmpty()) {
                try {
                    val latitude = latitudeStr.toDouble()
                    val longitude = longitudeStr.toDouble()
                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                    if (addresses != null && addresses.isNotEmpty()) {
                        val address = addresses[0]
                        addressOutput.text = address.getAddressLine(0)
                    } else {
                        addressOutput.text = "No address found."
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter valid coordinates.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}