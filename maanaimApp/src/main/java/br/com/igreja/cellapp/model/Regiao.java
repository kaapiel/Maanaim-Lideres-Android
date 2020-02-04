package br.com.igreja.cellapp.model;

import java.io.Serializable;

public class Regiao implements Serializable{

	private Integer idRegiao;
	private int[] idMembroPastor;
	private String descricao;
	private String cor;

	public Integer getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
	}

	public int[] getIdMembroPastor() {
		return idMembroPastor;
	}

	public void setIdMembroPastor(int[] idMembroPastor) {
		this.idMembroPastor = idMembroPastor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
}