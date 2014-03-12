package com.projeto.mysales.activitys;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.projeto.mysales.R;
import com.projeto.mysales.adaptadores.ItemVendaAdapter;
import com.projeto.mysales.daos.ItemVendaDAO;
import com.projeto.mysales.daos.ProdutoDAO;
import com.projeto.mysales.daos.VendaDAO;
import com.projeto.mysales.model.ItemVenda;
import com.projeto.mysales.model.Produto;
import com.projeto.mysales.model.Venda;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NovaVenda extends Activity {

	private AutoCompleteTextView autoProduto;
	private Produto produto;
	private Venda venda;
	private ListView lView;
	private EditText quantidade;
	private EditText preco;
	private TextView mostraTotal;
	private Double total = 0.0;
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putDouble("total", total);
		outState.putSerializable("venda", venda);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.venda_form);

		quantidade = (EditText) findViewById(R.id.quantidade);
		preco = (EditText) findViewById(R.id.preco);
		mostraTotal = (TextView) findViewById(R.id.total);

		venda = (Venda) getIntent().getSerializableExtra("venda");

		if (savedInstanceState != null) {
			venda = (Venda) savedInstanceState.get("venda");
			total = (Double) savedInstanceState.get("total");

			mostraTotal.setText(nf.format(total));
		}

		ItemVendaAdapter itemAdapter = new ItemVendaAdapter(getItensVenda(),
				this);
		lView = (ListView) findViewById(R.id.lista);

		View v = LayoutInflater.from(this).inflate(R.layout.item_venda_item,
				null);

		TextView item1 = (TextView) v.findViewById(R.id.item1);
		item1.setText("DESCRIÇÃO");
		item1.setTextColor(Color.WHITE);
		item1.setPadding(0, 2, 0, 2);

		TextView item2 = (TextView) v.findViewById(R.id.item2);
		item2.setText("QTDE");
		item2.setTextColor(Color.WHITE);
		item2.setPadding(0, 2, 0, 2);

		TextView item3 = (TextView) v.findViewById(R.id.item3);
		item3.setText("UNIT");
		item3.setTextColor(Color.WHITE);
		item3.setPadding(0, 2, 0, 2);
		
		TextView item4 = (TextView) v.findViewById(R.id.item4);
		item4.setText("TOTAL");
		item4.setTextColor(Color.WHITE);
		item4.setPadding(0, 2, 0, 2);

		v.setBackgroundColor(Color.BLACK);

		lView.addHeaderView(v);

		lView.setAdapter(itemAdapter);

		lView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				ItemVenda item = (ItemVenda) adapter
						.getItemAtPosition(position);
				excluirItem(item);
				return true;
			}
		});

		autoProduto = (AutoCompleteTextView) findViewById(R.id.autoProduto);
		ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,
				android.R.layout.simple_expandable_list_item_1, getProdutos());
		autoProduto.setThreshold(1);
		autoProduto.setAdapter(adapter);
		autoProduto.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				produto = (Produto) arg0.getAdapter().getItem(arg2);
				preco.setText(String.valueOf(produto.getPrecoVenda()));
				quantidade.setText("1");
			}
		});
	}

	public void incluirItem(View v) {
		double prec = Double.parseDouble(preco.getText().toString());

		if (produto.getPreco() <= prec) {

			ItemVendaDAO dao = ItemVendaDAO.getInstance(this);
			ItemVenda iv = new ItemVenda();
			int qtde = Integer.parseInt(quantidade.getText().toString());

			iv.setProdutoId(produto.getId());
			iv.setVendaId(venda.getId());
			iv.setQuantidade(qtde);
			iv.setPreco(prec);
			if (dao.gravar(iv)) {
				Toast.makeText(this, "Item Incluido! ", Toast.LENGTH_SHORT)
						.show();

				total = total + prec * qtde;
				venda.setTotal(total);

				int i = produto.getEstoque() - qtde;
				produto.setEstoque(i);
				atualizarEstoque(produto);

				autoProduto.setText("");
				preco.setText("");
				quantidade.setText("");
				atualizaListItemVenda();
			} else {
				Toast.makeText(this, "Erro ao tentar incluir Item de Venda!",
						Toast.LENGTH_LONG).show();
			}

		} else {
			Toast.makeText(this, "ERRO: Valor Inválido!", Toast.LENGTH_LONG).show();
		}
	}

	private void atualizarEstoque(Produto p) {
		ProdutoDAO dao = ProdutoDAO.getInstance(this);
		dao.atualizar(p);

	}

	private void excluirItem(final ItemVenda item) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Atenção!");

		alertDialogBuilder
				.setMessage("Deseja realmente excluir este item?")
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								ItemVendaDAO dao = ItemVendaDAO
										.getInstance(NovaVenda.this);
								int qtde = item.getQuantidade();
								double prec = item.getPreco();
								int produtoId = item.getProdutoId();

								if (dao.excluir(item.getId())) {
									Toast.makeText(NovaVenda.this,
											"Item excluido!",
											Toast.LENGTH_SHORT).show();
									ProdutoDAO pdao = ProdutoDAO
											.getInstance(NovaVenda.this);
									Produto prod = pdao.getForId(produtoId);
									int i = prod.getEstoque() + qtde;
									prod.setEstoque(i);
									atualizarEstoque(prod);

									total = total - qtde * prec;
									venda.setTotal(total);

									atualizaListItemVenda();
								} else {
									Toast.makeText(NovaVenda.this,
											"Erro ao tentar excluir o item!",
											Toast.LENGTH_LONG).show();
								}
							}
						})
				.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	private void atualizaListItemVenda() {
		ItemVendaAdapter adapter = new ItemVendaAdapter(getItensVenda(), this);
		lView.setAdapter(adapter);

		mostraTotal.setText(nf.format(total));
	}

	private List<Produto> getProdutos() {
		ProdutoDAO produtoDAO = ProdutoDAO.getInstance(this);
		return produtoDAO.listar();
	}

	private List<ItemVenda> getItensVenda() {
		ItemVendaDAO dao = ItemVendaDAO.getInstance(this);
		return dao.listar(venda.getId());
	}

	private void finalizarVenda() {
		VendaDAO dao = VendaDAO.getInstance(this);
		dao.atualizar(venda);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.item_salvar) {
			finalizarVenda();
			Intent i = new Intent(getApplicationContext(), Vendas.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		} else if (id == R.id.item_home) {
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		} else if (id == R.id.item_cancelar) {
			Intent i = new Intent(getApplicationContext(), Vendas.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		} else {
			return false;
		}
	}

}
