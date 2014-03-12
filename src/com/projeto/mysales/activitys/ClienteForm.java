package com.projeto.mysales.activitys;

import com.projeto.mysales.R;
import com.projeto.mysales.daos.ClienteDAO;
import com.projeto.mysales.model.Cliente;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteForm extends Activity {
	
	Cliente cliente;
	EditText nome;
	EditText telefone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cliente_form);
		
		nome = (EditText) findViewById(R.id.clienteNome);
		telefone = (EditText) findViewById(R.id.clienteTelefone);
		
		cliente = (Cliente) getIntent().getSerializableExtra("cliente");
		if(cliente != null){
			nome.setText(cliente.getNome());
			telefone.setText(cliente.getTelefone());
		}

	}
		
	public void salvarCliente() {
		Cliente c = new Cliente();
		c.setNome(nome.getText().toString());
		c.setTelefone(telefone.getText().toString());
		
		ClienteDAO dao = ClienteDAO.getInstance(this);
		if(dao.gravar(c)){
			Toast.makeText(this, "Cliente cadastrado com sucesso!",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(), Clientes.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}else{
			Toast.makeText(this, "Erro ao tentar cadastrar cliente!",
					Toast.LENGTH_LONG).show();
		}
	}
	
	
	public void atualizarCliente() {

		cliente.setNome(nome.getText().toString());
		cliente.setTelefone(telefone.getText().toString());

		ClienteDAO dao = ClienteDAO
				.getInstance(this);
		if (dao.atualizar(cliente)) {
			Toast.makeText(this, "Cliente atualizado com sucesso!",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(), Clientes.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		} else {
			Toast.makeText(this, "Erro ao tentar atualizar cliente!",
					Toast.LENGTH_LONG).show();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.item_salvar){
			if(cliente != null){
				this.atualizarCliente();
			}else{
				this.salvarCliente();
			}
			return true;
		}else if(id == R.id.item_home){
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}else if(id == R.id.item_cancelar){
			Intent i = new Intent(getApplicationContext(), Clientes.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}else{
			return false;
		}
	}

	

}
