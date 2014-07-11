package com.ehome.mobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ehome.mobile.model.Login;
import com.ehome.mobile.utils.Constants;
import com.ehome.mobile.utils.Database;
import com.ehome.mobile.utils.Functions;

/** 
 * Classe que gerencia os dados da tabela LOGIN
 *
 * @author Silas M. Ferreira
 * 
 */
public class LoginDAO {
	
	private Functions functions = new Functions();	
	private Database database;	
	private String[] columns = { Constants.COLUMN_ID, Constants.COLUMN_USERNAME, Constants.COLUMN_PASSWORD };
	
	public LoginDAO(Context context) throws Exception {		
		this.database = new Database(context);
	}
	
	public boolean save(Login login) throws Exception {		
		ContentValues values = new ContentValues();
		
		values.put(Constants.COLUMN_USERNAME, login.getUsername());
		values.put(Constants.COLUMN_PASSWORD, login.getPassword());
		
		Long idInserted = this.database.insert(Constants.LOGIN_TABLE, null, values);
		return ((idInserted.equals(-1)) ? false : true);
	}
	
	public boolean update(Login login) throws Exception {		
		ContentValues values = new ContentValues();
		
		values.put(Constants.COLUMN_USERNAME, login.getUsername());
		values.put(Constants.COLUMN_PASSWORD, login.getPassword());
		
		Integer updated = this.database.update(Constants.LOGIN_TABLE, values, Constants.COLUMN_ID + " = " + login.getId());
		return ((updated > 0) ? true : false);
	}
	
	public boolean remove(Login login) throws Exception {		
		Integer deleted = this.database.delete(Constants.LOGIN_TABLE, Constants.COLUMN_ID + " = " + login.getId());
		return ((deleted > 0) ? true : false);
	}
	
	public Login search() throws Exception {		
		Login login = new Login();
		
		Cursor cursor = database.query(Constants.LOGIN_TABLE, columns);
		
		if (cursor.moveToFirst()) {
			login.setId(cursor.getLong(0));
			login.setUsername(functions.nulo(cursor.getString(1)));
			login.setPassword(functions.nulo(cursor.getString(2)));
		}
		
		cursor.close();
		return login;
	}
	
	public void close() throws Exception {
		if (this.database != null) {
			this.database.close();
		}
	}
}