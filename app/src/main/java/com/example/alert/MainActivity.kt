

/*package com.example.alert

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val emergencyContacts = mutableListOf<String>()
    private val PERMISSION_REQUEST_CODE = 100

    private lateinit var txtContacts: TextView
    private lateinit var edtContactNumber: EditText
    private lateinit var btnAddContact: ImageButton
    private lateinit var btnEmergency: Button

    private val callDelayMs = 15000L // 15 seconds between calls

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Link UI elements
        txtContacts = findViewById(R.id.txtContacts)
        edtContactNumber = findViewById(R.id.edtContactNumber)
        btnAddContact = findViewById(R.id.btnAddContact)
        btnEmergency = findViewById(R.id.btnEmergency)

        // Add contact
        btnAddContact.setOnClickListener {
            val number = edtContactNumber.text.toString().trim()
            if (number.isNotEmpty()) {
                if (!emergencyContacts.contains(number)) {
                    emergencyContacts.add(number)
                    updateContactsUI()
                    Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()
                    edtContactNumber.text.clear()
                } else {
                    Toast.makeText(this, "Contact already exists", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        // Emergency button
        btnEmergency.setOnClickListener {
            if (checkAndRequestPermissions()) {
                sendAlertWithCalls()
            }
        }
    }

    // Update UI with current contacts
    private fun updateContactsUI() {
        txtContacts.text = if (emergencyContacts.isEmpty()) {
            "No contacts added"
        } else {
            emergencyContacts.joinToString("\n") { "• $it" }
        }
    }

    // Check & request permissions
    private fun checkAndRequestPermissions(): Boolean {
        val permissionsNeeded = mutableListOf<String>()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.SEND_SMS)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.CALL_PHONE)
        }

        return if (permissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
            false
        } else true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                sendAlertWithCalls()
            } else {
                Toast.makeText(this, "All permissions are required!", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Send SMS and make repeated calls
    private fun sendAlertWithCalls() {
        if (emergencyContacts.isEmpty()) {
            Toast.makeText(this, "Add at least one contact", Toast.LENGTH_SHORT).show()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            val locationUrl = location?.let { "https://maps.google.com/?q=${it.latitude},${it.longitude}" }
                ?: "Location unavailable"

            val message = "EMERGENCY ALERT! I need help.\nLocation: $locationUrl"

            // Send SMS
            val smsManager = SmsManager.getDefault()
            for (contact in emergencyContacts) {
                smsManager.sendTextMessage(contact, null, message, null, null)
            }
            Toast.makeText(this, "Emergency SMS sent!", Toast.LENGTH_SHORT).show()

            // Start calling contacts sequentially
            callContactsSequentially(0)
        }
    }

    // Call each contact sequentially with delay
    private fun callContactsSequentially(index: Int) {
        if (index >= emergencyContacts.size) return

        val contact = emergencyContacts[index]
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$contact")

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent)

            // Call next contact after delay
            Handler(Looper.getMainLooper()).postDelayed({
                callContactsSequentially(index + 1)
            }, callDelayMs)
        }
    }
}*/

