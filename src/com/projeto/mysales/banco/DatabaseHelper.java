package com.projeto.mysales.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.projeto.mysales.model.ItemPedido;
import com.projeto.mysales.model.ItemVenda;
import com.projeto.mysales.model.Parcela;
import com.projeto.mysales.model.Pedido;
import com.projeto.mysales.model.Produto;
import com.projeto.mysales.model.Cliente;
import com.projeto.mysales.model.Venda;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "banco.db";
	private static final int DATABASE_VERSION = 7;
	
	private Dao<Produto, Integer> produtoDao = null;
	private Dao<Cliente, Integer> clienteDao = null;
	private Dao<Venda, Integer> vendaDao = null;
	private Dao<ItemVenda, Integer> itemVendaDao = null;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connection) {
		try {
			TableUtils.createTable(connection, Produto.class);
			TableUtils.createTable(connection, Cliente.class);
			TableUtils.createTable(connection, ItemPedido.class);
			TableUtils.createTable(connection, ItemVenda.class);
			TableUtils.createTable(connection, Parcela.class);
			TableUtils.createTable(connection, Venda.class);
			TableUtils.createTable(connection, Pedido.class);
		}catch(Exception e) {
			Log.d("Erro ao Criar as Tabelas do Banco", e.getMessage().toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connection, int oldVersion,
			int newVersion) {
		
		try {
			TableUtils.dropTable(connectionSource, Produto.class, true);
			TableUtils.dropTable(connectionSource, Cliente.class, true);
			TableUtils.dropTable(connectionSource, ItemPedido.class, true);
			TableUtils.dropTable(connectionSource, ItemVenda.class, true);
			TableUtils.dropTable(connectionSource, Parcela.class, true);
			TableUtils.dropTable(connectionSource, Venda.class, true);
			TableUtils.dropTable(connectionSource, Pedido.class, true);
			
			TableUtils.createTable(connection, Produto.class);
			TableUtils.createTable(connection, Cliente.class);
			TableUtils.createTable(connection, ItemPedido.class);
			TableUtils.createTable(connection, ItemVenda.class);
			TableUtils.createTable(connection, Parcela.class);
			TableUtils.createTable(connection, Venda.class);
			TableUtils.createTable(connection, Pedido.class);
		}catch(Exception e) {
			Log.d("Erro ao Atualizar as Tabelas Banco", e.getMessage().toString());
		}
	}
	
	public Dao<Produto, Integer> getProdutoDao() {
		try {
			if(produtoDao == null)
				produtoDao = DaoManager.createDao(connectionSource, Produto.class);
			return produtoDao;
		}
		catch(Exception e) {
			Log.d("Erro ao Criar o DAO de Produto", e.getMessage().toString());
		}		
		return null;
	}
	

	public Dao<Cliente, Integer> getClienteDao() {
		try {
			if(clienteDao == null)
				clienteDao = DaoManager.createDao(connectionSource, Cliente.class);
			return clienteDao;
		}
		catch(Exception e) {
			Log.d("Erro ao Criar o DAO de Cliente", e.getMessage().toString());
		}		
		return null;
	}
	
	public Dao<Venda, Integer> getVendaDao() {
		try {
			if(vendaDao == null)
				vendaDao = DaoManager.createDao(connectionSource, Venda.class);
			return vendaDao;
		}
		catch(Exception e) {
			Log.d("Erro ao Criar o DAO de Venda", e.getMessage().toString());
		}		
		return null;
	}
	
	public Dao<ItemVenda, Integer> getItemVendaDao() {
		try {
			if(itemVendaDao == null)
				itemVendaDao = DaoManager.createDao(connectionSource, ItemVenda.class);
			return itemVendaDao;
		}
		catch(Exception e) {
			Log.d("Erro ao Criar o DAO de ItemVenda", e.getMessage().toString());
		}		
		return null;
	}
	
	 @Override
	 public void close() {
		 super.close();
		 clienteDao = null;
		 produtoDao = null;
		 vendaDao = null;
		 itemVendaDao = null;
	 }
}
