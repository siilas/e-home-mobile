package com.ehome.mobile.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.ehome.mobile.R;
import com.ehome.mobile.bo.ConfigurationsBO;
import com.ehome.mobile.model.Configurations;
import com.ehome.mobile.model.Login;
import com.ehome.mobile.utils.Constants;
import com.ehome.mobile.utils.Functions;

/**
 * Classe que controla as configurações
 * 
 * @author Silas M. Ferreira
 *
 */
public class ConfigurationsActivity extends Activity {

	private ConfigurationsBO configurationsBO;	
	private Configurations configurations = new Configurations();
	private Login login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configurations);
		
		Intent intent = getIntent();
		login = (Login) intent.getSerializableExtra("login");
		
		configurationsBO = new ConfigurationsBO(getApplicationContext());		
		configurations = configurationsBO.search();
		
		if (!configurations.isEmpty()) {
			EditText username = (EditText) findViewById(R.id.username_input);
			EditText email = (EditText) findViewById(R.id.email_input);
			EditText url = (EditText) findViewById(R.id.url_input);

			username.setText(configurations.getUsername());
			email.setText(configurations.getEmail());
			url.setText(configurations.getUrl());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_configurations, menu);
		if ((login == null) || (login.isEmpty())) {
			menu.getItem(Constants.MENU_STATUS).setVisible(false);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		
		switch (item.getItemId()) {
			case R.id.menu_logout:
				intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.menu_status:
				intent = new Intent(getApplicationContext(), StatusActivity.class);
				intent.putExtra("login", login);
				startActivity(intent);
				finish();
				break;
		}
		return true;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		configurationsBO.close();
	}
	
	public void save(View view) {		
		EditText username = (EditText) findViewById(R.id.username_input);
		EditText email = (EditText) findViewById(R.id.email_input);
		EditText url = (EditText) findViewById(R.id.url_input);
		
		configurations.setUsername(username.getText().toString());
		configurations.setEmail(email.getText().toString());
		configurations.setUrl(url.getText().toString());
		
		boolean success = false;		
		if (configurations.getId().equals(0)) {
			success = configurationsBO.save(configurations);
		} else {
			success = configurationsBO.update(configurations);
		}
		
		int msg;		
		if (success) {
			msg = R.string.configurations_success;
		} else {
			msg = R.string.configurations_error;
		}		
		Functions.showMessage(getApplicationContext(), msg);
	}
}