package com.projeto.mysales.adaptadores;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.projeto.mysales.daos.ProdutoDAO;
import com.projeto.mysales.model.ItemVenda;
import com.projeto.mysales.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemVendaAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<ItemVenda> lista = new ArrayList<ItemVenda>();
	Context context;

	public ItemVendaAdapter(List<ItemVenda> entidade, Context context) {
		this.lista = entidade;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void addItem(ItemVenda entidade) {
		lista.add(entidade);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		try{
			return lista.size();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		View v = inflater.inflate(R.layout.item_venda_item, null);
		ItemVenda entidade = lista.get(position);
		ProdutoDAO pDao = ProdutoDAO.getInstance(context);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		TextView item1 = (TextView) v.findViewById(R.id.item1);
		item1.setText(pDao.getForId(entidade.getProdutoId()).getDescricao().toString());
		item1.setPadding(0, 10, 0, 10);

		TextView item2 = (TextView) v.findViewById(R.id.item2);
		item2.setText(entidade.getQuantidade().toString());
		item2.setPadding(0, 10, 0, 10);
		
		TextView item3 = (TextView) v.findViewById(R.id.item3);
		item3.setText(nf.format(entidade.getPreco()));
		item3.setPadding(0, 10, 0, 10);
		
		TextView item4 = (TextView) v.findViewById(R.id.item4);
		item4.setText(nf.format(entidade.getPreco() * entidade.getQuantidade()));
		item4.setPadding(0, 10, 0, 10);
		
		if ((position % 2) == 0) {
			v.setBackgroundColor(Color.argb(50, 100, 100, 100));
		}

		return v;
	}

}
