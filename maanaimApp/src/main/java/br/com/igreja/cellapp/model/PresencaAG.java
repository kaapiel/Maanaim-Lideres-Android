package br.com.igreja.cellapp.model;

import java.util.List;

/**
 * Created by Gabriel Aguido on 08/04/2016.
 */
public class PresencaAG {

    private Integer idReuniao;
    private List<Boolean> presencaTadel;
    private List<Integer> idMembros;
    private List<Boolean> idMembrosPresentes;
    private Integer idCelula;

    public Integer getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(Integer idReuniao) {
        this.idReuniao = idReuniao;
    }

    public List<Boolean> getPresencaTadel() {
        return presencaTadel;
    }

    public void setPresencaTadel(List<Boolean> presencaTadel) {
        this.presencaTadel = presencaTadel;
    }

    public List<Integer> getIdMembros() {
        return idMembros;
    }

    public void setIdMembros(List<Integer> idMembros) {
        this.idMembros = idMembros;
    }

    public List<Boolean> getIdMembrosPresentes() {
        return idMembrosPresentes;
    }

    public void setIdMembrosPresentes(List<Boolean> idMembrosPresentes) {
        this.idMembrosPresentes = idMembrosPresentes;
    }

    public Integer getIdCelula() {
        return idCelula;
    }

    public void setIdCelula(Integer idCelula) {
        this.idCelula = idCelula;
    }
}
