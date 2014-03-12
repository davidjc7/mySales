package com.projeto.mysales.daos;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.projeto.mysales.banco.DatabaseHelper;
import com.projeto.mysales.model.ItemVenda;

public class ItemVendaDAO {
	
	private static ItemVendaDAO instance;
	private DatabaseHelper databaseHelper;
	private Dao<ItemVenda, Integer> clienteDao;
	
	private ItemVendaDAO(Context context){
		databaseHelper = new DatabaseHelper(context);
		clienteDao = databaseHelper.getItemVendaDao();
	}
	
	public static ItemVendaDAO getInstance(Context context) {
		if(instance == null)
			instance = new ItemVendaDAO(context);
		
		return instance;
	}
	
	public boolean gravar(ItemVenda entidade){
		try{
			return clienteDao.create(entidade) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Gravar ItemVenda", e.getMessage().toString());
		}		
		return false;
	}
	
	public boolean atualizar(ItemVenda entidade){
		try{
			return clienteDao.update(entidade) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Atualizar entidade", "ERRO");
		}		
		return false;
	}
	
	public boolean excluir(int id) {
		try {
			return clienteDao.deleteById(id) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			Log.d("Excluir entidade", "ERRO");
		}	
		return false;
	}
	
	public ItemVenda getForId(Integer id){
		try{
			return clienteDao.queryForId(id);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Buscar ItemVenda por ID", e.getMessage().toString());
		}		
		return null;
	}
	
	public List<ItemVenda> listar(Integer id) {
		try {
			return clienteDao.queryForEq("vendaId", id);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Listar entidades - ", "ERRO");
		}
		
		return null;
	}
	
}
