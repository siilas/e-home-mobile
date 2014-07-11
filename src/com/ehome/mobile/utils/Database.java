package com.ehome.mobile.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe que gerencia o banco de dados
 * 
 * @author Silas M. Ferreira
 *
 */
public class Database extends SQLiteOpenHelper {
	
	private SQLiteDatabase database;
	
	public Database(Context context) {
		super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);		
		this.database = this.getWritableDatabase();
	}
	
	public SQLiteDatabase getDatabase() {		
	    return this.database;
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		StringBuilder sql = new StringBuilder("");		
		sql.append(" CREATE TABLE LOGIN ( ");
		sql.append(" 	ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(" 	USERNAME TEXT, ");
		sql.append(" 	PASSWORD TEXT ");
		sql.append(" ); ");
		
		database.execSQL(sql.toString());
		
		sql = new StringBuilder("");		
		sql.append(" CREATE TABLE CONFIGURATIONS ( ");
		sql.append(" 	ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(" 	USERNAME TEXT, ");
		sql.append(" 	EMAIL TEXT, ");
		sql.append(" 	URL TEXT ");
		sql.append(" ); ");
		
		database.execSQL(sql.toString());		
		this.database = database;
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		StringBuilder sql = new StringBuilder("");
		
		sql.append(" DROP TABLE IF EXISTS LOGIN; ");
		sql.append(" DROP TABLE IF EXISTS CONFIGURATIONS; ");
		
		database.execSQL(sql.toString());		
		onCreate(database);
	}
	
	public void executeQuery(String sql) {
		database.execSQL(sql);
	}
	
	public Long insert(String table, String columnHack, ContentValues values) {
		Long id = 0L;		
		id = database.insert(table, columnHack, values);		
		return id;
	}
	
	public Cursor query(String table, String[] columns) {
		return this.database.query(table, columns, null, null, null, null, null);		
	}
	
	public Integer delete(String table, String where) {
		return this.database.delete(table, where, null);
	}
	
	public Integer update(String table, ContentValues values, String where) {
		return this.database.update(table, values, where, null);
	}
	
	@Override
	public void close() {
		super.close();
		this.database.close();
	}
}