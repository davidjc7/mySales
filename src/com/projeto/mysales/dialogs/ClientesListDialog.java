package com.projeto.mysales.dialogs;

import java.util.Date;
import java.util.List;

import com.projeto.mysales.activitys.NovaVenda;
import com.projeto.mysales.daos.ClienteDAO;
import com.projeto.mysales.daos.VendaDAO;
import com.projeto.mysales.model.Cliente;
import com.projeto.mysales.model.Venda;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ClientesListDialog extends DialogFragment implements DialogInterface.OnClickListener {

	ArrayAdapter<Cliente> adapterCliente;	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		adapterCliente  = new ArrayAdapter<Cliente>(getActivity(), android.R.layout.simple_expandable_list_item_1, getClientes());
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		
		builder.setTitle("Selecione um Cliente");
		builder.setAdapter(adapterCliente, this);
		
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		VendaDAO dao = VendaDAO.getInstance(getActivity());
		Cliente cliente = adapterCliente.getItem(which);
		Toast.makeText(getActivity(), "Cliente selecionado: " + cliente.getNome(), Toast.LENGTH_LONG).show();
		Venda v = new Venda(new Date());
		v.setClienteId(cliente.getId());
		v = dao.gravarRetorno(v);	
		Intent i = new Intent(getActivity(), NovaVenda.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("venda", v);
		startActivity(i);
	}
	
	private List<Cliente> getClientes() {
		ClienteDAO dao = ClienteDAO.getInstance(getActivity());
		return dao.listar();
	}

	
}
