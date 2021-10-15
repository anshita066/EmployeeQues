package com.example.databaseques


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log

class SQLiteDatabaseManager(private val context: Context) {

    // Create object of DatabaseHelper class
    // which we have created
    private val databaseHelper =
        SQLiteDatabaseHelper(context)

    private val TAG = "DB_MANAGER"

    fun insertValue(employeeDataClass: EmployeeDataClass) {
        // get writable database as we want to write data
        val db = databaseHelper.writableDatabase

        /**
         * ContentValues()
         * Creates an empty set of values using the default initial size
         */
        // Insert query asks for ContentValues() as arguments
        // So creating it
        val contentValues = ContentValues()
        contentValues.put(SQLiteDatabaseHelper.COLUMN_NAME, employeeDataClass.name)
        contentValues.put(SQLiteDatabaseHelper.COLUMN_CONTACT, employeeDataClass.contact)
        contentValues.put(SQLiteDatabaseHelper.COLUMN_ADDRESS, employeeDataClass.address)

        // insert is predefined function to insert values
        val id = db.insert(SQLiteDatabaseHelper.TABLE_NAME, null, contentValues)

        Log.i(TAG, "Data inserted with Id: $id")

        // close the connection
        db.close()
    }

    // Get all data from SQLite DB
    @SuppressLint("Range")
    fun getAllEmpDataFromSQLiteDB(): ArrayList<EmployeeDataClass> {
        // get writable database
        val db = databaseHelper.writableDatabase

        // Select All Query
        val selectQuery = "SELECT  * FROM " + SQLiteDatabaseHelper.TABLE_NAME
        val cursor = db.rawQuery(selectQuery, null)

        val employeesList: ArrayList<EmployeeDataClass> = arrayListOf()

        if (cursor.moveToFirst()) {
            do {

                val employee =
                    EmployeeDataClass()
                employee.id =
                    cursor.getInt(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_ID))
                employee.name =
                    cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_NAME))
                employee.contact =
                    cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_CONTACT))
                employee.address =
                    cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_ADDRESS))

                employeesList.add(employee)

            } while (cursor.moveToNext())
        }
        cursor.close()

        return employeesList
    }

    fun updateAnEmployeeData(empID: Int, name: String, contact: String, address: String): Int {
        // get writable database
        val db = databaseHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(SQLiteDatabaseHelper.COLUMN_NAME, name)
        contentValues.put(SQLiteDatabaseHelper.COLUMN_CONTACT, contact)
        contentValues.put(SQLiteDatabaseHelper.COLUMN_ADDRESS, address)

        return db.update(
            SQLiteDatabaseHelper.TABLE_NAME,
            contentValues,
            SQLiteDatabaseHelper.COLUMN_ID + "=?",
            arrayOf(empID.toString())
        )
    }

    fun deleteAnEmployeeData(empID: Int): Int {
        // get writable database
        val db = databaseHelper.writableDatabase

        return db.delete(
            SQLiteDatabaseHelper.TABLE_NAME,
            SQLiteDatabaseHelper.COLUMN_ID + "=?",
            arrayOf(empID.toString())
        )
    }

}