package com.projeto.mysales.activitys;

import java.util.List;

import com.projeto.mysales.R;
import com.projeto.mysales.adaptadores.ProdutoAdapter;
import com.projeto.mysales.daos.ProdutoDAO;
import com.projeto.mysales.model.Produto;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Produtos extends Activity {
	
	ListView lView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produtos);
		
		
		ProdutoAdapter adapter = new ProdutoAdapter(getProdutos(), this);
		lView = (ListView) findViewById(R.id.lvProdutos);
		View v = LayoutInflater.from(this).inflate(R.layout.produto_item, null);
		
		TextView item1 = (TextView) v.findViewById(R.id.p_item1);
		item1.setText("CÓDIGO");
		item1.setTextColor(Color.WHITE);
		item1.setPadding(0, 10, 0, 10);

		TextView item2 = (TextView) v.findViewById(R.id.p_item2);
		item2.setText("DESCRIÇÃO");
		item2.setTextColor(Color.WHITE);
		item2.setPadding(0, 10, 0, 10);
		
		TextView item3 = (TextView) v.findViewById(R.id.p_item3);
		item3.setText("QTDE");
		item3.setTextColor(Color.WHITE);
		item3.setPadding(0, 10, 0, 10);
		
		TextView item4 = (TextView) v.findViewById(R.id.p_item4);
		item4.setText("PREÇO");
		item4.setTextColor(Color.WHITE);
		item4.setPadding(0, 10, 0, 10);
		
		v.setBackgroundColor(Color.BLACK);
		
		lView.addHeaderView(v);
		
		lView.setAdapter(adapter);
		
		lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Produto p = (Produto) adapter.getItemAtPosition(position);
				Intent i = new Intent(Produtos.this, ProdutoForm.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("produto", p);
				startActivity(i);
				Toast.makeText(Produtos.this, "Descrição: " + p.getDescricao(), Toast.LENGTH_LONG).show();
				
			}
		});
		
		lView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Produto p = (Produto) adapter.getItemAtPosition(position);
				excluir(p);
				return true;
			}
			
		});	
		
	}
	
	
	private void excluir(final Produto entidade) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    alertDialogBuilder.setTitle("Atenção!");
	 
	    alertDialogBuilder.setMessage("Deseja realmente excluir o produto: "+entidade.getDescricao().toString()+"?")
	    .setCancelable(false).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                ProdutoDAO dao = ProdutoDAO.getInstance(Produtos.this);
	                
	        		if(dao.excluir(entidade.getId())){
	        			Toast.makeText(Produtos.this, "Produto excluido com sucesso!", Toast.LENGTH_LONG).show();
	        			ProdutoAdapter vAdapter = new ProdutoAdapter(getProdutos(), Produtos.this);
	        			lView.setAdapter(vAdapter);
	        			
	        		}else{
	        			Toast.makeText(Produtos.this, "Erro ao tentar excluir o produto!", Toast.LENGTH_LONG).show();
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
	
	
	private List<Produto> getProdutos() {
		ProdutoDAO dao = ProdutoDAO.getInstance(this);
		return dao.listar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.entidades, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.item_novo){
			startActivity(new Intent(this, ProdutoForm.class));
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
