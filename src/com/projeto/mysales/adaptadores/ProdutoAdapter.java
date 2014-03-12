package com.projeto.mysales.adaptadores;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.projeto.mysales.model.Produto;
import com.projeto.mysales.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProdutoAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Produto> list_produtos = new ArrayList<Produto>();

	public ProdutoAdapter(List<Produto> produtos, Context context) {
		this.list_produtos = produtos;
		inflater = LayoutInflater.from(context);
	}

	public void addItem(Produto p) {
		list_produtos.add(p);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		try{
			return list_produtos.size();
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return list_produtos.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		View v = inflater.inflate(R.layout.produto_item, null);
		Produto p = list_produtos.get(position);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		TextView item1 = (TextView) v.findViewById(R.id.p_item1);
		item1.setText(p.getCodigo().toString());
		item1.setPadding(0, 10, 0, 10);

		TextView item2 = (TextView) v.findViewById(R.id.p_item2);
		item2.setText(p.getDescricao().toString());
		item2.setPadding(0, 10, 0, 10);
		
		TextView item3 = (TextView) v.findViewById(R.id.p_item3);
		item3.setText(p.getEstoque().toString());
		item3.setPadding(0, 10, 0, 10);
		
		TextView item4 = (TextView) v.findViewById(R.id.p_item4);
		item4.setText(nf.format(p.getPrecoVenda()));
		item4.setPadding(0, 10, 0, 10);
		
		if ((position % 2) == 0) {
			v.setBackgroundColor(Color.argb(50, 100, 100, 100));
		}

		return v;
	}

}
