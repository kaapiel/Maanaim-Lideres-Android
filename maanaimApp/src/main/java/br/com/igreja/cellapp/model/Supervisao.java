package br.com.igreja.cellapp.model;

import java.io.Serializable;

public class Supervisao implements Serializable{

	private Integer idMembroSupervisor;
	private Integer idRegiao;
	private String descricao;


	public Integer getIdMembroSupervisor() {
		return idMembroSupervisor;
	}

	public void setIdMembroSupervisor(Integer idMembroSupervisor) {
		this.idMembroSupervisor = idMembroSupervisor;
	}

	public Integer getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}