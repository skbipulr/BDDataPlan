package com.walton.internetdataplan.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.walton.internetdataplan.utitls.AppConstants;

// Create a common helper class
public class DBHelper extends SQLiteOpenHelper {
	// db name for this application

	 private static final String DBNAME = AppConstants.DATABASE_NAME;

//	private static String DBNAME = null;

	// version number of this db

	private static final int VERSION = 1;

	// private variable which is going to store the singleton object of this
	// class and return it to caller

	private static DBHelper dbHelper = null;
	// private SQLiteDatabase variable which is going to be responsible for all
	// our db related operation // no need touch it

	private static SQLiteDatabase db = null;

	// a private constructor which is going to be called from getInstance method
	// of this constructor

	private DBHelper(Context context) {
		super(context, DBNAME, null, VERSION);
		// storing the object of this class to dbHelper
		dbHelper = this;
	}

	// Method is called during creation of the database
	/* ***************************
	 * 
	 * BE SURE TO ENTER THE CORRECT CREATE STATEMENT FOR TABLE IN DBTable.java
	 * FOR YOUR APPLICATION NEED
	 */

	@Override
	public void onCreate(SQLiteDatabase database) {
		DBTable.onCreate(database);
	}

	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		DBTable.onUpgrade(database, oldVersion, newVersion);
	}

	public Cursor executeQuery(String sql) {
		Cursor mCursor = db.rawQuery(sql, null);
		return mCursor;
	}

	public boolean executeDMLQuery(String sql) {
		try {
			db.execSQL(sql);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("abcd",":executeDMLQuery:e"+e.toString());
			return false;
		}
	}

	// will return a singleton object of this class will as well open the
	// connection for convenient

	public static DBHelper getInstance(Context context) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(context);
			openConnecion();
		}
		return dbHelper;
	}

	// will be called only once when singleton is created

	private static void openConnecion() {
		if (db == null) {
			db = dbHelper.getWritableDatabase();
		}
	}


//	public synchronized void closeConnecion() {
//		if (dbHelper != null) {
//			dbHelper.close();
//			db.close();
//			dbHelper = null;
//			db = null;
//		}
//	}
}
