package com.example.databaseques

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DatabaseActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        // Get the intent data to check from
        // which button click the user is coming
        // in this activity
        val intent = intent
        BUTTON_CLICKED = intent.getStringExtra(MainActivity.BUTTON_CLICKED_KEY).toString()

        initialiseRecyclerView()

    }

    private fun initialiseRecyclerView() {
        recyclerView = findViewById(R.id.employeeDetailsRecyclerView)

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        if (BUTTON_CLICKED == MainActivity.SQLITE_DEMO_BTN) {
            setupListFromSQLite()
            recyclerView.adapter = customAdapter
            customAdapter.notifyDataSetChanged()
        }

    }



    private fun setupListFromSQLite() {
        val databaseManager =
            SQLiteDatabaseManager(this)

        // get data from Database
        val employeeList = databaseManager.getAllEmpDataFromSQLiteDB()

        customAdapter =
            CustomAdapter(this, employeeList)
    }

    companion object {
        lateinit var BUTTON_CLICKED: String
    }
}