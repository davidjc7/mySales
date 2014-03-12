package com.projeto.mysales.activitys;

import com.projeto.mysales.R;
import com.projeto.mysales.daos.ProdutoDAO;
import com.projeto.mysales.model.Produto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ProdutoForm extends Activity {
	
	private Produto produto;
	private EditText codigo;
	private EditText descricao;
	private EditText estoque;
	private EditText preco;
	private EditText precoVenda;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produto_form);
		
		codigo = (EditText) findViewById(R.id.produtoCodigo);
		descricao = (EditText) findViewById(R.id.produtoDescricao);
		estoque = (EditText) findViewById(R.id.produtoEstoque);
		preco = (EditText) findViewById(R.id.produtoPreco);
		precoVenda = (EditText) findViewById(R.id.produtoPrecoVenda);
		
		produto = (Produto) getIntent().getSerializableExtra("produto");
		
		if(produto != null){
			codigo.setText(produto.getCodigo());
			descricao.setText(produto.getDescricao());
			estoque.setText(String.valueOf(produto.getEstoque()));
			preco.setText(String.valueOf(produto.getPreco()));
			precoVenda.setText(String.valueOf(produto.getPrecoVenda()));
		}
		
	}
	
	
	public void salvarProduto() {

		Produto p = new Produto();
		p.setCodigo(codigo.getText().toString());
		p.setDescricao(descricao.getText().toString());
		p.setEstoque(Integer.parseInt(estoque.getText().toString()));
		p.setPreco(Double.parseDouble(preco.getText().toString()));
		p.setPrecoVenda(Double.parseDouble(precoVenda.getText().toString()));

		ProdutoDAO dao = ProdutoDAO.getInstance(this);
		if (dao.gravar(p)) {
			Toast.makeText(this, "Produto cadastrado com sucesso!",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(), Produtos.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		} else {
			Toast.makeText(this, "Erro ao tentar cadastrar o produto!",
					Toast.LENGTH_LONG).show();
		}
	}
	
	
	public void atualizarProduto() {

		produto.setCodigo(codigo.getText().toString());
		produto.setDescricao(descricao.getText().toString());
		produto.setEstoque(Integer.parseInt(estoque.getText().toString()));
		produto.setPreco(Double.parseDouble(preco.getText().toString()));
		produto.setPrecoVenda(Double.parseDouble(precoVenda.getText().toString()));

		ProdutoDAO dao = ProdutoDAO.getInstance(this);
		if (dao.atualizar(produto)) {
			Toast.makeText(this, "Produto atualizado com sucesso!",
					Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(), Produtos.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		} else {
			Toast.makeText(this, "Erro ao tentar atualizar produto!",
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
			if(produto != null){
				this.atualizarProduto();
			}else{
				this.salvarProduto();
			}
			return true;
		}else if(id == R.id.item_home){
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}else if(id == R.id.item_cancelar){
			Intent i = new Intent(getApplicationContext(), Produtos.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}else{
			return false;
		}
	}

}
