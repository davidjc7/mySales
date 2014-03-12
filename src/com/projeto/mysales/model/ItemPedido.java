package com.projeto.mysales.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "itens_pedidos")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id = true, useGetSet=true)
	private Integer id;
	
	@DatabaseField(canBeNull = false, foreign = true)
	private Pedido pedido;
	
	@DatabaseField(useGetSet=true)
	private Double custoUnitario;
	
	@DatabaseField(useGetSet=true)
	private Integer quantidade;
	
	@DatabaseField(useGetSet=true)
	private Double custoTotal;

	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getCustoUnitario() {
		return custoUnitario;
	}
	public void setCustoUnitario(Double custoUnitario) {
		this.custoUnitario = custoUnitario;
	}
	public Double getCustoTotal() {
		return custoTotal;
	}
	public void setCustoTotal(Double custoTotal) {
		this.custoTotal = custoTotal;
	}
	
	
	
}
