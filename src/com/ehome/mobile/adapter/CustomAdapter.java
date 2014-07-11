package com.ehome.mobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehome.mobile.R;
import com.ehome.mobile.model.AcaoGrupo;
import com.ehome.mobile.utils.Functions;

public class CustomAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<AcaoGrupo> list;
	
	public CustomAdapter(Context context, int resource, List<AcaoGrupo> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition).getAcoes().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View view, ViewGroup parent) {		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.list_item, null);
	
		TextView label = (TextView) view.findViewById(R.id.listItem);
		label.setText(list.get(groupPosition).getAcoes().get(childPosition).toString());
		
		ImageView icon = (ImageView) view.findViewById(R.id.itemLogo);
		int iconId = Functions.getIcon(list.get(groupPosition).toString());
		icon.setImageResource(iconId);		
	
		view.setFocusable(false);
		parent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).getAcoes().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int position, boolean isExpanded, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.list_menu, null);
		
		TextView label = (TextView) view.findViewById(R.id.label);
		ImageView icon = (ImageView) view.findViewById(R.id.logo);
		
		label.setText(list.get(position).toString());
		
		AcaoGrupo acaoGrupo = (AcaoGrupo) list.get(position);		
		String description = acaoGrupo.getAgrNome();
		
		int iconId = Functions.getIcon(description);
		icon.setImageResource(iconId);
		
		view.setFocusable(false);
		parent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}