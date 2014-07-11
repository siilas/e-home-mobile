package com.ehome.mobile.bo;

import org.apache.log4j.Logger;

import android.content.Context;

import com.ehome.mobile.list.StatusList;
import com.ehome.mobile.model.Acao;
import com.ehome.mobile.model.Configurations;
import com.ehome.mobile.utils.Constants;
import com.ehome.mobile.utils.RestClient;

/**
 * Classe com as regras de status da residência
 * 
 * @author Silas M. Ferreira
 *
 */
public class StatusBO {
	
	private Logger logger = Logger.getLogger(StatusBO.class);
	private ConfigurationsBO configurationsBO;
	
	public StatusBO(Context context) {
		try {
			configurationsBO = new ConfigurationsBO(context);
		} catch (Exception e) {
			logger.error("StatusBO(Context context)", e);
		}
	}
	
	public StatusList getStatus(Long idUsuario) {
		StatusList status = new StatusList();
		
		try {
			RestClient<StatusList> restClient = new RestClient<StatusList>();
			Configurations configurations = configurationsBO.search();
			String url = "";				
			
			if (configurations.getUrl().isEmpty()) {
				url = Constants.URL;
			} else {
				url = configurations.getUrl();
			}
			
			url += Constants.METHOD_GET_STATUS + "/" + idUsuario;				
			restClient.setUrl(url);
			
			status = restClient.sendGet(StatusList.class);
		} catch (Exception e) {
			logger.error("getStatus()", e);
		}		
		
		return status;
	}
	
	public boolean setStatusWrite(Acao acao, Integer metodo, Long usuario) {
		boolean statusChaged = false;
		
		try {
			RestClient<Boolean> restClient = new RestClient<Boolean>();
			Configurations configurations = configurationsBO.search();
			String url = "";				
			
			if (configurations.getUrl().isEmpty()) {
				url = Constants.URL;
			} else {
				url = configurations.getUrl();
			}
			
			if (metodo.equals(Constants.ON)) {
				url += Constants.METHOD_SET_STATUS_ON;
			} else if (metodo.equals(Constants.OFF)) {
				url += Constants.METHOD_SET_STATUS_OFF;
			} else {
				url += Constants.METHOD_SET_STATUS_READ;
			}
			restClient.setUrl(url + "/" + acao.getAcaoCodigo() + "/" + usuario);
			
			statusChaged = restClient.sendGet(Boolean.class);
		} catch (Exception e) {
			logger.error("setStatusWrite(Acao acao, Integer metodo, Long usuario)", e);
		}		
		
		return statusChaged;
	}
	
	public Integer setStatusRead(Acao acao, Long usuario) {
		Integer leitura = -1;
		
		try {
			RestClient<Integer> restClient = new RestClient<Integer>();
			Configurations configurations = configurationsBO.search();
			String url = "";				
			
			if (configurations.getUrl().isEmpty()) {
				url = Constants.URL;
			} else {
				url = configurations.getUrl();
			}			
			url += Constants.METHOD_SET_STATUS_READ + "/" + acao.getAcaoCodigo() + "/" + usuario;
			
			restClient.setUrl(url);
			
			leitura = restClient.sendGet(Integer.class);
		} catch (Exception e) {
			logger.error("setStatusRead(Acao acao, Long usuario)", e);
		}
		
		return leitura;
	}
}