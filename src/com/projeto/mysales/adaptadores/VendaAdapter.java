package com.projeto.mysales.adaptadores;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.projeto.mysales.daos.ClienteDAO;
import com.projeto.mysales.model.Venda;
import com.projeto.mysales.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class VendaAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Venda> lista = new ArrayList<Venda>();
	Context context;
	

	public VendaAdapter(List<Venda> entidade, Context context) {
		this.lista = entidade;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void addItem(Venda entidade) {
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
		View v = inflater.inflate(R.layout.venda_item, null);
		Venda entidade = lista.get(position);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		try{
			ClienteDAO dao = ClienteDAO.getInstance(context);
	
			TextView tvCod = (TextView) v.findViewById(R.id.item1);
			tvCod.setText(df.format(entidade.getDataVenda()));
			tvCod.setPadding(0, 10, 0, 10);
	
			TextView tvNome = (TextView) v.findViewById(R.id.item2);
			tvNome.setText(dao.getForId(entidade.getClienteId()).getNome().toString());
			tvNome.setPadding(0, 10, 0, 10);
			
			TextView tvTotal = (TextView) v.findViewById(R.id.item3);
			tvTotal.setText(nf.format(entidade.getTotal()));
			tvTotal.setPadding(0, 10, 0, 10);
		
			if ((position % 2) == 0) {
				v.setBackgroundColor(Color.argb(50, 100, 100, 100));
			}
		}catch(NullPointerException e){
			
		}


		return v;
	}

}
