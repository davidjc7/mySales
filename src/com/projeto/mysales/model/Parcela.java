package com.projeto.mysales.model;

import java.io.Serializable;
import java.util.Date;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "parcelas")
public class Parcela implements Serializable {

	private static final long serialVersionUID = 1L;

	@DatabaseField(id = true, useGetSet=true)
	private Integer id;
	
	@DatabaseField(useGetSet=true, defaultValue = "0")
	private Integer pago;
	
	@DatabaseField(canBeNull = false, foreign = true)
	private Venda venda;
	
	@DatabaseField(useGetSet=true)
	private Double valor;

	@DatabaseField(useGetSet=true)
	private Date dataVencimento;
	
	@DatabaseField(useGetSet=true)
	private Date dataPagamento;
	

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPago() {
		return pago;
	}
	
	public void setPago(Integer pagor) {
		this.pago = pagor;
	}
	
	public boolean isPago(){
		return this.pago == 1;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	
	
}
