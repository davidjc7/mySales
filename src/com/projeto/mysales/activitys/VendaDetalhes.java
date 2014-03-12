package com.projeto.mysales.activitys;

import com.projeto.mysales.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VendaDetalhes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.venda_detalhes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venda_detalhes, menu);
		return true;
	}

}
