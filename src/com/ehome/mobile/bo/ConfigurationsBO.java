package com.ehome.mobile.bo;

import org.apache.log4j.Logger;

import android.content.Context;

import com.ehome.mobile.dao.ConfigurationsDAO;
import com.ehome.mobile.model.Configurations;

/**
 * Contém as regras da aplicação para as configurações
 * 
 * @author Silas M. Ferreira
 * 
 */
public class ConfigurationsBO {
	
	private Logger logger = Logger.getLogger(ConfigurationsBO.class);	
	private ConfigurationsDAO configurationsDAO;
	
	public ConfigurationsBO(Context context) {
		try {
			configurationsDAO = new ConfigurationsDAO(context);
		} catch (Exception e) {
			logger.error("ConfigurationsBO(Context context)");
		}
	}
	
	public boolean save(Configurations configurations) {
		boolean saved = false;
		try {
			saved = configurationsDAO.save(configurations);
		} catch (Exception e) {
			logger.error("save(Configurations configurations)", e);
		}		
		return saved;
	}
	
	public boolean update(Configurations configurations) {
		boolean updated = false;
		try {
			updated = configurationsDAO.update(configurations);
		} catch (Exception e) {
			logger.error("update(Configurations configurations)", e);
		}		
		return updated;
	}
	
	public boolean remove(Configurations configurations) {
		boolean removed = false;
		try {
			removed = configurationsDAO.remove(configurations);
		} catch (Exception e) {
			logger.error("remove(Configurations configurations)", e);
		}
		return removed;
	}

	public Configurations search() {
		Configurations configurations = new Configurations();
		try {
			configurations = configurationsDAO.search();
		} catch (Exception e) {
			logger.error("search(Configurations configurationsFilter)", e);
		}
		return configurations;
	}
	
	public void close() {
		try {
			configurationsDAO.close();
		} catch (Exception e) {
			logger.error("close()", e);
		}
	}
}