package com.ehome.mobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ehome.mobile.model.Configurations;
import com.ehome.mobile.utils.Constants;
import com.ehome.mobile.utils.Database;
import com.ehome.mobile.utils.Functions;

/**
 * Classe que gerencia os dados da tabela CONFIGURATIONS
 * 
 * @author Silas M. Ferreira
 *
 */
public class ConfigurationsDAO {
	
	private Functions functions = new Functions();
	private Database database;
	private String[] columns = { Constants.COLUMN_ID, Constants.COLUMN_USERNAME, Constants.COLUMN_EMAIL, Constants.COLUMN_URL };
	
	public ConfigurationsDAO(Context context) throws Exception {
		database = new Database(context);
	}
	
	public boolean save(Configurations configurations) throws Exception {
		ContentValues values = new ContentValues();
		
		values.put(Constants.COLUMN_USERNAME, configurations.getUsername());
		values.put(Constants.COLUMN_EMAIL, configurations.getEmail());
		values.put(Constants.COLUMN_URL, configurations.getUrl());
		
		Long idInserted = this.database.insert(Constants.CONFIGURATIONS_TABLE, null, values);
		return ((idInserted.equals(-1)) ? false : true);
	}
	
	public boolean update(Configurations configurations) throws Exception {
		ContentValues values = new ContentValues();
		
		values.put(Constants.COLUMN_USERNAME, configurations.getUsername());
		values.put(Constants.COLUMN_EMAIL, configurations.getEmail());
		values.put(Constants.COLUMN_URL, configurations.getUrl());
		
		Integer updated = this.database.update(Constants.CONFIGURATIONS_TABLE, values, Constants.COLUMN_ID + " = " + configurations.getId());
		return ((updated > 0) ? true : false);
	}
	
	public boolean remove(Configurations configurations) throws Exception {
		Integer deleted = this.database.delete(Constants.CONFIGURATIONS_TABLE, Constants.COLUMN_ID + " = " + configurations.getId());
		return ((deleted > 0) ? true : false);
	}
	
	public Configurations search() throws Exception {
		Configurations configurations = new Configurations();
		Cursor cursor = database.query(Constants.CONFIGURATIONS_TABLE, columns);
							
		if (cursor.moveToFirst()) {
			configurations.setId(cursor.getInt(0));
			configurations.setUsername(functions.nulo(cursor.getString(1)));
			configurations.setEmail(functions.nulo(cursor.getString(2)));
			configurations.setUrl(functions.nulo(cursor.getString(3)));
		}
		
		cursor.close();
		return configurations;
	}
	
	public void close() throws Exception {
		if (this.database != null) {
			this.database.close();
		}
	}
}