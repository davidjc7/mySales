package com.projeto.mysales.model;

import java.io.Serializable;
import java.util.Date;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vendas")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id = true, useGetSet = true)
	private Integer id;

	@DatabaseField(useGetSet = true)
	private Integer clienteId;

	@DatabaseField(useGetSet = true)
	private Date dataVenda;

	@DatabaseField(useGetSet = true, defaultValue = "0")
	private Double desconto;

	@DatabaseField(useGetSet = true)
	private Double total;

	@DatabaseField(useGetSet = true)
	private String tipo;

	public Venda() {

	}

	public Venda(Date data) {
		this.dataVenda = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
