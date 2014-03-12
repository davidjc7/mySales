package com.projeto.mysales.adaptadores;

import java.util.ArrayList;
import java.util.List;

import com.projeto.mysales.model.Cliente;
import com.projeto.mysales.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClienteAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Cliente> lista = new ArrayList<Cliente>();

	public ClienteAdapter(List<Cliente> entidade, Context context) {
		this.lista = entidade;
		inflater = LayoutInflater.from(context);
	}

	public void addItem(Cliente entidade) {
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
		View v = inflater.inflate(R.layout.cliente_item, null);
		Cliente entidade = lista.get(position);

		TextView tvCod = (TextView) v.findViewById(R.id.item1);
		tvCod.setText(entidade.getNome().toString());
		tvCod.setPadding(0, 10, 0, 10);

		TextView tvNome = (TextView) v.findViewById(R.id.item2);
		tvNome.setText(entidade.getTelefone().toString());
		tvNome.setPadding(0, 10, 0, 10);
		
		if ((position % 2) == 0) {
			v.setBackgroundColor(Color.argb(50, 100, 100, 100));
		}

		return v;
	}

}
