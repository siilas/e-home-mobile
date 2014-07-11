package com.ehome.mobile.utils;

/**
 * Classe com as contantes da aplicação
 * 
 * @author Silas M. Ferreira
 * 
 */
public abstract class Constants {
	
	//Conexão com o Web Service
	public static final String URL = "http://192.168.0.13:8080/EHomeWeb";
	public static final String GET = "GET";
	public static final String POST = "POST";
	
	//Métodos do Web Service
	private static final String URL_REST = "/webresources/services";
	public static final String METHOD_LOGIN = URL_REST + "/usuario";
	public static final String METHOD_GET_STATUS = URL_REST + "/automacao";
	public static final String METHOD_SET_STATUS_OFF = URL_REST + "/executarAcao/writeOff";
	public static final String METHOD_SET_STATUS_ON = URL_REST + "/executarAcao/writeOn";
	public static final String METHOD_SET_STATUS_READ = URL_REST + "/executarAcao/read";
	
	//Métodos 
	public static final int ON = 1;
	public static final int OFF = 0;
	public static final int READ = 2;
	
	//Database
	public static final String DATABASE_NAME = "ehome_mobile.db";
    public static final Integer DATABASE_VERSION = 1;
    
    //Tabelas
    public static final String LOGIN_TABLE = "LOGIN";
    public static final String CONFIGURATIONS_TABLE = "CONFIGURATIONS";
    
    //Colunas
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_URL = "URL";
    public static final String COLUMN_EMAIL = "EMAIL";
    
    //Menu
    public static final Integer MENU_STATUS = 0;
    
    //E-mail
    public static final String EMAIL_USER = "emynewcar@gmail.com";
	public static final String EMAIL_PASSWORD = "mynewcar01";
	public static final String EMAIL_SERVER = "smtp.gmail.com";
	public static final String EMAIL_PORT = "587";
	
	//Ícones
	public static final String DOOR = "Portas";
	public static final String WINDOW = "Janelas";
	public static final String LAMP = "Luzes";
	public static final String SENSOR = "Sensores";
	public static final String VENTILATOR = "Ventiladores";
	public static final String SECURITY = "Segurança";
}