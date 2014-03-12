package com.projeto.mysales.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id = true, useGetSet=true)
	private Integer id;
	@DatabaseField(useGetSet=true)
	private String nome;
	@DatabaseField(useGetSet=true)
	private String telefone;
	
	@Override
    public String toString() {
        return nome;
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
