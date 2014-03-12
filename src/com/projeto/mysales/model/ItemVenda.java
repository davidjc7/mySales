package com.projeto.mysales.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "itens_vendas")
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id = true, useGetSet=true)
	private Integer id;
	
	@DatabaseField(useGetSet=true)
	private Integer vendaId;
	
	@DatabaseField(useGetSet=true)
	private Integer produtoId;
		
	@DatabaseField(useGetSet=true)
	private Integer quantidade;
	
	@DatabaseField(useGetSet=true)
	private Double preco;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getVendaId() {
		return vendaId;
	}

	public void setVendaId(Integer vendaId) {
		this.vendaId = vendaId;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
	
}
