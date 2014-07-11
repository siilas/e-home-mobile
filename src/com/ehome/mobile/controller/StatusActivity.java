package com.ehome.mobile.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.ehome.mobile.R;
import com.ehome.mobile.adapter.CustomAdapter;
import com.ehome.mobile.bo.StatusBO;
import com.ehome.mobile.list.StatusList;
import com.ehome.mobile.model.Acao;
import com.ehome.mobile.model.Login;
import com.ehome.mobile.utils.Constants;
import com.ehome.mobile.utils.Functions;

/**
 * Classe responsável por receber as ações que o usuário logado
 * pode realizar, e enviar uma ação via web service
 * 
 * @author Silas M. Ferreira
 *
 */
public class StatusActivity extends Activity {

	private StatusBO statusBO;
	private Login login;
	private StatusList statusList;
	private Acao acao = new Acao();
	private String description;
	private ExpandableListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		statusBO = new StatusBO(getApplicationContext());
		
		Intent intent = getIntent();
		login = (Login) intent.getSerializableExtra("login");
		
		if ((login == null) || (login.isEmpty())) {
			intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
		getStatus(new View(getApplicationContext()));
		
		list = (ExpandableListView) findViewById(R.id.list);
		
		list.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
				acao = statusList.get(groupPosition).getAcoes().get(childPosition);
				description = statusList.get(groupPosition).toString();
				registerForContextMenu(view);
				openContextMenu(view);
				
				return true;
            }
		});
		
		list.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_status, menu);
		return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {		
		menu.setHeaderTitle(acao.getAcaoDescricao());
		menu.setHeaderIcon(Functions.getIcon(description));
		
		if ((description.equals(Constants.DOOR)) || (description.equals(Constants.WINDOW))) {
			menu.add(Menu.NONE, Constants.ON, Menu.NONE, R.string.abrir);
			menu.add(Menu.NONE, Constants.OFF, Menu.NONE, R.string.fechar);
		} else if (description.equals(Constants.SENSOR)) {
			menu.add(Menu.NONE, Constants.READ, Menu.NONE, R.string.ler);
		} else {
			menu.add(Menu.NONE, Constants.ON, Menu.NONE, R.string.ligar);
			menu.add(Menu.NONE, Constants.OFF, Menu.NONE, R.string.desligar);
		}
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
			case R.id.menu_configurations:
				intent = new Intent(getApplicationContext(), ConfigurationsActivity.class);
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
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem menuItem) {
		if (description.equals(Constants.SENSOR)) {
			Integer leitura = statusBO.setStatusRead(acao, login.getId());
			
			if ((leitura != null) && (!leitura.equals(-1))) {
				Functions.showMessageText(getApplicationContext(), "Leitura: " + leitura);
			} else {
				Functions.showMessage(getApplicationContext(), R.string.read_error);
			}
		} else {
			boolean statusChanged = statusBO.setStatusWrite(acao, menuItem.getItemId(), login.getId());	
			
			if (statusChanged) {
				Functions.showMessage(getApplicationContext(), R.string.status_changed);
			} else {
				Functions.showMessage(getApplicationContext(), R.string.status_not_changed);
			}
		}
		getStatus(new View(getApplicationContext()));
		
		return super.onContextItemSelected(menuItem);
	}
	
	public void getStatus(View view) {
		statusList = statusBO.getStatus(login.getId());
		
		if ((statusList != null) && (statusList.size() > 0)) {
			list = (ExpandableListView) findViewById(R.id.list);			
			CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_menu, statusList);
			list.setAdapter(customAdapter);
		} else {
			Functions.showMessage(getApplicationContext(), R.string.status_error);
		}
	}
}