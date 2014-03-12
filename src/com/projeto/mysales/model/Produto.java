package com.projeto.mysales.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "produtos")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@DatabaseField(id = true, useGetSet=true)
	private Integer id;
	
	@DatabaseField(useGetSet=true)
	private String codigo;
	
	@DatabaseField(useGetSet=true)
	private String descricao;
	
	@DatabaseField(useGetSet=true, defaultValue="0")
	private Integer estoque;
	
	@DatabaseField(useGetSet=true, defaultValue="0")
	private Double preco;
	
	@DatabaseField(useGetSet=true, defaultValue="0")
	private Double precoVenda;
	

	public Produto() {

	}
	
	@Override
    public String toString() {
        return descricao;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	
	
	
	
}
