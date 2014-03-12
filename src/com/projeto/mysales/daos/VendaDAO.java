package com.projeto.mysales.daos;

import java.sql.SQLException;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.projeto.mysales.banco.DatabaseHelper;
import com.projeto.mysales.model.Venda;

public class VendaDAO {
	
	private static VendaDAO instance;
	private DatabaseHelper databaseHelper;
	private Dao<Venda, Integer> vendaDao;
	
	public VendaDAO(Context context){
		databaseHelper = new DatabaseHelper(context);
		vendaDao = databaseHelper.getVendaDao();
	}
	
	public static VendaDAO getInstance(Context context) {
		if(instance == null)
			instance = new VendaDAO(context);
		
		return instance;
	}
	
	public boolean gravar(Venda entidade){
		try{
			vendaDao.create(entidade);
			return vendaDao.create(entidade) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Gravar Venda", e.getMessage().toString());
		}		
		return false;
	}
	
	@SuppressLint("UseValueOf")
	public Venda gravarRetorno(Venda entidade){
		try{	
			vendaDao.create(entidade);
			long num = 1;
			return vendaDao.query(vendaDao.queryBuilder().orderBy("id", false).limit(num).prepare()).get(0);
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Erro ao Gravar Venda", e.getMessage().toString());
		}		
		return null;
	}
	
	public boolean atualizar(Venda entidade){
		try{
			return vendaDao.update(entidade) ==  1;
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Atualizar entidade - ", "ERRO");
		}		
		return false;
	}
	
	public boolean excluir(int id) {
		try {
			return vendaDao.deleteById(id) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			Log.d("Excluir entidade - ", "ERRO");
		}	
		return false;
	}
	
	public List<Venda> listar() {
		try {
			return vendaDao.query(vendaDao.queryBuilder().orderBy("id", false).where().gt("total", 0).prepare());
		}catch(Exception e) {
			e.printStackTrace();
			Log.d("Listar entidades - ", "ERRO");
		}
		
		return null;
	}
	
	
}
