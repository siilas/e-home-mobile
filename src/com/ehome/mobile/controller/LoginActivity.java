package com.ehome.mobile.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.ehome.mobile.R;
import com.ehome.mobile.bo.LoginBO;
import com.ehome.mobile.model.Login;
import com.ehome.mobile.utils.Functions;

/**
 * Classe responsável pelo login de usuários
 * 
 * @author Silas M. Ferreira
 *
 */
public class LoginActivity extends Activity {

	private LoginBO loginBO;	
	private Login login = new Login();
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		progressDialog = new ProgressDialog(this);
		
		loginBO = new LoginBO(getApplicationContext());		
		login = loginBO.search();
						
		if (!login.isEmpty()) {
			boolean verified = loginBO.verifyLogin(login);
			
			if (verified) {
				Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
				intent.putExtra("login", login);
				startActivity(intent);
				finish();
			} else {
				loginBO.remove(login);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_login, menu);		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;		
		
		switch (item.getItemId()) {
			case R.id.menu_configurations:
				intent = new Intent(getApplicationContext(), ConfigurationsActivity.class);
				intent.putExtra("login", login);
				startActivity(intent);
				finish();
				break;
			case R.id.menu_exit:
				this.onDestroy();
				exit();
				break;
		}		
		return true;
	}
	
	@Override
	public void onDestroy() {
		loginBO.close();
		super.onDestroy();
	}

	private void exit() {
		System.exit(0);
	}
	
	public void login(View view) {		
		EditText username = (EditText) findViewById(R.id.user_input);
		EditText password = (EditText) findViewById(R.id.password_input);
		
		login.setUsername(username.getText().toString());
		login.setPassword(password.getText().toString());
		
		if (!login.isEmpty()) {
			progressDialog.setTitle(R.string.loading);
			progressDialog.setMessage("Aguarde...");
			progressDialog.setCancelable(false);
			progressDialog.setIndeterminate(true);
			progressDialog.show();
			
			boolean verified = loginBO.verifyLogin(login);
			
			if (verified) {
				if (login.getId().equals(0)) {
					loginBO.save(login);
				}
	
				Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
				intent.putExtra("login", login);
				startActivity(intent);
				finish();
			} else {
				login = new Login();
				username.setText("");
				password.setText("");			
				username.requestFocus();				
				
				Functions.showMessage(getApplicationContext(), R.string.login_error);
			}
			
			progressDialog.dismiss();
		} else {
			Functions.showMessage(getApplicationContext(), R.string.login_vazio);
		}
	}
}