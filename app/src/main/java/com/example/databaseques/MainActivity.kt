package com.example.databaseques

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // insertDataInDBUsingSQLite()
        val addDummySqliteDemoBtn:Button = findViewById(R.id.addDummyRoomDataBtn)
        val sqliteDemoBtn:Button = findViewById(R.id.sqliteDemoBtn)
        sqliteDemoBtn.setOnClickListener {
            startActivity(
                Intent(this, DatabaseActivity::class.java).putExtra(
                    BUTTON_CLICKED_KEY,
                    SQLITE_DEMO_BTN
                )
            )
        }

        addDummySqliteDemoBtn.setOnClickListener {
            insertDataInDBUsingSQLite()
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_LONG).show()
        }
    }

    private fun insertDataInDBUsingSQLite() {

        val databaseManager =
            SQLiteDatabaseManager(this)

        // insert data in DB
        databaseManager.insertValue(
            EmployeeDataClass(
                name = "Anshita",
                contact = "9654765412",
                address = "Delhi"
            )
        )
        databaseManager.insertValue(
            EmployeeDataClass(
                name = "Kavita",
                contact = "8076549761",
                address = "Punjab"
            )
        )
        databaseManager.insertValue(
            EmployeeDataClass(
                name = "Ritika",
                contact = "9236412365",
                address = "Mumbai"
            )
        )
    }


//    private fun insertDataInDBUsingSQLite(key: String, value: String) {
//
//        val databaseManager =
//            SQLiteDatabaseManager(this)
//
//        // insert data in DB
//        databaseManager.insertValue(
//            EmployeeDataClass(
//                name = value,
//                contact = key,
//                address = "New Delhi"
//            )
//        )
//    }

    companion object {
        const val BUTTON_CLICKED_KEY = "BUTTON_CLICKED"
        const val SQLITE_DEMO_BTN = "SQLITE_DEMO_BTN"

        const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }
}