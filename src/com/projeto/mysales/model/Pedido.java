package com.projeto.mysales.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pedidos")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id = true, useGetSet=true)
	private Integer id;
	@DatabaseField(useGetSet=true)
	private Date data;
	
	@ForeignCollectionField(eager = true)
	private Collection<ItemPedido> itens;
	
	@DatabaseField(useGetSet=true)
	private Date dataVencimento;
	
	@DatabaseField(useGetSet=true)
	private Double valorTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Collection<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Collection<ItemPedido> itens) {
		this.itens = itens;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	

	
	
	
}