/*ackage com.example.alert

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val emergencyContacts = mutableListOf<String>()
    private val PERMISSION_REQUEST_CODE = 100

    private lateinit var txtContacts: TextView
    private lateinit var edtContactNumber: EditText
    private lateinit var btnAddContact: ImageButton
    private lateinit var btnEmergency: Button

    private val callDelayMs = 15000L // 15 seconds between calls

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Link UI elements
        txtContacts = findViewById(R.id.txtContacts)
        edtContactNumber = findViewById(R.id.edtContactNumber)
        btnAddContact = findViewById(R.id.btnAddContact)
        btnEmergency = findViewById(R.id.btnEmergency)

        // Add contact
        btnAddContact.setOnClickListener {
            val number = edtContactNumber.text.toString().trim()
            if (number.isNotEmpty()) {
                if (!emergencyContacts.contains(number)) {
                    emergencyContacts.add(number)
                    updateContactsUI()
                    Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()
                    edtContactNumber.text.clear()
                } else {
                    Toast.makeText(this, "Contact already exists", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        // Emergency button
        btnEmergency.setOnClickListener {
            if (checkAndRequestPermissions()) {
                sendAlertWithCalls()
            }
        }
    }

    // Update UI with current contacts
    private fun updateContactsUI() {
        txtContacts.text = if (emergencyContacts.isEmpty()) {
            "No contacts added"
        } else {
            emergencyContacts.joinToString("\n") { "• $it" }
        }
    }

    // Check & request permissions
    private fun checkAndRequestPermissions(): Boolean {
        val permissionsNeeded = mutableListOf<String>()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.SEND_SMS)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.CALL_PHONE)
        }

        return if (permissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
            false
        } else true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                sendAlertWithCalls()
            } else {
                Toast.makeText(this, "All permissions are required!", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Send SMS and make repeated calls
    private fun sendAlertWithCalls() {
        if (emergencyContacts.isEmpty()) {
            Toast.makeText(this, "Add at least one contact", Toast.LENGTH_SHORT).show()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            val locationUrl = location?.let { "https://maps.google.com/?q=${it.latitude},${it.longitude}" }
                ?: "Location unavailable"

            val message = "EMERGENCY ALERT! I need help.\nLocation: $locationUrl"

            // Send SMS
            val smsManager = SmsManager.getDefault()
            for (contact in emergencyContacts) {
                smsManager.sendTextMessage(contact, null, message, null, null)
            }
            Toast.makeText(this, "Emergency SMS sent!", Toast.LENGTH_SHORT).show()

            // Start calling contacts sequentially
            callContactsSequentially(0)
        }
    }

    // Call each contact sequentially with delay
    private fun callContactsSequentially(index: Int) {
        if (index >= emergencyContacts.size) return

        val contact = emergencyContacts[index]
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$contact")

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent)

            // Call next contact after delay
            Handler(Looper.getMainLooper()).postDelayed({
                callContactsSequentially(index + 1)
            }, callDelayMs)
        }
    }
}*/
package com.example.alert

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val emergencyContacts = mutableListOf<String>()
    private val PERMISSION_REQUEST_CODE = 100

    private lateinit var edtContactNumber: EditText
    private lateinit var btnAddContact: ImageButton
    private lateinit var btnEmergency: Button
    private lateinit var contactsContainer: LinearLayout

    private val callDelayMs = 15000L // 15 seconds between calls

    // SharedPreferences for saving per user
    private val PREFS_NAME = "emergency_contacts_prefs"
    private val CONTACTS_KEY = "contacts_list"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Link UI elements
        edtContactNumber = findViewById(R.id.edtContactNumber)
        btnAddContact = findViewById(R.id.btnAddContact)
        btnEmergency = findViewById(R.id.btnEmergency)
        contactsContainer = findViewById(R.id.contactsContainer)

        loadContacts()
        refreshContactsUI()

        // Add contact
        btnAddContact.setOnClickListener {
            val number = edtContactNumber.text.toString().trim()
            if (number.isNotEmpty() && !emergencyContacts.contains(number)) {
                emergencyContacts.add(number)
                saveContacts()
                refreshContactsUI()
                edtContactNumber.text.clear()
                Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid or duplicate number", Toast.LENGTH_SHORT).show()
            }
        }

        // Emergency button
        btnEmergency.setOnClickListener {
            if (checkAndRequestPermissions()) {
                sendAlertWithCalls()
            }
        }
    }

    /** Refresh contacts dynamically in container */
    private fun refreshContactsUI() {
        contactsContainer.removeAllViews()
        val inflater = layoutInflater

        for (contact in emergencyContacts) {
            val view = inflater.inflate(R.layout.contact_item, contactsContainer, false)
            val txtNumber = view.findViewById<TextView>(R.id.txtContactNumber)
            val btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)

            txtNumber.text = contact
            btnDelete.setOnClickListener {
                emergencyContacts.remove(contact)
                saveContacts()
                refreshContactsUI()
                Toast.makeText(this, "Contact removed", Toast.LENGTH_SHORT).show()
            }

            contactsContainer.addView(view)
        }
    }

    /** Save contacts in SharedPreferences */
    private fun saveContacts() {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(CONTACTS_KEY, emergencyContacts.toSet())
            .apply()
    }

    /** Load saved contacts */
    private fun loadContacts() {
        val saved = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getStringSet(CONTACTS_KEY, emptySet())
        emergencyContacts.clear()
        emergencyContacts.addAll(saved ?: emptySet())
    }

    /** Check and request permissions */
    private fun checkAndRequestPermissions(): Boolean {
        val perms = mutableListOf<String>()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
            perms.add(Manifest.permission.SEND_SMS)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            perms.add(Manifest.permission.ACCESS_FINE_LOCATION)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            perms.add(Manifest.permission.CALL_PHONE)

        return if (perms.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, perms.toTypedArray(), PERMISSION_REQUEST_CODE)
            false
        } else true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE &&
            grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        ) {
            sendAlertWithCalls()
        }
    }

    /** Send SMS and make repeated calls */
    private fun sendAlertWithCalls() {
        if (emergencyContacts.isEmpty()) {
            Toast.makeText(this, "Add at least one contact", Toast.LENGTH_SHORT).show()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            val locationUrl = location?.let { "https://maps.google.com/?q=${it.latitude},${it.longitude}" }
                ?: "Location unavailable"

            val message = "EMERGENCY ALERT! I need help.\nLocation: $locationUrl"

            // Send SMS
            val smsManager = SmsManager.getDefault()
            for (contact in emergencyContacts) {
                smsManager.sendTextMessage(contact, null, message, null, null)
            }
            Toast.makeText(this, "Emergency SMS sent!", Toast.LENGTH_SHORT).show()

            // Start calling contacts sequentially
            callContactsSequentially(0)
        }
    }

    /** Call each contact sequentially */
    private fun callContactsSequentially(index: Int) {
        if (index >= emergencyContacts.size) return

        val contact = emergencyContacts[index]
        val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$contact"))

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent)
            Handler(Looper.getMainLooper()).postDelayed({
                callContactsSequentially(index + 1)
            }, callDelayMs)
        }
    }
}
