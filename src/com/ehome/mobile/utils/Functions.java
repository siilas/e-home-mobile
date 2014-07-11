package com.ehome.mobile.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.ehome.mobile.R;

import android.content.Context;
import android.widget.Toast;

/**
 * Classe que contém as funções gerais da aplicação
 * 
 * @author Silas M. Ferreira
 * 
 */
public class Functions {

	public String nulo(String texto) {
		try {
			if (texto != null) {
				if (texto.trim().equalsIgnoreCase("NULL")) {
                    return "";
                } else {
                    return texto.trim();
                }
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	public Integer nuloZero(String number) {
		try {
			if (number != null) {
				if (number.trim().equalsIgnoreCase("NULL")) {
					return 0;
				} else {
					return Integer.parseInt(number);
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	
	public String encriptarSenha(String senha) {		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");			
			BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes())); 	        
			return hash.toString(16);			
		} catch (Exception e) {
			return "";
		}
	}
	
	public static void showMessage(Context context, int message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
	
	public static void showMessageText(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
	
	public static int getIcon(String description) {
		int icon = 0;
		
		if (description.equals(Constants.DOOR)) {
			icon = R.drawable.ic_door;
		} else if (description.equals(Constants.LAMP)) {
			icon = R.drawable.ic_lamp;
		} else if (description.equals(Constants.SENSOR)) {
			icon = R.drawable.ic_sensor;
		} else if (description.equals(Constants.VENTILATOR)) {
			icon = R.drawable.ic_ventilator;
		} else if (description.equals(Constants.WINDOW)) {
			icon = R.drawable.ic_window;
		} else if (description.equals(Constants.SECURITY)) {
			icon = R.drawable.ic_security;
		} else {
			icon = R.drawable.ic_launcher;
		}
		
		return icon;
	}
}