package br.com.igreja.cellapp.model;

public class Reuniao {

    private Integer idReuniao;
    private Integer idCelula;
    private Integer idEstado;
    private String complemento;
    private String dataReuniao;
    private String horaReuniao;
    private String numeroLocal;
    private String cep;

    public Integer getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(Integer idReuniao) {
        this.idReuniao = idReuniao;
    }

    public Integer getIdCelula() {
        return idCelula;
    }

    public void setIdCelula(Integer idCelula) {
        this.idCelula = idCelula;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(String dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public String getHoraReuniao() {
        return horaReuniao;
    }

    public void setHoraReuniao(String horaReuniao) {
        this.horaReuniao = horaReuniao;
    }

    public String getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        String substring = dataReuniao.substring(0, 10);
        String[] split = substring.split("-");
        String data = (split[2] + "/" + split[1] + "/" + split[0]);

        String hora = horaReuniao.substring(11, 16);

        return data + " - " + hora;
    }
}
