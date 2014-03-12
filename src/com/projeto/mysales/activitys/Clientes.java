package com.projeto.mysales.activitys;

import java.util.List;

import com.projeto.mysales.R;
import com.projeto.mysales.adaptadores.ClienteAdapter;
import com.projeto.mysales.daos.ClienteDAO;
import com.projeto.mysales.model.Cliente;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class Clientes extends Activity {
	
	ListView lView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clientes);
		
		ClienteAdapter adapter = new ClienteAdapter(getClientes(), this);
		lView = (ListView) findViewById(R.id.lvClientes);
		View v = LayoutInflater.from(this).inflate(R.layout.cliente_item, null);
		
		TextView tvCod = (TextView) v.findViewById(R.id.item1);
		tvCod.setText("NOME");
		tvCod.setTextColor(Color.WHITE);
		tvCod.setPadding(0, 10, 0, 10);

		TextView tvNome = (TextView) v.findViewById(R.id.item2);
		tvNome.setText("TELEFONE");
		tvNome.setTextColor(Color.WHITE);
		tvNome.setPadding(0, 10, 0, 10);
		
		v.setBackgroundColor(Color.rgb(0, 20, 100));
		
		lView.addHeaderView(v);
		
		lView.setAdapter(adapter);
		
		lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Cliente entidade = (Cliente) adapter.getItemAtPosition(position);
				Intent i = new Intent(Clientes.this, ClienteForm.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("cliente", entidade);
				startActivity(i);
				Toast.makeText(Clientes.this, "Nome: " + entidade.getNome(), Toast.LENGTH_LONG).show();
				
			}
		});
		
		lView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Cliente c = (Cliente) adapter.getItemAtPosition(position);
				excluir(c);
				return true;
			}
		});	
	}
	
	
	private void excluir(final Cliente entidade) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    alertDialogBuilder.setTitle("Atenção!");
	 
	    alertDialogBuilder.setMessage("Deseja realmente excluir o(a) cliente: "+entidade.getNome().toString()+"?")
	    .setCancelable(false).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	           
	    	public void onClick(DialogInterface dialog, int id) {
	                ClienteDAO dao = ClienteDAO.getInstance(Clientes.this);
	                
	        		if(dao.excluir(entidade.getId())){
	        			Toast.makeText(Clientes.this, "Cliente excluido com sucesso!", Toast.LENGTH_LONG).show();
	        			ClienteAdapter adapter = new ClienteAdapter(getClientes(), Clientes.this);
	        			lView.setAdapter(adapter);      			
	        		}else{
	        			Toast.makeText(Clientes.this, "Erro ao tentar excluir cliente!", Toast.LENGTH_LONG).show();
	        		}
	            }
	        })
	        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	            }
	        });
	 
	    AlertDialog alertDialog = alertDialogBuilder.create();
	    alertDialog.show();		
	}
	
	
	private List<Cliente> getClientes() {
		ClienteDAO dao = ClienteDAO.getInstance(this);
		return dao.listar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entidades, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.item_novo){
			startActivity(new Intent(this, ClienteForm.class));
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
