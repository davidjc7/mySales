package com.projeto.mysales.activitys;

import com.projeto.mysales.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void produtos(View v){
		startActivity(new Intent(MainActivity.this, Produtos.class));
	}
	
	public void clientes(View v){
		startActivity(new Intent(MainActivity.this, Clientes.class));
	}
	
	public void vendas(View v){
		startActivity(new Intent(MainActivity.this, Vendas.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}
