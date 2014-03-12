package com.projeto.mysales.daos;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.projeto.mysales.banco.DatabaseHelper;
import com.projeto.mysales.model.Produto;

public class ProdutoDAO {
	
	private static ProdutoDAO instance;
	private DatabaseHelper databaseHelper;
	private Dao<Produto, Integer> produtoDao;
	
	public ProdutoDAO(Context context){
		databaseHelper = new DatabaseHelper(context);
		produtoDao = databaseHelper.getProdutoDao();
	}
	
	public static ProdutoDAO getInstance(Context context) {
		if(instance == null)
			instance = new ProdutoDAO(context);
		
		return instance;
	}
	
	public boolean gravar(Produto produto){
		try{
			return produtoDao.create(produto) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Gravar produto - ", "ERRO");
		}		
		return false;
	}
	
	public boolean atualizar(Produto produto){
		try{
			return produtoDao.update(produto) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Atualizar produto - ", "ERRO");
		}		
		return false;
	}
	
	
	public boolean excluir(int id) {
		try {
			return produtoDao.deleteById(id) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			Log.d("Excluir produto - ", "ERRO");
		}	
		return false;
	}
	
	public Produto getForId(Integer id) {
		try {
			return produtoDao.queryForId(id);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Buscar Produdo por ID", "ERRO");
		}
		
		return null;
	}
	
	public List<Produto> listar() {
		try {
			return produtoDao.queryForAll();
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Listar produto - ", "ERRO");
		}
		
		return null;
	}
	
	public List<Produto> listarPorDescricao(String descricao) {
		try {
			QueryBuilder<Produto, Integer> qBuilder = produtoDao.queryBuilder();
				
			PreparedQuery<Produto> query = qBuilder.where().like("descricao", "%"+descricao).prepare();
			return produtoDao.query(query);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Listar produto por descrição - ", "ERRO");
		}
		return null;
	}
	
}
