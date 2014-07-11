package com.ehome.mobile.bo;

import org.apache.log4j.Logger;

import android.content.Context;

import com.ehome.mobile.dao.LoginDAO;
import com.ehome.mobile.model.Configurations;
import com.ehome.mobile.model.Login;
import com.ehome.mobile.utils.Constants;
import com.ehome.mobile.utils.RestClient;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Contém as regras da aplicação para o login
 * 
 * @author Silas M. Ferreira
 * 
 */
public class LoginBO {

	private Logger logger = Logger.getLogger(LoginBO.class);
	private ConfigurationsBO configurationsBO;
	private LoginDAO loginDAO;
	
	public LoginBO(Context context) {
		try {
			loginDAO = new LoginDAO(context);
			configurationsBO = new ConfigurationsBO(context);
		} catch (Exception e) {
			logger.error("LoginBO(Context context)", e);
		}
	}
	
	public boolean save(Login login) {
		boolean saved = false;
		try {
			saved = loginDAO.save(login);
		} catch (Exception e) {
			logger.error("save(Login login)", e);
		}		
		return saved;
	}
	
	public boolean update(Login login) {
		boolean updated = false;
		try {
			updated = loginDAO.update(login);
		} catch (Exception e) {
			logger.error("update(Login login)", e);
		}		
		return updated;
	}
	
	public boolean remove(Login login) {
		boolean removed = false;
		try {
			removed = loginDAO.remove(login);
		} catch (Exception e) {
			logger.error("remove(Login login)", e);
		}
		return removed;
	}

	public Login search() {
		Login login = new Login();
		try {
			login = loginDAO.search();
		} catch (Exception e) {
			logger.error("search()", e);
		}
		return login;
	}
	
	public boolean verifyLogin(Login login) {
		boolean verified = false;
		try {
			if ((!login.getPassword().isEmpty()) && (!login.getUsername().isEmpty())) {
				Configurations configurations = configurationsBO.search();	
				RestClient<Object> restClient = new RestClient<Object>();
				String url = "";				
				
				if (configurations.getUrl().isEmpty()) {
					url = Constants.URL;
				} else {
					url = configurations.getUrl();
				}				
				url += Constants.METHOD_LOGIN + "/" + login.getUsername() + "/" + login.getPassword();				
				restClient.setUrl(url);
				
				Object result = restClient.sendGet(Object.class);				
				if (result != null) {
					objectToLogin(result, login);
					verified = true;
				}
			}			
		} catch (Exception e) {
			logger.error("verifyLogin(Login login)", e);
		}
		return verified;
	}
	
	public void close() {
		try {
			loginDAO.close();
		} catch (Exception e) {
			logger.error("close()", e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void objectToLogin(Object object, Login login) {
		LinkedTreeMap result = (LinkedTreeMap) object;
		login.setId((Long) ((Double) result.get("usuCodigo")).longValue());
		login.setUsername((String) result.get("usuEmail"));
		login.setPassword((String) result.get("usuSenha"));
	}
}