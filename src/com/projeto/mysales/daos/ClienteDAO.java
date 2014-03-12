package com.projeto.mysales.daos;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.projeto.mysales.banco.DatabaseHelper;
import com.projeto.mysales.model.Cliente;

public class ClienteDAO {
	
	private static ClienteDAO instance;
	private DatabaseHelper databaseHelper;
	private Dao<Cliente, Integer> clienteDao;
	
	private ClienteDAO(Context context){
		databaseHelper = new DatabaseHelper(context);
		clienteDao = databaseHelper.getClienteDao();
	}
	
	public static ClienteDAO getInstance(Context context) {
		if(instance == null)
			instance = new ClienteDAO(context);
		
		return instance;
	}
	
	public boolean gravar(Cliente entidade){
		try{
			return clienteDao.create(entidade) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Gravar Cliente", e.getMessage().toString());
		}		
		return false;
	}
	
	public boolean atualizar(Cliente entidade){
		try{
			return clienteDao.update(entidade) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Atualizar entidade - ", "ERRO");
		}		
		return false;
	}
	
	public boolean excluir(int id) {
		try {
			return clienteDao.deleteById(id) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			Log.d("Excluir entidade - ", "ERRO");
		}	
		return false;
	}
	
	public Cliente getForId(Integer id){
		try{
			return clienteDao.queryForId(id);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Buscar Cliente por ID", e.getMessage().toString());
		}		
		return null;
	}
	
	public List<Cliente> listar() {
		try {
			return clienteDao.queryForAll();
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Listar entidades - ", "ERRO");
		}
		
		return null;
	}
	
	public List<Cliente> listarPorNome(String descricao) {
		try {
			QueryBuilder<Cliente, Integer> qBuilder = clienteDao.queryBuilder();
				
			PreparedQuery<Cliente> query = qBuilder.where().like("nome", "%"+descricao).prepare();
			return clienteDao.query(query);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Listar entidade por nome - ", "ERRO");
		}
		return null;
	}
	
}
