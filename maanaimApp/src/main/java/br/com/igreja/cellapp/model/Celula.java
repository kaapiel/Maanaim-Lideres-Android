package br.com.igreja.cellapp.model;

import java.io.Serializable;

public class Celula implements Serializable {

    private static final long serialVersionUID = 6359352258488247621L;
    private Integer idCelula;
    private Integer idRegiao;
    private Integer idSupervisao;
    private Integer idCelulaOrigem;

    private String nome;
    private String dataCriacao;
    private String descricao;

    private boolean ativo;

    private int[] liderMembroId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdCelula() {
        return idCelula;
    }

    public void setIdCelula(Integer idCelula) {
        this.idCelula = idCelula;
    }

    public Integer getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Integer idRegiao) {
        this.idRegiao = idRegiao;
    }

    public Integer getIdSupervisao() {
        return idSupervisao;
    }

    public void setIdSupervisao(Integer idSupervisao) {
        this.idSupervisao = idSupervisao;
    }

    public Integer getIdCelulaOrigem() {
        return idCelulaOrigem;
    }

    public void setIdCelulaOrigem(Integer idCelulaOrigem) {
        this.idCelulaOrigem = idCelulaOrigem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int[] getLiderMembroId() {
        return liderMembroId;
    }

    public void setLiderMembroId(int[] liderMembroId) {
        this.liderMembroId = liderMembroId;
    }

    @Override
    public String toString() {
        return nome;
    }

}
