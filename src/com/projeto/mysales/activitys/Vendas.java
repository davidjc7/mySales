package com.projeto.mysales.activitys;

import java.util.List;

import com.projeto.mysales.R;
import com.projeto.mysales.adaptadores.VendaAdapter;
import com.projeto.mysales.daos.VendaDAO;
import com.projeto.mysales.dialogs.ClientesListDialog;
import com.projeto.mysales.model.Venda;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Vendas extends Activity {
	
	ListView lView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vendas);
		
		VendaAdapter adapter = new VendaAdapter(getVendas(), this);
		lView = (ListView) findViewById(R.id.lvVendas);
		View v = LayoutInflater.from(this).inflate(R.layout.venda_item, null);
		
		TextView tvCod = (TextView) v.findViewById(R.id.item1);
		tvCod.setText("DATA");
		tvCod.setTextColor(Color.WHITE);
		tvCod.setPadding(0, 10, 0, 10);

		TextView tvNome = (TextView) v.findViewById(R.id.item2);
		tvNome.setText("CLIENTE");
		tvNome.setTextColor(Color.WHITE);
		tvNome.setPadding(0, 10, 0, 10);
		
		TextView tvTotal = (TextView) v.findViewById(R.id.item3);
		tvTotal.setText("TOTAL");
		tvTotal.setTextColor(Color.WHITE);
		tvTotal.setPadding(0, 10, 0, 10);	
	
		v.setBackgroundColor(Color.rgb(0, 20, 100));
		
		lView.addHeaderView(v);	
		lView.setAdapter(adapter);	
	}
	
	private List<Venda> getVendas() {
		VendaDAO dao = VendaDAO.getInstance(this);
		return dao.listar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.entidades, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.item_novo){
			
			FragmentManager fm = getFragmentManager();
			ClientesListDialog cld = new ClientesListDialog();
			cld.show(fm, "dialogClientes");
			
			return true;
			
		}else if(id == R.id.item_voltar){
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}else{
			return false;
		}
	}

}
